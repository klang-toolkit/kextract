package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSMutableOrderedSet
 * Superclass: NSOrderedSet
 */
open class NSMutableOrderedSet(override val ptr: MemorySegment) : NSOrderedSet(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSMutableOrderedSet") }
        
    }
    
    open fun insertObject_atIndex(`object`: MemorySegment, idx: Long): Unit {
        val sel = ObjCRuntime.sel("insertObject:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, `object`, idx)
    }
    
    open fun removeObjectAtIndex(idx: Long): Unit {
        val sel = ObjCRuntime.sel("removeObjectAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, idx)
    }
    
    open fun replaceObjectAtIndex_withObject(idx: Long, `object`: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("replaceObjectAtIndex:withObject:")
        ObjCRuntime.msgSend(null, ptr, sel, idx, `object`)
    }
    
    override fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    override fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithCapacity(numItems: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCapacity:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, numItems) as MemorySegment
    }
    
}

// ── Category: NSExtendedMutableOrderedSet on NSMutableOrderedSet ─────────────────────────────────────────

fun NSMutableOrderedSet.addObject(`object`: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("addObject:")
    ObjCRuntime.msgSend(null, this.ptr, sel, `object`)
}

fun NSMutableOrderedSet.addObjects_count(objects: MemorySegment, count: Long): Unit {
    val sel = ObjCRuntime.sel("addObjects:count:")
    ObjCRuntime.msgSend(null, this.ptr, sel, objects, count)
}

fun NSMutableOrderedSet.addObjectsFromArray(array: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("addObjectsFromArray:")
    ObjCRuntime.msgSend(null, this.ptr, sel, array)
}

fun NSMutableOrderedSet.exchangeObjectAtIndex_withObjectAtIndex(idx1: Long, idx2: Long): Unit {
    val sel = ObjCRuntime.sel("exchangeObjectAtIndex:withObjectAtIndex:")
    ObjCRuntime.msgSend(null, this.ptr, sel, idx1, idx2)
}

fun NSMutableOrderedSet.moveObjectsAtIndexes_toIndex(indexes: MemorySegment, idx: Long): Unit {
    val sel = ObjCRuntime.sel("moveObjectsAtIndexes:toIndex:")
    ObjCRuntime.msgSend(null, this.ptr, sel, indexes, idx)
}

fun NSMutableOrderedSet.insertObjects_atIndexes(objects: MemorySegment, indexes: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("insertObjects:atIndexes:")
    ObjCRuntime.msgSend(null, this.ptr, sel, objects, indexes)
}

fun NSMutableOrderedSet.setObject_atIndex(obj: MemorySegment, idx: Long): Unit {
    val sel = ObjCRuntime.sel("setObject:atIndex:")
    ObjCRuntime.msgSend(null, this.ptr, sel, obj, idx)
}

fun NSMutableOrderedSet.setObject_atIndexedSubscript(obj: MemorySegment, idx: Long): Unit {
    val sel = ObjCRuntime.sel("setObject:atIndexedSubscript:")
    ObjCRuntime.msgSend(null, this.ptr, sel, obj, idx)
}

fun NSMutableOrderedSet.replaceObjectsInRange_withObjects_count(range: MemorySegment, objects: MemorySegment, count: Long): Unit {
    val sel = ObjCRuntime.sel("replaceObjectsInRange:withObjects:count:")
    ObjCRuntime.msgSend(null, this.ptr, sel, range, objects, count)
}

fun NSMutableOrderedSet.replaceObjectsAtIndexes_withObjects(indexes: MemorySegment, objects: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("replaceObjectsAtIndexes:withObjects:")
    ObjCRuntime.msgSend(null, this.ptr, sel, indexes, objects)
}

fun NSMutableOrderedSet.removeObjectsInRange(range: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeObjectsInRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, range)
}

fun NSMutableOrderedSet.removeObjectsAtIndexes(indexes: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeObjectsAtIndexes:")
    ObjCRuntime.msgSend(null, this.ptr, sel, indexes)
}

fun NSMutableOrderedSet.removeAllObjects(): Unit {
    val sel = ObjCRuntime.sel("removeAllObjects")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSMutableOrderedSet.removeObject(`object`: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeObject:")
    ObjCRuntime.msgSend(null, this.ptr, sel, `object`)
}

fun NSMutableOrderedSet.removeObjectsInArray(array: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeObjectsInArray:")
    ObjCRuntime.msgSend(null, this.ptr, sel, array)
}

fun NSMutableOrderedSet.intersectOrderedSet(other: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("intersectOrderedSet:")
    ObjCRuntime.msgSend(null, this.ptr, sel, other)
}

fun NSMutableOrderedSet.minusOrderedSet(other: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("minusOrderedSet:")
    ObjCRuntime.msgSend(null, this.ptr, sel, other)
}

fun NSMutableOrderedSet.unionOrderedSet(other: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("unionOrderedSet:")
    ObjCRuntime.msgSend(null, this.ptr, sel, other)
}

fun NSMutableOrderedSet.intersectSet(other: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("intersectSet:")
    ObjCRuntime.msgSend(null, this.ptr, sel, other)
}

fun NSMutableOrderedSet.minusSet(other: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("minusSet:")
    ObjCRuntime.msgSend(null, this.ptr, sel, other)
}

fun NSMutableOrderedSet.unionSet(other: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("unionSet:")
    ObjCRuntime.msgSend(null, this.ptr, sel, other)
}

fun NSMutableOrderedSet.sortUsingComparator(cmptr: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("sortUsingComparator:")
    ObjCRuntime.msgSend(null, this.ptr, sel, cmptr)
}

fun NSMutableOrderedSet.sortWithOptions_usingComparator(opts: MemorySegment, cmptr: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("sortWithOptions:usingComparator:")
    ObjCRuntime.msgSend(null, this.ptr, sel, opts, cmptr)
}

fun NSMutableOrderedSet.sortRange_options_usingComparator(range: MemorySegment, opts: MemorySegment, cmptr: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("sortRange:options:usingComparator:")
    ObjCRuntime.msgSend(null, this.ptr, sel, range, opts, cmptr)
}

// ── Category: NSMutableOrderedSetCreation on NSMutableOrderedSet ─────────────────────────────────────────

// Class method: +[NSMutableOrderedSet orderedSetWithCapacity:]
fun NSMutableOrderedSet_orderedSetWithCapacity(numItems: Long): MemorySegment {
    val sel = ObjCRuntime.sel("orderedSetWithCapacity:")
    val cls = ObjCRuntime.getClass("NSMutableOrderedSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, numItems) as MemorySegment
}

// ── Category: NSMutableOrderedSetDiffing on NSMutableOrderedSet ─────────────────────────────────────────

fun NSMutableOrderedSet.applyDifference(difference: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("applyDifference:")
    ObjCRuntime.msgSend(null, this.ptr, sel, difference)
}

// ── Category: NSKeyValueSorting on NSMutableOrderedSet ─────────────────────────────────────────

fun NSMutableOrderedSet.sortUsingDescriptors(sortDescriptors: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("sortUsingDescriptors:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sortDescriptors)
}

// ── Category: NSPredicateSupport on NSMutableOrderedSet ─────────────────────────────────────────

fun NSMutableOrderedSet.filterUsingPredicate(p: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("filterUsingPredicate:")
    ObjCRuntime.msgSend(null, this.ptr, sel, p)
}

