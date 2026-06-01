//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[WindowEvent](../index.md)/[ModifiersChanged](index.md)

# ModifiersChanged

[common]\
data class [ModifiersChanged](index.md)(val modifiers: [Modifiers](../../-modifiers/index.md)) : [WindowEvent](../index.md)

The set of active keyboard modifiers changed.

Emitted when a modifier key (Shift, Ctrl, Alt, Meta) is pressed or released. Backends emit this on a best-effort basis:

- 
   win32   : WM_KEYDOWN / WM_KEYUP on VK_SHIFT/CONTROL/MENU/WIN
- 
   web     : keydown / keyup when `KeyboardEvent.key` is a modifier
- 
   x11     : XkbStateNotify (TODO — not yet wired)
- 
   wayland : wl_keyboard.modifiers (TODO — not yet wired)
- 
   appkit  : NSEventTypeKeyDown / NSEventTypeKeyUp on modifier key codes
- 
   android : onKeyDown/Up for KEYCODE_SHIFT_* etc. (TODO — not yet wired)
- 
   uikit   : pressesBegan/Ended on modifier keys (TODO — not yet wired)

## Constructors

| | |
|---|---|
| [ModifiersChanged](-modifiers-changed.md) | [common]<br>constructor(modifiers: [Modifiers](../../-modifiers/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [modifiers](modifiers.md) | [common]<br>val [modifiers](modifiers.md): [Modifiers](../../-modifiers/index.md)<br>New modifier state after the change. |