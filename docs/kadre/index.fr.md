# Kadre

POC de fenêtrage cross-platform en **Kotlin pur**, inspiré de [winit](https://github.com/rust-windowing/winit).

Objectif : exposer des handles natifs (`NSView`, `UIView`, `android.view.Surface`) consommables par un renderer 3D (Metal, Vulkan, [wgpu4k](https://github.com/wgpu4k/wgpu4k)) **sans dépendance AWT/Swing**.

## Tutoriels

- [Intégrer Kadre dans une application Windows](./tutorials/windows-app.md) — fenêtre Win32, événements, DPI PerMonitorV2
- [Intégrer Kadre dans une application Linux](./tutorials/linux-app.md) — X11 et Wayland, auto-détection backend, DPI, CI headless
- [Intégrer Kadre dans une page web](./tutorials/web-embed.md) — canvas HTML, Kotlin/JS + Kotlin/Wasm, boucle RAF

## Blog

- [Kadre v1.0.0 — 6 plateformes, Pong cross-platform, Demo en direct](./blog/v1.0.0-release.md) — la release 1.0.0 : macOS, iOS, Android, Win32, Web (JS+Wasm), Linux (X11+Wayland), Pong

## Documents

- [Plan projet](./plan.md) — vision, périmètre, risques
- [Sprint Review](./sprint-review.md) — métriques, livrables, rétrospective
- [Spécifications techniques](./specs.md) — architecture, API, diagrammes

## Plateformes

| Plateforme | Backend |
|------------|---------|
| **macOS** | NSWindow avec contentView layer-backed prêt pour Metal |
| **iOS** | UIKit (UIWindow + UIView + CAMetalLayer) |
| **Android** | SurfaceView + Choreographer |
| **Windows** | Win32 (RegisterClassExW + CreateWindowExW) |
| **Linux** | X11 + Wayland, auto-détectés |
| **Web** | canvas HTML (Kotlin/JS + Kotlin/Wasm) |

## Statut

**Publié** — `org.graphiks.kadre:kadre:1.0.0` sur Maven Central.
