#!/usr/bin/env node
// CLI de diff d'images cross-plateforme.
//
// Compare deux PNG (capture courante vs baseline) avec une tolérance en % de pixels
// différents. Conçu pour être appelé par n'importe quel job CI de plateforme, quelle
// que soit la source de la capture (readback GPU desktop, Playwright web, etc.).
//
// Usage : node diff-cli.js <actual.png> <baseline.png> <diffOut.png> [tolerance] [reportOut.json] [label]
//
// NON BLOQUANT : affiche le verdict et écrit le diff + un report.json, mais sort toujours
// en 0 (le rendu GPU n'est pas déterministe au pixel près entre machines).
//
// Le rendu inline des captures dans l'UI GitHub n'est PAS fait ici : les `data:` URI sont
// assainies par GitHub. À la place, on émet un report.json que l'action `visual-report`
// consomme pour héberger les PNG (branche orpheline) et bâtir un Job Summary + commentaire
// de PR avec de vraies URLs `raw.githubusercontent.com` (rendues inline sur repo public).
const fs = require('fs');
const path = require('path');
const { PNG } = require('pngjs');
const pixelmatch = require('pixelmatch').default || require('pixelmatch');

const [actualPath, baselinePath, diffOut, toleranceArg, reportOut, label] = process.argv.slice(2);
const tolerance = toleranceArg ? parseFloat(toleranceArg) : 0.02;
const platform = label || 'inconnu';

function fail(msg) { console.error(`[visual] ${msg}`); process.exit(0); }

if (!actualPath || !baselinePath) fail('usage: diff-cli.js <actual> <baseline> <diffOut> [tol] [summary]');
if (!fs.existsSync(actualPath)) fail(`capture absente: ${actualPath}`);

let status, ratio = 0, diffPixels = 0, total = 0, reason = '';
if (!fs.existsSync(baselinePath)) {
  status = 'no-baseline';
  reason = `baseline absente: ${baselinePath} (lancer la mise à jour des baselines)`;
} else {
  const actual = PNG.sync.read(fs.readFileSync(actualPath));
  const baseline = PNG.sync.read(fs.readFileSync(baselinePath));
  total = actual.width * actual.height;
  if (actual.width !== baseline.width || actual.height !== baseline.height) {
    status = 'mismatch';
    ratio = 1; diffPixels = total;
    reason = `dimensions ${actual.width}x${actual.height} != baseline ${baseline.width}x${baseline.height}`;
  } else {
    const diff = new PNG({ width: actual.width, height: actual.height });
    diffPixels = pixelmatch(actual.data, baseline.data, diff.data, actual.width, actual.height, { threshold: 0.1 });
    ratio = diffPixels / total;
    status = ratio > tolerance ? 'mismatch' : 'match';
    if (status === 'mismatch' && diffOut) {
      fs.mkdirSync(path.dirname(diffOut), { recursive: true });
      fs.writeFileSync(diffOut, PNG.sync.write(diff));
    }
  }
}

const pct = (ratio * 100).toFixed(3);
const icon = status === 'match' ? '✅' : status === 'no-baseline' ? '🆕' : '⚠️';
const line = `${icon} ${status} — diff ${pct}% (tolérance ${tolerance * 100}%${total ? `, ${diffPixels}/${total} px` : ''})${reason ? ` — ${reason}` : ''}`;
console.log(`[visual] ${line}`);

if (reportOut) {
  // Émet un manifeste consommé par l'action `visual-report` (hébergement + rendu inline).
  const report = {
    platform,
    sample: 'hello-triangle',
    status, icon, line,
    pct: Number(pct), tolerance, diffPixels, total,
    reason: reason || null,
    images: {
      baseline: fs.existsSync(baselinePath || '') ? path.resolve(baselinePath) : null,
      actual: fs.existsSync(actualPath || '') ? path.resolve(actualPath) : null,
      diff: fs.existsSync(diffOut || '') ? path.resolve(diffOut) : null,
    },
  };
  fs.mkdirSync(path.dirname(reportOut), { recursive: true });
  fs.writeFileSync(reportOut, JSON.stringify(report, null, 2));
}

// Non bloquant.
process.exit(0);
