package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSArray
 * Superclass: NSObject
 * Protocols: NSCopying, NSMutableCopying, NSSecureCoding, NSFastEnumeration
 */
open class NSArray(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSArray") }
        
    }
    
    open fun objectAtIndex(index: Long): MemorySegment {
        val sel = ObjCRuntime.sel("objectAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, index) as MemorySegment
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

// ── Category: NSExtendedArray on NSArray ─────────────────────────────────────────

/** @return NSArray<ObjectType> * */
fun NSArray.arrayByAddingObject(anObject: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("arrayByAddingObject:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, anObject) as MemorySegment
}

/** @return NSArray<ObjectType> * */
fun NSArray.arrayByAddingObjectsFromArray(otherArray: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("arrayByAddingObjectsFromArray:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, otherArray) as MemorySegment
}

fun NSArray.componentsJoinedByString(separator: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("componentsJoinedByString:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, separator) as MemorySegment
}

fun NSArray.containsObject(anObject: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("containsObject:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, anObject) as Boolean
}

fun NSArray.descriptionWithLocale(locale: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("descriptionWithLocale:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, locale) as MemorySegment
}

fun NSArray.descriptionWithLocale_indent(locale: MemorySegment, level: Long): MemorySegment {
    val sel = ObjCRuntime.sel("descriptionWithLocale:indent:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, locale, level) as MemorySegment
}

fun NSArray.firstObjectCommonWithArray(otherArray: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("firstObjectCommonWithArray:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, otherArray) as MemorySegment
}

fun NSArray.getObjects_range(objects: MemorySegment, range: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("getObjects:range:")
    ObjCRuntime.msgSend(null, this.ptr, sel, objects, range)
}

fun NSArray.indexOfObject(anObject: MemorySegment): Long {
    val sel = ObjCRuntime.sel("indexOfObject:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, anObject) as Long
}

fun NSArray.indexOfObject_inRange(anObject: MemorySegment, range: MemorySegment): Long {
    val sel = ObjCRuntime.sel("indexOfObject:inRange:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, anObject, range) as Long
}

fun NSArray.indexOfObjectIdenticalTo(anObject: MemorySegment): Long {
    val sel = ObjCRuntime.sel("indexOfObjectIdenticalTo:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, anObject) as Long
}

fun NSArray.indexOfObjectIdenticalTo_inRange(anObject: MemorySegment, range: MemorySegment): Long {
    val sel = ObjCRuntime.sel("indexOfObjectIdenticalTo:inRange:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, anObject, range) as Long
}

fun NSArray.isEqualToArray(otherArray: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("isEqualToArray:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, otherArray) as Boolean
}

/** @return NSEnumerator<ObjectType> * */
fun NSArray.objectEnumerator(): MemorySegment {
    val sel = ObjCRuntime.sel("objectEnumerator")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

/** @return NSEnumerator<ObjectType> * */
fun NSArray.reverseObjectEnumerator(): MemorySegment {
    val sel = ObjCRuntime.sel("reverseObjectEnumerator")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

/** @return NSArray<ObjectType> * */
fun NSArray.sortedArrayUsingFunction_context(comparator: MemorySegment, context: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("sortedArrayUsingFunction:context:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, comparator, context) as MemorySegment
}

/** @return NSArray<ObjectType> * */
fun NSArray.sortedArrayUsingFunction_context_hint(comparator: MemorySegment, context: MemorySegment, hint: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("sortedArrayUsingFunction:context:hint:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, comparator, context, hint) as MemorySegment
}

/** @return NSArray<ObjectType> * */
fun NSArray.sortedArrayUsingSelector(comparator: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("sortedArrayUsingSelector:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, comparator) as MemorySegment
}

/** @return NSArray<ObjectType> * */
fun NSArray.subarrayWithRange(range: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("subarrayWithRange:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, range) as MemorySegment
}

fun NSArray.writeToURL_error(url: MemorySegment, error: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("writeToURL:error:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, url, error) as Boolean
}

fun NSArray.makeObjectsPerformSelector(aSelector: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("makeObjectsPerformSelector:")
    ObjCRuntime.msgSend(null, this.ptr, sel, aSelector)
}

fun NSArray.makeObjectsPerformSelector_withObject(aSelector: MemorySegment, argument: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("makeObjectsPerformSelector:withObject:")
    ObjCRuntime.msgSend(null, this.ptr, sel, aSelector, argument)
}

/** @return NSArray<ObjectType> * */
fun NSArray.objectsAtIndexes(indexes: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("objectsAtIndexes:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, indexes) as MemorySegment
}

fun NSArray.objectAtIndexedSubscript(idx: Long): MemorySegment {
    val sel = ObjCRuntime.sel("objectAtIndexedSubscript:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, idx) as MemorySegment
}

fun NSArray.enumerateObjectsUsingBlock(block: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("enumerateObjectsUsingBlock:")
    ObjCRuntime.msgSend(null, this.ptr, sel, block)
}

fun NSArray.enumerateObjectsWithOptions_usingBlock(opts: MemorySegment, block: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("enumerateObjectsWithOptions:usingBlock:")
    ObjCRuntime.msgSend(null, this.ptr, sel, opts, block)
}

fun NSArray.enumerateObjectsAtIndexes_options_usingBlock(s: MemorySegment, opts: MemorySegment, block: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("enumerateObjectsAtIndexes:options:usingBlock:")
    ObjCRuntime.msgSend(null, this.ptr, sel, s, opts, block)
}

fun NSArray.indexOfObjectPassingTest(predicate: MemorySegment): Long {
    val sel = ObjCRuntime.sel("indexOfObjectPassingTest:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, predicate) as Long
}

fun NSArray.indexOfObjectWithOptions_passingTest(opts: MemorySegment, predicate: MemorySegment): Long {
    val sel = ObjCRuntime.sel("indexOfObjectWithOptions:passingTest:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, opts, predicate) as Long
}

fun NSArray.indexOfObjectAtIndexes_options_passingTest(s: MemorySegment, opts: MemorySegment, predicate: MemorySegment): Long {
    val sel = ObjCRuntime.sel("indexOfObjectAtIndexes:options:passingTest:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, s, opts, predicate) as Long
}

fun NSArray.indexesOfObjectsPassingTest(predicate: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("indexesOfObjectsPassingTest:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, predicate) as MemorySegment
}

fun NSArray.indexesOfObjectsWithOptions_passingTest(opts: MemorySegment, predicate: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("indexesOfObjectsWithOptions:passingTest:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, opts, predicate) as MemorySegment
}

fun NSArray.indexesOfObjectsAtIndexes_options_passingTest(s: MemorySegment, opts: MemorySegment, predicate: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("indexesOfObjectsAtIndexes:options:passingTest:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, s, opts, predicate) as MemorySegment
}

/** @return NSArray<ObjectType> * */
fun NSArray.sortedArrayUsingComparator(cmptr: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("sortedArrayUsingComparator:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, cmptr) as MemorySegment
}

/** @return NSArray<ObjectType> * */
fun NSArray.sortedArrayWithOptions_usingComparator(opts: MemorySegment, cmptr: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("sortedArrayWithOptions:usingComparator:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, opts, cmptr) as MemorySegment
}

fun NSArray.indexOfObject_inSortedRange_options_usingComparator(obj: MemorySegment, r: MemorySegment, opts: MemorySegment, cmp: MemorySegment): Long {
    val sel = ObjCRuntime.sel("indexOfObject:inSortedRange:options:usingComparator:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, obj, r, opts, cmp) as Long
}

fun NSArray.description(): MemorySegment {
    val sel = ObjCRuntime.sel("description")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSArray.firstObject(): MemorySegment {
    val sel = ObjCRuntime.sel("firstObject")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSArray.lastObject(): MemorySegment {
    val sel = ObjCRuntime.sel("lastObject")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSArray.sortedArrayHint(): MemorySegment {
    val sel = ObjCRuntime.sel("sortedArrayHint")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSArrayCreation on NSArray ─────────────────────────────────────────

fun NSArray.initWithObjects(firstObj: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithObjects:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, firstObj) as MemorySegment
}

fun NSArray.initWithArray(array: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithArray:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, array) as MemorySegment
}

fun NSArray.initWithArray_copyItems(array: MemorySegment, flag: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("initWithArray:copyItems:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, array, flag) as MemorySegment
}

/** @return NSArray<ObjectType> * */
fun NSArray.initWithContentsOfURL_error(url: MemorySegment, error: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithContentsOfURL:error:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, url, error) as MemorySegment
}

// Class method: +[NSArray array]
fun NSArray_array(): MemorySegment {
    val sel = ObjCRuntime.sel("array")
    val cls = ObjCRuntime.getClass("NSArray")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSArray arrayWithObject:]
fun NSArray_arrayWithObject(anObject: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("arrayWithObject:")
    val cls = ObjCRuntime.getClass("NSArray")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, anObject) as MemorySegment
}

// Class method: +[NSArray arrayWithObjects:count:]
fun NSArray_arrayWithObjects_count(objects: MemorySegment, cnt: Long): MemorySegment {
    val sel = ObjCRuntime.sel("arrayWithObjects:count:")
    val cls = ObjCRuntime.getClass("NSArray")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, objects, cnt) as MemorySegment
}

// Class method: +[NSArray arrayWithObjects:]
fun NSArray_arrayWithObjects(firstObj: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("arrayWithObjects:")
    val cls = ObjCRuntime.getClass("NSArray")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, firstObj) as MemorySegment
}

// Class method: +[NSArray arrayWithArray:]
fun NSArray_arrayWithArray(array: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("arrayWithArray:")
    val cls = ObjCRuntime.getClass("NSArray")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, array) as MemorySegment
}

// Class method: +[NSArray arrayWithContentsOfURL:error:]
fun NSArray_arrayWithContentsOfURL_error(url: MemorySegment, error: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("arrayWithContentsOfURL:error:")
    val cls = ObjCRuntime.getClass("NSArray")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, url, error) as MemorySegment
}

// ── Category: NSArrayDiffing on NSArray ─────────────────────────────────────────

/** @return NSOrderedCollectionDifference<ObjectType> * */
fun NSArray.differenceFromArray_withOptions_usingEquivalenceTest(other: MemorySegment, options: MemorySegment, block: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("differenceFromArray:withOptions:usingEquivalenceTest:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, other, options, block) as MemorySegment
}

/** @return NSOrderedCollectionDifference<ObjectType> * */
fun NSArray.differenceFromArray_withOptions(other: MemorySegment, options: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("differenceFromArray:withOptions:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, other, options) as MemorySegment
}

/** @return NSOrderedCollectionDifference<ObjectType> * */
fun NSArray.differenceFromArray(other: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("differenceFromArray:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, other) as MemorySegment
}

/** @return NSArray<ObjectType> * */
fun NSArray.arrayByApplyingDifference(difference: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("arrayByApplyingDifference:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, difference) as MemorySegment
}

// ── Category: NSDeprecated on NSArray ─────────────────────────────────────────

fun NSArray.getObjects(objects: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("getObjects:")
    ObjCRuntime.msgSend(null, this.ptr, sel, objects)
}

/** @return NSArray<ObjectType> * */
fun NSArray.initWithContentsOfFile(path: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithContentsOfFile:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, path) as MemorySegment
}

/** @return NSArray<ObjectType> * */
fun NSArray.initWithContentsOfURL(url: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithContentsOfURL:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, url) as MemorySegment
}

fun NSArray.writeToFile_atomically(path: MemorySegment, useAuxiliaryFile: Boolean): Boolean {
    val sel = ObjCRuntime.sel("writeToFile:atomically:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, path, useAuxiliaryFile) as Boolean
}

fun NSArray.writeToURL_atomically(url: MemorySegment, atomically: Boolean): Boolean {
    val sel = ObjCRuntime.sel("writeToURL:atomically:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, url, atomically) as Boolean
}

// Class method: +[NSArray arrayWithContentsOfFile:]
fun NSArray_arrayWithContentsOfFile(path: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("arrayWithContentsOfFile:")
    val cls = ObjCRuntime.getClass("NSArray")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, path) as MemorySegment
}

// Class method: +[NSArray arrayWithContentsOfURL:]
fun NSArray_arrayWithContentsOfURL(url: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("arrayWithContentsOfURL:")
    val cls = ObjCRuntime.getClass("NSArray")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, url) as MemorySegment
}

// ── Category: NSArrayPathExtensions on NSArray ─────────────────────────────────────────

/** @return NSArray<NSString *> * */
fun NSArray.pathsMatchingExtensions(filterTypes: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("pathsMatchingExtensions:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, filterTypes) as MemorySegment
}

// ── Category: NSKeyValueCoding on NSArray ─────────────────────────────────────────

fun NSArray.valueForKey(key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("valueForKey:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, key) as MemorySegment
}

fun NSArray.setValue_forKey(value: MemorySegment, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setValue:forKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value, key)
}

// ── Category: NSKeyValueObserverRegistration on NSArray ─────────────────────────────────────────

fun NSArray.addObserver_toObjectsAtIndexes_forKeyPath_options_context(observer: MemorySegment, indexes: MemorySegment, keyPath: MemorySegment, options: MemorySegment, context: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("addObserver:toObjectsAtIndexes:forKeyPath:options:context:")
    ObjCRuntime.msgSend(null, this.ptr, sel, observer, indexes, keyPath, options, context)
}

fun NSArray.removeObserver_fromObjectsAtIndexes_forKeyPath_context(observer: MemorySegment, indexes: MemorySegment, keyPath: MemorySegment, context: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeObserver:fromObjectsAtIndexes:forKeyPath:context:")
    ObjCRuntime.msgSend(null, this.ptr, sel, observer, indexes, keyPath, context)
}

fun NSArray.removeObserver_fromObjectsAtIndexes_forKeyPath(observer: MemorySegment, indexes: MemorySegment, keyPath: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeObserver:fromObjectsAtIndexes:forKeyPath:")
    ObjCRuntime.msgSend(null, this.ptr, sel, observer, indexes, keyPath)
}

fun NSArray.addObserver_forKeyPath_options_context(observer: MemorySegment, keyPath: MemorySegment, options: MemorySegment, context: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("addObserver:forKeyPath:options:context:")
    ObjCRuntime.msgSend(null, this.ptr, sel, observer, keyPath, options, context)
}

fun NSArray.removeObserver_forKeyPath_context(observer: MemorySegment, keyPath: MemorySegment, context: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeObserver:forKeyPath:context:")
    ObjCRuntime.msgSend(null, this.ptr, sel, observer, keyPath, context)
}

fun NSArray.removeObserver_forKeyPath(observer: MemorySegment, keyPath: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeObserver:forKeyPath:")
    ObjCRuntime.msgSend(null, this.ptr, sel, observer, keyPath)
}

// ── Category: NSSortDescriptorSorting on NSArray ─────────────────────────────────────────

/** @return NSArray<ObjectType> * */
fun NSArray.sortedArrayUsingDescriptors(sortDescriptors: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("sortedArrayUsingDescriptors:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, sortDescriptors) as MemorySegment
}

// ── Category: NSPredicateSupport on NSArray ─────────────────────────────────────────

/** @return NSArray<ObjectType> * */
fun NSArray.filteredArrayUsingPredicate(predicate: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("filteredArrayUsingPredicate:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, predicate) as MemorySegment
}

