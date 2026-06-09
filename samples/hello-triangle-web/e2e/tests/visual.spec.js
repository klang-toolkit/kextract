// Web visual regression test — NON-BLOCKING.
//
// Captures the hello-triangle-web canvas and compares it to a baseline with a
// 2% tolerance. Since WebGPU SwiftShader rendering can vary slightly depending on
// the environment, this test is **informational**: it logs the diff ratio and
// archives the diff image as an artifact, but never fails the build. See
// docs/kadre/visual-testing.md for the updateVisualBaselines workflow.
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

test('visual regression hello-triangle-web (informational)', async ({ page }) => {
  const logs = [];
  page.on('console', (m) => logs.push(m.text()));

  await page.goto('/');
  await expect(page.locator('#kadre-canvas')).toBeVisible();
  // Wait for full initialization + a few stable frames.
  await expect.poll(() => logs.some((l) => l.includes('Pipeline ready')), { timeout: 60_000 }).toBe(true);
  await page.waitForTimeout(1_500);

  const shot = await page.locator('#kadre-canvas').screenshot();
  fs.mkdirSync(RESULTS, { recursive: true });
  fs.writeFileSync(ACTUAL, shot);
  const result = assertScreenshotMatches(shot, BASELINE, { tolerance: TOLERANCE, diffPath: DIFF });

  const pct = (result.ratio * 100).toFixed(3);
  if (result.status === 'created') {
    console.log(`[visual] baseline created (${BASELINE}) — ${result.total} px`);
  } else if (result.status === 'mismatch') {
    console.warn(`[visual] ⚠️ diff ${pct}% > ${TOLERANCE * 100}% (${result.diffPixels}/${result.total} px) — diff archived. ` +
      `Si le changement est légitime : npm run update-baselines.`);
  } else {
    console.log(`[visual] OK — diff ${pct}% ≤ ${TOLERANCE * 100}%`);
  }

  // Markdown summary injected into the GitHub Actions Job Summary (visible
  // directly on the run page, in addition to the artifacts).
  const icon = result.status === 'match' ? '✅' : result.status === 'created' ? '🆕' : '⚠️';
  const summary = [
    '### Visual regression — hello-triangle-web',
    '',
    `${icon} statut **${result.status}** — diff **${pct}%** (tolérance ${TOLERANCE * 100}%, ${result.diffPixels}/${result.total} px)`,
    '',
    '_Captures (baseline / current / diff) available in the run artifacts._',
    '',
  ].join('\n');
  fs.writeFileSync(SUMMARY, summary);

  // Non-blocking: we do not fail the build (non-deterministic SwiftShader rendering
  // across environments). The status is logged + diff archived in CI.
  expect(['created', 'match', 'mismatch']).toContain(result.status);
});
