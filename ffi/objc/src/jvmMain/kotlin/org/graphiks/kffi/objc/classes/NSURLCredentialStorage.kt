package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSURLCredentialStorage
 * Superclass: NSObject
 */
open class NSURLCredentialStorage(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSURLCredentialStorage") }
        
        fun sharedCredentialStorage(): MemorySegment {
            val sel = ObjCRuntime.sel("sharedCredentialStorage")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    /** @return NSDictionary<NSString *,NSURLCredential *> * */
    open fun credentialsForProtectionSpace(space: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("credentialsForProtectionSpace:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, space) as MemorySegment
    }
    
    open fun setCredential_forProtectionSpace(credential: MemorySegment, space: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setCredential:forProtectionSpace:")
        ObjCRuntime.msgSend(null, ptr, sel, credential, space)
    }
    
    open fun removeCredential_forProtectionSpace(credential: MemorySegment, space: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeCredential:forProtectionSpace:")
        ObjCRuntime.msgSend(null, ptr, sel, credential, space)
    }
    
    open fun removeCredential_forProtectionSpace_options(credential: MemorySegment, space: MemorySegment, options: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeCredential:forProtectionSpace:options:")
        ObjCRuntime.msgSend(null, ptr, sel, credential, space, options)
    }
    
    open fun defaultCredentialForProtectionSpace(space: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("defaultCredentialForProtectionSpace:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, space) as MemorySegment
    }
    
    open fun setDefaultCredential_forProtectionSpace(credential: MemorySegment, space: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setDefaultCredential:forProtectionSpace:")
        ObjCRuntime.msgSend(null, ptr, sel, credential, space)
    }
    
    // @property sharedCredentialStorage
    open fun sharedCredentialStorage(): MemorySegment {
        val sel = ObjCRuntime.sel("sharedCredentialStorage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property allCredentials
    /** @return NSDictionary<NSURLProtectionSpace *,NSDictionary<NSString *,NSURLCredential *> *> * */
    open fun allCredentials(): MemorySegment {
        val sel = ObjCRuntime.sel("allCredentials")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _internal: MemorySegment
}

// ── Category: NSURLSessionTaskAdditions on NSURLCredentialStorage ─────────────────────────────────────────

fun NSURLCredentialStorage.getCredentialsForProtectionSpace_task_completionHandler(protectionSpace: MemorySegment, task: MemorySegment, completionHandler: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("getCredentialsForProtectionSpace:task:completionHandler:")
    ObjCRuntime.msgSend(null, this.ptr, sel, protectionSpace, task, completionHandler)
}

fun NSURLCredentialStorage.setCredential_forProtectionSpace_task(credential: MemorySegment, protectionSpace: MemorySegment, task: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setCredential:forProtectionSpace:task:")
    ObjCRuntime.msgSend(null, this.ptr, sel, credential, protectionSpace, task)
}

fun NSURLCredentialStorage.removeCredential_forProtectionSpace_options_task(credential: MemorySegment, protectionSpace: MemorySegment, options: MemorySegment, task: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeCredential:forProtectionSpace:options:task:")
    ObjCRuntime.msgSend(null, this.ptr, sel, credential, protectionSpace, options, task)
}

fun NSURLCredentialStorage.getDefaultCredentialForProtectionSpace_task_completionHandler(space: MemorySegment, task: MemorySegment, completionHandler: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("getDefaultCredentialForProtectionSpace:task:completionHandler:")
    ObjCRuntime.msgSend(null, this.ptr, sel, space, task, completionHandler)
}

fun NSURLCredentialStorage.setDefaultCredential_forProtectionSpace_task(credential: MemorySegment, protectionSpace: MemorySegment, task: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setDefaultCredential:forProtectionSpace:task:")
    ObjCRuntime.msgSend(null, this.ptr, sel, credential, protectionSpace, task)
}

