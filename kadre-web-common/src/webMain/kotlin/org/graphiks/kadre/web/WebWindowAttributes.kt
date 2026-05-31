/**
 * Web window creation attributes — web-only extension of the
 * [org.graphiks.kadre.core.WindowAttributes] contract.
 *
 * Inspired by the `WindowAttributesExtWebSys` trait of [winit](https://docs.rs/winit/latest/winit/platform/web/trait.WindowAttributesExtWebSys.html),
 * which adds the web-target-specific `with_canvas(...)` and `with_append(...)` methods
 * to `WindowAttributes`. The core contract stays DOM-agnostic.
 *
 * ## Use cases
 *
 * 1. **Existing DOM canvas** (typical case of an embed in a host page):
 *    ```kotlin
 *    webLoop.createWindow(WebWindowAttributes(canvasId = "my-canvas"))
 *    ```
 * 2. **Auto-creation, append to `<body>`** (typical case of a standalone demo):
 *    ```kotlin
 *    webLoop.createWindow(WebWindowAttributes(appendToBody = true, width = 800, height = 600))
 *    ```
 * 3. **Auto-creation, explicit parent**:
 *    ```kotlin
 *    webLoop.createWindow(WebWindowAttributes(
 *        canvasId = "kadre",
 *        parentElementId = "app-root",
 *        appendToBody = true,
 *    ))
 *    ```
 *
 * ## webMain constraint
 * This file resides in `webMain`: no DOM type (`HTMLCanvasElement`, …)
 * can pass through here. The canvas is referenced by its CSS `id` only.
 * An overload directly exposing an `HTMLCanvasElement` could be added
 * in `jsMain` / `wasmJsMain` if needed (out of scope for this PR).
 *
 * @property canvasId          CSS id of an existing DOM `<canvas>` or one to create.
 *                              `null` ⇒ default id `"kadre-canvas"`.
 * @property appendToBody       If `true` and no canvas with [canvasId] exists,
 *                              Kadre creates a `<canvas>` (with [width] × [height]) and
 *                              adds it to the DOM (within [parentElementId] or `<body>`).
 * @property parentElementId    CSS id of the parent in which to insert the created canvas.
 *                              Ignored if the canvas already exists. `null` ⇒ `<body>`.
 * @property width              Initial width in physical pixels of the created canvas.
 * @property height             Initial height in physical pixels of the created canvas.
 * @property core               Underlying cross-platform attributes
 *                              ([org.graphiks.kadre.core.WindowAttributes.title],
 *                              size, visible, resizable) — most are no-op
 *                              on the Web side but kept for multi-platform consistency.
 *
 * @since 1.0.0
 */
package org.graphiks.kadre.web

import org.graphiks.kadre.core.WindowAttributes

data class WebWindowAttributes(
    val canvasId: String? = null,
    val appendToBody: Boolean = false,
    val parentElementId: String? = null,
    val width: Int = 800,
    val height: Int = 600,
    val core: WindowAttributes = WindowAttributes(),
) {
    /** Effective CSS identifier of the canvas (with `"kadre-canvas"` fallback). */
    val effectiveCanvasId: String get() = canvasId ?: DEFAULT_CANVAS_ID

    companion object {
        const val DEFAULT_CANVAS_ID: String = "kadre-canvas"
    }
}
