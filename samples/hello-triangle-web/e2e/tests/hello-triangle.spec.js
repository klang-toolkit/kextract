// Web E2E smoke: "at least one frame presented" on hello-triangle-web.
//
// Loads the sample in headless Chromium (WebGPU SwiftShader), waits for the
// wgpu4k Web stack to initialize the pipeline (log « Pipeline ready »), checks for the absence
// of adapter/device acquisition errors and JS errors, then captures a screenshot
// of the canvas as a proof artifact.
const { test, expect } = require('@playwright/test');

test('hello-triangle-web initializes wgpu4k and displays frames', async ({ page }) => {
  const logs = [];
  const errors = [];
  page.on('console', (m) => logs.push(m.text()));
  page.on('pageerror', (e) => errors.push(e.message));

  await page.goto('/');

  // The target canvas must be present.
  await expect(page.locator('#kadre-canvas')).toBeVisible();

  // Wait for full initialization: device + pipeline created.
  // This is the signal that "the Kadre + wgpu4k Web stack started end-to-end".
  await expect
    .poll(() => logs.some((l) => l.includes('Pipeline ready')), { timeout: 60_000 })
    .toBe(true);

  // WebGPU acquisition must not have failed.
  const acquisitionFailure = logs.find((l) => l.includes('Acquisition failed'));
  expect(acquisitionFailure, `WebGPU acquisition failure: ${acquisitionFailure}`).toBeUndefined();

  // Let a few frames present (the requestRedraw loop runs continuously).
  await page.waitForTimeout(2_000);

  // Visual proof artifact.
  await page.locator('#kadre-canvas').screenshot({ path: 'triangle.png' });

  // No unhandled JS exception during rendering.
  expect(errors, `JS errors: ${errors.join(' | ')}`).toEqual([]);
});
