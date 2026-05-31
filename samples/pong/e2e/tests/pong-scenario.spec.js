// Scripted Pong E2E test (Web), follow-up #88.
//
// ADDED VALUE vs `hello-triangle-web`:
//   - Scripts a sequence of keyboard inputs (ArrowDown / ArrowUp / release)
//   - Captures several frames at distinct moments
//   - Verifies that consecutive frames **differ** (effective animation)
//   - Verifies that keyboard events reach the Kadre handler
//     (log `[pong-web] key X Pressed/Released` emitted by PongAppWeb)
//   - .webm video + Playwright trace systematically archived
//
// Target: JS production bundle served by http-server (cf. playwright.config.js).
// The wasmJs target is added in a separate follow-up (depends on the Binaryen fix #137).
const fs = require('fs');
const path = require('path');
const { test, expect } = require('@playwright/test');
const { PNG } = require('pngjs');
const pixelmatch = require('pixelmatch').default || require('pixelmatch');

const RESULTS = path.join(__dirname, '..', 'test-results');

/**
 * Compares two PNGs (Buffers) and returns the fraction of pixels that differ.
 *
 * Used to prove that a frame changes between two moments (= effective
 * animation). The threshold is deliberately low (1%) — the goal is to detect
 * "it moves" and not pixel-perfect equality.
 */
function pixelDiffRatio(pngBuf1, pngBuf2) {
  const a = PNG.sync.read(pngBuf1);
  const b = PNG.sync.read(pngBuf2);
  if (a.width !== b.width || a.height !== b.height) return 1;
  const diff = new PNG({ width: a.width, height: a.height });
  const n = pixelmatch(a.data, b.data, diff.data, a.width, a.height, { threshold: 0.1 });
  return n / (a.width * a.height);
}

