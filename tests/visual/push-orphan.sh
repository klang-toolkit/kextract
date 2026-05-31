#!/usr/bin/env bash
# Pousse les captures de staging sur la branche orpheline d'hébergement (Redmine #88).
#
# Les 4 jobs de plateforme tournent en parallèle et poussent tous sur la même branche
# `ci-visual-reports`. Chaque job n'écrit QUE sous <run_id>/<platform>/ (chemins disjoints),
# donc aucun conflit de contenu — seul le `git push` peut être rejeté (non-fast-forward).
# On gère ça en re-clonant la branche à jour à chaque tentative puis en re-committant nos
# fichiers : comme ils sont dans un sous-dossier unique, le push fast-forward finit par passer.
#
# Non bloquant : sort 0 même en cas d'échec (le rapport reste visible en artefact).
#
# Usage : push-orphan.sh <stagingDir> <repo> <branch> <token>
set -u
STAGING="${1:?stagingDir}"; REPO="${2:?repo}"; BRANCH="${3:?branch}"; TOKEN="${4:?token}"
REMOTE="https://x-access-token:${TOKEN}@github.com/${REPO}.git"

if [ -z "$(find "$STAGING" -name '*.png' 2>/dev/null)" ]; then
  echo "[push-orphan] aucune image à pousser, skip"; exit 0
fi

for attempt in 1 2 3 4 5; do
  WORK="$(mktemp -d)"
  if git clone --quiet --depth 1 --branch "$BRANCH" "$REMOTE" "$WORK" 2>/dev/null; then
    : # branche existante clonée
  else
    # La branche n'existe pas encore : clone par défaut puis crée l'orphelin.
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
