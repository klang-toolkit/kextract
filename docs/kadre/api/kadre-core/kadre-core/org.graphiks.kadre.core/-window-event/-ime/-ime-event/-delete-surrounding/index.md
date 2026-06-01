//[kadre-core](../../../../../../index.md)/[org.graphiks.kadre.core](../../../../index.md)/[WindowEvent](../../../index.md)/[Ime](../../index.md)/[ImeEvent](../index.md)/[DeleteSurrounding](index.md)

# DeleteSurrounding

[common]\
data class [DeleteSurrounding](index.md)(val beforeBytes: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), val afterBytes: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)) : [WindowEvent.Ime.ImeEvent](../index.md)

The IME requests deletion of surrounding text.

The application should delete beforeBytes bytes before the cursor and afterBytes bytes after the cursor (byte offsets in UTF-8).

## Constructors

| | |
|---|---|
| [DeleteSurrounding](-delete-surrounding.md) | [common]<br>constructor(beforeBytes: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), afterBytes: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [afterBytes](after-bytes.md) | [common]<br>val [afterBytes](after-bytes.md): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)<br>Bytes to delete after the cursor (≥ 0). |
| [beforeBytes](before-bytes.md) | [common]<br>val [beforeBytes](before-bytes.md): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)<br>Bytes to delete before the cursor (≥ 0). |