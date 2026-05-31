//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[DeviceEvent](index.md)

# DeviceEvent

sealed interface [DeviceEvent](index.md)

Événement brut de périphérique d'entrée.

Contrairement à [WindowEvent](../-window-event/index.md), ces événements sont émis indépendamment de la fenêtre active et reflètent l'état brut du périphérique.

### Utilisation typique

```kotlin
fun onDeviceEvent(event: DeviceEvent) {
    when (event) {
        is DeviceEvent.PointerMotion -> gererMouvement(event.dx, event.dy)
        is DeviceEvent.Button        -> gererBouton(event.button, event.state)
        is DeviceEvent.Key           -> gererTouche(event.scancode, event.state)
    }
}
```

#### Inheritors

| |
|---|
| [PointerMotion](-pointer-motion/index.md) |
| [Button](-button/index.md) |
| [Key](-key/index.md) |

## Types

| Name | Summary |
|---|---|
| [Button](-button/index.md) | [common]<br>data class [Button](-button/index.md)(val button: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), val state: [KeyState](../-key-state/index.md)) : [DeviceEvent](index.md)<br>Un bouton physique de périphérique a changé d'état. |
| [Key](-key/index.md) | [common]<br>data class [Key](-key/index.md)(val scancode: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), val state: [KeyState](../-key-state/index.md)) : [DeviceEvent](index.md)<br>Une touche physique du clavier a changé d'état (identifiée par scancode). |
| [PointerMotion](-pointer-motion/index.md) | [common]<br>data class [PointerMotion](-pointer-motion/index.md)(val dx: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html), val dy: [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)) : [DeviceEvent](index.md)<br>Mouvement brut du pointeur (delta, non limité aux bords de l'écran). |