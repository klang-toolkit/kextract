// Test E2E scénarisé Pong (Web) — Redmine #95 / suivi #88.
//
// VALEUR AJOUTÉE vs `hello-triangle-web` :
//   - Scripte une séquence d'inputs clavier (ArrowDown / ArrowUp / release)
//   - Capture plusieurs frames à des instants distincts
//   - Vérifie que des frames consécutives **diffèrent** (animation effective)
//   - Vérifie que les events clavier remontent jusqu'au handler Kadre
//     (log `[pong-web] key X Pressed/Released` émis par PongAppWeb)
//   - Vidéo .webm + trace Playwright systématiquement archivées
//
// Cible : bundle JS de production servi par http-server (cf. playwright.config.js).
// La cible wasmJs est ajoutée dans un suivi séparé (dépend du fix Binaryen #137).
const fs = require('fs');
const path = require('path');
const { test, expect } = require('@playwright/test');
const { PNG } = require('pngjs');
const pixelmatch = require('pixelmatch').default || require('pixelmatch');

const RESULTS = path.join(__dirname, '..', 'test-results');

/**
 * Compare deux PNG (Buffers) et retourne la fraction de pixels qui diffèrent.
 *
 * Utilisé pour prouver qu'une frame change entre deux instants (= animation
 * effective). Le seuil est volontairement bas (1%) — il s'agit de détecter
 * « ça bouge » et non un pixel-perfect.
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
  // 1. Boot — chargement page + initialisation wgpu4k Web
  // -------------------------------------------------------------------------
  await page.goto('/');
  await expect(page.locator('#kadre-canvas')).toBeVisible();
  await expect
    .poll(() => logs.some((l) => l.includes('Pipeline prêt')), { timeout: 60_000 })
    .toBe(true);

  // L'acquisition WebGPU ne doit pas avoir échoué.
  const acquisitionFailure = logs.find((l) => l.includes('Échec acquisition'));
  expect(acquisitionFailure, `Échec WebGPU : ${acquisitionFailure}`).toBeUndefined();

  // PongAppWeb force `setControlFlow(ControlFlow.Poll)` dans canCreateSurfaces
  // → la boucle aboutToWait tourne en continu et anime le jeu (balle + IA)
  // sans nécessiter d'input utilisateur. On laisse l'init se stabiliser puis
  // on observe l'animation sur une fenêtre de 2.5s.
  await page.waitForTimeout(1_500);

  const frame1 = await page.locator('#kadre-canvas').screenshot();
  fs.writeFileSync(path.join(RESULTS, 'frame1-animation-start.png'), frame1);

  await page.waitForTimeout(2_500);
  const frame2 = await page.locator('#kadre-canvas').screenshot();
  fs.writeFileSync(path.join(RESULTS, 'frame2-animation-2.5s.png'), frame2);

  // -------------------------------------------------------------------------
  // 2. Assertion : animation effective (frames consécutives doivent différer)
  // -------------------------------------------------------------------------
  // Le seuil 0.3 % est calibré pour le rendu Pong réel : balle 1.8 % de l'écran
  // + raquette IA en mouvement lent → empreinte typique 0.5–1.5 % sur 2.5 s.
  // On garde une marge de sécurité au-dessus du bruit (~0 % entre frames stables).
  const animationDiff = pixelDiffRatio(frame1, frame2);
  console.log(`[scenario] diff animation (2.5s) = ${(animationDiff * 100).toFixed(2)}%`);
  expect(
    animationDiff,
    `Animation absente : frame1 et frame2 (2.5s d'écart) sont quasi identiques (diff ${(animationDiff * 100).toFixed(2)}%). ` +
      `Attendu : > 0.3% (balle + IA en mouvement).`,
  ).toBeGreaterThan(0.003);

  // -------------------------------------------------------------------------
  // 3. Inputs scriptés — focus canvas puis ArrowDown / ArrowUp
  // -------------------------------------------------------------------------
  // index.html du sample fait un .focus() au load + au click. On clique pour
  // garantir le focus avant d'envoyer des keyboard events (l'auto-focus initial
  // peut être perdu pendant les `waitForTimeout` selon l'env headless).
  await page.locator('#kadre-canvas').click();

  // ArrowDown maintenu ~1s
  await page.keyboard.down('ArrowDown');
  await page.waitForTimeout(1_000);
  await page.keyboard.up('ArrowDown');
  await page.waitForTimeout(300);

  // Vérifier que l'event clavier a bien remonté jusqu'au handler PongAppWeb.
  // Le log `[pong-web] key ArrowDown Pressed` est émis par `PongAppWeb.onKey()`.
  const downPressed = logs.some((l) => l.includes('key ArrowDown Pressed'));
  const downReleased = logs.some((l) => l.includes('key ArrowDown Released'));
  expect(
    downPressed,
    `Event ArrowDown Pressed jamais reçu par PongAppWeb. Logs : ${logs.filter((l) => l.includes('key')).join(' | ')}`,
  ).toBe(true);
  expect(downReleased, 'Event ArrowDown Released jamais reçu').toBe(true);

  const frame3 = await page.locator('#kadre-canvas').screenshot();
  fs.writeFileSync(path.join(RESULTS, 'frame3-after-arrowdown.png'), frame3);

  // ArrowUp maintenu ~1s — devrait remonter la raquette droite
  await page.keyboard.down('ArrowUp');
  await page.waitForTimeout(1_000);
  await page.keyboard.up('ArrowUp');
  await page.waitForTimeout(300);

  const upPressed = logs.some((l) => l.includes('key ArrowUp Pressed'));
  expect(upPressed, 'Event ArrowUp Pressed jamais reçu').toBe(true);

  const frame4 = await page.locator('#kadre-canvas').screenshot();
  fs.writeFileSync(path.join(RESULTS, 'frame4-after-arrowup.png'), frame4);

  // -------------------------------------------------------------------------
  // 4. Assertion : le déplacement clavier a un effet visible
  // -------------------------------------------------------------------------
  // Entre frame3 (juste après ArrowDown) et frame4 (juste après ArrowUp),
  // la raquette droite a forcément changé de position → pixels différents.
  const inputDiff = pixelDiffRatio(frame3, frame4);
  console.log(`[scenario] diff après inputs clavier = ${(inputDiff * 100).toFixed(2)}%`);
  // Seuil bas (0.15%) — l'effet typique du déplacement raquette + animation
  // continue donne 0.3–1.0% sur cette fenêtre, on garde une marge sécurité.
  expect(
    inputDiff,
    `Inputs clavier sans effet visible : frame3 (post-ArrowDown) ≈ frame4 (post-ArrowUp), diff ${(inputDiff * 100).toFixed(2)}%`,
  ).toBeGreaterThan(0.0015);

  // -------------------------------------------------------------------------
  // 5. Aucune erreur JS pendant tout le scénario
  // -------------------------------------------------------------------------
  expect(errors, `Erreurs JS pendant le scénario : ${errors.join(' | ')}`).toEqual([]);

  // -------------------------------------------------------------------------
  // 6. Résumé Markdown pour le Job Summary GitHub Actions
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
