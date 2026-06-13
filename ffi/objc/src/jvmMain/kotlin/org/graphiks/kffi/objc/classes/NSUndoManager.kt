package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUndoManager
 * Superclass: NSObject
 */
open class NSUndoManager(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUndoManager") }
        
    }
    
    open fun beginUndoGrouping(): Unit {
        val sel = ObjCRuntime.sel("beginUndoGrouping")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun endUndoGrouping(): Unit {
        val sel = ObjCRuntime.sel("endUndoGrouping")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun disableUndoRegistration(): Unit {
        val sel = ObjCRuntime.sel("disableUndoRegistration")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun enableUndoRegistration(): Unit {
        val sel = ObjCRuntime.sel("enableUndoRegistration")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun undo(): Unit {
        val sel = ObjCRuntime.sel("undo")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun redo(): Unit {
        val sel = ObjCRuntime.sel("redo")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun undoNestedGroup(): Unit {
        val sel = ObjCRuntime.sel("undoNestedGroup")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun removeAllActions(): Unit {
        val sel = ObjCRuntime.sel("removeAllActions")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun removeAllActionsWithTarget(target: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeAllActionsWithTarget:")
        ObjCRuntime.msgSend(null, ptr, sel, target)
    }
    
    open fun registerUndoWithTarget_selector_object(target: MemorySegment, selector: MemorySegment, `object`: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("registerUndoWithTarget:selector:object:")
        ObjCRuntime.msgSend(null, ptr, sel, target, selector, `object`)
    }
    
    open fun prepareWithInvocationTarget(target: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("prepareWithInvocationTarget:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, target) as MemorySegment
    }
    
    open fun registerUndoWithTarget_handler(target: MemorySegment, undoHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("registerUndoWithTarget:handler:")
        ObjCRuntime.msgSend(null, ptr, sel, target, undoHandler)
    }
    
    open fun setActionIsDiscardable(discardable: Boolean): Unit {
        val sel = ObjCRuntime.sel("setActionIsDiscardable:")
        ObjCRuntime.msgSend(null, ptr, sel, discardable)
    }
    
    open fun setActionName(actionName: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setActionName:")
        ObjCRuntime.msgSend(null, ptr, sel, actionName)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setActionName(actionName: String): Unit = setActionName(ObjCRuntime.newNSString(Arena.global(), actionName))
    
    open fun undoActionUserInfoValueForKey(key: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("undoActionUserInfoValueForKey:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, key) as MemorySegment
    }
    
    open fun redoActionUserInfoValueForKey(key: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("redoActionUserInfoValueForKey:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, key) as MemorySegment
    }
    
    open fun setActionUserInfoValue_forKey(info: MemorySegment, key: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setActionUserInfoValue:forKey:")
        ObjCRuntime.msgSend(null, ptr, sel, info, key)
    }
    
    open fun undoMenuTitleForUndoActionName(actionName: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("undoMenuTitleForUndoActionName:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, actionName) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun undoMenuTitleForUndoActionNameAsString(actionName: MemorySegment): String = ObjCRuntime.toJavaString(undoMenuTitleForUndoActionName(actionName))
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun undoMenuTitleForUndoActionName(actionName: String): MemorySegment = undoMenuTitleForUndoActionName(ObjCRuntime.newNSString(Arena.global(), actionName))
    
    /** Convenience overload — [String] parameters and [String] return type. */
    fun undoMenuTitleForUndoActionNameAsString(actionName: String): String = ObjCRuntime.toJavaString(undoMenuTitleForUndoActionName(ObjCRuntime.newNSString(Arena.global(), actionName)))
    
    open fun redoMenuTitleForUndoActionName(actionName: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("redoMenuTitleForUndoActionName:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, actionName) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun redoMenuTitleForUndoActionNameAsString(actionName: MemorySegment): String = ObjCRuntime.toJavaString(redoMenuTitleForUndoActionName(actionName))
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun redoMenuTitleForUndoActionName(actionName: String): MemorySegment = redoMenuTitleForUndoActionName(ObjCRuntime.newNSString(Arena.global(), actionName))
    
    /** Convenience overload — [String] parameters and [String] return type. */
    fun redoMenuTitleForUndoActionNameAsString(actionName: String): String = ObjCRuntime.toJavaString(redoMenuTitleForUndoActionName(ObjCRuntime.newNSString(Arena.global(), actionName)))
    
    // @property groupingLevel
    open fun groupingLevel(): Long {
        val sel = ObjCRuntime.sel("groupingLevel")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property undoRegistrationEnabled
    open fun isUndoRegistrationEnabled(): Boolean {
        val sel = ObjCRuntime.sel("isUndoRegistrationEnabled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property groupsByEvent
    open fun groupsByEvent(): Boolean {
        val sel = ObjCRuntime.sel("groupsByEvent")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setGroupsByEvent(value: Boolean) {
        val sel = ObjCRuntime.sel("setGroupsByEvent:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property levelsOfUndo
    open fun levelsOfUndo(): Long {
        val sel = ObjCRuntime.sel("levelsOfUndo")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setLevelsOfUndo(value: Long) {
        val sel = ObjCRuntime.sel("setLevelsOfUndo:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property runLoopModes
    /** @return NSArray<NSRunLoopMode> * */
    open fun runLoopModes(): MemorySegment {
        val sel = ObjCRuntime.sel("runLoopModes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setRunLoopModes(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setRunLoopModes:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property canUndo
    open fun canUndo(): Boolean {
        val sel = ObjCRuntime.sel("canUndo")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property canRedo
    open fun canRedo(): Boolean {
        val sel = ObjCRuntime.sel("canRedo")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property undoCount
    open fun undoCount(): Long {
        val sel = ObjCRuntime.sel("undoCount")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property redoCount
    open fun redoCount(): Long {
        val sel = ObjCRuntime.sel("redoCount")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property undoing
    open fun isUndoing(): Boolean {
        val sel = ObjCRuntime.sel("isUndoing")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property redoing
    open fun isRedoing(): Boolean {
        val sel = ObjCRuntime.sel("isRedoing")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property undoActionIsDiscardable
    open fun undoActionIsDiscardable(): Boolean {
        val sel = ObjCRuntime.sel("undoActionIsDiscardable")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property redoActionIsDiscardable
    open fun redoActionIsDiscardable(): Boolean {
        val sel = ObjCRuntime.sel("redoActionIsDiscardable")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property undoActionName
    open fun undoActionName(): MemorySegment {
        val sel = ObjCRuntime.sel("undoActionName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun undoActionNameAsString(): String = ObjCRuntime.toJavaString(undoActionName())
    
    // @property redoActionName
    open fun redoActionName(): MemorySegment {
        val sel = ObjCRuntime.sel("redoActionName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun redoActionNameAsString(): String = ObjCRuntime.toJavaString(redoActionName())
    
    // @property undoMenuItemTitle
    open fun undoMenuItemTitle(): MemorySegment {
        val sel = ObjCRuntime.sel("undoMenuItemTitle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun undoMenuItemTitleAsString(): String = ObjCRuntime.toJavaString(undoMenuItemTitle())
    
    // @property redoMenuItemTitle
    open fun redoMenuItemTitle(): MemorySegment {
        val sel = ObjCRuntime.sel("redoMenuItemTitle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun redoMenuItemTitleAsString(): String = ObjCRuntime.toJavaString(redoMenuItemTitle())
    
}

