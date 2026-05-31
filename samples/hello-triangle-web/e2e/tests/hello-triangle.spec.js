// Smoke E2E Web : « au moins une frame présentée » sur hello-triangle-web.
//
// Charge le sample dans Chromium headless (WebGPU SwiftShader), attend que la stack
// wgpu4k Web ait initialisé le pipeline (log « Pipeline prêt »), vérifie l'absence
// d'erreur d'acquisition adapter/device et d'erreur JS, puis capture un screenshot
// du canvas comme artefact de preuve.
const { test, expect } = require('@playwright/test');

test('hello-triangle-web initialise wgpu4k et présente des frames', async ({ page }) => {
  const logs = [];
  const errors = [];
  page.on('console', (m) => logs.push(m.text()));
  page.on('pageerror', (e) => errors.push(e.message));

  await page.goto('/');

  // Le canvas cible doit être présent.
  await expect(page.locator('#kadre-canvas')).toBeVisible();

  // Attendre l'initialisation complète : device + pipeline créés.
  // C'est le signal « la stack Kadre + wgpu4k Web a démarré bout-en-bout ».
  await expect
    .poll(() => logs.some((l) => l.includes('Pipeline prêt')), { timeout: 60_000 })
    .toBe(true);

  // L'acquisition WebGPU ne doit pas avoir échoué.
  const acquisitionFailure = logs.find((l) => l.includes('Échec acquisition'));
  expect(acquisitionFailure, `Échec WebGPU: ${acquisitionFailure}`).toBeUndefined();

  // Laisser quelques frames se présenter (la boucle requestRedraw tourne en continu).
  await page.waitForTimeout(2_000);

  // Artefact de preuve visuelle.
  await page.locator('#kadre-canvas').screenshot({ path: 'triangle.png' });

  // Aucune exception JS non gérée pendant le rendu.
  expect(errors, `Erreurs JS: ${errors.join(' | ')}`).toEqual([]);
});
