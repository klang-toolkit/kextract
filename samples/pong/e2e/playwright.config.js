// Playwright configuration for the scripted E2E test of samples/pong (Web).
//
// Serves the JS production bundle (produced by `jsBrowserDistribution`) via
// http-server, then launches **stable Chrome** headless with real WebGPU (host
// GPU). Choice of `channel: 'chrome'` + macOS / Windows runners in CI: the
// Pong scenario is more demanding than the single-frame smoke of hello-triangle
// (multi-second animation + pixel diff assertions) → Chromium + SwiftShader
// (software rendering on Linux) gives slower and less
// deterministic frames, poor ground for these thresholds. Cf. `feat/web-e2e-chrome-mac-win`.
//
// Trace + video enabled (`on`) to systematically produce:
//   - a .webm of the full run (visual proof / comms)
//   - a Playwright trace (interactive debugging via `npx playwright show-trace`)
const { defineConfig } = require('@playwright/test');

const DIST = '../build/dist/js/productionExecutable';

module.exports = defineConfig({
  testDir: './tests',
  // The scenario simulates several seconds of interaction (wgpu4k boot + scripted
  // inputs + observation frames) — leave headroom on loaded runners.
  timeout: 180_000,
  reporter: [['list']],
  use: {
    baseURL: 'http://127.0.0.1:8080',
    headless: true,
    // Stable Chrome (release channel) with native GPU acceleration. Avoids
    // SwiftShader (CPU) which slows the animation and blurs the pixel diff
    // thresholds the assertions rely on.
    channel: 'chrome',
    // Viewport large enough for the 800×600 canvas + the sample's HUD.
    viewport: { width: 1024, height: 768 },
    // Systematic video — this is the added value of the scripted E2E test
    // (vs the single-frame smoke of hello-triangle-web).
    video: 'on',
    // Playwright trace archived for debugging (openable via `npx playwright show-trace`).
    trace: 'on',
    launchOptions: {
      // --enable-unsafe-webgpu is still required in headless mode to expose
      // a WebGPU adapter even with a physical GPU present.
      args: ['--enable-unsafe-webgpu'],
    },
  },
  webServer: {
    command: `npx http-server ${DIST} -p 8080 -a 127.0.0.1 -s`,
    url: 'http://127.0.0.1:8080',
    reuseExistingServer: !process.env.CI,
    timeout: 60_000,
  },
});
