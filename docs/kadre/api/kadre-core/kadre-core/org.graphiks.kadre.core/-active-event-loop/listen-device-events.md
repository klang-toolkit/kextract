//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[ActiveEventLoop](index.md)/[listenDeviceEvents](listen-device-events.md)

# listenDeviceEvents

[common]\
abstract fun [listenDeviceEvents](listen-device-events.md)(mode: [DeviceEvents](../-device-events/index.md))

Controls which raw [DeviceEvent](../-device-event/index.md)s are dispatched to [ApplicationHandler.deviceEvent](../-application-handler/device-event.md).

| Mode | Behaviour |
|---|---|
| [DeviceEvents.Always](../-device-events/-always/index.md) | Events are dispatched regardless of window focus. |
| [DeviceEvents.WhenFocused](../-device-events/-when-focused/index.md) | Events are dispatched only while a window has focus (default). |
| [DeviceEvents.Never](../-device-events/-never/index.md) | No device events are dispatched. |

Backends that do not distinguish focus-filtered device events treat [DeviceEvents.WhenFocused](../-device-events/-when-focused/index.md) as [DeviceEvents.Always](../-device-events/-always/index.md) (documented no-op difference). Never throws.

#### Parameters

common

| | |
|---|---|
| mode | New filter mode. |