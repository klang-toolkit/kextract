package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSExpression
 * Superclass: NSObject
 * Protocols: NSSecureCoding, NSCopying
 */
open class NSExpression(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSExpression") }
        
        fun expressionWithFormat_argumentArray(expressionFormat: MemorySegment, arguments: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("expressionWithFormat:argumentArray:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, expressionFormat, arguments) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun expressionWithFormat_argumentArray(expressionFormat: String, arguments: MemorySegment): MemorySegment = expressionWithFormat_argumentArray(ObjCRuntime.newNSString(Arena.global(), expressionFormat), arguments)
        
        fun expressionWithFormat(expressionFormat: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("expressionWithFormat:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, expressionFormat) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun expressionWithFormat(expressionFormat: String): MemorySegment = expressionWithFormat(ObjCRuntime.newNSString(Arena.global(), expressionFormat))
        
        fun expressionWithFormat_arguments(expressionFormat: MemorySegment, argList: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("expressionWithFormat:arguments:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, expressionFormat, argList) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun expressionWithFormat_arguments(expressionFormat: String, argList: MemorySegment): MemorySegment = expressionWithFormat_arguments(ObjCRuntime.newNSString(Arena.global(), expressionFormat), argList)
        
        fun expressionForConstantValue(obj: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("expressionForConstantValue:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, obj) as MemorySegment
        }
        
        fun expressionForEvaluatedObject(): MemorySegment {
            val sel = ObjCRuntime.sel("expressionForEvaluatedObject")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun expressionForVariable(string: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("expressionForVariable:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, string) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun expressionForVariable(string: String): MemorySegment = expressionForVariable(ObjCRuntime.newNSString(Arena.global(), string))
        
        fun expressionForKeyPath(keyPath: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("expressionForKeyPath:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, keyPath) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun expressionForKeyPath(keyPath: String): MemorySegment = expressionForKeyPath(ObjCRuntime.newNSString(Arena.global(), keyPath))
        
        fun expressionForFunction_arguments(name: MemorySegment, parameters: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("expressionForFunction:arguments:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name, parameters) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun expressionForFunction_arguments(name: String, parameters: MemorySegment): MemorySegment = expressionForFunction_arguments(ObjCRuntime.newNSString(Arena.global(), name), parameters)
        
        fun expressionForAggregate(subexpressions: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("expressionForAggregate:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, subexpressions) as MemorySegment
        }
        
        fun expressionForUnionSet_with(left: MemorySegment, right: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("expressionForUnionSet:with:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, left, right) as MemorySegment
        }
        
        fun expressionForIntersectSet_with(left: MemorySegment, right: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("expressionForIntersectSet:with:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, left, right) as MemorySegment
        }
        
        fun expressionForMinusSet_with(left: MemorySegment, right: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("expressionForMinusSet:with:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, left, right) as MemorySegment
        }
        
        fun expressionForSubquery_usingIteratorVariable_predicate(expression: MemorySegment, variable: MemorySegment, predicate: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("expressionForSubquery:usingIteratorVariable:predicate:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, expression, variable, predicate) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun expressionForSubquery_usingIteratorVariable_predicate(expression: MemorySegment, variable: String, predicate: MemorySegment): MemorySegment = expressionForSubquery_usingIteratorVariable_predicate(expression, ObjCRuntime.newNSString(Arena.global(), variable), predicate)
        
        fun expressionForFunction_selectorName_arguments(target: MemorySegment, name: MemorySegment, parameters: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("expressionForFunction:selectorName:arguments:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, target, name, parameters) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun expressionForFunction_selectorName_arguments(target: MemorySegment, name: String, parameters: MemorySegment): MemorySegment = expressionForFunction_selectorName_arguments(target, ObjCRuntime.newNSString(Arena.global(), name), parameters)
        
        fun expressionForAnyKey(): MemorySegment {
            val sel = ObjCRuntime.sel("expressionForAnyKey")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun expressionForBlock_arguments(block: MemorySegment, arguments: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("expressionForBlock:arguments:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, block, arguments) as MemorySegment
        }
        
        fun expressionForConditional_trueExpression_falseExpression(predicate: MemorySegment, trueExpression: MemorySegment, falseExpression: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("expressionForConditional:trueExpression:falseExpression:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, predicate, trueExpression, falseExpression) as MemorySegment
        }
        
    }
    
    open fun initWithExpressionType(type: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithExpressionType:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, type) as MemorySegment
    }
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun expressionValueWithObject_context(`object`: MemorySegment, context: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("expressionValueWithObject:context:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, `object`, context) as MemorySegment
    }
    
    open fun allowEvaluation(): Unit {
        val sel = ObjCRuntime.sel("allowEvaluation")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property expressionType
    open fun expressionType(): MemorySegment {
        val sel = ObjCRuntime.sel("expressionType")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property constantValue
    open fun constantValue(): MemorySegment {
        val sel = ObjCRuntime.sel("constantValue")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property keyPath
    open fun keyPath(): MemorySegment {
        val sel = ObjCRuntime.sel("keyPath")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun keyPathAsString(): String = ObjCRuntime.toJavaString(keyPath())
    
    // @property function
    open fun function(): MemorySegment {
        val sel = ObjCRuntime.sel("function")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun functionAsString(): String = ObjCRuntime.toJavaString(function())
    
    // @property variable
    open fun variable(): MemorySegment {
        val sel = ObjCRuntime.sel("variable")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun variableAsString(): String = ObjCRuntime.toJavaString(variable())
    
    // @property operand
    open fun operand(): MemorySegment {
        val sel = ObjCRuntime.sel("operand")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property arguments
    /** @return NSArray<NSExpression *> * */
    open fun arguments(): MemorySegment {
        val sel = ObjCRuntime.sel("arguments")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property collection
    open fun collection(): MemorySegment {
        val sel = ObjCRuntime.sel("collection")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property predicate
    open fun predicate(): MemorySegment {
        val sel = ObjCRuntime.sel("predicate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property leftExpression
    open fun leftExpression(): MemorySegment {
        val sel = ObjCRuntime.sel("leftExpression")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property rightExpression
    open fun rightExpression(): MemorySegment {
        val sel = ObjCRuntime.sel("rightExpression")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property trueExpression
    open fun trueExpression(): MemorySegment {
        val sel = ObjCRuntime.sel("trueExpression")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property falseExpression
    open fun falseExpression(): MemorySegment {
        val sel = ObjCRuntime.sel("falseExpression")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property expressionBlock
    /** @return id  _Nonnull (^)(id  _Nullable __strong, NSArray<NSExpression *> * _Nonnull __strong, NSMutableDictionary * _Nullable __strong) */
    open fun expressionBlock(): MemorySegment {
        val sel = ObjCRuntime.sel("expressionBlock")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _expressionFlags: MemorySegment
    // ivar: reserved: Int
    // ivar: _expressionType: MemorySegment
}

