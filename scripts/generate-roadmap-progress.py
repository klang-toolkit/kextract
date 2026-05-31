#!/usr/bin/env python3
"""Génère docs/kadre/roadmap-progress.md depuis Redmine + git log (Redmine #87).

Lecture seule. Combine :
- l'API Redmine (tickets project=1 : statut, priorité, dates, sprint depuis la description)
- le git log (PRs mergées → tickets, via les `(#ID)` dans les messages de commit)

et produit une page de suivi : table sprint × statut, burn-down ASCII, top en cours,
tickets bloqués, vélocité 7 jours, risques (ouverts > 14 j sans PR).

Usage :
    REDMINE_URL=https://redmine.example REDMINE_API_KEY=xxx \\
        python scripts/generate-roadmap-progress.py [--dry-run]

Dépendances : stdlib + `requests`.
"""
from __future__ import annotations

import argparse
import os
import re
import subprocess
import sys
from collections import defaultdict
from datetime import datetime, timezone, timedelta

import requests

PROJECT_ID = 1
OUTPUT = "docs/kadre/roadmap-progress.md"
OPEN_STATUSES = {"Nouveau", "En cours", "À prioriser", "En attente", "Info nécessaire", "Review", "À tester"}
BLOCKED_STATUSES = {"En attente", "Info nécessaire"}


def fetch_issues(base_url: str, api_key: str) -> list[dict]:
    """Récupère tous les tickets du projet (open + closed), paginé."""
    issues: list[dict] = []
    offset = 0
    while True:
        resp = requests.get(
            f"{base_url.rstrip('/')}/issues.json",
            params={"project_id": PROJECT_ID, "status_id": "*", "limit": 100, "offset": offset},
            headers={"X-Redmine-API-Key": api_key},
            timeout=30,
        )
        resp.raise_for_status()
        data = resp.json()
        issues.extend(data.get("issues", []))
        total = data.get("total_count", 0)
        offset += 100
        if offset >= total or not data.get("issues"):
            break
    return issues


def merged_ticket_ids() -> set[int]:
    """Tickets référencés par des commits sur master (messages `(#ID)` ou `#ID`)."""
    try:
        log = subprocess.run(
            ["git", "log", "--pretty=%s", "-n", "2000"],
            capture_output=True, text=True, check=True,
        ).stdout
    except subprocess.CalledProcessError:
        return set()
    ids: set[int] = set()
    for m in re.finditer(r"[#(]#?(\d{1,4})\b", log):
        ids.add(int(m.group(1)))
    # forme `(#123)` et `Redmine #123`
    for m in re.finditer(r"(?:#|Redmine\s+#)(\d{1,4})", log):
        ids.add(int(m.group(1)))
    return ids


def sprint_of(issue: dict) -> str:
    """Déduit le sprint depuis la description (`Sprint X — ...`), sinon 'Autre'."""
    desc = issue.get("description") or ""
    m = re.search(r"Sprint\s+([0-9]+(?:-[0-9]+)?)", desc)
    if m:
        return f"Sprint {m.group(1)}"
    if "Instrumentation" in desc or "Transverse" in desc:
        return "Transverse"
    return "Autre"


def parse_dt(s: str | None):
    if not s:
        return None
    try:
        return datetime.fromisoformat(s.replace("Z", "+00:00"))
    except ValueError:
        return None


def bar(done: int, total: int, width: int = 20) -> str:
    if total == 0:
        return "·" * width + " 0%"
    filled = round(width * done / total)
    pct = round(100 * done / total)
    return "█" * filled + "·" * (width - filled) + f" {pct}%"


