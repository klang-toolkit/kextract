//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[FrameTimingTracer](index.md)

# FrameTimingTracer

[common]\
object [FrameTimingTracer](index.md)

## Properties

| Name | Summary |
|---|---|
| [enabled](enabled.md) | [common]<br>var [enabled](enabled.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>Enables/disables tracing. `false` = 0 overhead (guards at the top of each method). |
| [sink](sink.md) | [common]<br>var [sink](sink.md): ([String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)) -&gt; [Unit](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-unit/index.html)<br>Sink of log lines — overridable in tests. Default: standard output. |
| [slowFrameThresholdMs](slow-frame-threshold-ms.md) | [common]<br>var [slowFrameThresholdMs](slow-frame-threshold-ms.md): [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)<br>Threshold above which a slow frame is logged individually (ms). |

## Functions

| Name | Summary |
|---|---|
| [flush](flush.md) | [common]<br>fun [flush](flush.md)()<br>Forces immediate publication of the current statistics (useful in tests). |
| [onPresentEnd](on-present-end.md) | [common]<br>fun [onPresentEnd](on-present-end.md)()<br>Marks the end of presentation of a frame. Computes the duration since [onRedrawStart](on-redraw-start.md), accumulates it, logs it if slow, and publishes the aggregated stats every ~1 s. |
| [onRedrawStart](on-redraw-start.md) | [common]<br>fun [onRedrawStart](on-redraw-start.md)()<br>Marks the start of a frame (reception of RedrawRequested). |
| [reset](reset.md) | [common]<br>fun [reset](reset.md)()<br>Resets the internal state (useful in tests). |