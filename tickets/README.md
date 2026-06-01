# Kadre — Index des tickets de remédiation

Ce dossier contient les tickets de correction et d'extension de Kadre vers la parité winit.
Source de vérité : [`docs/kadre/remediation-plan.md`](../docs/kadre/remediation-plan.md).

## Tableau de bord

| ID | Titre | Milestone | Statut | Dépend de |
|----|-------|-----------|--------|-----------|
| [R0.1](./R0.1-typage-fort.md) | Typage fort — supprimer tous les `Any` | R0 | ✅ Fait | — |
| [R0.2](./R0.2-web-tailles-scale.md) | Web — tailles et `scaleFactor` réels | R0 | ✅ Fait | — |
| [R0.3](./R0.3-ios-wakeup.md) | iOS — `wakeUp()` fonctionnel | R0 | ✅ Fait | — |
| [R0.4](./R0.4-x11-scalefactor.md) | X11 — `scaleFactor` réel (Xft.dpi / RANDR) | R0 | ✅ Fait | — |
| [R0.5](./R0.5-wayland-events-residuels.md) | Wayland — événements résiduels | R0 | ✅ Fait | — |
| [R0.6](./R0.6-win32-tailles-non-cachees.md) | Win32 — tailles non cachées | R0 | ✅ Fait | — |
| [R1](./R1-etat-geometrie-fenetre.md) | État et géométrie de la fenêtre | R1 | ✅ Fait | R0.1 |
| [R2](./R2-moniteurs-plein-ecran.md) | Moniteurs et plein écran | R2 | À faire | R1 |
| [R3](./R3-curseur-theme-apparence.md) | Curseur, thème et apparence | R3 | À faire | R0.1 |
| [R4](./R4-richesse-entree.md) | Richesse des entrées (clavier / pointeur) | R4 | À faire | R0.1 |
| [R5-IME](./R5-ime.md) | Saisie IME | R5 | À faire | R3, R4 |
| [R5-DnD](./R5-glisser-deposer.md) | Glisser-déposer | R5 | À faire | R3, R4 |
| [R5-Gestures](./R5-gestes-trackpad.md) | Gestes trackpad | R5 | À faire | R3, R4 |
| [R5-CustomCursor](./R5-curseurs-custom.md) | Curseurs personnalisés | R5 | À faire | R3, R4 |
| [R5-MiscWindow](./R5-divers-fenetre.md) | Divers fenêtre | R5 | À faire | R3, R4 |
| [R5-Gamepad](./R5-manette.md) | Manette / Gamepad (hors périmètre winit) | R5 | À faire | R3, R4 |

## Séquençage

```
R0 (tous) → R1 → R2
R0.1      → R3  ↘
R0.1      → R4  →  R5 (lots indépendants)
```

R3 et R4 sont parallélisables une fois R0.1 validé.
Les lots R5 sont indépendants entre eux et n'ont pas à bloquer la 1.0.
