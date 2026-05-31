#!/usr/bin/env node
// Publishes a visual regression report in the GitHub CI UI.
//
// Why: GitHub sanitizes the `data:` URIs in Job Summaries and comments
// → base64-encoded captures are NEVER displayed inline. Only real URLs
// are rendered. On a PUBLIC repo, `raw.githubusercontent.com` is rendered inline via
// GitHub's camo proxy, without an external dependency or an undocumented API (the
// user-attachments CDN requires a browser session cookie, unsuitable for CI).
//
// Strategy: this script receives a report.json (produced by diff-cli.js), copies the PNGs
// into <stagingDir>/<run_id>/<platform>/ so the action can push them to the
// orphan branch `ci-visual-reports`, then writes:
//   - a Job Summary fragment (image gallery via raw URLs),
//   - a PR comment fragment (same gallery),
// both referencing the final raw URLs.
//
// Usage:
//   node publish-report.js <report.json> <stagingDir> <repo> <branch> <runId> \
//                          <summaryOut.md> <commentOut.md>
const fs = require('fs');
const path = require('path');

const [reportPath, stagingDir, repo, branch, runId, summaryOut, commentOut] =
  process.argv.slice(2);

if (!reportPath || !fs.existsSync(reportPath)) {
  console.error(`[visual-report] report introuvable: ${reportPath}`);
  process.exit(0); // non-blocking
}

const report = JSON.parse(fs.readFileSync(reportPath, 'utf8'));
const platform = report.platform || 'inconnu';
// Safe slug for paths/URLs (e.g. "Linux X11" → "linux-x11").
const slug = platform.replace(/[^a-zA-Z0-9_-]+/g, '-').replace(/^-+|-+$/g, '').toLowerCase() || 'inconnu';

// Staging subfolder specific to this run + platform (avoids collisions between jobs).
const subPath = `${runId}/${slug}`;
const outDir = path.join(stagingDir, subPath);
fs.mkdirSync(outDir, { recursive: true });

// Copy each present image and compute its final raw URL.
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
  // HTML marker to allow upsert of a "sticky" comment per platform.
  const marker = `<!-- visual-report:${slug} -->`;
  fs.mkdirSync(path.dirname(commentOut), { recursive: true });
  fs.writeFileSync(commentOut, `${marker}\n${body}`);
}

console.log(`[visual-report] ${platform}: ${Object.keys(urls).length} image(s) → ${rawBase}`);
process.exit(0);
