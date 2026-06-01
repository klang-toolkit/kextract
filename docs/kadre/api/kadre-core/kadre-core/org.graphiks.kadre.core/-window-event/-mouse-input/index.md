//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[WindowEvent](../index.md)/[MouseInput](index.md)

# MouseInput

[common]\
data class [MouseInput](index.md)(val button: [MouseButton](../../-mouse-button/index.md), val state: [KeyState](../../-key-state/index.md)) : [WindowEvent](../index.md)

A mouse button has been pressed or released.

## Constructors

| | |
|---|---|
| [MouseInput](-mouse-input.md) | [common]<br>constructor(button: [MouseButton](../../-mouse-button/index.md), state: [KeyState](../../-key-state/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [button](button.md) | [common]<br>val [button](button.md): [MouseButton](../../-mouse-button/index.md)<br>Button involved. |
| [state](state.md) | [common]<br>val [state](state.md): [KeyState](../../-key-state/index.md)<br>Button state ([KeyState.Pressed](../../-key-state/-pressed/index.md) or [KeyState.Released](../../-key-state/-released/index.md)). |