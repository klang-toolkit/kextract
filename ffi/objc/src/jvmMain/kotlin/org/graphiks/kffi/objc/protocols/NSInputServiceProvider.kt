package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSInputServiceProvider
 */
interface NSInputServiceProvider {
    fun insertText_client(string: MemorySegment, sender: MemorySegment): Unit
    
    fun doCommandBySelector_client(selector: MemorySegment, sender: MemorySegment): Unit
    
    fun markedTextAbandoned(sender: MemorySegment): Unit
    
    fun markedTextSelectionChanged_client(newSel: MemorySegment, sender: MemorySegment): Unit
    
    fun terminate(sender: MemorySegment): Unit
    
    fun canBeDisabled(): Boolean
    
    fun wantsToInterpretAllKeystrokes(): Boolean
    
    fun wantsToHandleMouseEvents(): Boolean
    
    fun wantsToDelayTextChangeNotifications(): Boolean
    
    fun inputClientBecomeActive(sender: MemorySegment): Unit
    
    fun inputClientResignActive(sender: MemorySegment): Unit
    
    fun inputClientEnabled(sender: MemorySegment): Unit
    
    fun inputClientDisabled(sender: MemorySegment): Unit
    
    fun activeConversationWillChange_fromOldConversation(sender: MemorySegment, oldConversation: Long): Unit
    
    fun activeConversationChanged_toNewConversation(sender: MemorySegment, newConversation: Long): Unit
    
}

