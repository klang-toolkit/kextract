//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[rawWindowHandle](raw-window-handle.md)

# rawWindowHandle

[common]\
abstract val [rawWindowHandle](raw-window-handle.md): [Any](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-any/index.html)

Retourne le handle natif de la surface de rendu.

Le type concret sera `RawWindowHandle` une fois GRA-122 mergé ; déclaré `Any` pour que commonMain reste indépendant de la plateforme.