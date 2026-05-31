#!/usr/bin/env node
// Cross-platform image diff CLI.
//
// Compares two PNGs (current capture vs baseline) with a tolerance in % of differing
// pixels. Designed to be called by any platform CI job, whatever the source of the
// capture (desktop GPU readback, Playwright web, etc.).
//
// Usage: node diff-cli.js <actual.png> <baseline.png> <diffOut.png> [tolerance] [reportOut.json] [label]
//
// NON-BLOCKING: prints the verdict and writes the diff + a report.json, but always exits
// 0 (GPU rendering is not pixel-perfect deterministic across machines).
//
// Inline rendering of the captures in the GitHub UI is NOT done here: the `data:` URIs are
// sanitized by GitHub. Instead, we emit a report.json that the `visual-report` action
// consumes to host the PNGs (orphan branch) and build a Job Summary + PR comment
// with real `raw.githubusercontent.com` URLs (rendered inline on a public repo).
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
  // Emits a manifest consumed by the `visual-report` action (hosting + inline rendering).
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

// Non-blocking.
process.exit(0);
