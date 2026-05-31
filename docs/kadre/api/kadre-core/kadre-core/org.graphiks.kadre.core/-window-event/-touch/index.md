//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[WindowEvent](../index.md)/[Touch](index.md)

# Touch

[common]\
data class [Touch](index.md)(val phase: [TouchPhase](../../-touch-phase/index.md), val location: [PhysicalPosition](../../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;, val id: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) : [WindowEvent](../index.md)

Un contact tactile a changé d'état.

## Constructors

| | |
|---|---|
| [Touch](-touch.md) | [common]<br>constructor(phase: [TouchPhase](../../-touch-phase/index.md), location: [PhysicalPosition](../../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;, id: [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [id](id.md) | [common]<br>val [id](id.md): [Long](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-long/index.html)<br>Identifiant unique du contact (stable entre [TouchPhase.Started](../../-touch-phase/-started/index.md) et [TouchPhase.Ended](../../-touch-phase/-ended/index.md)/[TouchPhase.Cancelled](../../-touch-phase/-cancelled/index.md)). |
| [location](location.md) | [common]<br>val [location](location.md): [PhysicalPosition](../../-physical-position/index.md)&lt;[Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)&gt;<br>Position du contact en pixels physiques. |
| [phase](phase.md) | [common]<br>val [phase](phase.md): [TouchPhase](../../-touch-phase/index.md)<br>Phase du contact. |