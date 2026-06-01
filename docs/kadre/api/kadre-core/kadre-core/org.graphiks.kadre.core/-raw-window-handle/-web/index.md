//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[RawWindowHandle](../index.md)/[Web](index.md)

# Web

data class [Web](index.md)(val canvasElementId: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)? = null, val canvasElement: [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html)? = null) : [RawWindowHandle](../index.md)

Web window handle (browser / Wasm).

Two modes of identifying the target canvas are supported:

- 
   canvasElementId: CSS identifier of the `<canvas>` in the DOM (e.g. `"my-canvas"`). Convenient for a declarative configuration.
- 
   canvasElement: direct reference to the `HTMLCanvasElement` element. Declared [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html) in commonMain to avoid any DOM import; on the JS/Wasm side, perform the explicit cast: `canvasElement as HTMLCanvasElement`.

At least one of the two parameters must be non-null. If both are provided, canvasElement takes precedence over canvasElementId.

#### Throws

| | |
|---|---|
| [IllegalArgumentException](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-illegal-argument-exception/index.html) | if canvasElementId and canvasElement are both `null`. |

## Constructors

| | |
|---|---|
| [Web](-web.md) | [common]<br>constructor(canvasElementId: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)? = null, canvasElement: [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html)? = null) |

## Properties

| Name | Summary |
|---|---|
| [canvasElement](canvas-element.md) | [common]<br>val [canvasElement](canvas-element.md): [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html)?<br>Direct reference to the `HTMLCanvasElement`, or `null`.                            On the JS/Wasm side, cast with `canvasElement as HTMLCanvasElement`. |
| [canvasElementId](canvas-element-id.md) | [common]<br>val [canvasElementId](canvas-element-id.md): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)?<br>CSS identifier of the canvas in the DOM, or `null`. |