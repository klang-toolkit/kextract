package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSSoundDelegate
 * Inherits protocols: NSObject
 */
interface NSSoundDelegate {
    // @optional
    fun sound_didFinishPlaying(sound: MemorySegment, flag: Boolean): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'sound:didFinishPlaying:' not implemented")
    
}

