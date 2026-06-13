package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUnitLength
 * Superclass: NSDimension
 * Protocols: NSSecureCoding
 */
open class NSUnitLength(override val ptr: MemorySegment) : NSDimension(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUnitLength") }
        
        fun megameters(): MemorySegment {
            val sel = ObjCRuntime.sel("megameters")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun kilometers(): MemorySegment {
            val sel = ObjCRuntime.sel("kilometers")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun hectometers(): MemorySegment {
            val sel = ObjCRuntime.sel("hectometers")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun decameters(): MemorySegment {
            val sel = ObjCRuntime.sel("decameters")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun meters(): MemorySegment {
            val sel = ObjCRuntime.sel("meters")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun decimeters(): MemorySegment {
            val sel = ObjCRuntime.sel("decimeters")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun centimeters(): MemorySegment {
            val sel = ObjCRuntime.sel("centimeters")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun millimeters(): MemorySegment {
            val sel = ObjCRuntime.sel("millimeters")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun micrometers(): MemorySegment {
            val sel = ObjCRuntime.sel("micrometers")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun nanometers(): MemorySegment {
            val sel = ObjCRuntime.sel("nanometers")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun picometers(): MemorySegment {
            val sel = ObjCRuntime.sel("picometers")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun inches(): MemorySegment {
            val sel = ObjCRuntime.sel("inches")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun feet(): MemorySegment {
            val sel = ObjCRuntime.sel("feet")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun yards(): MemorySegment {
            val sel = ObjCRuntime.sel("yards")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun miles(): MemorySegment {
            val sel = ObjCRuntime.sel("miles")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun scandinavianMiles(): MemorySegment {
            val sel = ObjCRuntime.sel("scandinavianMiles")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun lightyears(): MemorySegment {
            val sel = ObjCRuntime.sel("lightyears")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun nauticalMiles(): MemorySegment {
            val sel = ObjCRuntime.sel("nauticalMiles")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun fathoms(): MemorySegment {
            val sel = ObjCRuntime.sel("fathoms")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun furlongs(): MemorySegment {
            val sel = ObjCRuntime.sel("furlongs")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun astronomicalUnits(): MemorySegment {
            val sel = ObjCRuntime.sel("astronomicalUnits")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun parsecs(): MemorySegment {
            val sel = ObjCRuntime.sel("parsecs")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    // @property megameters
    open fun megameters(): MemorySegment {
        val sel = ObjCRuntime.sel("megameters")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property kilometers
    open fun kilometers(): MemorySegment {
        val sel = ObjCRuntime.sel("kilometers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property hectometers
    open fun hectometers(): MemorySegment {
        val sel = ObjCRuntime.sel("hectometers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property decameters
    open fun decameters(): MemorySegment {
        val sel = ObjCRuntime.sel("decameters")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property meters
    open fun meters(): MemorySegment {
        val sel = ObjCRuntime.sel("meters")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property decimeters
    open fun decimeters(): MemorySegment {
        val sel = ObjCRuntime.sel("decimeters")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property centimeters
    open fun centimeters(): MemorySegment {
        val sel = ObjCRuntime.sel("centimeters")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property millimeters
    open fun millimeters(): MemorySegment {
        val sel = ObjCRuntime.sel("millimeters")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property micrometers
    open fun micrometers(): MemorySegment {
        val sel = ObjCRuntime.sel("micrometers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property nanometers
    open fun nanometers(): MemorySegment {
        val sel = ObjCRuntime.sel("nanometers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property picometers
    open fun picometers(): MemorySegment {
        val sel = ObjCRuntime.sel("picometers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property inches
    open fun inches(): MemorySegment {
        val sel = ObjCRuntime.sel("inches")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property feet
    open fun feet(): MemorySegment {
        val sel = ObjCRuntime.sel("feet")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property yards
    open fun yards(): MemorySegment {
        val sel = ObjCRuntime.sel("yards")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property miles
    open fun miles(): MemorySegment {
        val sel = ObjCRuntime.sel("miles")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property scandinavianMiles
    open fun scandinavianMiles(): MemorySegment {
        val sel = ObjCRuntime.sel("scandinavianMiles")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property lightyears
    open fun lightyears(): MemorySegment {
        val sel = ObjCRuntime.sel("lightyears")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property nauticalMiles
    open fun nauticalMiles(): MemorySegment {
        val sel = ObjCRuntime.sel("nauticalMiles")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property fathoms
    open fun fathoms(): MemorySegment {
        val sel = ObjCRuntime.sel("fathoms")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property furlongs
    open fun furlongs(): MemorySegment {
        val sel = ObjCRuntime.sel("furlongs")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property astronomicalUnits
    open fun astronomicalUnits(): MemorySegment {
        val sel = ObjCRuntime.sel("astronomicalUnits")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property parsecs
    open fun parsecs(): MemorySegment {
        val sel = ObjCRuntime.sel("parsecs")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

