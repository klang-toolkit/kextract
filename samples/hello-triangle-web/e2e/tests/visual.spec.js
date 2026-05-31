// Test de régression visuelle Web — NON BLOQUANT.
//
// Capture le canvas de hello-triangle-web et le compare à une baseline avec une
// tolérance de 2 %. Le rendu WebGPU SwiftShader pouvant varier légèrement selon
// l'environnement, ce test est **informatif** : il journalise le ratio de diff et
// archive l'image de diff en artefact, mais n'échoue jamais le build. Voir
// docs/kadre/visual-testing.md pour le workflow updateVisualBaselines.
const fs = require('fs');
const path = require('path');
const { test, expect } = require('@playwright/test');
const { assertScreenshotMatches } = require('../visual/assert-screenshot');

const RESULTS = path.join(__dirname, '..', 'test-results');
const BASELINE = path.join(__dirname, '..', 'baselines', 'hello-triangle-web.png');
const ACTUAL = path.join(RESULTS, 'hello-triangle-web.actual.png');
const DIFF = path.join(RESULTS, 'hello-triangle-web.diff.png');
const SUMMARY = path.join(RESULTS, 'visual-summary.md');
const TOLERANCE = 0.02;

test('régression visuelle hello-triangle-web (informatif)', async ({ page }) => {
  const logs = [];
  page.on('console', (m) => logs.push(m.text()));

  await page.goto('/');
  await expect(page.locator('#kadre-canvas')).toBeVisible();
  // Attendre l'initialisation complète + quelques frames stables.
  await expect.poll(() => logs.some((l) => l.includes('Pipeline prêt')), { timeout: 60_000 }).toBe(true);
  await page.waitForTimeout(1_500);

  const shot = await page.locator('#kadre-canvas').screenshot();
  fs.mkdirSync(RESULTS, { recursive: true });
  fs.writeFileSync(ACTUAL, shot);
  const result = assertScreenshotMatches(shot, BASELINE, { tolerance: TOLERANCE, diffPath: DIFF });

  const pct = (result.ratio * 100).toFixed(3);
  if (result.status === 'created') {
    console.log(`[visual] baseline créée (${BASELINE}) — ${result.total} px`);
  } else if (result.status === 'mismatch') {
    console.warn(`[visual] ⚠️ diff ${pct}% > ${TOLERANCE * 100}% (${result.diffPixels}/${result.total} px) — diff archivé. ` +
      `Si le changement est légitime : npm run update-baselines.`);
  } else {
    console.log(`[visual] OK — diff ${pct}% ≤ ${TOLERANCE * 100}%`);
  }

  // Résumé Markdown injecté dans le Job Summary GitHub Actions (capture visible
  // directement sur la page du run, en plus des artefacts).
  const icon = result.status === 'match' ? '✅' : result.status === 'created' ? '🆕' : '⚠️';
  const summary = [
    '### Régression visuelle — hello-triangle-web',
    '',
    `${icon} statut **${result.status}** — diff **${pct}%** (tolérance ${TOLERANCE * 100}%, ${result.diffPixels}/${result.total} px)`,
    '',
    '_Captures (baseline / courante / diff) disponibles dans les artefacts du run._',
    '',
  ].join('\n');
  fs.writeFileSync(SUMMARY, summary);

  // Non bloquant : on n'échoue pas le build (rendu SwiftShader non déterministe
  // entre environnements). Le statut est journalisé + diff archivé en CI.
  expect(['created', 'match', 'mismatch']).toContain(result.status);
});
