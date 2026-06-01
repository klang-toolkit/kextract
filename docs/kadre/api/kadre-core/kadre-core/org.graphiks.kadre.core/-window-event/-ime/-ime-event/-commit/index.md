//[kadre-core](../../../../../../index.md)/[org.graphiks.kadre.core](../../../../index.md)/[WindowEvent](../../../index.md)/[Ime](../../index.md)/[ImeEvent](../index.md)/[Commit](index.md)

# Commit

[common]\
data class [Commit](index.md)(val text: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)) : [WindowEvent.Ime.ImeEvent](../index.md)

The IME committed a final string.

The application should insert text into its text buffer at the cursor position, replacing any active pre-edit string.

## Constructors

| | |
|---|---|
| [Commit](-commit.md) | [common]<br>constructor(text: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [text](text.md) | [common]<br>val [text](text.md): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)<br>The committed text to insert. |