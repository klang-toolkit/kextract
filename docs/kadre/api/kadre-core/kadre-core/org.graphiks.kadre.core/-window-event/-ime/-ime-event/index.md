//[kadre-core](../../../../../index.md)/[org.graphiks.kadre.core](../../../index.md)/[WindowEvent](../../index.md)/[Ime](../index.md)/[ImeEvent](index.md)

# ImeEvent

sealed interface [ImeEvent](index.md)

Sub-events of the IME pipeline.

The typical lifecycle is:

1.
   [Enabled](-enabled/index.md)  — the IME context was activated (e.g. focus entered a text field).
2.
   [Preedit](-preedit/index.md)  — intermediate composed text (shown with underline in most UIs).
3.
   [Commit](-commit/index.md)   — the final string to insert into the text buffer.
4.
   [Disabled](-disabled/index.md) — the IME context was deactivated.

[DeleteSurrounding](-delete-surrounding/index.md) may be emitted at any point to request deletion of text around the cursor (needed by some CJK / prediction engines).

#### Inheritors

| |
|---|
| [Enabled](-enabled/index.md) |
| [Preedit](-preedit/index.md) |
| [Commit](-commit/index.md) |
| [DeleteSurrounding](-delete-surrounding/index.md) |
| [Disabled](-disabled/index.md) |

## Types

| Name | Summary |
|---|---|
| [Commit](-commit/index.md) | [common]<br>data class [Commit](-commit/index.md)(val text: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)) : [WindowEvent.Ime.ImeEvent](index.md)<br>The IME committed a final string. |
| [DeleteSurrounding](-delete-surrounding/index.md) | [common]<br>data class [DeleteSurrounding](-delete-surrounding/index.md)(val beforeBytes: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), val afterBytes: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)) : [WindowEvent.Ime.ImeEvent](index.md)<br>The IME requests deletion of surrounding text. |
| [Disabled](-disabled/index.md) | [common]<br>data object [Disabled](-disabled/index.md) : [WindowEvent.Ime.ImeEvent](index.md)<br>The IME context was deactivated for this window. |
| [Enabled](-enabled/index.md) | [common]<br>data object [Enabled](-enabled/index.md) : [WindowEvent.Ime.ImeEvent](index.md)<br>The IME context was activated for this window. |
| [Preedit](-preedit/index.md) | [common]<br>data class [Preedit](-preedit/index.md)(val text: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html), val cursorRange: [Pair](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-pair/index.html)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;?) : [WindowEvent.Ime.ImeEvent](index.md)<br>The IME is composing text (pre-edit string). |