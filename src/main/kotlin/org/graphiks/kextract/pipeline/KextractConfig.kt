package org.graphiks.kextract.pipeline

/**
 * Global configuration singleton for the kextract pipeline.
 *
 * Set [verbose] to `true` before running the pipeline to emit warnings even
 * for declarations that are marked [org.graphiks.kextract.DeclarationImpl.Skip]
 * (e.g. system declarations filtered out by an include list).  By default those
 * warnings are suppressed to keep stderr readable when processing large SDK
 * headers such as Foundation.
 */
object KextractConfig {
    /** When false (default), warnings for Skip-marked declarations are suppressed. */
    var verbose: Boolean = false

    /**
     * When true, C `long`/`unsigned long` scalars are lowered to a fixed 64-bit carrier in
     * multiplatform C ABI contexts instead of being rejected as target-dependent width.
     *
     * Only valid when every generated target uses a 64-bit `long` data model (LP64), such as
     * macOS/Linux desktop and Android 64-bit. Do not enable for LLP64 or 32-bit targets.
     */
    var allow64BitScalars: Boolean = false
}
