package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSIndexSet
 * Superclass: NSObject
 * Protocols: NSCopying, NSMutableCopying, NSSecureCoding
 */
open class NSIndexSet(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSIndexSet") }
        
        fun indexSet(): MemorySegment {
            val sel = ObjCRuntime.sel("indexSet")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun indexSetWithIndex(value: Long): MemorySegment {
            val sel = ObjCRuntime.sel("indexSetWithIndex:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, value) as MemorySegment
        }
        
        fun indexSetWithIndexesInRange(range: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("indexSetWithIndexesInRange:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"))) as MemorySegment
        }
        
    }
    
    open fun initWithIndexesInRange(range: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithIndexesInRange:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"))) as MemorySegment
    }
    
    open fun initWithIndexSet(indexSet: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithIndexSet:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, indexSet) as MemorySegment
    }
    
    open fun initWithIndex(value: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithIndex:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, value) as MemorySegment
    }
    
    open fun isEqualToIndexSet(indexSet: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isEqualToIndexSet:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, indexSet) as Boolean
    }
    
    open fun indexGreaterThanIndex(value: Long): Long {
        val sel = ObjCRuntime.sel("indexGreaterThanIndex:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, value) as Long
    }
    
    open fun indexLessThanIndex(value: Long): Long {
        val sel = ObjCRuntime.sel("indexLessThanIndex:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, value) as Long
    }
    
    open fun indexGreaterThanOrEqualToIndex(value: Long): Long {
        val sel = ObjCRuntime.sel("indexGreaterThanOrEqualToIndex:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, value) as Long
    }
    
    open fun indexLessThanOrEqualToIndex(value: Long): Long {
        val sel = ObjCRuntime.sel("indexLessThanOrEqualToIndex:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, value) as Long
    }
    
    open fun getIndexes_maxCount_inIndexRange(indexBuffer: MemorySegment, bufferSize: Long, range: MemorySegment): Long {
        val sel = ObjCRuntime.sel("getIndexes:maxCount:inIndexRange:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, indexBuffer, bufferSize, range) as Long
    }
    
    open fun countOfIndexesInRange(range: MemorySegment): Long {
        val sel = ObjCRuntime.sel("countOfIndexesInRange:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"))) as Long
    }
    
    open fun containsIndex(value: Long): Boolean {
        val sel = ObjCRuntime.sel("containsIndex:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, value) as Boolean
    }
    
    open fun containsIndexesInRange(range: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("containsIndexesInRange:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"))) as Boolean
    }
    
    open fun containsIndexes(indexSet: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("containsIndexes:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, indexSet) as Boolean
    }
    
    open fun intersectsIndexesInRange(range: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("intersectsIndexesInRange:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"))) as Boolean
    }
    
    open fun enumerateIndexesUsingBlock(block: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("enumerateIndexesUsingBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, block)
    }
    
    open fun enumerateIndexesWithOptions_usingBlock(opts: MemorySegment, block: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("enumerateIndexesWithOptions:usingBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, opts, block)
    }
    
    open fun enumerateIndexesInRange_options_usingBlock(range: MemorySegment, opts: MemorySegment, block: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("enumerateIndexesInRange:options:usingBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), opts, block)
    }
    
    open fun indexPassingTest(predicate: MemorySegment): Long {
        val sel = ObjCRuntime.sel("indexPassingTest:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, predicate) as Long
    }
    
    open fun indexWithOptions_passingTest(opts: MemorySegment, predicate: MemorySegment): Long {
        val sel = ObjCRuntime.sel("indexWithOptions:passingTest:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, opts, predicate) as Long
    }
    
    open fun indexInRange_options_passingTest(range: MemorySegment, opts: MemorySegment, predicate: MemorySegment): Long {
        val sel = ObjCRuntime.sel("indexInRange:options:passingTest:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), opts, predicate) as Long
    }
    
    open fun indexesPassingTest(predicate: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("indexesPassingTest:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, predicate) as MemorySegment
    }
    
    open fun indexesWithOptions_passingTest(opts: MemorySegment, predicate: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("indexesWithOptions:passingTest:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, opts, predicate) as MemorySegment
    }
    
    open fun indexesInRange_options_passingTest(range: MemorySegment, opts: MemorySegment, predicate: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("indexesInRange:options:passingTest:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), opts, predicate) as MemorySegment
    }
    
    open fun enumerateRangesUsingBlock(block: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("enumerateRangesUsingBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, block)
    }
    
    open fun enumerateRangesWithOptions_usingBlock(opts: MemorySegment, block: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("enumerateRangesWithOptions:usingBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, opts, block)
    }
    
    open fun enumerateRangesInRange_options_usingBlock(range: MemorySegment, opts: MemorySegment, block: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("enumerateRangesInRange:options:usingBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), opts, block)
    }
    
    // @property count
    open fun count(): Long {
        val sel = ObjCRuntime.sel("count")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property firstIndex
    open fun firstIndex(): Long {
        val sel = ObjCRuntime.sel("firstIndex")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property lastIndex
    open fun lastIndex(): Long {
        val sel = ObjCRuntime.sel("lastIndex")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _indexSetFlags: MemorySegment
    // ivar: _internal: MemorySegment
}

