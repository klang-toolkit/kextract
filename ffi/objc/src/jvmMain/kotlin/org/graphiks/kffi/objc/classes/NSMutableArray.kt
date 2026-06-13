package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSMutableArray
 * Superclass: NSArray
 */
open class NSMutableArray(override val ptr: MemorySegment) : NSArray(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSMutableArray") }
        
    }
    
    open fun addObject(anObject: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addObject:")
        ObjCRuntime.msgSend(null, ptr, sel, anObject)
    }
    
    open fun insertObject_atIndex(anObject: MemorySegment, index: Long): Unit {
        val sel = ObjCRuntime.sel("insertObject:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, anObject, index)
    }
    
    open fun removeLastObject(): Unit {
        val sel = ObjCRuntime.sel("removeLastObject")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun removeObjectAtIndex(index: Long): Unit {
        val sel = ObjCRuntime.sel("removeObjectAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, index)
    }
    
    open fun replaceObjectAtIndex_withObject(index: Long, anObject: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("replaceObjectAtIndex:withObject:")
        ObjCRuntime.msgSend(null, ptr, sel, index, anObject)
    }
    
    override fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithCapacity(numItems: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCapacity:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, numItems) as MemorySegment
    }
    
    override fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
}

// ── Category: NSExtendedMutableArray on NSMutableArray ─────────────────────────────────────────

fun NSMutableArray.addObjectsFromArray(otherArray: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("addObjectsFromArray:")
    ObjCRuntime.msgSend(null, this.ptr, sel, otherArray)
}

fun NSMutableArray.exchangeObjectAtIndex_withObjectAtIndex(idx1: Long, idx2: Long): Unit {
    val sel = ObjCRuntime.sel("exchangeObjectAtIndex:withObjectAtIndex:")
    ObjCRuntime.msgSend(null, this.ptr, sel, idx1, idx2)
}

fun NSMutableArray.removeAllObjects(): Unit {
    val sel = ObjCRuntime.sel("removeAllObjects")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSMutableArray.removeObject_inRange(anObject: MemorySegment, range: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeObject:inRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, anObject, range)
}

fun NSMutableArray.removeObject(anObject: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeObject:")
    ObjCRuntime.msgSend(null, this.ptr, sel, anObject)
}

fun NSMutableArray.removeObjectIdenticalTo_inRange(anObject: MemorySegment, range: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeObjectIdenticalTo:inRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, anObject, range)
}

fun NSMutableArray.removeObjectIdenticalTo(anObject: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeObjectIdenticalTo:")
    ObjCRuntime.msgSend(null, this.ptr, sel, anObject)
}

fun NSMutableArray.removeObjectsFromIndices_numIndices(indices: MemorySegment, cnt: Long): Unit {
    val sel = ObjCRuntime.sel("removeObjectsFromIndices:numIndices:")
    ObjCRuntime.msgSend(null, this.ptr, sel, indices, cnt)
}

fun NSMutableArray.removeObjectsInArray(otherArray: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeObjectsInArray:")
    ObjCRuntime.msgSend(null, this.ptr, sel, otherArray)
}

fun NSMutableArray.removeObjectsInRange(range: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeObjectsInRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, range)
}

fun NSMutableArray.replaceObjectsInRange_withObjectsFromArray_range(range: MemorySegment, otherArray: MemorySegment, otherRange: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("replaceObjectsInRange:withObjectsFromArray:range:")
    ObjCRuntime.msgSend(null, this.ptr, sel, range, otherArray, otherRange)
}

fun NSMutableArray.replaceObjectsInRange_withObjectsFromArray(range: MemorySegment, otherArray: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("replaceObjectsInRange:withObjectsFromArray:")
    ObjCRuntime.msgSend(null, this.ptr, sel, range, otherArray)
}

fun NSMutableArray.setArray(otherArray: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setArray:")
    ObjCRuntime.msgSend(null, this.ptr, sel, otherArray)
}

