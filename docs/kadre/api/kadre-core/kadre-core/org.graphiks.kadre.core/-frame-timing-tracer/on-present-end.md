//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[FrameTimingTracer](index.md)/[onPresentEnd](on-present-end.md)

# onPresentEnd

[common]\
fun [onPresentEnd](on-present-end.md)()

Marks the end of presentation of a frame. Computes the duration since [onRedrawStart](on-redraw-start.md), accumulates it, logs it if slow, and publishes the aggregated stats every ~1 s.