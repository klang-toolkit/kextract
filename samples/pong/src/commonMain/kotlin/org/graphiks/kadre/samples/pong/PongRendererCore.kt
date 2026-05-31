/**
 * PongRendererCore — éléments de rendu partagés cross-plateforme.
 *
 * Le shader WGSL, les constantes de layout 2D et la construction de la liste
 * de quads (raquettes, balle, dashes, score) sont strictement identiques entre
 * les implémentations JVM (wgpu4k natif) et Web (wgpu4k WebGPU). Ce fichier
 * extrait ces parties pour éviter la duplication.
 *
 * Ce qui RESTE plateforme-specific (init Surface, runBlocking vs coroutines,
 * cleanup) est dans `PongRenderer.kt` (jvmMain) et `PongRendererWeb.kt` (jsMain).
 *
 * @since v0.2 — factorisation suite démo Pong web
 */
package org.graphiks.kadre.samples.pong

// ---------------------------------------------------------------------------
// Shader WGSL — 2D quad par vertex_index (TL TR BL  TR BR BL = 2 triangles CCW)
// ---------------------------------------------------------------------------

/**
 * Shader WGSL commun vertex + fragment.
 *
 * Le vertex shader génère les 6 sommets du quad à partir du `vertex_index`
 * (aucun vertex buffer requis). Uniforms (binding 0) : `[x, y, w, h, r, g, b, _pad]`
 * = 8 floats = 32 bytes.
 *
 * Coordonnées d'entrée : espace normalisé `[0..1]` avec Y vers le bas (origine TL).
 * Coordonnées de sortie : NDC `[-1..1]` avec Y vers le haut (convention WebGPU).
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
// Constantes de mise en page (coordonnées normalisées [0..1])
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

/** Taille du uniform buffer : 8 floats × 4 bytes = 32 bytes. */
internal const val UNIFORM_BYTES_LONG = 32L

/**
 * Nombre max de quads par frame (pool d'uniform buffers + bind groups).
 *
 * Décompte : 12 dashes + 2 paddles + 1 ball + 2 × (max 3 digits × ~25 pixels chiffre) ≈ 165.
 * On prend 256 pour garder une marge sans gaspiller (256 × 32B = 8 KB GPU memory).
 */
internal const val MAX_QUADS_PER_FRAME = 256

// ---------------------------------------------------------------------------
// Construction de la liste des quads à dessiner
// ---------------------------------------------------------------------------

/**
 * Construit la liste des quads à dessiner pour l'état courant du jeu.
 *
 * Chaque entrée est un [FloatArray] de 8 floats : `[x, y, w, h, r, g, b, _pad]`,
 * directement uploadable dans un uniform buffer aligné sur 32 bytes.
 *
 * Ordre :
 *   - 12 dashes centraux (gris)
 *   - Raquette joueur (gauche, blanche)
 *   - Raquette IA (droite, blanche)
 *   - Balle (blanche)
 *   - Score joueur (blanc, via [BitmapFont])
 *   - Score IA (blanc, via [BitmapFont])
 */
internal fun buildPongQuads(state: GameState): List<FloatArray> = buildList {
    // Dashes centraux (gris)
    repeat(DASH_COUNT) { i ->
        val dashY = (i.toDouble() / DASH_COUNT) + (0.5 / DASH_COUNT) - DASH_HEIGHT_N / 2
        add(floatArrayOf(
            (0.5 - DASH_WIDTH_N / 2).toFloat(), dashY.toFloat(),
            DASH_WIDTH_N.toFloat(), DASH_HEIGHT_N.toFloat(),
            0.4f, 0.4f, 0.4f, 0f,
        ))
    }
    // Raquette joueur (gauche, blanc)
    add(floatArrayOf(
        PADDLE_X_LEFT.toFloat(), (state.player.y - state.player.height / 2).toFloat(),
        PADDLE_WIDTH_N.toFloat(), state.player.height.toFloat(),
        1f, 1f, 1f, 0f,
    ))
    // Raquette IA (droite, blanc)
    add(floatArrayOf(
        PADDLE_X_RIGHT.toFloat(), (state.ai.y - state.ai.height / 2).toFloat(),
        PADDLE_WIDTH_N.toFloat(), state.ai.height.toFloat(),
        1f, 1f, 1f, 0f,
    ))
    // Balle (blanc)
    add(floatArrayOf(
        (state.ball.x - BALL_SIZE_N / 2).toFloat(), (state.ball.y - BALL_SIZE_N / 2).toFloat(),
        BALL_SIZE_N.toFloat(), BALL_SIZE_N.toFloat(),
        1f, 1f, 1f, 0f,
    ))
    // Score joueur (gauche)
    BitmapFont.renderNumber(state.score.player, x = 0.30, y = SCORE_Y, pixelSize = SCORE_PIXEL)
        .forEach { q ->
            add(floatArrayOf(q.x.toFloat(), q.y.toFloat(), q.w.toFloat(), q.h.toFloat(), 1f, 1f, 1f, 0f))
        }
    // Score IA (droite)
    BitmapFont.renderNumber(state.score.ai, x = 0.62, y = SCORE_Y, pixelSize = SCORE_PIXEL)
        .forEach { q ->
            add(floatArrayOf(q.x.toFloat(), q.y.toFloat(), q.w.toFloat(), q.h.toFloat(), 1f, 1f, 1f, 0f))
        }
}
