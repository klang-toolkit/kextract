#!/usr/bin/env bash
# Pushes the staging captures to the orphan hosting branch.
#
# The 4 platform jobs run in parallel and all push to the same branch
# `ci-visual-reports`. Each job writes ONLY under <run_id>/<platform>/ (disjoint paths),
# so no content conflict — only the `git push` can be rejected (non-fast-forward).
# We handle this by re-cloning the up-to-date branch on each attempt then re-committing our
# files: since they are in a unique subfolder, the fast-forward push eventually succeeds.
#
# Non-blocking: exits 0 even on failure (the report stays visible as an artifact).
#
# Usage: push-orphan.sh <stagingDir> <repo> <branch> <token>
set -u
STAGING="${1:?stagingDir}"; REPO="${2:?repo}"; BRANCH="${3:?branch}"; TOKEN="${4:?token}"
REMOTE="https://x-access-token:${TOKEN}@github.com/${REPO}.git"

if [ -z "$(find "$STAGING" -name '*.png' 2>/dev/null)" ]; then
  echo "[push-orphan] aucune image à pousser, skip"; exit 0
fi

for attempt in 1 2 3 4 5; do
  WORK="$(mktemp -d)"
  if git clone --quiet --depth 1 --branch "$BRANCH" "$REMOTE" "$WORK" 2>/dev/null; then
    : # existing branch cloned
  else
    # The branch does not exist yet: default clone then create the orphan.
    git clone --quiet --depth 1 "$REMOTE" "$WORK" || { echo "[push-orphan] clone échoué"; exit 0; }
    git -C "$WORK" checkout --quiet --orphan "$BRANCH"
    git -C "$WORK" rm -rqf . >/dev/null 2>&1 || true
  fi

  git -C "$WORK" config user.name "github-actions[bot]"
  git -C "$WORK" config user.email "41898282+github-actions[bot]@users.noreply.github.com"
  cp -R "$STAGING"/. "$WORK"/
  git -C "$WORK" add -A
  if git -C "$WORK" diff --cached --quiet; then
    echo "[push-orphan] rien de neuf à committer"; rm -rf "$WORK"; exit 0
  fi
  git -C "$WORK" commit --quiet -m "visual: rapport CI (run ${GITHUB_RUN_ID:-?})"

  if git -C "$WORK" push --quiet origin "HEAD:$BRANCH" 2>/dev/null; then
    echo "[push-orphan] poussé sur $BRANCH (tentative $attempt)"
    rm -rf "$WORK"; exit 0
  fi
  echo "[push-orphan] push rejeté (tentative $attempt), nouvelle tentative…"
  rm -rf "$WORK"
  sleep $(( (RANDOM % 4) + attempt ))
done

echo "[push-orphan] échec après 5 tentatives (non bloquant)"
exit 0