def render(issues: list[dict], merged: set[int], now: datetime) -> str:
    by_sprint_status: dict[str, dict[str, int]] = defaultdict(lambda: defaultdict(int))
    by_sprint_total: dict[str, int] = defaultdict(int)
    by_sprint_done: dict[str, int] = defaultdict(int)
    for it in issues:
        sp = sprint_of(it)
        st = it["status"]["name"]
        by_sprint_status[sp][st] += 1
        by_sprint_total[sp] += 1
        if st in ("Résolu", "Fermé"):
            by_sprint_done[sp] += 1

    lines: list[str] = []
    lines.append("# Roadmap progress\n")
    lines.append(f"> Généré automatiquement le {now.strftime('%Y-%m-%d %H:%M UTC')} "
                 "par `scripts/generate-roadmap-progress.py` (Redmine #87). Ne pas éditer à la main.\n")

    total = len(issues)
    done = sum(1 for it in issues if it["status"]["name"] in ("Résolu", "Fermé"))
    lines.append(f"**Avancement global : {done}/{total} tickets résolus** — {bar(done, total)}\n")

    # Table sprint × completion
    lines.append("## Par sprint\n")
    lines.append("| Sprint | Total | Résolus | Avancement |")
    lines.append("|--------|-------|---------|------------|")
    for sp in sorted(by_sprint_total):
        t = by_sprint_total[sp]
        d = by_sprint_done[sp]
        lines.append(f"| {sp} | {t} | {d} | `{bar(d, t)}` |")
    lines.append("")

    # Répartition par statut
    status_count: dict[str, int] = defaultdict(int)
    for it in issues:
        status_count[it["status"]["name"]] += 1
    lines.append("## Par statut\n")
    lines.append("| Statut | Tickets |")
    lines.append("|--------|---------|")
    for st in sorted(status_count, key=lambda s: -status_count[s]):
        lines.append(f"| {st} | {status_count[st]} |")
    lines.append("")

    # Top en cours
    in_progress = [it for it in issues if it["status"]["name"] == "En cours"]
    lines.append("## En cours (top 5)\n")
    if in_progress:
        for it in in_progress[:5]:
            pr = " — PR mergée" if it["id"] in merged else ""
            lines.append(f"- #{it['id']} {it['subject']}{pr}")
    else:
        lines.append("- _(aucun)_")
    lines.append("")

    # Bloqués
    blocked = [it for it in issues if it["status"]["name"] in BLOCKED_STATUSES]
    lines.append("## Bloqués (En attente / Info nécessaire)\n")
    if blocked:
        for it in blocked[:5]:
            lines.append(f"- #{it['id']} [{it['status']['name']}] {it['subject']}")
    else:
        lines.append("- _(aucun)_")
    lines.append("")

    # Vélocité 7 jours
    week_ago = now - timedelta(days=7)
    recent = [it for it in issues
              if (dt := parse_dt(it.get("closed_on"))) and dt >= week_ago]
    lines.append("## Vélocité (7 derniers jours)\n")
    lines.append(f"**{len(recent)} tickets clos** sur les 7 derniers jours.\n")

    # Risques : ouverts > 14j sans PR mergée
    fortnight_ago = now - timedelta(days=14)
    risks = [it for it in issues
             if it["status"]["name"] in OPEN_STATUSES
             and it["id"] not in merged
             and (dt := parse_dt(it.get("created_on"))) and dt < fortnight_ago]
    lines.append("## Risques (ouverts > 14 j, sans PR)\n")
    if risks:
        for it in risks[:10]:
            age = (now - parse_dt(it["created_on"])).days
            lines.append(f"- #{it['id']} ({age} j) {it['subject']}")
    else:
        lines.append("- _(aucun)_")
    lines.append("")

    return "\n".join(lines) + "\n"


def main() -> int:
    ap = argparse.ArgumentParser()
    ap.add_argument("--dry-run", action="store_true", help="Affiche sur stdout au lieu d'écrire le fichier")
    args = ap.parse_args()

    base_url = os.environ.get("REDMINE_URL")
    api_key = os.environ.get("REDMINE_API_KEY")
    if not base_url or not api_key:
        print("REDMINE_URL et REDMINE_API_KEY doivent être définis dans l'environnement.", file=sys.stderr)
        return 2

    issues = fetch_issues(base_url, api_key)
    merged = merged_ticket_ids()
    now = datetime.now(timezone.utc)
    content = render(issues, merged, now)

    if args.dry_run:
        print(content)
        return 0

    with open(OUTPUT, "w", encoding="utf-8") as f:
        f.write(content)
    print(f"Écrit {OUTPUT} ({len(issues)} tickets).")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
