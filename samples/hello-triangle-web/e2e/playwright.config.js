// Configuration Playwright pour le smoke E2E de hello-triangle-web (Redmine #22).
//
// Sert le bundle de production JS (produit par `jsBrowserDistribution`, toolchain
// activée par #91) via http-server, puis lance **Chrome stable** sur macOS avec
// le GPU Metal réel.
//
// ## Pourquoi seulement macOS ?
//
// Les runners GitHub Actions n'exposent pas tous le même backend graphique :
// - `macos-latest` : Mac mini physique avec Metal (GPU hardware) → WebGPU réel
// - `windows-latest` : VM Hyper-V SANS GPU passthrough → `requestAdapter()` = null,
//   y compris avec SwiftShader Vulkan (testé toutes les combinaisons de flags,
//   cf. PR #131). À revisiter quand des runners Windows avec GPU seront dispo.
// - `ubuntu-latest` : pas dans le matrix — Chromium + SwiftShader sur Linux
//   donne un rendu qui peut différer de l'impl GPU réelle des end-users.
const { defineConfig } = require('@playwright/test');

const DIST = '../build/dist/js/productionExecutable';

module.exports = defineConfig({
  testDir: './tests',
  timeout: 90_000,
  reporter: [['list']],
  use: {
    baseURL: 'http://127.0.0.1:8080',
    headless: true,
    // Chrome stable (canal release) installé par Playwright via `npx playwright
    // install chrome`. Le GPU Metal du runner macOS est utilisé pour WebGPU réel.
    channel: 'chrome',
    launchOptions: {
      // Requis pour exposer un adapter WebGPU en mode headless (policy Chrome).
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
