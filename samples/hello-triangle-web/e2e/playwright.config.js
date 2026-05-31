// Playwright configuration for the hello-triangle-web E2E smoke test.
//
// Serves the JS production bundle (produced by `jsBrowserDistribution`, toolchain
// enabled by #91) via http-server, then launches **stable Chrome** on macOS with
// the real Metal GPU.
//
// ## Why only macOS?
//
// GitHub Actions runners do not all expose the same graphics backend:
// - `macos-latest`: physical Mac mini with Metal (hardware GPU) → real WebGPU
// - `windows-latest`: Hyper-V VM WITHOUT GPU passthrough → `requestAdapter()` = null,
//   including with SwiftShader Vulkan (tested all flag combinations,
//   cf. PR #131). Revisit when Windows runners with GPU become available.
// - `ubuntu-latest`: not in the matrix — Chromium + SwiftShader on Linux
//   gives a rendering that may differ from the real GPU impl of end-users.
const { defineConfig } = require('@playwright/test');

const DIST = '../build/dist/js/productionExecutable';

module.exports = defineConfig({
  testDir: './tests',
  timeout: 90_000,
  reporter: [['list']],
  use: {
    baseURL: 'http://127.0.0.1:8080',
    headless: true,
    // Stable Chrome (release channel) installed by Playwright via `npx playwright
    // install chrome`. The macOS runner's Metal GPU is used for real WebGPU.
    channel: 'chrome',
    launchOptions: {
      // Required to expose a WebGPU adapter in headless mode (Chrome policy).
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
