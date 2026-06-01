//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[EventLoopProxy](index.md)

# EventLoopProxy

[common]\
interface [EventLoopProxy](index.md)

Thread-safe proxy to the main event loop.

Allows secondary threads to wake up the event loop without having direct access to it.

## Functions

| Name | Summary |
|---|---|
| [wakeUp](wake-up.md) | [common]<br>abstract fun [wakeUp](wake-up.md)()<br>Wakes up the event loop if it is waiting. |