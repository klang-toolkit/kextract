package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUnitMass
 * Superclass: NSDimension
 * Protocols: NSSecureCoding
 */
open class NSUnitMass(override val ptr: MemorySegment) : NSDimension(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUnitMass") }
        
        fun kilograms(): MemorySegment {
            val sel = ObjCRuntime.sel("kilograms")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun grams(): MemorySegment {
            val sel = ObjCRuntime.sel("grams")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun decigrams(): MemorySegment {
            val sel = ObjCRuntime.sel("decigrams")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun centigrams(): MemorySegment {
            val sel = ObjCRuntime.sel("centigrams")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun milligrams(): MemorySegment {
            val sel = ObjCRuntime.sel("milligrams")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun micrograms(): MemorySegment {
            val sel = ObjCRuntime.sel("micrograms")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun nanograms(): MemorySegment {
            val sel = ObjCRuntime.sel("nanograms")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun picograms(): MemorySegment {
            val sel = ObjCRuntime.sel("picograms")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun ounces(): MemorySegment {
            val sel = ObjCRuntime.sel("ounces")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun poundsMass(): MemorySegment {
            val sel = ObjCRuntime.sel("poundsMass")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun stones(): MemorySegment {
            val sel = ObjCRuntime.sel("stones")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun metricTons(): MemorySegment {
            val sel = ObjCRuntime.sel("metricTons")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun shortTons(): MemorySegment {
            val sel = ObjCRuntime.sel("shortTons")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun carats(): MemorySegment {
            val sel = ObjCRuntime.sel("carats")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun ouncesTroy(): MemorySegment {
            val sel = ObjCRuntime.sel("ouncesTroy")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun slugs(): MemorySegment {
            val sel = ObjCRuntime.sel("slugs")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    // @property kilograms
    open fun kilograms(): MemorySegment {
        val sel = ObjCRuntime.sel("kilograms")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property grams
    open fun grams(): MemorySegment {
        val sel = ObjCRuntime.sel("grams")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property decigrams
    open fun decigrams(): MemorySegment {
        val sel = ObjCRuntime.sel("decigrams")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property centigrams
    open fun centigrams(): MemorySegment {
        val sel = ObjCRuntime.sel("centigrams")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property milligrams
    open fun milligrams(): MemorySegment {
        val sel = ObjCRuntime.sel("milligrams")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property micrograms
    open fun micrograms(): MemorySegment {
        val sel = ObjCRuntime.sel("micrograms")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property nanograms
    open fun nanograms(): MemorySegment {
        val sel = ObjCRuntime.sel("nanograms")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property picograms
    open fun picograms(): MemorySegment {
        val sel = ObjCRuntime.sel("picograms")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property ounces
    open fun ounces(): MemorySegment {
        val sel = ObjCRuntime.sel("ounces")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property poundsMass
    open fun poundsMass(): MemorySegment {
        val sel = ObjCRuntime.sel("poundsMass")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property stones
    open fun stones(): MemorySegment {
        val sel = ObjCRuntime.sel("stones")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property metricTons
    open fun metricTons(): MemorySegment {
        val sel = ObjCRuntime.sel("metricTons")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property shortTons
    open fun shortTons(): MemorySegment {
        val sel = ObjCRuntime.sel("shortTons")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property carats
    open fun carats(): MemorySegment {
        val sel = ObjCRuntime.sel("carats")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property ouncesTroy
    open fun ouncesTroy(): MemorySegment {
        val sel = ObjCRuntime.sel("ouncesTroy")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property slugs
    open fun slugs(): MemorySegment {
        val sel = ObjCRuntime.sel("slugs")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

