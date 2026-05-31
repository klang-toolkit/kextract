#!/usr/bin/env node
// Publie un rapport de régression visuelle dans l'UI GitHub CI.
//
// Pourquoi : GitHub assainit les `data:` URI dans les Job Summaries et les commentaires
// → les captures encodées en base64 ne s'affichent JAMAIS inline. Seules de vraies URLs
// sont rendues. Sur un repo PUBLIC, `raw.githubusercontent.com` est rendu inline via le
// proxy camo de GitHub, sans dépendance externe ni API non documentée (le CDN
// user-attachments exige un cookie de session navigateur, inadapté à la CI).
//
// Stratégie : ce script reçoit un report.json (produit par diff-cli.js), copie les PNG
// dans <stagingDir>/<run_id>/<platform>/ pour que l'action puisse les pousser sur la
// branche orpheline `ci-visual-reports`, puis écrit :
//   - un fragment de Job Summary (galerie d'images via URLs raw),
//   - un fragment de commentaire de PR (même galerie),
// les deux référençant les URLs raw définitives.
//
// Usage :
//   node publish-report.js <report.json> <stagingDir> <repo> <branch> <runId> \
//                          <summaryOut.md> <commentOut.md>
const fs = require('fs');
const path = require('path');

const [reportPath, stagingDir, repo, branch, runId, summaryOut, commentOut] =
  process.argv.slice(2);

if (!reportPath || !fs.existsSync(reportPath)) {
  console.error(`[visual-report] report introuvable: ${reportPath}`);
  process.exit(0); // non bloquant
}

const report = JSON.parse(fs.readFileSync(reportPath, 'utf8'));
const platform = report.platform || 'inconnu';
// Slug sûr pour chemins/URLs (ex. "Linux X11" → "linux-x11").
const slug = platform.replace(/[^a-zA-Z0-9_-]+/g, '-').replace(/^-+|-+$/g, '').toLowerCase() || 'inconnu';

// Sous-dossier de staging propre à ce run + plateforme (évite les collisions entre jobs).
const subPath = `${runId}/${slug}`;
const outDir = path.join(stagingDir, subPath);
fs.mkdirSync(outDir, { recursive: true });

// Copie chaque image présente et calcule son URL raw définitive.
const rawBase = `https://raw.githubusercontent.com/${repo}/${branch}/${subPath}`;
const urls = {};
for (const [kind, srcPath] of Object.entries(report.images || {})) {
  if (!srcPath || !fs.existsSync(srcPath)) continue;
  const dest = path.join(outDir, `${kind}.png`);
  fs.copyFileSync(srcPath, dest);
  urls[kind] = `${rawBase}/${kind}.png`;
}

const labels = { baseline: 'baseline', actual: 'courante', diff: 'diff' };
const gallery = Object.entries(urls)
  .map(([kind, url]) => {
    const alt = labels[kind] || kind;
    return `<a href="${url}"><img alt="${alt}" title="${alt}" width="280" src="${url}"></a>`;
  })
  .join(' ');

const heading = `### Régression visuelle — ${report.sample || 'hello-triangle'} (${platform})`;
const noImg = '_Aucune capture disponible (échec de la capture ?)._';
const body =
  `${heading}\n\n${report.line || ''}\n\n` +
  `${gallery || noImg}\n\n` +
  `<sub>Captures hébergées sur la branche \`${branch}\` · aussi disponibles en artefacts du run.</sub>\n`;

if (summaryOut) {
  fs.mkdirSync(path.dirname(summaryOut), { recursive: true });
  fs.writeFileSync(summaryOut, body);
}
if (commentOut) {
  // Marqueur HTML pour permettre l'upsert d'un commentaire « collant » par plateforme.
  const marker = `<!-- visual-report:${slug} -->`;
  fs.mkdirSync(path.dirname(commentOut), { recursive: true });
  fs.writeFileSync(commentOut, `${marker}\n${body}`);
}

console.log(`[visual-report] ${platform}: ${Object.keys(urls).length} image(s) → ${rawBase}`);
process.exit(0);
