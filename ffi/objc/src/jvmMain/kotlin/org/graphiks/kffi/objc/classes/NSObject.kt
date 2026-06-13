package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

// ── Category: NSCoderMethods on NSObject ─────────────────────────────────────────

fun NSObject.replacementObjectForCoder(coder: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("replacementObjectForCoder:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, coder) as MemorySegment
}

fun NSObject.awakeAfterUsingCoder(coder: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("awakeAfterUsingCoder:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, coder) as MemorySegment
}

fun NSObject.classForCoder(): MemorySegment {
    val sel = ObjCRuntime.sel("classForCoder")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// Class method: +[NSObject version]
fun NSObject_version(): Long {
    val sel = ObjCRuntime.sel("version")
    val cls = ObjCRuntime.getClass("NSObject")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, cls, sel) as Long
}

// Class method: +[NSObject setVersion:]
fun NSObject_setVersion(aVersion: Long): Unit {
    val sel = ObjCRuntime.sel("setVersion:")
    val cls = ObjCRuntime.getClass("NSObject")
    ObjCRuntime.msgSend(null, cls, sel, aVersion)
}

// ── Category: NSDeprecatedMethods on NSObject ─────────────────────────────────────────

// Class method: +[NSObject poseAsClass:]
fun NSObject_poseAsClass(aClass: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("poseAsClass:")
    val cls = ObjCRuntime.getClass("NSObject")
    ObjCRuntime.msgSend(null, cls, sel, aClass)
}

// ── Category: NSDiscardableContentProxy on NSObject ─────────────────────────────────────────

fun NSObject.autoContentAccessingProxy(): MemorySegment {
    val sel = ObjCRuntime.sel("autoContentAccessingProxy")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSErrorRecoveryAttempting on NSObject ─────────────────────────────────────────

fun NSObject.attemptRecoveryFromError_optionIndex_delegate_didRecoverSelector_contextInfo(error: MemorySegment, recoveryOptionIndex: Long, delegate: MemorySegment, didRecoverSelector: MemorySegment, contextInfo: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("attemptRecoveryFromError:optionIndex:delegate:didRecoverSelector:contextInfo:")
    ObjCRuntime.msgSend(null, this.ptr, sel, error, recoveryOptionIndex, delegate, didRecoverSelector, contextInfo)
}

fun NSObject.attemptRecoveryFromError_optionIndex(error: MemorySegment, recoveryOptionIndex: Long): Boolean {
    val sel = ObjCRuntime.sel("attemptRecoveryFromError:optionIndex:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, error, recoveryOptionIndex) as Boolean
}

// ── Category: NSDelayedPerforming on NSObject ─────────────────────────────────────────

fun NSObject.performSelector_withObject_afterDelay_inModes(aSelector: MemorySegment, anArgument: MemorySegment, delay: Double, modes: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("performSelector:withObject:afterDelay:inModes:")
    ObjCRuntime.msgSend(null, this.ptr, sel, aSelector, anArgument, delay, modes)
}

fun NSObject.performSelector_withObject_afterDelay(aSelector: MemorySegment, anArgument: MemorySegment, delay: Double): Unit {
    val sel = ObjCRuntime.sel("performSelector:withObject:afterDelay:")
    ObjCRuntime.msgSend(null, this.ptr, sel, aSelector, anArgument, delay)
}

// Class method: +[NSObject cancelPreviousPerformRequestsWithTarget:selector:object:]
fun NSObject_cancelPreviousPerformRequestsWithTarget_selector_object(aTarget: MemorySegment, aSelector: MemorySegment, anArgument: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("cancelPreviousPerformRequestsWithTarget:selector:object:")
    val cls = ObjCRuntime.getClass("NSObject")
    ObjCRuntime.msgSend(null, cls, sel, aTarget, aSelector, anArgument)
}

// Class method: +[NSObject cancelPreviousPerformRequestsWithTarget:]
fun NSObject_cancelPreviousPerformRequestsWithTarget(aTarget: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("cancelPreviousPerformRequestsWithTarget:")
    val cls = ObjCRuntime.getClass("NSObject")
    ObjCRuntime.msgSend(null, cls, sel, aTarget)
}

// ── Category: NSURLClient on NSObject ─────────────────────────────────────────

fun NSObject.URL_resourceDataDidBecomeAvailable(sender: MemorySegment, newBytes: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("URL:resourceDataDidBecomeAvailable:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender, newBytes)
}

fun NSObject.URLResourceDidFinishLoading(sender: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("URLResourceDidFinishLoading:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender)
}

fun NSObject.URLResourceDidCancelLoading(sender: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("URLResourceDidCancelLoading:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender)
}

fun NSObject.URL_resourceDidFailLoadingWithReason(sender: MemorySegment, reason: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("URL:resourceDidFailLoadingWithReason:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender, reason)
}

// ── Category: NSCopyLinkMoveHandler on NSObject ─────────────────────────────────────────

fun NSObject.fileManager_shouldProceedAfterError(fm: MemorySegment, errorInfo: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("fileManager:shouldProceedAfterError:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, fm, errorInfo) as Boolean
}

fun NSObject.fileManager_willProcessPath(fm: MemorySegment, path: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("fileManager:willProcessPath:")
    ObjCRuntime.msgSend(null, this.ptr, sel, fm, path)
}

// ── Category: NSKeyValueCoding on NSObject ─────────────────────────────────────────

fun NSObject.valueForKey(key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("valueForKey:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, key) as MemorySegment
}

fun NSObject.setValue_forKey(value: MemorySegment, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setValue:forKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value, key)
}

fun NSObject.validateValue_forKey_error(ioValue: MemorySegment, inKey: MemorySegment, outError: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("validateValue:forKey:error:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, ioValue, inKey, outError) as Boolean
}

fun NSObject.mutableArrayValueForKey(key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("mutableArrayValueForKey:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, key) as MemorySegment
}

fun NSObject.mutableOrderedSetValueForKey(key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("mutableOrderedSetValueForKey:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, key) as MemorySegment
}

fun NSObject.mutableSetValueForKey(key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("mutableSetValueForKey:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, key) as MemorySegment
}

fun NSObject.valueForKeyPath(keyPath: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("valueForKeyPath:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, keyPath) as MemorySegment
}

fun NSObject.setValue_forKeyPath(value: MemorySegment, keyPath: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setValue:forKeyPath:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value, keyPath)
}

fun NSObject.validateValue_forKeyPath_error(ioValue: MemorySegment, inKeyPath: MemorySegment, outError: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("validateValue:forKeyPath:error:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, ioValue, inKeyPath, outError) as Boolean
}

fun NSObject.mutableArrayValueForKeyPath(keyPath: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("mutableArrayValueForKeyPath:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, keyPath) as MemorySegment
}

fun NSObject.mutableOrderedSetValueForKeyPath(keyPath: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("mutableOrderedSetValueForKeyPath:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, keyPath) as MemorySegment
}

fun NSObject.mutableSetValueForKeyPath(keyPath: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("mutableSetValueForKeyPath:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, keyPath) as MemorySegment
}

fun NSObject.valueForUndefinedKey(key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("valueForUndefinedKey:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, key) as MemorySegment
}

fun NSObject.setValue_forUndefinedKey(value: MemorySegment, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setValue:forUndefinedKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value, key)
}

fun NSObject.setNilValueForKey(key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setNilValueForKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, key)
}

/** @return NSDictionary<NSString *,id> * */
fun NSObject.dictionaryWithValuesForKeys(keys: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("dictionaryWithValuesForKeys:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, keys) as MemorySegment
}

fun NSObject.setValuesForKeysWithDictionary(keyedValues: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setValuesForKeysWithDictionary:")
    ObjCRuntime.msgSend(null, this.ptr, sel, keyedValues)
}

// Class method: +[NSObject accessInstanceVariablesDirectly]
fun NSObject_accessInstanceVariablesDirectly(): Boolean {
    val sel = ObjCRuntime.sel("accessInstanceVariablesDirectly")
    val cls = ObjCRuntime.getClass("NSObject")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, cls, sel) as Boolean
}

// @property accessInstanceVariablesDirectly
fun NSObject.accessInstanceVariablesDirectly(): Boolean {
    val sel = ObjCRuntime.sel("accessInstanceVariablesDirectly")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

// ── Category: NSDeprecatedKeyValueCoding on NSObject ─────────────────────────────────────────

fun NSObject.storedValueForKey(key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("storedValueForKey:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, key) as MemorySegment
}

fun NSObject.takeStoredValue_forKey(value: MemorySegment, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("takeStoredValue:forKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value, key)
}

fun NSObject.takeValue_forKey(value: MemorySegment, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("takeValue:forKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value, key)
}

fun NSObject.takeValue_forKeyPath(value: MemorySegment, keyPath: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("takeValue:forKeyPath:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value, keyPath)
}

fun NSObject.handleQueryWithUnboundKey(key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("handleQueryWithUnboundKey:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, key) as MemorySegment
}

fun NSObject.handleTakeValue_forUnboundKey(value: MemorySegment, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("handleTakeValue:forUnboundKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value, key)
}

fun NSObject.unableToSetNilForKey(key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("unableToSetNilForKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, key)
}

fun NSObject.valuesForKeys(keys: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("valuesForKeys:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, keys) as MemorySegment
}

fun NSObject.takeValuesFromDictionary(properties: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("takeValuesFromDictionary:")
    ObjCRuntime.msgSend(null, this.ptr, sel, properties)
}

// Class method: +[NSObject useStoredAccessor]
fun NSObject_useStoredAccessor(): Boolean {
    val sel = ObjCRuntime.sel("useStoredAccessor")
    val cls = ObjCRuntime.getClass("NSObject")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, cls, sel) as Boolean
}

// ── Category: NSKeyValueObserving on NSObject ─────────────────────────────────────────

fun NSObject.observeValueForKeyPath_ofObject_change_context(keyPath: MemorySegment, `object`: MemorySegment, change: MemorySegment, context: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("observeValueForKeyPath:ofObject:change:context:")
    ObjCRuntime.msgSend(null, this.ptr, sel, keyPath, `object`, change, context)
}

// ── Category: NSKeyValueObserverRegistration on NSObject ─────────────────────────────────────────

fun NSObject.addObserver_forKeyPath_options_context(observer: MemorySegment, keyPath: MemorySegment, options: MemorySegment, context: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("addObserver:forKeyPath:options:context:")
    ObjCRuntime.msgSend(null, this.ptr, sel, observer, keyPath, options, context)
}

fun NSObject.removeObserver_forKeyPath_context(observer: MemorySegment, keyPath: MemorySegment, context: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeObserver:forKeyPath:context:")
    ObjCRuntime.msgSend(null, this.ptr, sel, observer, keyPath, context)
}

fun NSObject.removeObserver_forKeyPath(observer: MemorySegment, keyPath: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeObserver:forKeyPath:")
    ObjCRuntime.msgSend(null, this.ptr, sel, observer, keyPath)
}

// ── Category: NSKeyValueObserverNotification on NSObject ─────────────────────────────────────────

fun NSObject.willChangeValueForKey(key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("willChangeValueForKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, key)
}

fun NSObject.didChangeValueForKey(key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("didChangeValueForKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, key)
}

fun NSObject.willChange_valuesAtIndexes_forKey(changeKind: MemorySegment, indexes: MemorySegment, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("willChange:valuesAtIndexes:forKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, changeKind, indexes, key)
}

fun NSObject.didChange_valuesAtIndexes_forKey(changeKind: MemorySegment, indexes: MemorySegment, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("didChange:valuesAtIndexes:forKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, changeKind, indexes, key)
}

fun NSObject.willChangeValueForKey_withSetMutation_usingObjects(key: MemorySegment, mutationKind: MemorySegment, objects: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("willChangeValueForKey:withSetMutation:usingObjects:")
    ObjCRuntime.msgSend(null, this.ptr, sel, key, mutationKind, objects)
}

fun NSObject.didChangeValueForKey_withSetMutation_usingObjects(key: MemorySegment, mutationKind: MemorySegment, objects: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("didChangeValueForKey:withSetMutation:usingObjects:")
    ObjCRuntime.msgSend(null, this.ptr, sel, key, mutationKind, objects)
}

// ── Category: NSKeyValueObservingCustomization on NSObject ─────────────────────────────────────────

fun NSObject.observationInfo(): MemorySegment {
    val sel = ObjCRuntime.sel("observationInfo")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSObject.setObservationInfo(observationInfo: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setObservationInfo:")
    ObjCRuntime.msgSend(null, this.ptr, sel, observationInfo)
}

// Class method: +[NSObject keyPathsForValuesAffectingValueForKey:]
fun NSObject_keyPathsForValuesAffectingValueForKey(key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("keyPathsForValuesAffectingValueForKey:")
    val cls = ObjCRuntime.getClass("NSObject")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, key) as MemorySegment
}

// Class method: +[NSObject automaticallyNotifiesObserversForKey:]
fun NSObject_automaticallyNotifiesObserversForKey(key: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("automaticallyNotifiesObserversForKey:")
    val cls = ObjCRuntime.getClass("NSObject")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, cls, sel, key) as Boolean
}

// ── Category: NSDeprecatedKeyValueObservingCustomization on NSObject ─────────────────────────────────────────

// Class method: +[NSObject setKeys:triggerChangeNotificationsForDependentKey:]
fun NSObject_setKeys_triggerChangeNotificationsForDependentKey(keys: MemorySegment, dependentKey: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setKeys:triggerChangeNotificationsForDependentKey:")
    val cls = ObjCRuntime.getClass("NSObject")
    ObjCRuntime.msgSend(null, cls, sel, keys, dependentKey)
}

// ── Category: NSKeyValueSharedObserverRegistration on NSObject ─────────────────────────────────────────

fun NSObject.setSharedObservers(sharedObservers: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setSharedObservers:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sharedObservers)
}

// ── Category: NSKeyedArchiverObjectSubstitution on NSObject ─────────────────────────────────────────

fun NSObject.replacementObjectForKeyedArchiver(archiver: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("replacementObjectForKeyedArchiver:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, archiver) as MemorySegment
}

fun NSObject.classForKeyedArchiver(): MemorySegment {
    val sel = ObjCRuntime.sel("classForKeyedArchiver")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// Class method: +[NSObject classFallbacksForKeyedArchiver]
fun NSObject_classFallbacksForKeyedArchiver(): MemorySegment {
    val sel = ObjCRuntime.sel("classFallbacksForKeyedArchiver")
    val cls = ObjCRuntime.getClass("NSObject")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// ── Category: NSKeyedUnarchiverObjectSubstitution on NSObject ─────────────────────────────────────────

// Class method: +[NSObject classForKeyedUnarchiver]
fun NSObject_classForKeyedUnarchiver(): MemorySegment {
    val sel = ObjCRuntime.sel("classForKeyedUnarchiver")
    val cls = ObjCRuntime.getClass("NSObject")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// ── Category: NSThreadPerformAdditions on NSObject ─────────────────────────────────────────

fun NSObject.performSelectorOnMainThread_withObject_waitUntilDone_modes(aSelector: MemorySegment, arg: MemorySegment, wait: Boolean, array: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("performSelectorOnMainThread:withObject:waitUntilDone:modes:")
    ObjCRuntime.msgSend(null, this.ptr, sel, aSelector, arg, wait, array)
}

fun NSObject.performSelectorOnMainThread_withObject_waitUntilDone(aSelector: MemorySegment, arg: MemorySegment, wait: Boolean): Unit {
    val sel = ObjCRuntime.sel("performSelectorOnMainThread:withObject:waitUntilDone:")
    ObjCRuntime.msgSend(null, this.ptr, sel, aSelector, arg, wait)
}

fun NSObject.performSelector_onThread_withObject_waitUntilDone_modes(aSelector: MemorySegment, thr: MemorySegment, arg: MemorySegment, wait: Boolean, array: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("performSelector:onThread:withObject:waitUntilDone:modes:")
    ObjCRuntime.msgSend(null, this.ptr, sel, aSelector, thr, arg, wait, array)
}

fun NSObject.performSelector_onThread_withObject_waitUntilDone(aSelector: MemorySegment, thr: MemorySegment, arg: MemorySegment, wait: Boolean): Unit {
    val sel = ObjCRuntime.sel("performSelector:onThread:withObject:waitUntilDone:")
    ObjCRuntime.msgSend(null, this.ptr, sel, aSelector, thr, arg, wait)
}

fun NSObject.performSelectorInBackground_withObject(aSelector: MemorySegment, arg: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("performSelectorInBackground:withObject:")
    ObjCRuntime.msgSend(null, this.ptr, sel, aSelector, arg)
}

// ── Category: NSArchiverCallback on NSObject ─────────────────────────────────────────

fun NSObject.replacementObjectForArchiver(archiver: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("replacementObjectForArchiver:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, archiver) as MemorySegment
}

fun NSObject.classForArchiver(): MemorySegment {
    val sel = ObjCRuntime.sel("classForArchiver")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSDistributedObjects on NSObject ─────────────────────────────────────────

fun NSObject.replacementObjectForPortCoder(coder: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("replacementObjectForPortCoder:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, coder) as MemorySegment
}

fun NSObject.classForPortCoder(): MemorySegment {
    val sel = ObjCRuntime.sel("classForPortCoder")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSClassDescriptionPrimitives on NSObject ─────────────────────────────────────────

fun NSObject.inverseForRelationshipKey(relationshipKey: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("inverseForRelationshipKey:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, relationshipKey) as MemorySegment
}

fun NSObject.classDescription(): MemorySegment {
    val sel = ObjCRuntime.sel("classDescription")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

/** @return NSArray<NSString *> * */
fun NSObject.attributeKeys(): MemorySegment {
    val sel = ObjCRuntime.sel("attributeKeys")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

/** @return NSArray<NSString *> * */
fun NSObject.toOneRelationshipKeys(): MemorySegment {
    val sel = ObjCRuntime.sel("toOneRelationshipKeys")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

/** @return NSArray<NSString *> * */
fun NSObject.toManyRelationshipKeys(): MemorySegment {
    val sel = ObjCRuntime.sel("toManyRelationshipKeys")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSScripting on NSObject ─────────────────────────────────────────

fun NSObject.scriptingValueForSpecifier(objectSpecifier: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("scriptingValueForSpecifier:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, objectSpecifier) as MemorySegment
}

fun NSObject.copyScriptingValue_forKey_withProperties(value: MemorySegment, key: MemorySegment, properties: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("copyScriptingValue:forKey:withProperties:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, value, key, properties) as MemorySegment
}

fun NSObject.newScriptingObjectOfClass_forValueForKey_withContentsValue_properties(objectClass: MemorySegment, key: MemorySegment, contentsValue: MemorySegment, properties: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("newScriptingObjectOfClass:forValueForKey:withContentsValue:properties:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, objectClass, key, contentsValue, properties) as MemorySegment
}

/** @return NSDictionary<NSString *,id> * */
fun NSObject.scriptingProperties(): MemorySegment {
    val sel = ObjCRuntime.sel("scriptingProperties")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSObject.setScriptingProperties(scriptingProperties: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setScriptingProperties:")
    ObjCRuntime.msgSend(null, this.ptr, sel, scriptingProperties)
}

// ── Category: NSScriptClassDescription on NSObject ─────────────────────────────────────────

fun NSObject.classCode(): Int {
    val sel = ObjCRuntime.sel("classCode")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, this.ptr, sel) as Int
}

fun NSObject.className(): MemorySegment {
    val sel = ObjCRuntime.sel("className")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSScriptKeyValueCoding on NSObject ─────────────────────────────────────────

fun NSObject.valueAtIndex_inPropertyWithKey(index: Long, key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("valueAtIndex:inPropertyWithKey:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, index, key) as MemorySegment
}

fun NSObject.valueWithName_inPropertyWithKey(name: MemorySegment, key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("valueWithName:inPropertyWithKey:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, name, key) as MemorySegment
}

fun NSObject.valueWithUniqueID_inPropertyWithKey(uniqueID: MemorySegment, key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("valueWithUniqueID:inPropertyWithKey:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, uniqueID, key) as MemorySegment
}

fun NSObject.insertValue_atIndex_inPropertyWithKey(value: MemorySegment, index: Long, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("insertValue:atIndex:inPropertyWithKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value, index, key)
}

fun NSObject.removeValueAtIndex_fromPropertyWithKey(index: Long, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeValueAtIndex:fromPropertyWithKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, index, key)
}

fun NSObject.replaceValueAtIndex_inPropertyWithKey_withValue(index: Long, key: MemorySegment, value: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("replaceValueAtIndex:inPropertyWithKey:withValue:")
    ObjCRuntime.msgSend(null, this.ptr, sel, index, key, value)
}

fun NSObject.insertValue_inPropertyWithKey(value: MemorySegment, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("insertValue:inPropertyWithKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value, key)
}

fun NSObject.coerceValue_forKey(value: MemorySegment, key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("coerceValue:forKey:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, value, key) as MemorySegment
}

// ── Category: NSScriptObjectSpecifiers on NSObject ─────────────────────────────────────────

/** @return NSArray<NSNumber *> * */
fun NSObject.indicesOfObjectsByEvaluatingObjectSpecifier(specifier: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("indicesOfObjectsByEvaluatingObjectSpecifier:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, specifier) as MemorySegment
}

fun NSObject.objectSpecifier(): MemorySegment {
    val sel = ObjCRuntime.sel("objectSpecifier")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSComparisonMethods on NSObject ─────────────────────────────────────────

fun NSObject.isEqualTo(`object`: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("isEqualTo:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `object`) as Boolean
}

fun NSObject.isLessThanOrEqualTo(`object`: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("isLessThanOrEqualTo:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `object`) as Boolean
}

fun NSObject.isLessThan(`object`: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("isLessThan:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `object`) as Boolean
}

fun NSObject.isGreaterThanOrEqualTo(`object`: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("isGreaterThanOrEqualTo:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `object`) as Boolean
}

fun NSObject.isGreaterThan(`object`: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("isGreaterThan:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `object`) as Boolean
}

fun NSObject.isNotEqualTo(`object`: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("isNotEqualTo:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `object`) as Boolean
}

fun NSObject.doesContain(`object`: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("doesContain:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `object`) as Boolean
}

fun NSObject.isLike(`object`: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("isLike:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `object`) as Boolean
}

fun NSObject.isCaseInsensitiveLike(`object`: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("isCaseInsensitiveLike:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `object`) as Boolean
}

// ── Category: NSScriptingComparisonMethods on NSObject ─────────────────────────────────────────

fun NSObject.scriptingIsEqualTo(`object`: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("scriptingIsEqualTo:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `object`) as Boolean
}

fun NSObject.scriptingIsLessThanOrEqualTo(`object`: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("scriptingIsLessThanOrEqualTo:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `object`) as Boolean
}

fun NSObject.scriptingIsLessThan(`object`: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("scriptingIsLessThan:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `object`) as Boolean
}

fun NSObject.scriptingIsGreaterThanOrEqualTo(`object`: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("scriptingIsGreaterThanOrEqualTo:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `object`) as Boolean
}

fun NSObject.scriptingIsGreaterThan(`object`: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("scriptingIsGreaterThan:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `object`) as Boolean
}

fun NSObject.scriptingBeginsWith(`object`: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("scriptingBeginsWith:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `object`) as Boolean
}

fun NSObject.scriptingEndsWith(`object`: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("scriptingEndsWith:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `object`) as Boolean
}

fun NSObject.scriptingContains(`object`: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("scriptingContains:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `object`) as Boolean
}

// ── Category: NSAccessibility on NSObject ─────────────────────────────────────────

/** @return NSArray<NSAccessibilityAttributeName> * */
fun NSObject.accessibilityAttributeNames(): MemorySegment {
    val sel = ObjCRuntime.sel("accessibilityAttributeNames")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSObject.accessibilityAttributeValue(attribute: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("accessibilityAttributeValue:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, attribute) as MemorySegment
}

fun NSObject.accessibilityIsAttributeSettable(attribute: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("accessibilityIsAttributeSettable:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, attribute) as Boolean
}

fun NSObject.accessibilitySetValue_forAttribute(value: MemorySegment, attribute: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("accessibilitySetValue:forAttribute:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value, attribute)
}

/** @return NSArray<NSAccessibilityParameterizedAttributeName> * */
fun NSObject.accessibilityParameterizedAttributeNames(): MemorySegment {
    val sel = ObjCRuntime.sel("accessibilityParameterizedAttributeNames")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSObject.accessibilityAttributeValue_forParameter(attribute: MemorySegment, parameter: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("accessibilityAttributeValue:forParameter:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, attribute, parameter) as MemorySegment
}

/** @return NSArray<NSAccessibilityActionName> * */
fun NSObject.accessibilityActionNames(): MemorySegment {
    val sel = ObjCRuntime.sel("accessibilityActionNames")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSObject.accessibilityActionDescription(action: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("accessibilityActionDescription:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, action) as MemorySegment
}

fun NSObject.accessibilityPerformAction(action: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("accessibilityPerformAction:")
    ObjCRuntime.msgSend(null, this.ptr, sel, action)
}

fun NSObject.accessibilityIsIgnored(): Boolean {
    val sel = ObjCRuntime.sel("accessibilityIsIgnored")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSObject.accessibilityHitTest(point: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("accessibilityHitTest:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, point) as MemorySegment
}

fun NSObject.accessibilityIndexOfChild(child: MemorySegment): Long {
    val sel = ObjCRuntime.sel("accessibilityIndexOfChild:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, child) as Long
}

fun NSObject.accessibilityArrayAttributeCount(attribute: MemorySegment): Long {
    val sel = ObjCRuntime.sel("accessibilityArrayAttributeCount:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, attribute) as Long
}

fun NSObject.accessibilityArrayAttributeValues_index_maxCount(attribute: MemorySegment, index: Long, maxCount: Long): MemorySegment {
    val sel = ObjCRuntime.sel("accessibilityArrayAttributeValues:index:maxCount:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, attribute, index, maxCount) as MemorySegment
}

fun NSObject.accessibilityFocusedUIElement(): MemorySegment {
    val sel = ObjCRuntime.sel("accessibilityFocusedUIElement")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSObject.accessibilityNotifiesWhenDestroyed(): Boolean {
    val sel = ObjCRuntime.sel("accessibilityNotifiesWhenDestroyed")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

// ── Category: NSAccessibilityAdditions on NSObject ─────────────────────────────────────────

fun NSObject.accessibilitySetOverrideValue_forAttribute(value: MemorySegment, attribute: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("accessibilitySetOverrideValue:forAttribute:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, value, attribute) as Boolean
}

// ── Category: NSPasteboardOwner on NSObject ─────────────────────────────────────────

fun NSObject.pasteboard_provideDataForType(sender: MemorySegment, type: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("pasteboard:provideDataForType:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender, type)
}

fun NSObject.pasteboardChangedOwner(sender: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("pasteboardChangedOwner:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender)
}

// ── Category: NSDraggingSourceDeprecated on NSObject ─────────────────────────────────────────

/** @return NSArray<NSString *> * */
fun NSObject.namesOfPromisedFilesDroppedAtDestination(dropDestination: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("namesOfPromisedFilesDroppedAtDestination:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, dropDestination) as MemorySegment
}

fun NSObject.draggingSourceOperationMaskForLocal(flag: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("draggingSourceOperationMaskForLocal:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, flag) as MemorySegment
}

fun NSObject.draggedImage_beganAt(image: MemorySegment, screenPoint: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("draggedImage:beganAt:")
    ObjCRuntime.msgSend(null, this.ptr, sel, image, screenPoint)
}

fun NSObject.draggedImage_endedAt_operation(image: MemorySegment, screenPoint: MemorySegment, operation: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("draggedImage:endedAt:operation:")
    ObjCRuntime.msgSend(null, this.ptr, sel, image, screenPoint, operation)
}

fun NSObject.draggedImage_movedTo(image: MemorySegment, screenPoint: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("draggedImage:movedTo:")
    ObjCRuntime.msgSend(null, this.ptr, sel, image, screenPoint)
}

fun NSObject.ignoreModifierKeysWhileDragging(): Boolean {
    val sel = ObjCRuntime.sel("ignoreModifierKeysWhileDragging")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSObject.draggedImage_endedAt_deposited(image: MemorySegment, screenPoint: MemorySegment, flag: Boolean): Unit {
    val sel = ObjCRuntime.sel("draggedImage:endedAt:deposited:")
    ObjCRuntime.msgSend(null, this.ptr, sel, image, screenPoint, flag)
}

// ── Category: NSLayerDelegateContentsScaleUpdating on NSObject ─────────────────────────────────────────

fun NSObject.layer_shouldInheritContentsScale_fromWindow(layer: MemorySegment, newScale: Double, window: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("layer:shouldInheritContentsScale:fromWindow:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, layer, newScale, window) as Boolean
}

// ── Category: NSToolTipOwner on NSObject ─────────────────────────────────────────

fun NSObject.view_stringForToolTip_point_userData(view: MemorySegment, tag: Long, point: MemorySegment, `data`: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("view:stringForToolTip:point:userData:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, view, tag, point, `data`) as MemorySegment
}

// ── Category: NSMenuValidation on NSObject ─────────────────────────────────────────

fun NSObject.validateMenuItem(menuItem: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("validateMenuItem:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, menuItem) as Boolean
}

// ── Category: NSKeyValueBindingCreation on NSObject ─────────────────────────────────────────

fun NSObject.valueClassForBinding(binding: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("valueClassForBinding:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, binding) as MemorySegment
}

fun NSObject.bind_toObject_withKeyPath_options(binding: MemorySegment, observable: MemorySegment, keyPath: MemorySegment, options: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("bind:toObject:withKeyPath:options:")
    ObjCRuntime.msgSend(null, this.ptr, sel, binding, observable, keyPath, options)
}

fun NSObject.unbind(binding: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("unbind:")
    ObjCRuntime.msgSend(null, this.ptr, sel, binding)
}

/** @return NSDictionary<NSBindingInfoKey,id> * */
fun NSObject.infoForBinding(binding: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("infoForBinding:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, binding) as MemorySegment
}

/** @return NSArray<NSAttributeDescription *> * */
fun NSObject.optionDescriptionsForBinding(binding: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("optionDescriptionsForBinding:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, binding) as MemorySegment
}

/** @return NSArray<NSBindingName> * */
fun NSObject.exposedBindings(): MemorySegment {
    val sel = ObjCRuntime.sel("exposedBindings")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// Class method: +[NSObject exposeBinding:]
fun NSObject_exposeBinding(binding: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("exposeBinding:")
    val cls = ObjCRuntime.getClass("NSObject")
    ObjCRuntime.msgSend(null, cls, sel, binding)
}

// ── Category: NSPlaceholders on NSObject ─────────────────────────────────────────

// Class method: +[NSObject setDefaultPlaceholder:forMarker:withBinding:]
fun NSObject_setDefaultPlaceholder_forMarker_withBinding(placeholder: MemorySegment, marker: MemorySegment, binding: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setDefaultPlaceholder:forMarker:withBinding:")
    val cls = ObjCRuntime.getClass("NSObject")
    ObjCRuntime.msgSend(null, cls, sel, placeholder, marker, binding)
}

// Class method: +[NSObject defaultPlaceholderForMarker:withBinding:]
fun NSObject_defaultPlaceholderForMarker_withBinding(marker: MemorySegment, binding: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("defaultPlaceholderForMarker:withBinding:")
    val cls = ObjCRuntime.getClass("NSObject")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, marker, binding) as MemorySegment
}

// ── Category: NSEditor on NSObject ─────────────────────────────────────────

fun NSObject.discardEditing(): Unit {
    val sel = ObjCRuntime.sel("discardEditing")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSObject.commitEditing(): Boolean {
    val sel = ObjCRuntime.sel("commitEditing")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSObject.commitEditingWithDelegate_didCommitSelector_contextInfo(delegate: MemorySegment, didCommitSelector: MemorySegment, contextInfo: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("commitEditingWithDelegate:didCommitSelector:contextInfo:")
    ObjCRuntime.msgSend(null, this.ptr, sel, delegate, didCommitSelector, contextInfo)
}

fun NSObject.commitEditingAndReturnError(error: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("commitEditingAndReturnError:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, error) as Boolean
}

// ── Category: NSEditorRegistration on NSObject ─────────────────────────────────────────

fun NSObject.objectDidBeginEditing(editor: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("objectDidBeginEditing:")
    ObjCRuntime.msgSend(null, this.ptr, sel, editor)
}

fun NSObject.objectDidEndEditing(editor: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("objectDidEndEditing:")
    ObjCRuntime.msgSend(null, this.ptr, sel, editor)
}

// ── Category: NSControlSubclassNotifications on NSObject ─────────────────────────────────────────

fun NSObject.controlTextDidBeginEditing(obj: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("controlTextDidBeginEditing:")
    ObjCRuntime.msgSend(null, this.ptr, sel, obj)
}

fun NSObject.controlTextDidEndEditing(obj: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("controlTextDidEndEditing:")
    ObjCRuntime.msgSend(null, this.ptr, sel, obj)
}

fun NSObject.controlTextDidChange(obj: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("controlTextDidChange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, obj)
}

// ── Category: NSFontManagerDelegate on NSObject ─────────────────────────────────────────

fun NSObject.fontManager_willIncludeFont(sender: MemorySegment, fontName: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("fontManager:willIncludeFont:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, sender, fontName) as Boolean
}

// ── Category: NSFontManagerResponderMethod on NSObject ─────────────────────────────────────────

fun NSObject.changeFont(sender: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("changeFont:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender)
}

// ── Category: NSFontPanelValidationAdditions on NSObject ─────────────────────────────────────────

fun NSObject.validModesForFontPanel(fontPanel: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("validModesForFontPanel:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, fontPanel) as MemorySegment
}

// ── Category: NSColorPanelResponderMethod on NSObject ─────────────────────────────────────────

fun NSObject.changeColor(sender: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("changeColor:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender)
}

// ── Category: NSNibAwaking on NSObject ─────────────────────────────────────────

fun NSObject.awakeFromNib(): Unit {
    val sel = ObjCRuntime.sel("awakeFromNib")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSObject.prepareForInterfaceBuilder(): Unit {
    val sel = ObjCRuntime.sel("prepareForInterfaceBuilder")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

// ── Category: NSSavePanelDelegateDeprecated on NSObject ─────────────────────────────────────────

fun NSObject.panel_isValidFilename(sender: MemorySegment, filename: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("panel:isValidFilename:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, sender, filename) as Boolean
}

fun NSObject.panel_directoryDidChange(sender: MemorySegment, path: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("panel:directoryDidChange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender, path)
}

fun NSObject.panel_compareFilename_with_caseSensitive(sender: MemorySegment, name1: MemorySegment, name2: MemorySegment, caseSensitive: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("panel:compareFilename:with:caseSensitive:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, sender, name1, name2, caseSensitive) as MemorySegment
}

fun NSObject.panel_shouldShowFilename(sender: MemorySegment, filename: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("panel:shouldShowFilename:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, sender, filename) as Boolean
}

// ── Category: NSDeprecatedTextStorageDelegateInterface on NSObject ─────────────────────────────────────────

fun NSObject.textStorageWillProcessEditing(notification: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("textStorageWillProcessEditing:")
    ObjCRuntime.msgSend(null, this.ptr, sel, notification)
}

fun NSObject.textStorageDidProcessEditing(notification: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("textStorageDidProcessEditing:")
    ObjCRuntime.msgSend(null, this.ptr, sel, notification)
}

// ── Category: NSToolbarItemValidation on NSObject ─────────────────────────────────────────

fun NSObject.validateToolbarItem(item: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("validateToolbarItem:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, item) as Boolean
}

// ── Category: NSTableViewDataSourceDeprecated on NSObject ─────────────────────────────────────────

fun NSObject.tableView_writeRows_toPasteboard(tableView: MemorySegment, rows: MemorySegment, pboard: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("tableView:writeRows:toPasteboard:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, tableView, rows, pboard) as Boolean
}

// ── Category: NSApplicationScriptingDelegation on NSObject ─────────────────────────────────────────

fun NSObject.application_delegateHandlesKey(sender: MemorySegment, key: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("application:delegateHandlesKey:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, sender, key) as Boolean
}

/**
 * Kotlin/JVM wrapper for root class NSObject.
 * Synthesised because it is referenced as a superclass by generated classes
 * but was not included in the framework filter set.
 */
open class NSObject(open val ptr: MemorySegment) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSObject") }
    }
    
}