test('Pong Web — scénario scripté : animation + clavier + vidéo', async ({ page }, testInfo) => {
  fs.mkdirSync(RESULTS, { recursive: true });

  const logs = [];
  const errors = [];
  page.on('console', (m) => logs.push(m.text()));
  page.on('pageerror', (e) => errors.push(e.message));

  // -------------------------------------------------------------------------
  // 1. Boot — page load + wgpu4k Web initialization
  // -------------------------------------------------------------------------
  await page.goto('/');
  await expect(page.locator('#kadre-canvas')).toBeVisible();
  await expect
    .poll(() => logs.some((l) => l.includes('Pipeline prêt')), { timeout: 60_000 })
    .toBe(true);

  // WebGPU acquisition must not have failed.
  const acquisitionFailure = logs.find((l) => l.includes('Échec acquisition'));
  expect(acquisitionFailure, `Échec WebGPU : ${acquisitionFailure}`).toBeUndefined();

  // PongAppWeb forces `setControlFlow(ControlFlow.Poll)` in canCreateSurfaces
  // → the aboutToWait loop runs continuously and animates the game (ball + AI)
  // without requiring user input. We let the init stabilize then
  // observe the animation over a 2.5s window.
  await page.waitForTimeout(1_500);

  const frame1 = await page.locator('#kadre-canvas').screenshot();
  fs.writeFileSync(path.join(RESULTS, 'frame1-animation-start.png'), frame1);

  await page.waitForTimeout(2_500);
  const frame2 = await page.locator('#kadre-canvas').screenshot();
  fs.writeFileSync(path.join(RESULTS, 'frame2-animation-2.5s.png'), frame2);

  // -------------------------------------------------------------------------
  // 2. Assertion: effective animation (consecutive frames must differ)
  // -------------------------------------------------------------------------
  // The 0.3% threshold is calibrated for real Pong rendering: ball 1.8% of the screen
  // + slowly moving AI paddle → typical footprint 0.5–1.5% over 2.5s.
  // We keep a safety margin above the noise (~0% between stable frames).
  const animationDiff = pixelDiffRatio(frame1, frame2);
  console.log(`[scenario] diff animation (2.5s) = ${(animationDiff * 100).toFixed(2)}%`);
  expect(
    animationDiff,
    `Animation absente : frame1 et frame2 (2.5s d'écart) sont quasi identiques (diff ${(animationDiff * 100).toFixed(2)}%). ` +
      `Attendu : > 0.3% (balle + IA en mouvement).`,
  ).toBeGreaterThan(0.003);

  // -------------------------------------------------------------------------
  // 3. Scripted inputs — focus canvas then ArrowDown / ArrowUp
  // -------------------------------------------------------------------------
  // The sample's index.html does a .focus() on load + on click. We click to
  // guarantee focus before sending keyboard events (the initial auto-focus
  // may be lost during the `waitForTimeout` depending on the headless env).
  await page.locator('#kadre-canvas').click();

  // ArrowDown held ~1s
  await page.keyboard.down('ArrowDown');
  await page.waitForTimeout(1_000);
  await page.keyboard.up('ArrowDown');
  await page.waitForTimeout(300);

  // Verify that the keyboard event indeed reached the PongAppWeb handler.
  // The log `[pong-web] key ArrowDown Pressed` is emitted by `PongAppWeb.onKey()`.
  const downPressed = logs.some((l) => l.includes('key ArrowDown Pressed'));
  const downReleased = logs.some((l) => l.includes('key ArrowDown Released'));
  expect(
    downPressed,
    `Event ArrowDown Pressed jamais reçu par PongAppWeb. Logs : ${logs.filter((l) => l.includes('key')).join(' | ')}`,
  ).toBe(true);
  expect(downReleased, 'Event ArrowDown Released jamais reçu').toBe(true);

  const frame3 = await page.locator('#kadre-canvas').screenshot();
  fs.writeFileSync(path.join(RESULTS, 'frame3-after-arrowdown.png'), frame3);

  // ArrowUp held ~1s — should move the right paddle up
  await page.keyboard.down('ArrowUp');
  await page.waitForTimeout(1_000);
  await page.keyboard.up('ArrowUp');
  await page.waitForTimeout(300);

  const upPressed = logs.some((l) => l.includes('key ArrowUp Pressed'));
  expect(upPressed, 'Event ArrowUp Pressed jamais reçu').toBe(true);

  const frame4 = await page.locator('#kadre-canvas').screenshot();
  fs.writeFileSync(path.join(RESULTS, 'frame4-after-arrowup.png'), frame4);

  // -------------------------------------------------------------------------
  // 4. Assertion: the keyboard movement has a visible effect
  // -------------------------------------------------------------------------
  // Between frame3 (just after ArrowDown) and frame4 (just after ArrowUp),
  // the right paddle necessarily changed position → different pixels.
  const inputDiff = pixelDiffRatio(frame3, frame4);
  console.log(`[scenario] diff après inputs clavier = ${(inputDiff * 100).toFixed(2)}%`);
  // Low threshold (0.15%) — the typical effect of paddle movement + continuous
  // animation gives 0.3–1.0% over this window, we keep a safety margin.
  expect(
    inputDiff,
    `Inputs clavier sans effet visible : frame3 (post-ArrowDown) ≈ frame4 (post-ArrowUp), diff ${(inputDiff * 100).toFixed(2)}%`,
  ).toBeGreaterThan(0.0015);

  // -------------------------------------------------------------------------
  // 5. No JS error during the entire scenario
  // -------------------------------------------------------------------------
  expect(errors, `Erreurs JS pendant le scénario : ${errors.join(' | ')}`).toEqual([]);

  // -------------------------------------------------------------------------
  // 6. Markdown summary for the GitHub Actions Job Summary
  // -------------------------------------------------------------------------
  const keyLogs = logs.filter((l) => l.includes('[pong-web] key')).length;
  const summary = [
    '### Pong E2E — scénario scripté',
    '',
    `- ✅ Pipeline wgpu4k Web initialisé`,
    `- ✅ Animation effective (diff t=0 vs t=2.5s : **${(animationDiff * 100).toFixed(2)}%**)`,
    `- ✅ Inputs clavier remontés au handler (${keyLogs} events \`[pong-web] key …\` reçus)`,
    `- ✅ Effet visible (diff frame3 vs frame4 : **${(inputDiff * 100).toFixed(2)}%**)`,
    `- ✅ Aucune erreur JS`,
    '',
    '_Frames + vidéo .webm + trace Playwright dans les artefacts du run._',
    '',
  ].join('\n');
  fs.writeFileSync(path.join(RESULTS, 'scenario-summary.md'), summary);
});
