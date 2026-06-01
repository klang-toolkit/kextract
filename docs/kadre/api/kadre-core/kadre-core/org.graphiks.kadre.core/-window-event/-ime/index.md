//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[WindowEvent](../index.md)/[Ime](index.md)

# Ime

data class [Ime](index.md)(val ime: [WindowEvent.Ime.ImeEvent](-ime-event/index.md)) : [WindowEvent](../index.md)

An IME (Input Method Editor) event occurred on this window.

IME events are emitted on platforms that expose an input method pipeline (Wayland via `zwp_text_input_v3`, X11 via XIC, Win32 via TSF/IMM32, Android via `InputMethodManager`, iOS/macOS via `NSTextInputClient`).

Emission of these events is out of scope for R5-IME — backends will be wired in later milestones. Use [Window.setImeAllowed](../../-window/set-ime-allowed.md) to opt in.

#### See also

| |
|---|
| [ImeEvent](-ime-event/index.md) |

## Constructors

| | |
|---|---|
| [Ime](-ime.md) | [common]<br>constructor(ime: [WindowEvent.Ime.ImeEvent](-ime-event/index.md)) |

## Types

| Name | Summary |
|---|---|
| [ImeEvent](-ime-event/index.md) | [common]<br>sealed interface [ImeEvent](-ime-event/index.md)<br>Sub-events of the IME pipeline. |

## Properties

| Name | Summary |
|---|---|
| [ime](ime.md) | [common]<br>val [ime](ime.md): [WindowEvent.Ime.ImeEvent](-ime-event/index.md)<br>The concrete IME event sub-type. |