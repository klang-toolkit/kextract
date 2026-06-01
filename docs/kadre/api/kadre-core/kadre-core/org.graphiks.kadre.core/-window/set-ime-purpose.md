//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[setImePurpose](set-ime-purpose.md)

# setImePurpose

[common]\
open fun [setImePurpose](set-ime-purpose.md)(purpose: [ImePurpose](../-ime-purpose/index.md))

Hints the IME about the intended purpose of the focused text field.

Allows the input method to adapt its behaviour (e.g. hide suggestions for a terminal, mask characters for a password field).

Default implementation is a no-op — backends that support IME will override. TODO R5-IME: wire in each backend.

#### Parameters

common

| | |
|---|---|
| purpose | Intended use of the text field. |

#### See also

| |
|---|
| [ImePurpose](../-ime-purpose/index.md) |