fun NSMutableArray.sortUsingFunction_context(compare: MemorySegment, context: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("sortUsingFunction:context:")
    ObjCRuntime.msgSend(null, this.ptr, sel, compare, context)
}

fun NSMutableArray.sortUsingSelector(comparator: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("sortUsingSelector:")
    ObjCRuntime.msgSend(null, this.ptr, sel, comparator)
}

fun NSMutableArray.insertObjects_atIndexes(objects: MemorySegment, indexes: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("insertObjects:atIndexes:")
    ObjCRuntime.msgSend(null, this.ptr, sel, objects, indexes)
}

fun NSMutableArray.removeObjectsAtIndexes(indexes: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeObjectsAtIndexes:")
    ObjCRuntime.msgSend(null, this.ptr, sel, indexes)
}

fun NSMutableArray.replaceObjectsAtIndexes_withObjects(indexes: MemorySegment, objects: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("replaceObjectsAtIndexes:withObjects:")
    ObjCRuntime.msgSend(null, this.ptr, sel, indexes, objects)
}

fun NSMutableArray.setObject_atIndexedSubscript(obj: MemorySegment, idx: Long): Unit {
    val sel = ObjCRuntime.sel("setObject:atIndexedSubscript:")
    ObjCRuntime.msgSend(null, this.ptr, sel, obj, idx)
}

fun NSMutableArray.sortUsingComparator(cmptr: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("sortUsingComparator:")
    ObjCRuntime.msgSend(null, this.ptr, sel, cmptr)
}

fun NSMutableArray.sortWithOptions_usingComparator(opts: MemorySegment, cmptr: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("sortWithOptions:usingComparator:")
    ObjCRuntime.msgSend(null, this.ptr, sel, opts, cmptr)
}

// ── Category: NSMutableArrayCreation on NSMutableArray ─────────────────────────────────────────

/** @return NSMutableArray<ObjectType> * */
fun NSMutableArray.initWithContentsOfFile(path: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithContentsOfFile:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, path) as MemorySegment
}

/** @return NSMutableArray<ObjectType> * */
fun NSMutableArray.initWithContentsOfURL(url: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithContentsOfURL:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, url) as MemorySegment
}

// Class method: +[NSMutableArray arrayWithCapacity:]
fun NSMutableArray_arrayWithCapacity(numItems: Long): MemorySegment {
    val sel = ObjCRuntime.sel("arrayWithCapacity:")
    val cls = ObjCRuntime.getClass("NSMutableArray")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, numItems) as MemorySegment
}

// Class method: +[NSMutableArray arrayWithContentsOfFile:]
fun NSMutableArray_arrayWithContentsOfFile(path: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("arrayWithContentsOfFile:")
    val cls = ObjCRuntime.getClass("NSMutableArray")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, path) as MemorySegment
}

// Class method: +[NSMutableArray arrayWithContentsOfURL:]
fun NSMutableArray_arrayWithContentsOfURL(url: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("arrayWithContentsOfURL:")
    val cls = ObjCRuntime.getClass("NSMutableArray")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, url) as MemorySegment
}

// ── Category: NSMutableArrayDiffing on NSMutableArray ─────────────────────────────────────────

fun NSMutableArray.applyDifference(difference: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("applyDifference:")
    ObjCRuntime.msgSend(null, this.ptr, sel, difference)
}

// ── Category: NSSortDescriptorSorting on NSMutableArray ─────────────────────────────────────────

fun NSMutableArray.sortUsingDescriptors(sortDescriptors: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("sortUsingDescriptors:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sortDescriptors)
}

// ── Category: NSPredicateSupport on NSMutableArray ─────────────────────────────────────────

fun NSMutableArray.filterUsingPredicate(predicate: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("filterUsingPredicate:")
    ObjCRuntime.msgSend(null, this.ptr, sel, predicate)
}

