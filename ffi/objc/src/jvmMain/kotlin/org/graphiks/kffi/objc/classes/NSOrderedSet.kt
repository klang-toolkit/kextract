package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSOrderedSet
 * Superclass: NSObject
 * Protocols: NSCopying, NSMutableCopying, NSSecureCoding, NSFastEnumeration
 */
open class NSOrderedSet(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSOrderedSet") }
        
    }
    
    open fun objectAtIndex(idx: Long): MemorySegment {
        val sel = ObjCRuntime.sel("objectAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, idx) as MemorySegment
    }
    
    open fun indexOfObject(`object`: MemorySegment): Long {
        val sel = ObjCRuntime.sel("indexOfObject:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, `object`) as Long
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithObjects_count(objects: MemorySegment, cnt: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithObjects:count:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, objects, cnt) as MemorySegment
    }
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    // @property count
    open fun count(): Long {
        val sel = ObjCRuntime.sel("count")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
}

// ── Category: NSExtendedOrderedSet on NSOrderedSet ─────────────────────────────────────────

fun NSOrderedSet.getObjects_range(objects: MemorySegment, range: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("getObjects:range:")
    ObjCRuntime.msgSend(null, this.ptr, sel, objects, range)
}

/** @return NSArray<ObjectType> * */
fun NSOrderedSet.objectsAtIndexes(indexes: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("objectsAtIndexes:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, indexes) as MemorySegment
}

fun NSOrderedSet.isEqualToOrderedSet(other: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("isEqualToOrderedSet:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, other) as Boolean
}

fun NSOrderedSet.containsObject(`object`: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("containsObject:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `object`) as Boolean
}

fun NSOrderedSet.intersectsOrderedSet(other: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("intersectsOrderedSet:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, other) as Boolean
}

fun NSOrderedSet.intersectsSet(`set`: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("intersectsSet:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `set`) as Boolean
}

fun NSOrderedSet.isSubsetOfOrderedSet(other: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("isSubsetOfOrderedSet:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, other) as Boolean
}

fun NSOrderedSet.isSubsetOfSet(`set`: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("isSubsetOfSet:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `set`) as Boolean
}

fun NSOrderedSet.objectAtIndexedSubscript(idx: Long): MemorySegment {
    val sel = ObjCRuntime.sel("objectAtIndexedSubscript:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, idx) as MemorySegment
}

/** @return NSEnumerator<ObjectType> * */
fun NSOrderedSet.objectEnumerator(): MemorySegment {
    val sel = ObjCRuntime.sel("objectEnumerator")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

/** @return NSEnumerator<ObjectType> * */
fun NSOrderedSet.reverseObjectEnumerator(): MemorySegment {
    val sel = ObjCRuntime.sel("reverseObjectEnumerator")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSOrderedSet.enumerateObjectsUsingBlock(block: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("enumerateObjectsUsingBlock:")
    ObjCRuntime.msgSend(null, this.ptr, sel, block)
}

fun NSOrderedSet.enumerateObjectsWithOptions_usingBlock(opts: MemorySegment, block: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("enumerateObjectsWithOptions:usingBlock:")
    ObjCRuntime.msgSend(null, this.ptr, sel, opts, block)
}

fun NSOrderedSet.enumerateObjectsAtIndexes_options_usingBlock(s: MemorySegment, opts: MemorySegment, block: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("enumerateObjectsAtIndexes:options:usingBlock:")
    ObjCRuntime.msgSend(null, this.ptr, sel, s, opts, block)
}

fun NSOrderedSet.indexOfObjectPassingTest(predicate: MemorySegment): Long {
    val sel = ObjCRuntime.sel("indexOfObjectPassingTest:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, predicate) as Long
}

fun NSOrderedSet.indexOfObjectWithOptions_passingTest(opts: MemorySegment, predicate: MemorySegment): Long {
    val sel = ObjCRuntime.sel("indexOfObjectWithOptions:passingTest:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, opts, predicate) as Long
}

fun NSOrderedSet.indexOfObjectAtIndexes_options_passingTest(s: MemorySegment, opts: MemorySegment, predicate: MemorySegment): Long {
    val sel = ObjCRuntime.sel("indexOfObjectAtIndexes:options:passingTest:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, s, opts, predicate) as Long
}

fun NSOrderedSet.indexesOfObjectsPassingTest(predicate: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("indexesOfObjectsPassingTest:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, predicate) as MemorySegment
}

fun NSOrderedSet.indexesOfObjectsWithOptions_passingTest(opts: MemorySegment, predicate: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("indexesOfObjectsWithOptions:passingTest:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, opts, predicate) as MemorySegment
}

fun NSOrderedSet.indexesOfObjectsAtIndexes_options_passingTest(s: MemorySegment, opts: MemorySegment, predicate: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("indexesOfObjectsAtIndexes:options:passingTest:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, s, opts, predicate) as MemorySegment
}

fun NSOrderedSet.indexOfObject_inSortedRange_options_usingComparator(`object`: MemorySegment, range: MemorySegment, opts: MemorySegment, cmp: MemorySegment): Long {
    val sel = ObjCRuntime.sel("indexOfObject:inSortedRange:options:usingComparator:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, `object`, range, opts, cmp) as Long
}

/** @return NSArray<ObjectType> * */
fun NSOrderedSet.sortedArrayUsingComparator(cmptr: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("sortedArrayUsingComparator:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, cmptr) as MemorySegment
}

/** @return NSArray<ObjectType> * */
fun NSOrderedSet.sortedArrayWithOptions_usingComparator(opts: MemorySegment, cmptr: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("sortedArrayWithOptions:usingComparator:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, opts, cmptr) as MemorySegment
}

fun NSOrderedSet.descriptionWithLocale(locale: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("descriptionWithLocale:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, locale) as MemorySegment
}

fun NSOrderedSet.descriptionWithLocale_indent(locale: MemorySegment, level: Long): MemorySegment {
    val sel = ObjCRuntime.sel("descriptionWithLocale:indent:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, locale, level) as MemorySegment
}

fun NSOrderedSet.firstObject(): MemorySegment {
    val sel = ObjCRuntime.sel("firstObject")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSOrderedSet.lastObject(): MemorySegment {
    val sel = ObjCRuntime.sel("lastObject")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

/** @return NSOrderedSet<ObjectType> * */
fun NSOrderedSet.reversedOrderedSet(): MemorySegment {
    val sel = ObjCRuntime.sel("reversedOrderedSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

/** @return NSArray<ObjectType> * */
fun NSOrderedSet.array(): MemorySegment {
    val sel = ObjCRuntime.sel("array")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

/** @return NSSet<ObjectType> * */
fun NSOrderedSet.`set`(): MemorySegment {
    val sel = ObjCRuntime.sel("set")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSOrderedSet.description(): MemorySegment {
    val sel = ObjCRuntime.sel("description")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSOrderedSetCreation on NSOrderedSet ─────────────────────────────────────────

fun NSOrderedSet.initWithObject(`object`: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithObject:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, `object`) as MemorySegment
}

fun NSOrderedSet.initWithObjects(firstObj: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithObjects:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, firstObj) as MemorySegment
}

fun NSOrderedSet.initWithOrderedSet(`set`: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithOrderedSet:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, `set`) as MemorySegment
}

fun NSOrderedSet.initWithOrderedSet_copyItems(`set`: MemorySegment, flag: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("initWithOrderedSet:copyItems:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, `set`, flag) as MemorySegment
}

fun NSOrderedSet.initWithOrderedSet_range_copyItems(`set`: MemorySegment, range: MemorySegment, flag: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("initWithOrderedSet:range:copyItems:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, `set`, range, flag) as MemorySegment
}

fun NSOrderedSet.initWithArray(array: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithArray:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, array) as MemorySegment
}

fun NSOrderedSet.initWithArray_copyItems(`set`: MemorySegment, flag: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("initWithArray:copyItems:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, `set`, flag) as MemorySegment
}

fun NSOrderedSet.initWithArray_range_copyItems(`set`: MemorySegment, range: MemorySegment, flag: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("initWithArray:range:copyItems:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, `set`, range, flag) as MemorySegment
}

fun NSOrderedSet.initWithSet(`set`: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithSet:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, `set`) as MemorySegment
}

fun NSOrderedSet.initWithSet_copyItems(`set`: MemorySegment, flag: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("initWithSet:copyItems:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, `set`, flag) as MemorySegment
}

// Class method: +[NSOrderedSet orderedSet]
fun NSOrderedSet_orderedSet(): MemorySegment {
    val sel = ObjCRuntime.sel("orderedSet")
    val cls = ObjCRuntime.getClass("NSOrderedSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSOrderedSet orderedSetWithObject:]
fun NSOrderedSet_orderedSetWithObject(`object`: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("orderedSetWithObject:")
    val cls = ObjCRuntime.getClass("NSOrderedSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, `object`) as MemorySegment
}

// Class method: +[NSOrderedSet orderedSetWithObjects:count:]
fun NSOrderedSet_orderedSetWithObjects_count(objects: MemorySegment, cnt: Long): MemorySegment {
    val sel = ObjCRuntime.sel("orderedSetWithObjects:count:")
    val cls = ObjCRuntime.getClass("NSOrderedSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, objects, cnt) as MemorySegment
}

// Class method: +[NSOrderedSet orderedSetWithObjects:]
fun NSOrderedSet_orderedSetWithObjects(firstObj: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("orderedSetWithObjects:")
    val cls = ObjCRuntime.getClass("NSOrderedSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, firstObj) as MemorySegment
}

// Class method: +[NSOrderedSet orderedSetWithOrderedSet:]
fun NSOrderedSet_orderedSetWithOrderedSet(`set`: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("orderedSetWithOrderedSet:")
    val cls = ObjCRuntime.getClass("NSOrderedSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, `set`) as MemorySegment
}

// Class method: +[NSOrderedSet orderedSetWithOrderedSet:range:copyItems:]
fun NSOrderedSet_orderedSetWithOrderedSet_range_copyItems(`set`: MemorySegment, range: MemorySegment, flag: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("orderedSetWithOrderedSet:range:copyItems:")
    val cls = ObjCRuntime.getClass("NSOrderedSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, `set`, range, flag) as MemorySegment
}

// Class method: +[NSOrderedSet orderedSetWithArray:]
fun NSOrderedSet_orderedSetWithArray(array: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("orderedSetWithArray:")
    val cls = ObjCRuntime.getClass("NSOrderedSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, array) as MemorySegment
}

// Class method: +[NSOrderedSet orderedSetWithArray:range:copyItems:]
fun NSOrderedSet_orderedSetWithArray_range_copyItems(array: MemorySegment, range: MemorySegment, flag: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("orderedSetWithArray:range:copyItems:")
    val cls = ObjCRuntime.getClass("NSOrderedSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, array, range, flag) as MemorySegment
}

// Class method: +[NSOrderedSet orderedSetWithSet:]
fun NSOrderedSet_orderedSetWithSet(`set`: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("orderedSetWithSet:")
    val cls = ObjCRuntime.getClass("NSOrderedSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, `set`) as MemorySegment
}

// Class method: +[NSOrderedSet orderedSetWithSet:copyItems:]
fun NSOrderedSet_orderedSetWithSet_copyItems(`set`: MemorySegment, flag: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("orderedSetWithSet:copyItems:")
    val cls = ObjCRuntime.getClass("NSOrderedSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, `set`, flag) as MemorySegment
}

// ── Category: NSOrderedSetDiffing on NSOrderedSet ─────────────────────────────────────────

/** @return NSOrderedCollectionDifference<ObjectType> * */
fun NSOrderedSet.differenceFromOrderedSet_withOptions_usingEquivalenceTest(other: MemorySegment, options: MemorySegment, block: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("differenceFromOrderedSet:withOptions:usingEquivalenceTest:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, other, options, block) as MemorySegment
}

/** @return NSOrderedCollectionDifference<ObjectType> * */
fun NSOrderedSet.differenceFromOrderedSet_withOptions(other: MemorySegment, options: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("differenceFromOrderedSet:withOptions:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, other, options) as MemorySegment
}

/** @return NSOrderedCollectionDifference<ObjectType> * */
fun NSOrderedSet.differenceFromOrderedSet(other: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("differenceFromOrderedSet:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, other) as MemorySegment
}

/** @return NSOrderedSet<ObjectType> * */
fun NSOrderedSet.orderedSetByApplyingDifference(difference: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("orderedSetByApplyingDifference:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, difference) as MemorySegment
}

// ── Category: NSKeyValueCoding on NSOrderedSet ─────────────────────────────────────────

fun NSOrderedSet.valueForKey(key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("valueForKey:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, key) as MemorySegment
}

fun NSOrderedSet.setValue_forKey(value: MemorySegment, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setValue:forKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value, key)
}

// ── Category: NSKeyValueObserverRegistration on NSOrderedSet ─────────────────────────────────────────

fun NSOrderedSet.addObserver_forKeyPath_options_context(observer: MemorySegment, keyPath: MemorySegment, options: MemorySegment, context: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("addObserver:forKeyPath:options:context:")
    ObjCRuntime.msgSend(null, this.ptr, sel, observer, keyPath, options, context)
}

fun NSOrderedSet.removeObserver_forKeyPath_context(observer: MemorySegment, keyPath: MemorySegment, context: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeObserver:forKeyPath:context:")
    ObjCRuntime.msgSend(null, this.ptr, sel, observer, keyPath, context)
}

fun NSOrderedSet.removeObserver_forKeyPath(observer: MemorySegment, keyPath: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeObserver:forKeyPath:")
    ObjCRuntime.msgSend(null, this.ptr, sel, observer, keyPath)
}

// ── Category: NSKeyValueSorting on NSOrderedSet ─────────────────────────────────────────

/** @return NSArray<ObjectType> * */
fun NSOrderedSet.sortedArrayUsingDescriptors(sortDescriptors: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("sortedArrayUsingDescriptors:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, sortDescriptors) as MemorySegment
}

// ── Category: NSPredicateSupport on NSOrderedSet ─────────────────────────────────────────

/** @return NSOrderedSet<ObjectType> * */
fun NSOrderedSet.filteredOrderedSetUsingPredicate(p: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("filteredOrderedSetUsingPredicate:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, p) as MemorySegment
}

