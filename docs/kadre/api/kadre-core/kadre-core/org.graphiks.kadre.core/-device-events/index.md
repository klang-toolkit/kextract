//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[DeviceEvents](index.md)

# DeviceEvents

[common]\
enum [DeviceEvents](index.md) : [Enum](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-enum/index.html)&lt;[DeviceEvents](index.md)&gt;

Controls which raw [DeviceEvent](../-device-event/index.md)s the event loop dispatches to the application.

Passed to [ActiveEventLoop.listenDeviceEvents](../-active-event-loop/listen-device-events.md).

#### Since

R4

## Entries

| | |
|---|---|
| [Always](-always/index.md) | [common]<br>[Always](-always/index.md)<br>Device events are dispatched unconditionally, even when no application window has focus (e.g. for FPS-style raw mouse input). |
| [WhenFocused](-when-focused/index.md) | [common]<br>[WhenFocused](-when-focused/index.md)<br>Device events are dispatched only while at least one application window has keyboard focus. This is the default behavior. |
| [Never](-never/index.md) | [common]<br>[Never](-never/index.md)<br>No device events are dispatched. Useful to suppress raw input entirely. |

## Properties

| Name | Summary |
|---|---|
| [entries](entries.md) | [common]<br>val [entries](entries.md): [EnumEntries](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.enums/-enum-entries/index.html)&lt;[DeviceEvents](index.md)&gt; |
| [name](../-ime-purpose/-terminal/index.md#-372974862%2FProperties%2F-959609056) | [common]<br>expect val [name](../-ime-purpose/-terminal/index.md#-372974862%2FProperties%2F-959609056): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html) |
| [ordinal](../-ime-purpose/-terminal/index.md#-739389684%2FProperties%2F-959609056) | [common]<br>expect val [ordinal](../-ime-purpose/-terminal/index.md#-739389684%2FProperties%2F-959609056): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) |

## Functions

| Name | Summary |
|---|---|
| [valueOf](value-of.md) | [common]<br>fun [valueOf](value-of.md)(value: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)): [DeviceEvents](index.md) |
| [values](values.md) | [common]<br>fun [values](values.md)(): [Array](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-array/index.html)&lt;[DeviceEvents](index.md)&gt; |