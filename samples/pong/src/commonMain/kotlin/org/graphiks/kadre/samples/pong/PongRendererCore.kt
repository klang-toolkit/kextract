/**
 * PongRendererCore — cross-platform shared rendering elements.
 *
 * The WGSL shader, the 2D layout constants and the construction of the quad
 * list (paddles, ball, dashes, score) are strictly identical between
 * the JVM (native wgpu4k) and Web (wgpu4k WebGPU) implementations. This file
 * extracts these parts to avoid duplication.
 *
 * What REMAINS platform-specific (Surface init, runBlocking vs coroutines,
 * cleanup) is in `PongRenderer.kt` (jvmMain) and `PongRendererWeb.kt` (jsMain).
 *
 * @since v0.2 — factored out following the Pong web demo
 */
package org.graphiks.kadre.samples.pong

// ---------------------------------------------------------------------------
// WGSL shader — 2D quad by vertex_index (TL TR BL  TR BR BL = 2 CCW triangles)
// ---------------------------------------------------------------------------

/**
 * Common vertex + fragment WGSL shader.
 *
 * The vertex shader generates the 6 vertices of the quad from the `vertex_index`
 * (no vertex buffer required). Uniforms (binding 0): `[x, y, w, h, r, g, b, _pad]`
 * = 8 floats = 32 bytes.
 *
 * Input coordinates: normalized space `[0..1]` with Y downward (TL origin).
 * Output coordinates: NDC `[-1..1]` with Y upward (WebGPU convention).
 */
internal val PONG_WGSL = """
struct Uniforms {
    x: f32, y: f32, w: f32, h: f32,
    r: f32, g: f32, b: f32, _pad: f32,
}

@group(0) @binding(0) var<uniform> u: Uniforms;

struct VertexOut {
    @builtin(position) pos: vec4<f32>,
    @location(0) color: vec3<f32>,
}

@vertex
fn vs_main(@builtin(vertex_index) vi: u32) -> VertexOut {
    let cx = array<f32, 6>(0.0, 1.0, 0.0, 1.0, 1.0, 0.0);
    let cy = array<f32, 6>(0.0, 0.0, 1.0, 0.0, 1.0, 1.0);
    let nx = u.x + cx[vi] * u.w;
    let ny = u.y + cy[vi] * u.h;
    var out: VertexOut;
    out.pos   = vec4<f32>(nx * 2.0 - 1.0, 1.0 - ny * 2.0, 0.0, 1.0);
    out.color = vec3<f32>(u.r, u.g, u.b);
    return out;
}

@fragment
fn fs_main(in: VertexOut) -> @location(0) vec4<f32> {
    return vec4<f32>(in.color, 1.0);
}
""".trimIndent()

// ---------------------------------------------------------------------------
// Layout constants (normalized coordinates [0..1])
// ---------------------------------------------------------------------------

internal const val PADDLE_WIDTH_N  = 0.02
internal const val PADDLE_HEIGHT_N = 0.20
internal const val PADDLE_X_LEFT   = 0.02
internal const val PADDLE_X_RIGHT  = 1.0 - PADDLE_X_LEFT - PADDLE_WIDTH_N

internal const val BALL_SIZE_N = 0.018

internal const val DASH_WIDTH_N   = 0.008
internal const val DASH_HEIGHT_N  = 0.04
internal const val DASH_COUNT     = 12

internal const val SCORE_PIXEL    = 0.012
internal const val SCORE_Y        = 0.04

/** Uniform buffer size: 8 floats × 4 bytes = 32 bytes. */
internal const val UNIFORM_BYTES_LONG = 32L

/**
 * Max number of quads per frame (pool of uniform buffers + bind groups).
 *
 * Count: 12 dashes + 2 paddles + 1 ball + 2 × (max 3 digits × ~25 pixels per digit) ≈ 165.
 * We take 256 to keep a margin without waste (256 × 32B = 8 KB GPU memory).
 */
internal const val MAX_QUADS_PER_FRAME = 256

// ---------------------------------------------------------------------------
// Construction of the list of quads to draw
// ---------------------------------------------------------------------------

/**
 * Builds the list of quads to draw for the current game state.
 *
 * Each entry is a [FloatArray] of 8 floats: `[x, y, w, h, r, g, b, _pad]`,
 * directly uploadable into a uniform buffer aligned on 32 bytes.
 *
 * Order:
 *   - 12 center dashes (gray)
 *   - Player paddle (left, white)
 *   - AI paddle (right, white)
 *   - Ball (white)
 *   - Player score (white, via [BitmapFont])
 *   - AI score (white, via [BitmapFont])
 */
internal fun buildPongQuads(state: GameState): List<FloatArray> = buildList {
    // Center dashes (gray)
    repeat(DASH_COUNT) { i ->
        val dashY = (i.toDouble() / DASH_COUNT) + (0.5 / DASH_COUNT) - DASH_HEIGHT_N / 2
        add(floatArrayOf(
            (0.5 - DASH_WIDTH_N / 2).toFloat(), dashY.toFloat(),
            DASH_WIDTH_N.toFloat(), DASH_HEIGHT_N.toFloat(),
            0.4f, 0.4f, 0.4f, 0f,
        ))
    }
    // Player paddle (left, white)
    add(floatArrayOf(
        PADDLE_X_LEFT.toFloat(), (state.player.y - state.player.height / 2).toFloat(),
        PADDLE_WIDTH_N.toFloat(), state.player.height.toFloat(),
        1f, 1f, 1f, 0f,
    ))
    // AI paddle (right, white)
    add(floatArrayOf(
        PADDLE_X_RIGHT.toFloat(), (state.ai.y - state.ai.height / 2).toFloat(),
        PADDLE_WIDTH_N.toFloat(), state.ai.height.toFloat(),
        1f, 1f, 1f, 0f,
    ))
    // Ball (white)
    add(floatArrayOf(
        (state.ball.x - BALL_SIZE_N / 2).toFloat(), (state.ball.y - BALL_SIZE_N / 2).toFloat(),
        BALL_SIZE_N.toFloat(), BALL_SIZE_N.toFloat(),
        1f, 1f, 1f, 0f,
    ))
    // Player score (left)
    BitmapFont.renderNumber(state.score.player, x = 0.30, y = SCORE_Y, pixelSize = SCORE_PIXEL)
        .forEach { q ->
            add(floatArrayOf(q.x.toFloat(), q.y.toFloat(), q.w.toFloat(), q.h.toFloat(), 1f, 1f, 1f, 0f))
        }
    // AI score (right)
    BitmapFont.renderNumber(state.score.ai, x = 0.62, y = SCORE_Y, pixelSize = SCORE_PIXEL)
        .forEach { q ->
            add(floatArrayOf(q.x.toFloat(), q.y.toFloat(), q.w.toFloat(), q.h.toFloat(), 1f, 1f, 1f, 0f))
        }
}
