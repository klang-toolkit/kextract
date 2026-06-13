package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSLocale
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding
 */
open class NSLocale(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSLocale") }
        
    }
    
    open fun objectForKey(key: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("objectForKey:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, key) as MemorySegment
    }
    
    open fun displayNameForKey_value(key: MemorySegment, value: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("displayNameForKey:value:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, key, value) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun displayNameForKey_valueAsString(key: MemorySegment, value: MemorySegment): String = ObjCRuntime.toJavaString(displayNameForKey_value(key, value))
    
    open fun initWithLocaleIdentifier(string: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithLocaleIdentifier:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, string) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithLocaleIdentifier(string: String): MemorySegment = initWithLocaleIdentifier(ObjCRuntime.newNSString(Arena.global(), string))
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
}

// ── Category: NSExtendedLocale on NSLocale ─────────────────────────────────────────

fun NSLocale.localizedStringForLocaleIdentifier(localeIdentifier: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("localizedStringForLocaleIdentifier:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, localeIdentifier) as MemorySegment
}

fun NSLocale.localizedStringForLanguageCode(languageCode: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("localizedStringForLanguageCode:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, languageCode) as MemorySegment
}

fun NSLocale.localizedStringForCountryCode(countryCode: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("localizedStringForCountryCode:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, countryCode) as MemorySegment
}

fun NSLocale.localizedStringForScriptCode(scriptCode: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("localizedStringForScriptCode:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, scriptCode) as MemorySegment
}

fun NSLocale.localizedStringForVariantCode(variantCode: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("localizedStringForVariantCode:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, variantCode) as MemorySegment
}

fun NSLocale.localizedStringForCalendarIdentifier(calendarIdentifier: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("localizedStringForCalendarIdentifier:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, calendarIdentifier) as MemorySegment
}

fun NSLocale.localizedStringForCollationIdentifier(collationIdentifier: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("localizedStringForCollationIdentifier:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, collationIdentifier) as MemorySegment
}

fun NSLocale.localizedStringForCurrencyCode(currencyCode: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("localizedStringForCurrencyCode:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, currencyCode) as MemorySegment
}

fun NSLocale.localizedStringForCollatorIdentifier(collatorIdentifier: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("localizedStringForCollatorIdentifier:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, collatorIdentifier) as MemorySegment
}

fun NSLocale.localeIdentifier(): MemorySegment {
    val sel = ObjCRuntime.sel("localeIdentifier")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSLocale.languageCode(): MemorySegment {
    val sel = ObjCRuntime.sel("languageCode")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSLocale.languageIdentifier(): MemorySegment {
    val sel = ObjCRuntime.sel("languageIdentifier")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSLocale.countryCode(): MemorySegment {
    val sel = ObjCRuntime.sel("countryCode")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSLocale.regionCode(): MemorySegment {
    val sel = ObjCRuntime.sel("regionCode")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSLocale.scriptCode(): MemorySegment {
    val sel = ObjCRuntime.sel("scriptCode")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSLocale.variantCode(): MemorySegment {
    val sel = ObjCRuntime.sel("variantCode")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSLocale.exemplarCharacterSet(): MemorySegment {
    val sel = ObjCRuntime.sel("exemplarCharacterSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSLocale.calendarIdentifier(): MemorySegment {
    val sel = ObjCRuntime.sel("calendarIdentifier")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSLocale.collationIdentifier(): MemorySegment {
    val sel = ObjCRuntime.sel("collationIdentifier")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSLocale.usesMetricSystem(): Boolean {
    val sel = ObjCRuntime.sel("usesMetricSystem")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSLocale.decimalSeparator(): MemorySegment {
    val sel = ObjCRuntime.sel("decimalSeparator")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSLocale.groupingSeparator(): MemorySegment {
    val sel = ObjCRuntime.sel("groupingSeparator")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSLocale.currencySymbol(): MemorySegment {
    val sel = ObjCRuntime.sel("currencySymbol")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSLocale.currencyCode(): MemorySegment {
    val sel = ObjCRuntime.sel("currencyCode")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSLocale.collatorIdentifier(): MemorySegment {
    val sel = ObjCRuntime.sel("collatorIdentifier")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSLocale.quotationBeginDelimiter(): MemorySegment {
    val sel = ObjCRuntime.sel("quotationBeginDelimiter")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSLocale.quotationEndDelimiter(): MemorySegment {
    val sel = ObjCRuntime.sel("quotationEndDelimiter")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSLocale.alternateQuotationBeginDelimiter(): MemorySegment {
    val sel = ObjCRuntime.sel("alternateQuotationBeginDelimiter")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSLocale.alternateQuotationEndDelimiter(): MemorySegment {
    val sel = ObjCRuntime.sel("alternateQuotationEndDelimiter")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSLocaleCreation on NSLocale ─────────────────────────────────────────

fun NSLocale.init(): MemorySegment {
    val sel = ObjCRuntime.sel("init")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// Class method: +[NSLocale localeWithLocaleIdentifier:]
fun NSLocale_localeWithLocaleIdentifier(ident: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("localeWithLocaleIdentifier:")
    val cls = ObjCRuntime.getClass("NSLocale")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, ident) as MemorySegment
}

// Class method: +[NSLocale autoupdatingCurrentLocale]
fun NSLocale_autoupdatingCurrentLocale(): MemorySegment {
    val sel = ObjCRuntime.sel("autoupdatingCurrentLocale")
    val cls = ObjCRuntime.getClass("NSLocale")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSLocale currentLocale]
fun NSLocale_currentLocale(): MemorySegment {
    val sel = ObjCRuntime.sel("currentLocale")
    val cls = ObjCRuntime.getClass("NSLocale")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSLocale systemLocale]
fun NSLocale_systemLocale(): MemorySegment {
    val sel = ObjCRuntime.sel("systemLocale")
    val cls = ObjCRuntime.getClass("NSLocale")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// @property autoupdatingCurrentLocale
fun NSLocale.autoupdatingCurrentLocale(): MemorySegment {
    val sel = ObjCRuntime.sel("autoupdatingCurrentLocale")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// @property currentLocale
fun NSLocale.currentLocale(): MemorySegment {
    val sel = ObjCRuntime.sel("currentLocale")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// @property systemLocale
fun NSLocale.systemLocale(): MemorySegment {
    val sel = ObjCRuntime.sel("systemLocale")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSLocaleGeneralInfo on NSLocale ─────────────────────────────────────────

// Class method: +[NSLocale componentsFromLocaleIdentifier:]
fun NSLocale_componentsFromLocaleIdentifier(string: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("componentsFromLocaleIdentifier:")
    val cls = ObjCRuntime.getClass("NSLocale")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, string) as MemorySegment
}

// Class method: +[NSLocale localeIdentifierFromComponents:]
fun NSLocale_localeIdentifierFromComponents(dict: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("localeIdentifierFromComponents:")
    val cls = ObjCRuntime.getClass("NSLocale")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, dict) as MemorySegment
}

// Class method: +[NSLocale canonicalLocaleIdentifierFromString:]
fun NSLocale_canonicalLocaleIdentifierFromString(string: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("canonicalLocaleIdentifierFromString:")
    val cls = ObjCRuntime.getClass("NSLocale")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, string) as MemorySegment
}

// Class method: +[NSLocale canonicalLanguageIdentifierFromString:]
fun NSLocale_canonicalLanguageIdentifierFromString(string: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("canonicalLanguageIdentifierFromString:")
    val cls = ObjCRuntime.getClass("NSLocale")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, string) as MemorySegment
}

// Class method: +[NSLocale localeIdentifierFromWindowsLocaleCode:]
fun NSLocale_localeIdentifierFromWindowsLocaleCode(lcid: Int): MemorySegment {
    val sel = ObjCRuntime.sel("localeIdentifierFromWindowsLocaleCode:")
    val cls = ObjCRuntime.getClass("NSLocale")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, lcid) as MemorySegment
}

// Class method: +[NSLocale windowsLocaleCodeFromLocaleIdentifier:]
fun NSLocale_windowsLocaleCodeFromLocaleIdentifier(localeIdentifier: MemorySegment): Int {
    val sel = ObjCRuntime.sel("windowsLocaleCodeFromLocaleIdentifier:")
    val cls = ObjCRuntime.getClass("NSLocale")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, cls, sel, localeIdentifier) as Int
}

// Class method: +[NSLocale characterDirectionForLanguage:]
fun NSLocale_characterDirectionForLanguage(isoLangCode: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("characterDirectionForLanguage:")
    val cls = ObjCRuntime.getClass("NSLocale")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, isoLangCode) as MemorySegment
}

// Class method: +[NSLocale lineDirectionForLanguage:]
fun NSLocale_lineDirectionForLanguage(isoLangCode: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("lineDirectionForLanguage:")
    val cls = ObjCRuntime.getClass("NSLocale")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, isoLangCode) as MemorySegment
}

// Class method: +[NSLocale availableLocaleIdentifiers]
fun NSLocale_availableLocaleIdentifiers(): MemorySegment {
    val sel = ObjCRuntime.sel("availableLocaleIdentifiers")
    val cls = ObjCRuntime.getClass("NSLocale")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSLocale ISOLanguageCodes]
fun NSLocale_ISOLanguageCodes(): MemorySegment {
    val sel = ObjCRuntime.sel("ISOLanguageCodes")
    val cls = ObjCRuntime.getClass("NSLocale")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSLocale ISOCountryCodes]
fun NSLocale_ISOCountryCodes(): MemorySegment {
    val sel = ObjCRuntime.sel("ISOCountryCodes")
    val cls = ObjCRuntime.getClass("NSLocale")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSLocale ISOCurrencyCodes]
fun NSLocale_ISOCurrencyCodes(): MemorySegment {
    val sel = ObjCRuntime.sel("ISOCurrencyCodes")
    val cls = ObjCRuntime.getClass("NSLocale")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSLocale commonISOCurrencyCodes]
fun NSLocale_commonISOCurrencyCodes(): MemorySegment {
    val sel = ObjCRuntime.sel("commonISOCurrencyCodes")
    val cls = ObjCRuntime.getClass("NSLocale")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSLocale preferredLanguages]
fun NSLocale_preferredLanguages(): MemorySegment {
    val sel = ObjCRuntime.sel("preferredLanguages")
    val cls = ObjCRuntime.getClass("NSLocale")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// @property availableLocaleIdentifiers
/** @return NSArray<NSString *> * */
fun NSLocale.availableLocaleIdentifiers(): MemorySegment {
    val sel = ObjCRuntime.sel("availableLocaleIdentifiers")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// @property ISOLanguageCodes
/** @return NSArray<NSString *> * */
fun NSLocale.ISOLanguageCodes(): MemorySegment {
    val sel = ObjCRuntime.sel("ISOLanguageCodes")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// @property ISOCountryCodes
/** @return NSArray<NSString *> * */
fun NSLocale.ISOCountryCodes(): MemorySegment {
    val sel = ObjCRuntime.sel("ISOCountryCodes")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// @property ISOCurrencyCodes
/** @return NSArray<NSString *> * */
fun NSLocale.ISOCurrencyCodes(): MemorySegment {
    val sel = ObjCRuntime.sel("ISOCurrencyCodes")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// @property commonISOCurrencyCodes
/** @return NSArray<NSString *> * */
fun NSLocale.commonISOCurrencyCodes(): MemorySegment {
    val sel = ObjCRuntime.sel("commonISOCurrencyCodes")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// @property preferredLanguages
/** @return NSArray<NSString *> * */
fun NSLocale.preferredLanguages(): MemorySegment {
    val sel = ObjCRuntime.sel("preferredLanguages")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

