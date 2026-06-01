//[kadre-core](../../../../../../index.md)/[org.graphiks.kadre.core](../../../../index.md)/[WindowEvent](../../../index.md)/[Ime](../../index.md)/[ImeEvent](../index.md)/[Preedit](index.md)

# Preedit

[common]\
data class [Preedit](index.md)(val text: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html), val cursorRange: [Pair](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-pair/index.html)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;?) : [WindowEvent.Ime.ImeEvent](../index.md)

The IME is composing text (pre-edit string).

The application should display text with a visual indicator (underline, highlight) at the current cursor position. When text is empty the pre-edit string is cleared.

## Constructors

| | |
|---|---|
| [Preedit](-preedit.md) | [common]<br>constructor(text: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html), cursorRange: [Pair](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-pair/index.html)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;?) |

## Properties

| Name | Summary |
|---|---|
| [cursorRange](cursor-range.md) | [common]<br>val [cursorRange](cursor-range.md): [Pair](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-pair/index.html)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;?<br>Byte range `[start, end)` within text where the IME cursor / selection sits, or null if the IME does not expose it. |
| [text](text.md) | [common]<br>val [text](text.md): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)<br>The current pre-edit string (may be empty). |