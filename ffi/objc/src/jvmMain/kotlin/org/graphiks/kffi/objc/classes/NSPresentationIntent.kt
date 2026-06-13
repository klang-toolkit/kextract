package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSPresentationIntent
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding
 */
open class NSPresentationIntent(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSPresentationIntent") }
        
        fun paragraphIntentWithIdentity_nestedInsideIntent(identity: Long, parent: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("paragraphIntentWithIdentity:nestedInsideIntent:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identity, parent) as MemorySegment
        }
        
        fun headerIntentWithIdentity_level_nestedInsideIntent(identity: Long, level: Long, parent: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("headerIntentWithIdentity:level:nestedInsideIntent:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identity, level, parent) as MemorySegment
        }
        
        fun codeBlockIntentWithIdentity_languageHint_nestedInsideIntent(identity: Long, languageHint: MemorySegment, parent: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("codeBlockIntentWithIdentity:languageHint:nestedInsideIntent:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identity, languageHint, parent) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun codeBlockIntentWithIdentity_languageHint_nestedInsideIntent(identity: Long, languageHint: String, parent: MemorySegment): MemorySegment = codeBlockIntentWithIdentity_languageHint_nestedInsideIntent(identity, ObjCRuntime.newNSString(Arena.global(), languageHint), parent)
        
        fun thematicBreakIntentWithIdentity_nestedInsideIntent(identity: Long, parent: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("thematicBreakIntentWithIdentity:nestedInsideIntent:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identity, parent) as MemorySegment
        }
        
        fun orderedListIntentWithIdentity_nestedInsideIntent(identity: Long, parent: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("orderedListIntentWithIdentity:nestedInsideIntent:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identity, parent) as MemorySegment
        }
        
        fun unorderedListIntentWithIdentity_nestedInsideIntent(identity: Long, parent: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("unorderedListIntentWithIdentity:nestedInsideIntent:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identity, parent) as MemorySegment
        }
        
        fun listItemIntentWithIdentity_ordinal_nestedInsideIntent(identity: Long, ordinal: Long, parent: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("listItemIntentWithIdentity:ordinal:nestedInsideIntent:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identity, ordinal, parent) as MemorySegment
        }
        
        fun blockQuoteIntentWithIdentity_nestedInsideIntent(identity: Long, parent: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("blockQuoteIntentWithIdentity:nestedInsideIntent:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identity, parent) as MemorySegment
        }
        
        fun tableIntentWithIdentity_columnCount_alignments_nestedInsideIntent(identity: Long, columnCount: Long, alignments: MemorySegment, parent: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("tableIntentWithIdentity:columnCount:alignments:nestedInsideIntent:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identity, columnCount, alignments, parent) as MemorySegment
        }
        
        fun tableHeaderRowIntentWithIdentity_nestedInsideIntent(identity: Long, parent: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("tableHeaderRowIntentWithIdentity:nestedInsideIntent:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identity, parent) as MemorySegment
        }
        
        fun tableRowIntentWithIdentity_row_nestedInsideIntent(identity: Long, row: Long, parent: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("tableRowIntentWithIdentity:row:nestedInsideIntent:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identity, row, parent) as MemorySegment
        }
        
        fun tableCellIntentWithIdentity_column_nestedInsideIntent(identity: Long, column: Long, parent: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("tableCellIntentWithIdentity:column:nestedInsideIntent:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identity, column, parent) as MemorySegment
        }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun isEquivalentToPresentationIntent(other: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isEquivalentToPresentationIntent:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, other) as Boolean
    }
    
    // @property intentKind
    open fun intentKind(): MemorySegment {
        val sel = ObjCRuntime.sel("intentKind")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property parentIntent
    open fun parentIntent(): MemorySegment {
        val sel = ObjCRuntime.sel("parentIntent")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property identity
    open fun identity(): Long {
        val sel = ObjCRuntime.sel("identity")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property ordinal
    open fun ordinal(): Long {
        val sel = ObjCRuntime.sel("ordinal")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property columnAlignments
    /** @return NSArray<NSNumber *> * */
    open fun columnAlignments(): MemorySegment {
        val sel = ObjCRuntime.sel("columnAlignments")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property columnCount
    open fun columnCount(): Long {
        val sel = ObjCRuntime.sel("columnCount")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property headerLevel
    open fun headerLevel(): Long {
        val sel = ObjCRuntime.sel("headerLevel")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property languageHint
    open fun languageHint(): MemorySegment {
        val sel = ObjCRuntime.sel("languageHint")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun languageHintAsString(): String = ObjCRuntime.toJavaString(languageHint())
    
    // @property column
    open fun column(): Long {
        val sel = ObjCRuntime.sel("column")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property row
    open fun row(): Long {
        val sel = ObjCRuntime.sel("row")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property indentationLevel
    open fun indentationLevel(): Long {
        val sel = ObjCRuntime.sel("indentationLevel")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
}

