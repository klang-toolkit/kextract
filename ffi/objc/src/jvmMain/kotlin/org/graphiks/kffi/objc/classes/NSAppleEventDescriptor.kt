package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSAppleEventDescriptor
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding
 */
open class NSAppleEventDescriptor(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSAppleEventDescriptor") }
        
        fun nullDescriptor(): MemorySegment {
            val sel = ObjCRuntime.sel("nullDescriptor")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun descriptorWithDescriptorType_bytes_length(descriptorType: Int, bytes: MemorySegment, byteCount: Long): MemorySegment {
            val sel = ObjCRuntime.sel("descriptorWithDescriptorType:bytes:length:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, descriptorType, bytes, byteCount) as MemorySegment
        }
        
        fun descriptorWithDescriptorType_data(descriptorType: Int, `data`: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("descriptorWithDescriptorType:data:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, descriptorType, `data`) as MemorySegment
        }
        
        fun descriptorWithBoolean(boolean: Byte): MemorySegment {
            val sel = ObjCRuntime.sel("descriptorWithBoolean:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, boolean) as MemorySegment
        }
        
        fun descriptorWithEnumCode(enumerator: Int): MemorySegment {
            val sel = ObjCRuntime.sel("descriptorWithEnumCode:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, enumerator) as MemorySegment
        }
        
        fun descriptorWithInt32(signedInt: Int): MemorySegment {
            val sel = ObjCRuntime.sel("descriptorWithInt32:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, signedInt) as MemorySegment
        }
        
        fun descriptorWithDouble(doubleValue: Double): MemorySegment {
            val sel = ObjCRuntime.sel("descriptorWithDouble:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, doubleValue) as MemorySegment
        }
        
        fun descriptorWithTypeCode(typeCode: Int): MemorySegment {
            val sel = ObjCRuntime.sel("descriptorWithTypeCode:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, typeCode) as MemorySegment
        }
        
        fun descriptorWithString(string: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("descriptorWithString:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, string) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun descriptorWithString(string: String): MemorySegment = descriptorWithString(ObjCRuntime.newNSString(Arena.global(), string))
        
        fun descriptorWithDate(date: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("descriptorWithDate:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, date) as MemorySegment
        }
        
        fun descriptorWithFileURL(fileURL: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("descriptorWithFileURL:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fileURL) as MemorySegment
        }
        
        fun appleEventWithEventClass_eventID_targetDescriptor_returnID_transactionID(eventClass: Int, eventID: Int, targetDescriptor: MemorySegment, returnID: Short, transactionID: Int): MemorySegment {
            val sel = ObjCRuntime.sel("appleEventWithEventClass:eventID:targetDescriptor:returnID:transactionID:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, eventClass, eventID, targetDescriptor, returnID, transactionID) as MemorySegment
        }
        
        fun listDescriptor(): MemorySegment {
            val sel = ObjCRuntime.sel("listDescriptor")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun recordDescriptor(): MemorySegment {
            val sel = ObjCRuntime.sel("recordDescriptor")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun currentProcessDescriptor(): MemorySegment {
            val sel = ObjCRuntime.sel("currentProcessDescriptor")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun descriptorWithProcessIdentifier(processIdentifier: Int): MemorySegment {
            val sel = ObjCRuntime.sel("descriptorWithProcessIdentifier:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, processIdentifier) as MemorySegment
        }
        
        fun descriptorWithBundleIdentifier(bundleIdentifier: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("descriptorWithBundleIdentifier:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, bundleIdentifier) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun descriptorWithBundleIdentifier(bundleIdentifier: String): MemorySegment = descriptorWithBundleIdentifier(ObjCRuntime.newNSString(Arena.global(), bundleIdentifier))
        
        fun descriptorWithApplicationURL(applicationURL: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("descriptorWithApplicationURL:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, applicationURL) as MemorySegment
        }
        
    }
    
    open fun initWithAEDescNoCopy(aeDesc: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithAEDescNoCopy:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, aeDesc) as MemorySegment
    }
    
    open fun initWithDescriptorType_bytes_length(descriptorType: Int, bytes: MemorySegment, byteCount: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithDescriptorType:bytes:length:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, descriptorType, bytes, byteCount) as MemorySegment
    }
    
    open fun initWithDescriptorType_data(descriptorType: Int, `data`: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithDescriptorType:data:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, descriptorType, `data`) as MemorySegment
    }
    
    open fun initWithEventClass_eventID_targetDescriptor_returnID_transactionID(eventClass: Int, eventID: Int, targetDescriptor: MemorySegment, returnID: Short, transactionID: Int): MemorySegment {
        val sel = ObjCRuntime.sel("initWithEventClass:eventID:targetDescriptor:returnID:transactionID:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, eventClass, eventID, targetDescriptor, returnID, transactionID) as MemorySegment
    }
    
    open fun initListDescriptor(): MemorySegment {
        val sel = ObjCRuntime.sel("initListDescriptor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initRecordDescriptor(): MemorySegment {
        val sel = ObjCRuntime.sel("initRecordDescriptor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun setParamDescriptor_forKeyword(descriptor: MemorySegment, keyword: Int): Unit {
        val sel = ObjCRuntime.sel("setParamDescriptor:forKeyword:")
        ObjCRuntime.msgSend(null, ptr, sel, descriptor, keyword)
    }
    
    open fun paramDescriptorForKeyword(keyword: Int): MemorySegment {
        val sel = ObjCRuntime.sel("paramDescriptorForKeyword:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, keyword) as MemorySegment
    }
    
    open fun removeParamDescriptorWithKeyword(keyword: Int): Unit {
        val sel = ObjCRuntime.sel("removeParamDescriptorWithKeyword:")
        ObjCRuntime.msgSend(null, ptr, sel, keyword)
    }
    
    open fun setAttributeDescriptor_forKeyword(descriptor: MemorySegment, keyword: Int): Unit {
        val sel = ObjCRuntime.sel("setAttributeDescriptor:forKeyword:")
        ObjCRuntime.msgSend(null, ptr, sel, descriptor, keyword)
    }
    
    open fun attributeDescriptorForKeyword(keyword: Int): MemorySegment {
        val sel = ObjCRuntime.sel("attributeDescriptorForKeyword:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, keyword) as MemorySegment
    }
    
    open fun sendEventWithOptions_timeout_error(sendOptions: MemorySegment, timeoutInSeconds: Double, error: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("sendEventWithOptions:timeout:error:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, sendOptions, timeoutInSeconds, error) as MemorySegment
    }
    
    open fun insertDescriptor_atIndex(descriptor: MemorySegment, index: Long): Unit {
        val sel = ObjCRuntime.sel("insertDescriptor:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, descriptor, index)
    }
    
    open fun descriptorAtIndex(index: Long): MemorySegment {
        val sel = ObjCRuntime.sel("descriptorAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, index) as MemorySegment
    }
    
    open fun removeDescriptorAtIndex(index: Long): Unit {
        val sel = ObjCRuntime.sel("removeDescriptorAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, index)
    }
    
    open fun setDescriptor_forKeyword(descriptor: MemorySegment, keyword: Int): Unit {
        val sel = ObjCRuntime.sel("setDescriptor:forKeyword:")
        ObjCRuntime.msgSend(null, ptr, sel, descriptor, keyword)
    }
    
    open fun descriptorForKeyword(keyword: Int): MemorySegment {
        val sel = ObjCRuntime.sel("descriptorForKeyword:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, keyword) as MemorySegment
    }
    
    open fun removeDescriptorWithKeyword(keyword: Int): Unit {
        val sel = ObjCRuntime.sel("removeDescriptorWithKeyword:")
        ObjCRuntime.msgSend(null, ptr, sel, keyword)
    }
    
    open fun keywordForDescriptorAtIndex(index: Long): Int {
        val sel = ObjCRuntime.sel("keywordForDescriptorAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel, index) as Int
    }
    
    open fun coerceToDescriptorType(descriptorType: Int): MemorySegment {
        val sel = ObjCRuntime.sel("coerceToDescriptorType:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, descriptorType) as MemorySegment
    }
    
    // @property aeDesc
    open fun aeDesc(): MemorySegment {
        val sel = ObjCRuntime.sel("aeDesc")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property descriptorType
    open fun descriptorType(): Int {
        val sel = ObjCRuntime.sel("descriptorType")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
    // @property data
    open fun `data`(): MemorySegment {
        val sel = ObjCRuntime.sel("data")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property booleanValue
    open fun booleanValue(): Byte {
        val sel = ObjCRuntime.sel("booleanValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BYTE, ptr, sel) as Byte
    }
    
    // @property enumCodeValue
    open fun enumCodeValue(): Int {
        val sel = ObjCRuntime.sel("enumCodeValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
    // @property int32Value
    open fun int32Value(): Int {
        val sel = ObjCRuntime.sel("int32Value")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
    // @property doubleValue
    open fun doubleValue(): Double {
        val sel = ObjCRuntime.sel("doubleValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property typeCodeValue
    open fun typeCodeValue(): Int {
        val sel = ObjCRuntime.sel("typeCodeValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
    // @property stringValue
    open fun stringValue(): MemorySegment {
        val sel = ObjCRuntime.sel("stringValue")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun stringValueAsString(): String = ObjCRuntime.toJavaString(stringValue())
    
    // @property dateValue
    open fun dateValue(): MemorySegment {
        val sel = ObjCRuntime.sel("dateValue")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property fileURLValue
    open fun fileURLValue(): MemorySegment {
        val sel = ObjCRuntime.sel("fileURLValue")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property eventClass
    open fun eventClass(): Int {
        val sel = ObjCRuntime.sel("eventClass")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
    // @property eventID
    open fun eventID(): Int {
        val sel = ObjCRuntime.sel("eventID")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
    // @property returnID
    open fun returnID(): Short {
        val sel = ObjCRuntime.sel("returnID")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_SHORT, ptr, sel) as Short
    }
    
    // @property transactionID
    open fun transactionID(): Int {
        val sel = ObjCRuntime.sel("transactionID")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
    // @property isRecordDescriptor
    open fun isRecordDescriptor(): Boolean {
        val sel = ObjCRuntime.sel("isRecordDescriptor")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property numberOfItems
    open fun numberOfItems(): Long {
        val sel = ObjCRuntime.sel("numberOfItems")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _desc: MemorySegment
    // ivar: _hasValidDesc: Boolean
    // ivar: _padding: MemorySegment
}

