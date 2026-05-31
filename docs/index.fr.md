# Bienvenue sur la Documentation Kadre

Ce site regroupe l'ensemble des documentations techniques, des guides d'architecture et de la référence API de **Kadre** — une bibliothèque de fenêtrage et d'événements multiplateforme pour Kotlin Multiplatform.

---

## Fonctionnalités Clés

*   **Fenêtrage Multiplateforme** : Gestion native des fenêtres ciblant **macOS**, **Linux**, **Windows**, **Android**, **iOS** et **WebAssembly**.
*   **Modèle d'événements unifié** : API `WindowEvent` et `DeviceEvent` cohérente sur toutes les plateformes.
*   **Kotlin Multiplatform** : API Kotlin pure — `expect`/`actual` avec backends natifs spécifiques (AppKit, Win32, Wayland/X11, UIKit).
*   **Intégration wgpu4k** : Compatible avec `wgpu4k` pour le rendu GPU sur toutes les cibles.
*   **Moteur de Documentation API** : Génération automatisée via **Dokka v2** et rendu via **MkDocs Material**.

---

## Démarrage Rapide

### Générer la documentation API localement
```bash
./gradlew dokkaGenerate
```

### Compiler le site MkDocs localement
```bash
mkdocs build
```

### Lancer l'exemple hello-triangle
```bash
./gradlew :samples:hello-triangle:run
```
