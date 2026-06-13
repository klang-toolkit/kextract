package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * {@snippet lang=c : NSUbiquitousUserDefaultsCompletedInitialSyncNotification typedef const NSNotificationName = (Void)*
 */
private val NSUbiquitousUserDefaultsCompletedInitialSyncNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUbiquitousUserDefaultsCompletedInitialSyncNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUbiquitousUserDefaultsCompletedInitialSyncNotification").orElseThrow() }
private val NSUbiquitousUserDefaultsCompletedInitialSyncNotification_VH: VarHandle by lazy { NSUbiquitousUserDefaultsCompletedInitialSyncNotification_LAYOUT.varHandle() }

var NSUbiquitousUserDefaultsCompletedInitialSyncNotification: MemorySegment
    get() = NSUbiquitousUserDefaultsCompletedInitialSyncNotification_VH.get(NSUbiquitousUserDefaultsCompletedInitialSyncNotification_SEGMENT) as MemorySegment
    set(value) = NSUbiquitousUserDefaultsCompletedInitialSyncNotification_VH.set(NSUbiquitousUserDefaultsCompletedInitialSyncNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSUserDefaultsDidChangeNotification typedef const NSNotificationName = (Void)*
 */
private val NSUserDefaultsDidChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUserDefaultsDidChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUserDefaultsDidChangeNotification").orElseThrow() }
private val NSUserDefaultsDidChangeNotification_VH: VarHandle by lazy { NSUserDefaultsDidChangeNotification_LAYOUT.varHandle() }

var NSUserDefaultsDidChangeNotification: MemorySegment
    get() = NSUserDefaultsDidChangeNotification_VH.get(NSUserDefaultsDidChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSUserDefaultsDidChangeNotification_VH.set(NSUserDefaultsDidChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSWeekDayNameArray (Void)*
 */
private val NSWeekDayNameArray_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWeekDayNameArray_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWeekDayNameArray").orElseThrow() }
private val NSWeekDayNameArray_VH: VarHandle by lazy { NSWeekDayNameArray_LAYOUT.varHandle() }

var NSWeekDayNameArray: MemorySegment
    get() = NSWeekDayNameArray_VH.get(NSWeekDayNameArray_SEGMENT) as MemorySegment
    set(value) = NSWeekDayNameArray_VH.set(NSWeekDayNameArray_SEGMENT, value)

/**
 * {@snippet lang=c : NSShortWeekDayNameArray (Void)*
 */
private val NSShortWeekDayNameArray_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSShortWeekDayNameArray_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSShortWeekDayNameArray").orElseThrow() }
private val NSShortWeekDayNameArray_VH: VarHandle by lazy { NSShortWeekDayNameArray_LAYOUT.varHandle() }

var NSShortWeekDayNameArray: MemorySegment
    get() = NSShortWeekDayNameArray_VH.get(NSShortWeekDayNameArray_SEGMENT) as MemorySegment
    set(value) = NSShortWeekDayNameArray_VH.set(NSShortWeekDayNameArray_SEGMENT, value)

/**
 * {@snippet lang=c : NSMonthNameArray (Void)*
 */
private val NSMonthNameArray_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMonthNameArray_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMonthNameArray").orElseThrow() }
private val NSMonthNameArray_VH: VarHandle by lazy { NSMonthNameArray_LAYOUT.varHandle() }

var NSMonthNameArray: MemorySegment
    get() = NSMonthNameArray_VH.get(NSMonthNameArray_SEGMENT) as MemorySegment
    set(value) = NSMonthNameArray_VH.set(NSMonthNameArray_SEGMENT, value)

/**
 * {@snippet lang=c : NSShortMonthNameArray (Void)*
 */
private val NSShortMonthNameArray_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSShortMonthNameArray_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSShortMonthNameArray").orElseThrow() }
private val NSShortMonthNameArray_VH: VarHandle by lazy { NSShortMonthNameArray_LAYOUT.varHandle() }

var NSShortMonthNameArray: MemorySegment
    get() = NSShortMonthNameArray_VH.get(NSShortMonthNameArray_SEGMENT) as MemorySegment
    set(value) = NSShortMonthNameArray_VH.set(NSShortMonthNameArray_SEGMENT, value)

/**
 * {@snippet lang=c : NSTimeFormatString (Void)*
 */
private val NSTimeFormatString_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTimeFormatString_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTimeFormatString").orElseThrow() }
private val NSTimeFormatString_VH: VarHandle by lazy { NSTimeFormatString_LAYOUT.varHandle() }

var NSTimeFormatString: MemorySegment
    get() = NSTimeFormatString_VH.get(NSTimeFormatString_SEGMENT) as MemorySegment
    set(value) = NSTimeFormatString_VH.set(NSTimeFormatString_SEGMENT, value)

/**
 * {@snippet lang=c : NSDateFormatString (Void)*
 */
private val NSDateFormatString_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDateFormatString_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDateFormatString").orElseThrow() }
private val NSDateFormatString_VH: VarHandle by lazy { NSDateFormatString_LAYOUT.varHandle() }

var NSDateFormatString: MemorySegment
    get() = NSDateFormatString_VH.get(NSDateFormatString_SEGMENT) as MemorySegment
    set(value) = NSDateFormatString_VH.set(NSDateFormatString_SEGMENT, value)

/**
 * {@snippet lang=c : NSTimeDateFormatString (Void)*
 */
private val NSTimeDateFormatString_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTimeDateFormatString_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTimeDateFormatString").orElseThrow() }
private val NSTimeDateFormatString_VH: VarHandle by lazy { NSTimeDateFormatString_LAYOUT.varHandle() }

var NSTimeDateFormatString: MemorySegment
    get() = NSTimeDateFormatString_VH.get(NSTimeDateFormatString_SEGMENT) as MemorySegment
    set(value) = NSTimeDateFormatString_VH.set(NSTimeDateFormatString_SEGMENT, value)

/**
 * {@snippet lang=c : NSShortTimeDateFormatString (Void)*
 */
private val NSShortTimeDateFormatString_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSShortTimeDateFormatString_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSShortTimeDateFormatString").orElseThrow() }
private val NSShortTimeDateFormatString_VH: VarHandle by lazy { NSShortTimeDateFormatString_LAYOUT.varHandle() }

var NSShortTimeDateFormatString: MemorySegment
    get() = NSShortTimeDateFormatString_VH.get(NSShortTimeDateFormatString_SEGMENT) as MemorySegment
    set(value) = NSShortTimeDateFormatString_VH.set(NSShortTimeDateFormatString_SEGMENT, value)

/**
 * {@snippet lang=c : NSCurrencySymbol (Void)*
 */
private val NSCurrencySymbol_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCurrencySymbol_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCurrencySymbol").orElseThrow() }
private val NSCurrencySymbol_VH: VarHandle by lazy { NSCurrencySymbol_LAYOUT.varHandle() }

var NSCurrencySymbol: MemorySegment
    get() = NSCurrencySymbol_VH.get(NSCurrencySymbol_SEGMENT) as MemorySegment
    set(value) = NSCurrencySymbol_VH.set(NSCurrencySymbol_SEGMENT, value)

/**
 * {@snippet lang=c : NSDecimalSeparator (Void)*
 */
private val NSDecimalSeparator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDecimalSeparator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDecimalSeparator").orElseThrow() }
private val NSDecimalSeparator_VH: VarHandle by lazy { NSDecimalSeparator_LAYOUT.varHandle() }

var NSDecimalSeparator: MemorySegment
    get() = NSDecimalSeparator_VH.get(NSDecimalSeparator_SEGMENT) as MemorySegment
    set(value) = NSDecimalSeparator_VH.set(NSDecimalSeparator_SEGMENT, value)

/**
 * {@snippet lang=c : NSThousandsSeparator (Void)*
 */
private val NSThousandsSeparator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSThousandsSeparator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSThousandsSeparator").orElseThrow() }
private val NSThousandsSeparator_VH: VarHandle by lazy { NSThousandsSeparator_LAYOUT.varHandle() }

var NSThousandsSeparator: MemorySegment
    get() = NSThousandsSeparator_VH.get(NSThousandsSeparator_SEGMENT) as MemorySegment
    set(value) = NSThousandsSeparator_VH.set(NSThousandsSeparator_SEGMENT, value)

/**
 * {@snippet lang=c : NSDecimalDigits (Void)*
 */
private val NSDecimalDigits_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDecimalDigits_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDecimalDigits").orElseThrow() }
private val NSDecimalDigits_VH: VarHandle by lazy { NSDecimalDigits_LAYOUT.varHandle() }

var NSDecimalDigits: MemorySegment
    get() = NSDecimalDigits_VH.get(NSDecimalDigits_SEGMENT) as MemorySegment
    set(value) = NSDecimalDigits_VH.set(NSDecimalDigits_SEGMENT, value)

/**
 * {@snippet lang=c : NSAMPMDesignation (Void)*
 */
private val NSAMPMDesignation_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAMPMDesignation_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAMPMDesignation").orElseThrow() }
private val NSAMPMDesignation_VH: VarHandle by lazy { NSAMPMDesignation_LAYOUT.varHandle() }

var NSAMPMDesignation: MemorySegment
    get() = NSAMPMDesignation_VH.get(NSAMPMDesignation_SEGMENT) as MemorySegment
    set(value) = NSAMPMDesignation_VH.set(NSAMPMDesignation_SEGMENT, value)

/**
 * {@snippet lang=c : NSHourNameDesignations (Void)*
 */
private val NSHourNameDesignations_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHourNameDesignations_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHourNameDesignations").orElseThrow() }
private val NSHourNameDesignations_VH: VarHandle by lazy { NSHourNameDesignations_LAYOUT.varHandle() }

var NSHourNameDesignations: MemorySegment
    get() = NSHourNameDesignations_VH.get(NSHourNameDesignations_SEGMENT) as MemorySegment
    set(value) = NSHourNameDesignations_VH.set(NSHourNameDesignations_SEGMENT, value)

/**
 * {@snippet lang=c : NSYearMonthWeekDesignations (Void)*
 */
private val NSYearMonthWeekDesignations_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSYearMonthWeekDesignations_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSYearMonthWeekDesignations").orElseThrow() }
private val NSYearMonthWeekDesignations_VH: VarHandle by lazy { NSYearMonthWeekDesignations_LAYOUT.varHandle() }

var NSYearMonthWeekDesignations: MemorySegment
    get() = NSYearMonthWeekDesignations_VH.get(NSYearMonthWeekDesignations_SEGMENT) as MemorySegment
    set(value) = NSYearMonthWeekDesignations_VH.set(NSYearMonthWeekDesignations_SEGMENT, value)

/**
 * {@snippet lang=c : NSEarlierTimeDesignations (Void)*
 */
private val NSEarlierTimeDesignations_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSEarlierTimeDesignations_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSEarlierTimeDesignations").orElseThrow() }
private val NSEarlierTimeDesignations_VH: VarHandle by lazy { NSEarlierTimeDesignations_LAYOUT.varHandle() }

var NSEarlierTimeDesignations: MemorySegment
    get() = NSEarlierTimeDesignations_VH.get(NSEarlierTimeDesignations_SEGMENT) as MemorySegment
    set(value) = NSEarlierTimeDesignations_VH.set(NSEarlierTimeDesignations_SEGMENT, value)

/**
 * {@snippet lang=c : NSLaterTimeDesignations (Void)*
 */
private val NSLaterTimeDesignations_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLaterTimeDesignations_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLaterTimeDesignations").orElseThrow() }
private val NSLaterTimeDesignations_VH: VarHandle by lazy { NSLaterTimeDesignations_LAYOUT.varHandle() }

var NSLaterTimeDesignations: MemorySegment
    get() = NSLaterTimeDesignations_VH.get(NSLaterTimeDesignations_SEGMENT) as MemorySegment
    set(value) = NSLaterTimeDesignations_VH.set(NSLaterTimeDesignations_SEGMENT, value)

/**
 * {@snippet lang=c : NSThisDayDesignations (Void)*
 */
private val NSThisDayDesignations_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSThisDayDesignations_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSThisDayDesignations").orElseThrow() }
private val NSThisDayDesignations_VH: VarHandle by lazy { NSThisDayDesignations_LAYOUT.varHandle() }

var NSThisDayDesignations: MemorySegment
    get() = NSThisDayDesignations_VH.get(NSThisDayDesignations_SEGMENT) as MemorySegment
    set(value) = NSThisDayDesignations_VH.set(NSThisDayDesignations_SEGMENT, value)

/**
 * {@snippet lang=c : NSNextDayDesignations (Void)*
 */
private val NSNextDayDesignations_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSNextDayDesignations_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSNextDayDesignations").orElseThrow() }
private val NSNextDayDesignations_VH: VarHandle by lazy { NSNextDayDesignations_LAYOUT.varHandle() }

var NSNextDayDesignations: MemorySegment
    get() = NSNextDayDesignations_VH.get(NSNextDayDesignations_SEGMENT) as MemorySegment
    set(value) = NSNextDayDesignations_VH.set(NSNextDayDesignations_SEGMENT, value)

/**
 * {@snippet lang=c : NSNextNextDayDesignations (Void)*
 */
private val NSNextNextDayDesignations_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSNextNextDayDesignations_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSNextNextDayDesignations").orElseThrow() }
private val NSNextNextDayDesignations_VH: VarHandle by lazy { NSNextNextDayDesignations_LAYOUT.varHandle() }

var NSNextNextDayDesignations: MemorySegment
    get() = NSNextNextDayDesignations_VH.get(NSNextNextDayDesignations_SEGMENT) as MemorySegment
    set(value) = NSNextNextDayDesignations_VH.set(NSNextNextDayDesignations_SEGMENT, value)

/**
 * {@snippet lang=c : NSPriorDayDesignations (Void)*
 */
private val NSPriorDayDesignations_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPriorDayDesignations_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPriorDayDesignations").orElseThrow() }
private val NSPriorDayDesignations_VH: VarHandle by lazy { NSPriorDayDesignations_LAYOUT.varHandle() }

var NSPriorDayDesignations: MemorySegment
    get() = NSPriorDayDesignations_VH.get(NSPriorDayDesignations_SEGMENT) as MemorySegment
    set(value) = NSPriorDayDesignations_VH.set(NSPriorDayDesignations_SEGMENT, value)

/**
 * {@snippet lang=c : NSDateTimeOrdering (Void)*
 */
private val NSDateTimeOrdering_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDateTimeOrdering_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDateTimeOrdering").orElseThrow() }
private val NSDateTimeOrdering_VH: VarHandle by lazy { NSDateTimeOrdering_LAYOUT.varHandle() }

var NSDateTimeOrdering: MemorySegment
    get() = NSDateTimeOrdering_VH.get(NSDateTimeOrdering_SEGMENT) as MemorySegment
    set(value) = NSDateTimeOrdering_VH.set(NSDateTimeOrdering_SEGMENT, value)

/**
 * {@snippet lang=c : NSInternationalCurrencyString (Void)*
 */
private val NSInternationalCurrencyString_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSInternationalCurrencyString_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSInternationalCurrencyString").orElseThrow() }
private val NSInternationalCurrencyString_VH: VarHandle by lazy { NSInternationalCurrencyString_LAYOUT.varHandle() }

var NSInternationalCurrencyString: MemorySegment
    get() = NSInternationalCurrencyString_VH.get(NSInternationalCurrencyString_SEGMENT) as MemorySegment
    set(value) = NSInternationalCurrencyString_VH.set(NSInternationalCurrencyString_SEGMENT, value)

/**
 * {@snippet lang=c : NSShortDateFormatString (Void)*
 */
private val NSShortDateFormatString_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSShortDateFormatString_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSShortDateFormatString").orElseThrow() }
private val NSShortDateFormatString_VH: VarHandle by lazy { NSShortDateFormatString_LAYOUT.varHandle() }

var NSShortDateFormatString: MemorySegment
    get() = NSShortDateFormatString_VH.get(NSShortDateFormatString_SEGMENT) as MemorySegment
    set(value) = NSShortDateFormatString_VH.set(NSShortDateFormatString_SEGMENT, value)

/**
 * {@snippet lang=c : NSPositiveCurrencyFormatString (Void)*
 */
private val NSPositiveCurrencyFormatString_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPositiveCurrencyFormatString_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPositiveCurrencyFormatString").orElseThrow() }
private val NSPositiveCurrencyFormatString_VH: VarHandle by lazy { NSPositiveCurrencyFormatString_LAYOUT.varHandle() }

var NSPositiveCurrencyFormatString: MemorySegment
    get() = NSPositiveCurrencyFormatString_VH.get(NSPositiveCurrencyFormatString_SEGMENT) as MemorySegment
    set(value) = NSPositiveCurrencyFormatString_VH.set(NSPositiveCurrencyFormatString_SEGMENT, value)

/**
 * {@snippet lang=c : NSNegativeCurrencyFormatString (Void)*
 */
private val NSNegativeCurrencyFormatString_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSNegativeCurrencyFormatString_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSNegativeCurrencyFormatString").orElseThrow() }
private val NSNegativeCurrencyFormatString_VH: VarHandle by lazy { NSNegativeCurrencyFormatString_LAYOUT.varHandle() }

var NSNegativeCurrencyFormatString: MemorySegment
    get() = NSNegativeCurrencyFormatString_VH.get(NSNegativeCurrencyFormatString_SEGMENT) as MemorySegment
    set(value) = NSNegativeCurrencyFormatString_VH.set(NSNegativeCurrencyFormatString_SEGMENT, value)

/**
 * {@snippet lang=c : NSNegateBooleanTransformerName typedef const NSValueTransformerName = (Void)*
 */
private val NSNegateBooleanTransformerName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSNegateBooleanTransformerName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSNegateBooleanTransformerName").orElseThrow() }
private val NSNegateBooleanTransformerName_VH: VarHandle by lazy { NSNegateBooleanTransformerName_LAYOUT.varHandle() }

var NSNegateBooleanTransformerName: MemorySegment
    get() = NSNegateBooleanTransformerName_VH.get(NSNegateBooleanTransformerName_SEGMENT) as MemorySegment
    set(value) = NSNegateBooleanTransformerName_VH.set(NSNegateBooleanTransformerName_SEGMENT, value)

/**
 * {@snippet lang=c : NSIsNilTransformerName typedef const NSValueTransformerName = (Void)*
 */
private val NSIsNilTransformerName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSIsNilTransformerName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSIsNilTransformerName").orElseThrow() }
private val NSIsNilTransformerName_VH: VarHandle by lazy { NSIsNilTransformerName_LAYOUT.varHandle() }

var NSIsNilTransformerName: MemorySegment
    get() = NSIsNilTransformerName_VH.get(NSIsNilTransformerName_SEGMENT) as MemorySegment
    set(value) = NSIsNilTransformerName_VH.set(NSIsNilTransformerName_SEGMENT, value)

/**
 * {@snippet lang=c : NSIsNotNilTransformerName typedef const NSValueTransformerName = (Void)*
 */
private val NSIsNotNilTransformerName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSIsNotNilTransformerName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSIsNotNilTransformerName").orElseThrow() }
private val NSIsNotNilTransformerName_VH: VarHandle by lazy { NSIsNotNilTransformerName_LAYOUT.varHandle() }

var NSIsNotNilTransformerName: MemorySegment
    get() = NSIsNotNilTransformerName_VH.get(NSIsNotNilTransformerName_SEGMENT) as MemorySegment
    set(value) = NSIsNotNilTransformerName_VH.set(NSIsNotNilTransformerName_SEGMENT, value)

/**
 * {@snippet lang=c : NSUnarchiveFromDataTransformerName typedef const NSValueTransformerName = (Void)*
 */
private val NSUnarchiveFromDataTransformerName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUnarchiveFromDataTransformerName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUnarchiveFromDataTransformerName").orElseThrow() }
private val NSUnarchiveFromDataTransformerName_VH: VarHandle by lazy { NSUnarchiveFromDataTransformerName_LAYOUT.varHandle() }

var NSUnarchiveFromDataTransformerName: MemorySegment
    get() = NSUnarchiveFromDataTransformerName_VH.get(NSUnarchiveFromDataTransformerName_SEGMENT) as MemorySegment
    set(value) = NSUnarchiveFromDataTransformerName_VH.set(NSUnarchiveFromDataTransformerName_SEGMENT, value)

/**
 * {@snippet lang=c : NSKeyedUnarchiveFromDataTransformerName typedef const NSValueTransformerName = (Void)*
 */
private val NSKeyedUnarchiveFromDataTransformerName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSKeyedUnarchiveFromDataTransformerName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSKeyedUnarchiveFromDataTransformerName").orElseThrow() }
private val NSKeyedUnarchiveFromDataTransformerName_VH: VarHandle by lazy { NSKeyedUnarchiveFromDataTransformerName_LAYOUT.varHandle() }

var NSKeyedUnarchiveFromDataTransformerName: MemorySegment
    get() = NSKeyedUnarchiveFromDataTransformerName_VH.get(NSKeyedUnarchiveFromDataTransformerName_SEGMENT) as MemorySegment
    set(value) = NSKeyedUnarchiveFromDataTransformerName_VH.set(NSKeyedUnarchiveFromDataTransformerName_SEGMENT, value)

/**
 * {@snippet lang=c : NSSecureUnarchiveFromDataTransformerName typedef const NSValueTransformerName = (Void)*
 */
private val NSSecureUnarchiveFromDataTransformerName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSecureUnarchiveFromDataTransformerName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSecureUnarchiveFromDataTransformerName").orElseThrow() }
private val NSSecureUnarchiveFromDataTransformerName_VH: VarHandle by lazy { NSSecureUnarchiveFromDataTransformerName_LAYOUT.varHandle() }

var NSSecureUnarchiveFromDataTransformerName: MemorySegment
    get() = NSSecureUnarchiveFromDataTransformerName_VH.get(NSSecureUnarchiveFromDataTransformerName_SEGMENT) as MemorySegment
    set(value) = NSSecureUnarchiveFromDataTransformerName_VH.set(NSSecureUnarchiveFromDataTransformerName_SEGMENT, value)

/**
 * {@snippet lang=c : NSXMLParserErrorDomain typedef const NSErrorDomain = (Void)*
 */
private val NSXMLParserErrorDomain_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSXMLParserErrorDomain_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSXMLParserErrorDomain").orElseThrow() }
private val NSXMLParserErrorDomain_VH: VarHandle by lazy { NSXMLParserErrorDomain_LAYOUT.varHandle() }

var NSXMLParserErrorDomain: MemorySegment
    get() = NSXMLParserErrorDomain_VH.get(NSXMLParserErrorDomain_SEGMENT) as MemorySegment
    set(value) = NSXMLParserErrorDomain_VH.set(NSXMLParserErrorDomain_SEGMENT, value)

/**
 * {@snippet lang=c : NSExtensionItemsAndErrorsKey (Void)*
 */
private val NSExtensionItemsAndErrorsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSExtensionItemsAndErrorsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSExtensionItemsAndErrorsKey").orElseThrow() }
private val NSExtensionItemsAndErrorsKey_VH: VarHandle by lazy { NSExtensionItemsAndErrorsKey_LAYOUT.varHandle() }

var NSExtensionItemsAndErrorsKey: MemorySegment
    get() = NSExtensionItemsAndErrorsKey_VH.get(NSExtensionItemsAndErrorsKey_SEGMENT) as MemorySegment
    set(value) = NSExtensionItemsAndErrorsKey_VH.set(NSExtensionItemsAndErrorsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSExtensionHostWillEnterForegroundNotification (Void)*
 */
private val NSExtensionHostWillEnterForegroundNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSExtensionHostWillEnterForegroundNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSExtensionHostWillEnterForegroundNotification").orElseThrow() }
private val NSExtensionHostWillEnterForegroundNotification_VH: VarHandle by lazy { NSExtensionHostWillEnterForegroundNotification_LAYOUT.varHandle() }

var NSExtensionHostWillEnterForegroundNotification: MemorySegment
    get() = NSExtensionHostWillEnterForegroundNotification_VH.get(NSExtensionHostWillEnterForegroundNotification_SEGMENT) as MemorySegment
    set(value) = NSExtensionHostWillEnterForegroundNotification_VH.set(NSExtensionHostWillEnterForegroundNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSExtensionHostDidEnterBackgroundNotification (Void)*
 */
private val NSExtensionHostDidEnterBackgroundNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSExtensionHostDidEnterBackgroundNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSExtensionHostDidEnterBackgroundNotification").orElseThrow() }
private val NSExtensionHostDidEnterBackgroundNotification_VH: VarHandle by lazy { NSExtensionHostDidEnterBackgroundNotification_LAYOUT.varHandle() }

var NSExtensionHostDidEnterBackgroundNotification: MemorySegment
    get() = NSExtensionHostDidEnterBackgroundNotification_VH.get(NSExtensionHostDidEnterBackgroundNotification_SEGMENT) as MemorySegment
    set(value) = NSExtensionHostDidEnterBackgroundNotification_VH.set(NSExtensionHostDidEnterBackgroundNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSExtensionHostWillResignActiveNotification (Void)*
 */
private val NSExtensionHostWillResignActiveNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSExtensionHostWillResignActiveNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSExtensionHostWillResignActiveNotification").orElseThrow() }
private val NSExtensionHostWillResignActiveNotification_VH: VarHandle by lazy { NSExtensionHostWillResignActiveNotification_LAYOUT.varHandle() }

var NSExtensionHostWillResignActiveNotification: MemorySegment
    get() = NSExtensionHostWillResignActiveNotification_VH.get(NSExtensionHostWillResignActiveNotification_SEGMENT) as MemorySegment
    set(value) = NSExtensionHostWillResignActiveNotification_VH.set(NSExtensionHostWillResignActiveNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSExtensionHostDidBecomeActiveNotification (Void)*
 */
private val NSExtensionHostDidBecomeActiveNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSExtensionHostDidBecomeActiveNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSExtensionHostDidBecomeActiveNotification").orElseThrow() }
private val NSExtensionHostDidBecomeActiveNotification_VH: VarHandle by lazy { NSExtensionHostDidBecomeActiveNotification_LAYOUT.varHandle() }

var NSExtensionHostDidBecomeActiveNotification: MemorySegment
    get() = NSExtensionHostDidBecomeActiveNotification_VH.get(NSExtensionHostDidBecomeActiveNotification_SEGMENT) as MemorySegment
    set(value) = NSExtensionHostDidBecomeActiveNotification_VH.set(NSExtensionHostDidBecomeActiveNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSExtensionItemAttributedTitleKey (Void)*
 */
private val NSExtensionItemAttributedTitleKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSExtensionItemAttributedTitleKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSExtensionItemAttributedTitleKey").orElseThrow() }
private val NSExtensionItemAttributedTitleKey_VH: VarHandle by lazy { NSExtensionItemAttributedTitleKey_LAYOUT.varHandle() }

var NSExtensionItemAttributedTitleKey: MemorySegment
    get() = NSExtensionItemAttributedTitleKey_VH.get(NSExtensionItemAttributedTitleKey_SEGMENT) as MemorySegment
    set(value) = NSExtensionItemAttributedTitleKey_VH.set(NSExtensionItemAttributedTitleKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSExtensionItemAttributedContentTextKey (Void)*
 */
private val NSExtensionItemAttributedContentTextKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSExtensionItemAttributedContentTextKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSExtensionItemAttributedContentTextKey").orElseThrow() }
private val NSExtensionItemAttributedContentTextKey_VH: VarHandle by lazy { NSExtensionItemAttributedContentTextKey_LAYOUT.varHandle() }

var NSExtensionItemAttributedContentTextKey: MemorySegment
    get() = NSExtensionItemAttributedContentTextKey_VH.get(NSExtensionItemAttributedContentTextKey_SEGMENT) as MemorySegment
    set(value) = NSExtensionItemAttributedContentTextKey_VH.set(NSExtensionItemAttributedContentTextKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSExtensionItemAttachmentsKey (Void)*
 */
private val NSExtensionItemAttachmentsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSExtensionItemAttachmentsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSExtensionItemAttachmentsKey").orElseThrow() }
private val NSExtensionItemAttachmentsKey_VH: VarHandle by lazy { NSExtensionItemAttachmentsKey_LAYOUT.varHandle() }

var NSExtensionItemAttachmentsKey: MemorySegment
    get() = NSExtensionItemAttachmentsKey_VH.get(NSExtensionItemAttachmentsKey_SEGMENT) as MemorySegment
    set(value) = NSExtensionItemAttachmentsKey_VH.set(NSExtensionItemAttachmentsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagSchemeTokenType typedef const NSLinguisticTagScheme = (Void)*
 */
private val NSLinguisticTagSchemeTokenType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagSchemeTokenType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagSchemeTokenType").orElseThrow() }
private val NSLinguisticTagSchemeTokenType_VH: VarHandle by lazy { NSLinguisticTagSchemeTokenType_LAYOUT.varHandle() }

var NSLinguisticTagSchemeTokenType: MemorySegment
    get() = NSLinguisticTagSchemeTokenType_VH.get(NSLinguisticTagSchemeTokenType_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagSchemeTokenType_VH.set(NSLinguisticTagSchemeTokenType_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagSchemeLexicalClass typedef const NSLinguisticTagScheme = (Void)*
 */
private val NSLinguisticTagSchemeLexicalClass_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagSchemeLexicalClass_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagSchemeLexicalClass").orElseThrow() }
private val NSLinguisticTagSchemeLexicalClass_VH: VarHandle by lazy { NSLinguisticTagSchemeLexicalClass_LAYOUT.varHandle() }

var NSLinguisticTagSchemeLexicalClass: MemorySegment
    get() = NSLinguisticTagSchemeLexicalClass_VH.get(NSLinguisticTagSchemeLexicalClass_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagSchemeLexicalClass_VH.set(NSLinguisticTagSchemeLexicalClass_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagSchemeNameType typedef const NSLinguisticTagScheme = (Void)*
 */
private val NSLinguisticTagSchemeNameType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagSchemeNameType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagSchemeNameType").orElseThrow() }
private val NSLinguisticTagSchemeNameType_VH: VarHandle by lazy { NSLinguisticTagSchemeNameType_LAYOUT.varHandle() }

var NSLinguisticTagSchemeNameType: MemorySegment
    get() = NSLinguisticTagSchemeNameType_VH.get(NSLinguisticTagSchemeNameType_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagSchemeNameType_VH.set(NSLinguisticTagSchemeNameType_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagSchemeNameTypeOrLexicalClass typedef const NSLinguisticTagScheme = (Void)*
 */
private val NSLinguisticTagSchemeNameTypeOrLexicalClass_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagSchemeNameTypeOrLexicalClass_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagSchemeNameTypeOrLexicalClass").orElseThrow() }
private val NSLinguisticTagSchemeNameTypeOrLexicalClass_VH: VarHandle by lazy { NSLinguisticTagSchemeNameTypeOrLexicalClass_LAYOUT.varHandle() }

var NSLinguisticTagSchemeNameTypeOrLexicalClass: MemorySegment
    get() = NSLinguisticTagSchemeNameTypeOrLexicalClass_VH.get(NSLinguisticTagSchemeNameTypeOrLexicalClass_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagSchemeNameTypeOrLexicalClass_VH.set(NSLinguisticTagSchemeNameTypeOrLexicalClass_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagSchemeLemma typedef const NSLinguisticTagScheme = (Void)*
 */
private val NSLinguisticTagSchemeLemma_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagSchemeLemma_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagSchemeLemma").orElseThrow() }
private val NSLinguisticTagSchemeLemma_VH: VarHandle by lazy { NSLinguisticTagSchemeLemma_LAYOUT.varHandle() }

var NSLinguisticTagSchemeLemma: MemorySegment
    get() = NSLinguisticTagSchemeLemma_VH.get(NSLinguisticTagSchemeLemma_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagSchemeLemma_VH.set(NSLinguisticTagSchemeLemma_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagSchemeLanguage typedef const NSLinguisticTagScheme = (Void)*
 */
private val NSLinguisticTagSchemeLanguage_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagSchemeLanguage_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagSchemeLanguage").orElseThrow() }
private val NSLinguisticTagSchemeLanguage_VH: VarHandle by lazy { NSLinguisticTagSchemeLanguage_LAYOUT.varHandle() }

var NSLinguisticTagSchemeLanguage: MemorySegment
    get() = NSLinguisticTagSchemeLanguage_VH.get(NSLinguisticTagSchemeLanguage_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagSchemeLanguage_VH.set(NSLinguisticTagSchemeLanguage_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagSchemeScript typedef const NSLinguisticTagScheme = (Void)*
 */
private val NSLinguisticTagSchemeScript_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagSchemeScript_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagSchemeScript").orElseThrow() }
private val NSLinguisticTagSchemeScript_VH: VarHandle by lazy { NSLinguisticTagSchemeScript_LAYOUT.varHandle() }

var NSLinguisticTagSchemeScript: MemorySegment
    get() = NSLinguisticTagSchemeScript_VH.get(NSLinguisticTagSchemeScript_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagSchemeScript_VH.set(NSLinguisticTagSchemeScript_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagWord typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagWord_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagWord_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagWord").orElseThrow() }
private val NSLinguisticTagWord_VH: VarHandle by lazy { NSLinguisticTagWord_LAYOUT.varHandle() }

var NSLinguisticTagWord: MemorySegment
    get() = NSLinguisticTagWord_VH.get(NSLinguisticTagWord_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagWord_VH.set(NSLinguisticTagWord_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagPunctuation typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagPunctuation_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagPunctuation_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagPunctuation").orElseThrow() }
private val NSLinguisticTagPunctuation_VH: VarHandle by lazy { NSLinguisticTagPunctuation_LAYOUT.varHandle() }

var NSLinguisticTagPunctuation: MemorySegment
    get() = NSLinguisticTagPunctuation_VH.get(NSLinguisticTagPunctuation_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagPunctuation_VH.set(NSLinguisticTagPunctuation_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagWhitespace typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagWhitespace_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagWhitespace_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagWhitespace").orElseThrow() }
private val NSLinguisticTagWhitespace_VH: VarHandle by lazy { NSLinguisticTagWhitespace_LAYOUT.varHandle() }

var NSLinguisticTagWhitespace: MemorySegment
    get() = NSLinguisticTagWhitespace_VH.get(NSLinguisticTagWhitespace_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagWhitespace_VH.set(NSLinguisticTagWhitespace_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagOther typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagOther_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagOther_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagOther").orElseThrow() }
private val NSLinguisticTagOther_VH: VarHandle by lazy { NSLinguisticTagOther_LAYOUT.varHandle() }

var NSLinguisticTagOther: MemorySegment
    get() = NSLinguisticTagOther_VH.get(NSLinguisticTagOther_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagOther_VH.set(NSLinguisticTagOther_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagNoun typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagNoun_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagNoun_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagNoun").orElseThrow() }
private val NSLinguisticTagNoun_VH: VarHandle by lazy { NSLinguisticTagNoun_LAYOUT.varHandle() }

var NSLinguisticTagNoun: MemorySegment
    get() = NSLinguisticTagNoun_VH.get(NSLinguisticTagNoun_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagNoun_VH.set(NSLinguisticTagNoun_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagVerb typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagVerb_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagVerb_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagVerb").orElseThrow() }
private val NSLinguisticTagVerb_VH: VarHandle by lazy { NSLinguisticTagVerb_LAYOUT.varHandle() }

var NSLinguisticTagVerb: MemorySegment
    get() = NSLinguisticTagVerb_VH.get(NSLinguisticTagVerb_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagVerb_VH.set(NSLinguisticTagVerb_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagAdjective typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagAdjective_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagAdjective_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagAdjective").orElseThrow() }
private val NSLinguisticTagAdjective_VH: VarHandle by lazy { NSLinguisticTagAdjective_LAYOUT.varHandle() }

var NSLinguisticTagAdjective: MemorySegment
    get() = NSLinguisticTagAdjective_VH.get(NSLinguisticTagAdjective_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagAdjective_VH.set(NSLinguisticTagAdjective_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagAdverb typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagAdverb_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagAdverb_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagAdverb").orElseThrow() }
private val NSLinguisticTagAdverb_VH: VarHandle by lazy { NSLinguisticTagAdverb_LAYOUT.varHandle() }

var NSLinguisticTagAdverb: MemorySegment
    get() = NSLinguisticTagAdverb_VH.get(NSLinguisticTagAdverb_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagAdverb_VH.set(NSLinguisticTagAdverb_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagPronoun typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagPronoun_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagPronoun_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagPronoun").orElseThrow() }
private val NSLinguisticTagPronoun_VH: VarHandle by lazy { NSLinguisticTagPronoun_LAYOUT.varHandle() }

var NSLinguisticTagPronoun: MemorySegment
    get() = NSLinguisticTagPronoun_VH.get(NSLinguisticTagPronoun_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagPronoun_VH.set(NSLinguisticTagPronoun_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagDeterminer typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagDeterminer_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagDeterminer_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagDeterminer").orElseThrow() }
private val NSLinguisticTagDeterminer_VH: VarHandle by lazy { NSLinguisticTagDeterminer_LAYOUT.varHandle() }

var NSLinguisticTagDeterminer: MemorySegment
    get() = NSLinguisticTagDeterminer_VH.get(NSLinguisticTagDeterminer_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagDeterminer_VH.set(NSLinguisticTagDeterminer_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagParticle typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagParticle_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagParticle_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagParticle").orElseThrow() }
private val NSLinguisticTagParticle_VH: VarHandle by lazy { NSLinguisticTagParticle_LAYOUT.varHandle() }

var NSLinguisticTagParticle: MemorySegment
    get() = NSLinguisticTagParticle_VH.get(NSLinguisticTagParticle_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagParticle_VH.set(NSLinguisticTagParticle_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagPreposition typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagPreposition_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagPreposition_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagPreposition").orElseThrow() }
private val NSLinguisticTagPreposition_VH: VarHandle by lazy { NSLinguisticTagPreposition_LAYOUT.varHandle() }

var NSLinguisticTagPreposition: MemorySegment
    get() = NSLinguisticTagPreposition_VH.get(NSLinguisticTagPreposition_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagPreposition_VH.set(NSLinguisticTagPreposition_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagNumber typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagNumber_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagNumber_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagNumber").orElseThrow() }
private val NSLinguisticTagNumber_VH: VarHandle by lazy { NSLinguisticTagNumber_LAYOUT.varHandle() }

var NSLinguisticTagNumber: MemorySegment
    get() = NSLinguisticTagNumber_VH.get(NSLinguisticTagNumber_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagNumber_VH.set(NSLinguisticTagNumber_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagConjunction typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagConjunction_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagConjunction_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagConjunction").orElseThrow() }
private val NSLinguisticTagConjunction_VH: VarHandle by lazy { NSLinguisticTagConjunction_LAYOUT.varHandle() }

var NSLinguisticTagConjunction: MemorySegment
    get() = NSLinguisticTagConjunction_VH.get(NSLinguisticTagConjunction_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagConjunction_VH.set(NSLinguisticTagConjunction_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagInterjection typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagInterjection_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagInterjection_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagInterjection").orElseThrow() }
private val NSLinguisticTagInterjection_VH: VarHandle by lazy { NSLinguisticTagInterjection_LAYOUT.varHandle() }

var NSLinguisticTagInterjection: MemorySegment
    get() = NSLinguisticTagInterjection_VH.get(NSLinguisticTagInterjection_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagInterjection_VH.set(NSLinguisticTagInterjection_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagClassifier typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagClassifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagClassifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagClassifier").orElseThrow() }
private val NSLinguisticTagClassifier_VH: VarHandle by lazy { NSLinguisticTagClassifier_LAYOUT.varHandle() }

var NSLinguisticTagClassifier: MemorySegment
    get() = NSLinguisticTagClassifier_VH.get(NSLinguisticTagClassifier_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagClassifier_VH.set(NSLinguisticTagClassifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagIdiom typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagIdiom_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagIdiom_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagIdiom").orElseThrow() }
private val NSLinguisticTagIdiom_VH: VarHandle by lazy { NSLinguisticTagIdiom_LAYOUT.varHandle() }

var NSLinguisticTagIdiom: MemorySegment
    get() = NSLinguisticTagIdiom_VH.get(NSLinguisticTagIdiom_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagIdiom_VH.set(NSLinguisticTagIdiom_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagOtherWord typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagOtherWord_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagOtherWord_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagOtherWord").orElseThrow() }
private val NSLinguisticTagOtherWord_VH: VarHandle by lazy { NSLinguisticTagOtherWord_LAYOUT.varHandle() }

var NSLinguisticTagOtherWord: MemorySegment
    get() = NSLinguisticTagOtherWord_VH.get(NSLinguisticTagOtherWord_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagOtherWord_VH.set(NSLinguisticTagOtherWord_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagSentenceTerminator typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagSentenceTerminator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagSentenceTerminator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagSentenceTerminator").orElseThrow() }
private val NSLinguisticTagSentenceTerminator_VH: VarHandle by lazy { NSLinguisticTagSentenceTerminator_LAYOUT.varHandle() }

var NSLinguisticTagSentenceTerminator: MemorySegment
    get() = NSLinguisticTagSentenceTerminator_VH.get(NSLinguisticTagSentenceTerminator_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagSentenceTerminator_VH.set(NSLinguisticTagSentenceTerminator_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagOpenQuote typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagOpenQuote_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagOpenQuote_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagOpenQuote").orElseThrow() }
private val NSLinguisticTagOpenQuote_VH: VarHandle by lazy { NSLinguisticTagOpenQuote_LAYOUT.varHandle() }

var NSLinguisticTagOpenQuote: MemorySegment
    get() = NSLinguisticTagOpenQuote_VH.get(NSLinguisticTagOpenQuote_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagOpenQuote_VH.set(NSLinguisticTagOpenQuote_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagCloseQuote typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagCloseQuote_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagCloseQuote_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagCloseQuote").orElseThrow() }
private val NSLinguisticTagCloseQuote_VH: VarHandle by lazy { NSLinguisticTagCloseQuote_LAYOUT.varHandle() }

var NSLinguisticTagCloseQuote: MemorySegment
    get() = NSLinguisticTagCloseQuote_VH.get(NSLinguisticTagCloseQuote_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagCloseQuote_VH.set(NSLinguisticTagCloseQuote_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagOpenParenthesis typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagOpenParenthesis_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagOpenParenthesis_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagOpenParenthesis").orElseThrow() }
private val NSLinguisticTagOpenParenthesis_VH: VarHandle by lazy { NSLinguisticTagOpenParenthesis_LAYOUT.varHandle() }

var NSLinguisticTagOpenParenthesis: MemorySegment
    get() = NSLinguisticTagOpenParenthesis_VH.get(NSLinguisticTagOpenParenthesis_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagOpenParenthesis_VH.set(NSLinguisticTagOpenParenthesis_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagCloseParenthesis typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagCloseParenthesis_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagCloseParenthesis_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagCloseParenthesis").orElseThrow() }
private val NSLinguisticTagCloseParenthesis_VH: VarHandle by lazy { NSLinguisticTagCloseParenthesis_LAYOUT.varHandle() }

var NSLinguisticTagCloseParenthesis: MemorySegment
    get() = NSLinguisticTagCloseParenthesis_VH.get(NSLinguisticTagCloseParenthesis_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagCloseParenthesis_VH.set(NSLinguisticTagCloseParenthesis_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagWordJoiner typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagWordJoiner_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagWordJoiner_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagWordJoiner").orElseThrow() }
private val NSLinguisticTagWordJoiner_VH: VarHandle by lazy { NSLinguisticTagWordJoiner_LAYOUT.varHandle() }

var NSLinguisticTagWordJoiner: MemorySegment
    get() = NSLinguisticTagWordJoiner_VH.get(NSLinguisticTagWordJoiner_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagWordJoiner_VH.set(NSLinguisticTagWordJoiner_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagDash typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagDash_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagDash_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagDash").orElseThrow() }
private val NSLinguisticTagDash_VH: VarHandle by lazy { NSLinguisticTagDash_LAYOUT.varHandle() }

var NSLinguisticTagDash: MemorySegment
    get() = NSLinguisticTagDash_VH.get(NSLinguisticTagDash_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagDash_VH.set(NSLinguisticTagDash_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagOtherPunctuation typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagOtherPunctuation_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagOtherPunctuation_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagOtherPunctuation").orElseThrow() }
private val NSLinguisticTagOtherPunctuation_VH: VarHandle by lazy { NSLinguisticTagOtherPunctuation_LAYOUT.varHandle() }

var NSLinguisticTagOtherPunctuation: MemorySegment
    get() = NSLinguisticTagOtherPunctuation_VH.get(NSLinguisticTagOtherPunctuation_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagOtherPunctuation_VH.set(NSLinguisticTagOtherPunctuation_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagParagraphBreak typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagParagraphBreak_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagParagraphBreak_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagParagraphBreak").orElseThrow() }
private val NSLinguisticTagParagraphBreak_VH: VarHandle by lazy { NSLinguisticTagParagraphBreak_LAYOUT.varHandle() }

var NSLinguisticTagParagraphBreak: MemorySegment
    get() = NSLinguisticTagParagraphBreak_VH.get(NSLinguisticTagParagraphBreak_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagParagraphBreak_VH.set(NSLinguisticTagParagraphBreak_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagOtherWhitespace typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagOtherWhitespace_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagOtherWhitespace_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagOtherWhitespace").orElseThrow() }
private val NSLinguisticTagOtherWhitespace_VH: VarHandle by lazy { NSLinguisticTagOtherWhitespace_LAYOUT.varHandle() }

var NSLinguisticTagOtherWhitespace: MemorySegment
    get() = NSLinguisticTagOtherWhitespace_VH.get(NSLinguisticTagOtherWhitespace_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagOtherWhitespace_VH.set(NSLinguisticTagOtherWhitespace_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagPersonalName typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagPersonalName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagPersonalName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagPersonalName").orElseThrow() }
private val NSLinguisticTagPersonalName_VH: VarHandle by lazy { NSLinguisticTagPersonalName_LAYOUT.varHandle() }

var NSLinguisticTagPersonalName: MemorySegment
    get() = NSLinguisticTagPersonalName_VH.get(NSLinguisticTagPersonalName_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagPersonalName_VH.set(NSLinguisticTagPersonalName_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagPlaceName typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagPlaceName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagPlaceName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagPlaceName").orElseThrow() }
private val NSLinguisticTagPlaceName_VH: VarHandle by lazy { NSLinguisticTagPlaceName_LAYOUT.varHandle() }

var NSLinguisticTagPlaceName: MemorySegment
    get() = NSLinguisticTagPlaceName_VH.get(NSLinguisticTagPlaceName_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagPlaceName_VH.set(NSLinguisticTagPlaceName_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinguisticTagOrganizationName typedef const NSLinguisticTag = (Void)*
 */
private val NSLinguisticTagOrganizationName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinguisticTagOrganizationName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinguisticTagOrganizationName").orElseThrow() }
private val NSLinguisticTagOrganizationName_VH: VarHandle by lazy { NSLinguisticTagOrganizationName_LAYOUT.varHandle() }

var NSLinguisticTagOrganizationName: MemorySegment
    get() = NSLinguisticTagOrganizationName_VH.get(NSLinguisticTagOrganizationName_SEGMENT) as MemorySegment
    set(value) = NSLinguisticTagOrganizationName_VH.set(NSLinguisticTagOrganizationName_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemFSNameKey (Void)*
 */
private val NSMetadataItemFSNameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemFSNameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemFSNameKey").orElseThrow() }
private val NSMetadataItemFSNameKey_VH: VarHandle by lazy { NSMetadataItemFSNameKey_LAYOUT.varHandle() }

var NSMetadataItemFSNameKey: MemorySegment
    get() = NSMetadataItemFSNameKey_VH.get(NSMetadataItemFSNameKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemFSNameKey_VH.set(NSMetadataItemFSNameKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemDisplayNameKey (Void)*
 */
private val NSMetadataItemDisplayNameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemDisplayNameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemDisplayNameKey").orElseThrow() }
private val NSMetadataItemDisplayNameKey_VH: VarHandle by lazy { NSMetadataItemDisplayNameKey_LAYOUT.varHandle() }

var NSMetadataItemDisplayNameKey: MemorySegment
    get() = NSMetadataItemDisplayNameKey_VH.get(NSMetadataItemDisplayNameKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemDisplayNameKey_VH.set(NSMetadataItemDisplayNameKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemURLKey (Void)*
 */
private val NSMetadataItemURLKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemURLKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemURLKey").orElseThrow() }
private val NSMetadataItemURLKey_VH: VarHandle by lazy { NSMetadataItemURLKey_LAYOUT.varHandle() }

var NSMetadataItemURLKey: MemorySegment
    get() = NSMetadataItemURLKey_VH.get(NSMetadataItemURLKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemURLKey_VH.set(NSMetadataItemURLKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemPathKey (Void)*
 */
private val NSMetadataItemPathKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemPathKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemPathKey").orElseThrow() }
private val NSMetadataItemPathKey_VH: VarHandle by lazy { NSMetadataItemPathKey_LAYOUT.varHandle() }

var NSMetadataItemPathKey: MemorySegment
    get() = NSMetadataItemPathKey_VH.get(NSMetadataItemPathKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemPathKey_VH.set(NSMetadataItemPathKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemFSSizeKey (Void)*
 */
private val NSMetadataItemFSSizeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemFSSizeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemFSSizeKey").orElseThrow() }
private val NSMetadataItemFSSizeKey_VH: VarHandle by lazy { NSMetadataItemFSSizeKey_LAYOUT.varHandle() }

var NSMetadataItemFSSizeKey: MemorySegment
    get() = NSMetadataItemFSSizeKey_VH.get(NSMetadataItemFSSizeKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemFSSizeKey_VH.set(NSMetadataItemFSSizeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemFSCreationDateKey (Void)*
 */
private val NSMetadataItemFSCreationDateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemFSCreationDateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemFSCreationDateKey").orElseThrow() }
private val NSMetadataItemFSCreationDateKey_VH: VarHandle by lazy { NSMetadataItemFSCreationDateKey_LAYOUT.varHandle() }

var NSMetadataItemFSCreationDateKey: MemorySegment
    get() = NSMetadataItemFSCreationDateKey_VH.get(NSMetadataItemFSCreationDateKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemFSCreationDateKey_VH.set(NSMetadataItemFSCreationDateKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemFSContentChangeDateKey (Void)*
 */
private val NSMetadataItemFSContentChangeDateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemFSContentChangeDateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemFSContentChangeDateKey").orElseThrow() }
private val NSMetadataItemFSContentChangeDateKey_VH: VarHandle by lazy { NSMetadataItemFSContentChangeDateKey_LAYOUT.varHandle() }

var NSMetadataItemFSContentChangeDateKey: MemorySegment
    get() = NSMetadataItemFSContentChangeDateKey_VH.get(NSMetadataItemFSContentChangeDateKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemFSContentChangeDateKey_VH.set(NSMetadataItemFSContentChangeDateKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemContentTypeKey (Void)*
 */
private val NSMetadataItemContentTypeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemContentTypeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemContentTypeKey").orElseThrow() }
private val NSMetadataItemContentTypeKey_VH: VarHandle by lazy { NSMetadataItemContentTypeKey_LAYOUT.varHandle() }

var NSMetadataItemContentTypeKey: MemorySegment
    get() = NSMetadataItemContentTypeKey_VH.get(NSMetadataItemContentTypeKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemContentTypeKey_VH.set(NSMetadataItemContentTypeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemContentTypeTreeKey (Void)*
 */
private val NSMetadataItemContentTypeTreeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemContentTypeTreeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemContentTypeTreeKey").orElseThrow() }
private val NSMetadataItemContentTypeTreeKey_VH: VarHandle by lazy { NSMetadataItemContentTypeTreeKey_LAYOUT.varHandle() }

var NSMetadataItemContentTypeTreeKey: MemorySegment
    get() = NSMetadataItemContentTypeTreeKey_VH.get(NSMetadataItemContentTypeTreeKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemContentTypeTreeKey_VH.set(NSMetadataItemContentTypeTreeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemIsUbiquitousKey (Void)*
 */
private val NSMetadataItemIsUbiquitousKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemIsUbiquitousKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemIsUbiquitousKey").orElseThrow() }
private val NSMetadataItemIsUbiquitousKey_VH: VarHandle by lazy { NSMetadataItemIsUbiquitousKey_LAYOUT.varHandle() }

var NSMetadataItemIsUbiquitousKey: MemorySegment
    get() = NSMetadataItemIsUbiquitousKey_VH.get(NSMetadataItemIsUbiquitousKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemIsUbiquitousKey_VH.set(NSMetadataItemIsUbiquitousKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousItemHasUnresolvedConflictsKey (Void)*
 */
private val NSMetadataUbiquitousItemHasUnresolvedConflictsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousItemHasUnresolvedConflictsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousItemHasUnresolvedConflictsKey").orElseThrow() }
private val NSMetadataUbiquitousItemHasUnresolvedConflictsKey_VH: VarHandle by lazy { NSMetadataUbiquitousItemHasUnresolvedConflictsKey_LAYOUT.varHandle() }

var NSMetadataUbiquitousItemHasUnresolvedConflictsKey: MemorySegment
    get() = NSMetadataUbiquitousItemHasUnresolvedConflictsKey_VH.get(NSMetadataUbiquitousItemHasUnresolvedConflictsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousItemHasUnresolvedConflictsKey_VH.set(NSMetadataUbiquitousItemHasUnresolvedConflictsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousItemIsDownloadedKey (Void)*
 */
private val NSMetadataUbiquitousItemIsDownloadedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousItemIsDownloadedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousItemIsDownloadedKey").orElseThrow() }
private val NSMetadataUbiquitousItemIsDownloadedKey_VH: VarHandle by lazy { NSMetadataUbiquitousItemIsDownloadedKey_LAYOUT.varHandle() }

var NSMetadataUbiquitousItemIsDownloadedKey: MemorySegment
    get() = NSMetadataUbiquitousItemIsDownloadedKey_VH.get(NSMetadataUbiquitousItemIsDownloadedKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousItemIsDownloadedKey_VH.set(NSMetadataUbiquitousItemIsDownloadedKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousItemDownloadingStatusKey (Void)*
 */
private val NSMetadataUbiquitousItemDownloadingStatusKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousItemDownloadingStatusKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousItemDownloadingStatusKey").orElseThrow() }
private val NSMetadataUbiquitousItemDownloadingStatusKey_VH: VarHandle by lazy { NSMetadataUbiquitousItemDownloadingStatusKey_LAYOUT.varHandle() }

var NSMetadataUbiquitousItemDownloadingStatusKey: MemorySegment
    get() = NSMetadataUbiquitousItemDownloadingStatusKey_VH.get(NSMetadataUbiquitousItemDownloadingStatusKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousItemDownloadingStatusKey_VH.set(NSMetadataUbiquitousItemDownloadingStatusKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousItemDownloadingStatusNotDownloaded (Void)*
 */
private val NSMetadataUbiquitousItemDownloadingStatusNotDownloaded_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousItemDownloadingStatusNotDownloaded_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousItemDownloadingStatusNotDownloaded").orElseThrow() }
private val NSMetadataUbiquitousItemDownloadingStatusNotDownloaded_VH: VarHandle by lazy { NSMetadataUbiquitousItemDownloadingStatusNotDownloaded_LAYOUT.varHandle() }

var NSMetadataUbiquitousItemDownloadingStatusNotDownloaded: MemorySegment
    get() = NSMetadataUbiquitousItemDownloadingStatusNotDownloaded_VH.get(NSMetadataUbiquitousItemDownloadingStatusNotDownloaded_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousItemDownloadingStatusNotDownloaded_VH.set(NSMetadataUbiquitousItemDownloadingStatusNotDownloaded_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousItemDownloadingStatusDownloaded (Void)*
 */
private val NSMetadataUbiquitousItemDownloadingStatusDownloaded_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousItemDownloadingStatusDownloaded_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousItemDownloadingStatusDownloaded").orElseThrow() }
private val NSMetadataUbiquitousItemDownloadingStatusDownloaded_VH: VarHandle by lazy { NSMetadataUbiquitousItemDownloadingStatusDownloaded_LAYOUT.varHandle() }

var NSMetadataUbiquitousItemDownloadingStatusDownloaded: MemorySegment
    get() = NSMetadataUbiquitousItemDownloadingStatusDownloaded_VH.get(NSMetadataUbiquitousItemDownloadingStatusDownloaded_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousItemDownloadingStatusDownloaded_VH.set(NSMetadataUbiquitousItemDownloadingStatusDownloaded_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousItemDownloadingStatusCurrent (Void)*
 */
private val NSMetadataUbiquitousItemDownloadingStatusCurrent_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousItemDownloadingStatusCurrent_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousItemDownloadingStatusCurrent").orElseThrow() }
private val NSMetadataUbiquitousItemDownloadingStatusCurrent_VH: VarHandle by lazy { NSMetadataUbiquitousItemDownloadingStatusCurrent_LAYOUT.varHandle() }

var NSMetadataUbiquitousItemDownloadingStatusCurrent: MemorySegment
    get() = NSMetadataUbiquitousItemDownloadingStatusCurrent_VH.get(NSMetadataUbiquitousItemDownloadingStatusCurrent_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousItemDownloadingStatusCurrent_VH.set(NSMetadataUbiquitousItemDownloadingStatusCurrent_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousItemIsDownloadingKey (Void)*
 */
private val NSMetadataUbiquitousItemIsDownloadingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousItemIsDownloadingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousItemIsDownloadingKey").orElseThrow() }
private val NSMetadataUbiquitousItemIsDownloadingKey_VH: VarHandle by lazy { NSMetadataUbiquitousItemIsDownloadingKey_LAYOUT.varHandle() }

var NSMetadataUbiquitousItemIsDownloadingKey: MemorySegment
    get() = NSMetadataUbiquitousItemIsDownloadingKey_VH.get(NSMetadataUbiquitousItemIsDownloadingKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousItemIsDownloadingKey_VH.set(NSMetadataUbiquitousItemIsDownloadingKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousItemIsUploadedKey (Void)*
 */
private val NSMetadataUbiquitousItemIsUploadedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousItemIsUploadedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousItemIsUploadedKey").orElseThrow() }
private val NSMetadataUbiquitousItemIsUploadedKey_VH: VarHandle by lazy { NSMetadataUbiquitousItemIsUploadedKey_LAYOUT.varHandle() }

var NSMetadataUbiquitousItemIsUploadedKey: MemorySegment
    get() = NSMetadataUbiquitousItemIsUploadedKey_VH.get(NSMetadataUbiquitousItemIsUploadedKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousItemIsUploadedKey_VH.set(NSMetadataUbiquitousItemIsUploadedKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousItemIsUploadingKey (Void)*
 */
private val NSMetadataUbiquitousItemIsUploadingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousItemIsUploadingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousItemIsUploadingKey").orElseThrow() }
private val NSMetadataUbiquitousItemIsUploadingKey_VH: VarHandle by lazy { NSMetadataUbiquitousItemIsUploadingKey_LAYOUT.varHandle() }

var NSMetadataUbiquitousItemIsUploadingKey: MemorySegment
    get() = NSMetadataUbiquitousItemIsUploadingKey_VH.get(NSMetadataUbiquitousItemIsUploadingKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousItemIsUploadingKey_VH.set(NSMetadataUbiquitousItemIsUploadingKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousItemPercentDownloadedKey (Void)*
 */
private val NSMetadataUbiquitousItemPercentDownloadedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousItemPercentDownloadedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousItemPercentDownloadedKey").orElseThrow() }
private val NSMetadataUbiquitousItemPercentDownloadedKey_VH: VarHandle by lazy { NSMetadataUbiquitousItemPercentDownloadedKey_LAYOUT.varHandle() }

var NSMetadataUbiquitousItemPercentDownloadedKey: MemorySegment
    get() = NSMetadataUbiquitousItemPercentDownloadedKey_VH.get(NSMetadataUbiquitousItemPercentDownloadedKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousItemPercentDownloadedKey_VH.set(NSMetadataUbiquitousItemPercentDownloadedKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousItemPercentUploadedKey (Void)*
 */
private val NSMetadataUbiquitousItemPercentUploadedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousItemPercentUploadedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousItemPercentUploadedKey").orElseThrow() }
private val NSMetadataUbiquitousItemPercentUploadedKey_VH: VarHandle by lazy { NSMetadataUbiquitousItemPercentUploadedKey_LAYOUT.varHandle() }

var NSMetadataUbiquitousItemPercentUploadedKey: MemorySegment
    get() = NSMetadataUbiquitousItemPercentUploadedKey_VH.get(NSMetadataUbiquitousItemPercentUploadedKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousItemPercentUploadedKey_VH.set(NSMetadataUbiquitousItemPercentUploadedKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousItemDownloadingErrorKey (Void)*
 */
private val NSMetadataUbiquitousItemDownloadingErrorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousItemDownloadingErrorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousItemDownloadingErrorKey").orElseThrow() }
private val NSMetadataUbiquitousItemDownloadingErrorKey_VH: VarHandle by lazy { NSMetadataUbiquitousItemDownloadingErrorKey_LAYOUT.varHandle() }

var NSMetadataUbiquitousItemDownloadingErrorKey: MemorySegment
    get() = NSMetadataUbiquitousItemDownloadingErrorKey_VH.get(NSMetadataUbiquitousItemDownloadingErrorKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousItemDownloadingErrorKey_VH.set(NSMetadataUbiquitousItemDownloadingErrorKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousItemUploadingErrorKey (Void)*
 */
private val NSMetadataUbiquitousItemUploadingErrorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousItemUploadingErrorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousItemUploadingErrorKey").orElseThrow() }
private val NSMetadataUbiquitousItemUploadingErrorKey_VH: VarHandle by lazy { NSMetadataUbiquitousItemUploadingErrorKey_LAYOUT.varHandle() }

var NSMetadataUbiquitousItemUploadingErrorKey: MemorySegment
    get() = NSMetadataUbiquitousItemUploadingErrorKey_VH.get(NSMetadataUbiquitousItemUploadingErrorKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousItemUploadingErrorKey_VH.set(NSMetadataUbiquitousItemUploadingErrorKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousItemDownloadRequestedKey (Void)*
 */
private val NSMetadataUbiquitousItemDownloadRequestedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousItemDownloadRequestedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousItemDownloadRequestedKey").orElseThrow() }
private val NSMetadataUbiquitousItemDownloadRequestedKey_VH: VarHandle by lazy { NSMetadataUbiquitousItemDownloadRequestedKey_LAYOUT.varHandle() }

var NSMetadataUbiquitousItemDownloadRequestedKey: MemorySegment
    get() = NSMetadataUbiquitousItemDownloadRequestedKey_VH.get(NSMetadataUbiquitousItemDownloadRequestedKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousItemDownloadRequestedKey_VH.set(NSMetadataUbiquitousItemDownloadRequestedKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousItemIsExternalDocumentKey (Void)*
 */
private val NSMetadataUbiquitousItemIsExternalDocumentKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousItemIsExternalDocumentKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousItemIsExternalDocumentKey").orElseThrow() }
private val NSMetadataUbiquitousItemIsExternalDocumentKey_VH: VarHandle by lazy { NSMetadataUbiquitousItemIsExternalDocumentKey_LAYOUT.varHandle() }

var NSMetadataUbiquitousItemIsExternalDocumentKey: MemorySegment
    get() = NSMetadataUbiquitousItemIsExternalDocumentKey_VH.get(NSMetadataUbiquitousItemIsExternalDocumentKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousItemIsExternalDocumentKey_VH.set(NSMetadataUbiquitousItemIsExternalDocumentKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousItemContainerDisplayNameKey (Void)*
 */
private val NSMetadataUbiquitousItemContainerDisplayNameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousItemContainerDisplayNameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousItemContainerDisplayNameKey").orElseThrow() }
private val NSMetadataUbiquitousItemContainerDisplayNameKey_VH: VarHandle by lazy { NSMetadataUbiquitousItemContainerDisplayNameKey_LAYOUT.varHandle() }

var NSMetadataUbiquitousItemContainerDisplayNameKey: MemorySegment
    get() = NSMetadataUbiquitousItemContainerDisplayNameKey_VH.get(NSMetadataUbiquitousItemContainerDisplayNameKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousItemContainerDisplayNameKey_VH.set(NSMetadataUbiquitousItemContainerDisplayNameKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousItemURLInLocalContainerKey (Void)*
 */
private val NSMetadataUbiquitousItemURLInLocalContainerKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousItemURLInLocalContainerKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousItemURLInLocalContainerKey").orElseThrow() }
private val NSMetadataUbiquitousItemURLInLocalContainerKey_VH: VarHandle by lazy { NSMetadataUbiquitousItemURLInLocalContainerKey_LAYOUT.varHandle() }

var NSMetadataUbiquitousItemURLInLocalContainerKey: MemorySegment
    get() = NSMetadataUbiquitousItemURLInLocalContainerKey_VH.get(NSMetadataUbiquitousItemURLInLocalContainerKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousItemURLInLocalContainerKey_VH.set(NSMetadataUbiquitousItemURLInLocalContainerKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousItemIsSharedKey (Void)*
 */
private val NSMetadataUbiquitousItemIsSharedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousItemIsSharedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousItemIsSharedKey").orElseThrow() }
private val NSMetadataUbiquitousItemIsSharedKey_VH: VarHandle by lazy { NSMetadataUbiquitousItemIsSharedKey_LAYOUT.varHandle() }

var NSMetadataUbiquitousItemIsSharedKey: MemorySegment
    get() = NSMetadataUbiquitousItemIsSharedKey_VH.get(NSMetadataUbiquitousItemIsSharedKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousItemIsSharedKey_VH.set(NSMetadataUbiquitousItemIsSharedKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousSharedItemCurrentUserRoleKey (Void)*
 */
private val NSMetadataUbiquitousSharedItemCurrentUserRoleKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousSharedItemCurrentUserRoleKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousSharedItemCurrentUserRoleKey").orElseThrow() }
private val NSMetadataUbiquitousSharedItemCurrentUserRoleKey_VH: VarHandle by lazy { NSMetadataUbiquitousSharedItemCurrentUserRoleKey_LAYOUT.varHandle() }

var NSMetadataUbiquitousSharedItemCurrentUserRoleKey: MemorySegment
    get() = NSMetadataUbiquitousSharedItemCurrentUserRoleKey_VH.get(NSMetadataUbiquitousSharedItemCurrentUserRoleKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousSharedItemCurrentUserRoleKey_VH.set(NSMetadataUbiquitousSharedItemCurrentUserRoleKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousSharedItemCurrentUserPermissionsKey (Void)*
 */
private val NSMetadataUbiquitousSharedItemCurrentUserPermissionsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousSharedItemCurrentUserPermissionsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousSharedItemCurrentUserPermissionsKey").orElseThrow() }
private val NSMetadataUbiquitousSharedItemCurrentUserPermissionsKey_VH: VarHandle by lazy { NSMetadataUbiquitousSharedItemCurrentUserPermissionsKey_LAYOUT.varHandle() }

var NSMetadataUbiquitousSharedItemCurrentUserPermissionsKey: MemorySegment
    get() = NSMetadataUbiquitousSharedItemCurrentUserPermissionsKey_VH.get(NSMetadataUbiquitousSharedItemCurrentUserPermissionsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousSharedItemCurrentUserPermissionsKey_VH.set(NSMetadataUbiquitousSharedItemCurrentUserPermissionsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousSharedItemOwnerNameComponentsKey (Void)*
 */
private val NSMetadataUbiquitousSharedItemOwnerNameComponentsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousSharedItemOwnerNameComponentsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousSharedItemOwnerNameComponentsKey").orElseThrow() }
private val NSMetadataUbiquitousSharedItemOwnerNameComponentsKey_VH: VarHandle by lazy { NSMetadataUbiquitousSharedItemOwnerNameComponentsKey_LAYOUT.varHandle() }

var NSMetadataUbiquitousSharedItemOwnerNameComponentsKey: MemorySegment
    get() = NSMetadataUbiquitousSharedItemOwnerNameComponentsKey_VH.get(NSMetadataUbiquitousSharedItemOwnerNameComponentsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousSharedItemOwnerNameComponentsKey_VH.set(NSMetadataUbiquitousSharedItemOwnerNameComponentsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousSharedItemMostRecentEditorNameComponentsKey (Void)*
 */
private val NSMetadataUbiquitousSharedItemMostRecentEditorNameComponentsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousSharedItemMostRecentEditorNameComponentsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousSharedItemMostRecentEditorNameComponentsKey").orElseThrow() }
private val NSMetadataUbiquitousSharedItemMostRecentEditorNameComponentsKey_VH: VarHandle by lazy { NSMetadataUbiquitousSharedItemMostRecentEditorNameComponentsKey_LAYOUT.varHandle() }

var NSMetadataUbiquitousSharedItemMostRecentEditorNameComponentsKey: MemorySegment
    get() = NSMetadataUbiquitousSharedItemMostRecentEditorNameComponentsKey_VH.get(NSMetadataUbiquitousSharedItemMostRecentEditorNameComponentsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousSharedItemMostRecentEditorNameComponentsKey_VH.set(NSMetadataUbiquitousSharedItemMostRecentEditorNameComponentsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousSharedItemRoleOwner (Void)*
 */
private val NSMetadataUbiquitousSharedItemRoleOwner_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousSharedItemRoleOwner_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousSharedItemRoleOwner").orElseThrow() }
private val NSMetadataUbiquitousSharedItemRoleOwner_VH: VarHandle by lazy { NSMetadataUbiquitousSharedItemRoleOwner_LAYOUT.varHandle() }

var NSMetadataUbiquitousSharedItemRoleOwner: MemorySegment
    get() = NSMetadataUbiquitousSharedItemRoleOwner_VH.get(NSMetadataUbiquitousSharedItemRoleOwner_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousSharedItemRoleOwner_VH.set(NSMetadataUbiquitousSharedItemRoleOwner_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousSharedItemRoleParticipant (Void)*
 */
private val NSMetadataUbiquitousSharedItemRoleParticipant_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousSharedItemRoleParticipant_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousSharedItemRoleParticipant").orElseThrow() }
private val NSMetadataUbiquitousSharedItemRoleParticipant_VH: VarHandle by lazy { NSMetadataUbiquitousSharedItemRoleParticipant_LAYOUT.varHandle() }

var NSMetadataUbiquitousSharedItemRoleParticipant: MemorySegment
    get() = NSMetadataUbiquitousSharedItemRoleParticipant_VH.get(NSMetadataUbiquitousSharedItemRoleParticipant_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousSharedItemRoleParticipant_VH.set(NSMetadataUbiquitousSharedItemRoleParticipant_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousSharedItemPermissionsReadOnly (Void)*
 */
private val NSMetadataUbiquitousSharedItemPermissionsReadOnly_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousSharedItemPermissionsReadOnly_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousSharedItemPermissionsReadOnly").orElseThrow() }
private val NSMetadataUbiquitousSharedItemPermissionsReadOnly_VH: VarHandle by lazy { NSMetadataUbiquitousSharedItemPermissionsReadOnly_LAYOUT.varHandle() }

var NSMetadataUbiquitousSharedItemPermissionsReadOnly: MemorySegment
    get() = NSMetadataUbiquitousSharedItemPermissionsReadOnly_VH.get(NSMetadataUbiquitousSharedItemPermissionsReadOnly_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousSharedItemPermissionsReadOnly_VH.set(NSMetadataUbiquitousSharedItemPermissionsReadOnly_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataUbiquitousSharedItemPermissionsReadWrite (Void)*
 */
private val NSMetadataUbiquitousSharedItemPermissionsReadWrite_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataUbiquitousSharedItemPermissionsReadWrite_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataUbiquitousSharedItemPermissionsReadWrite").orElseThrow() }
private val NSMetadataUbiquitousSharedItemPermissionsReadWrite_VH: VarHandle by lazy { NSMetadataUbiquitousSharedItemPermissionsReadWrite_LAYOUT.varHandle() }

var NSMetadataUbiquitousSharedItemPermissionsReadWrite: MemorySegment
    get() = NSMetadataUbiquitousSharedItemPermissionsReadWrite_VH.get(NSMetadataUbiquitousSharedItemPermissionsReadWrite_SEGMENT) as MemorySegment
    set(value) = NSMetadataUbiquitousSharedItemPermissionsReadWrite_VH.set(NSMetadataUbiquitousSharedItemPermissionsReadWrite_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemAttributeChangeDateKey (Void)*
 */
private val NSMetadataItemAttributeChangeDateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemAttributeChangeDateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemAttributeChangeDateKey").orElseThrow() }
private val NSMetadataItemAttributeChangeDateKey_VH: VarHandle by lazy { NSMetadataItemAttributeChangeDateKey_LAYOUT.varHandle() }

var NSMetadataItemAttributeChangeDateKey: MemorySegment
    get() = NSMetadataItemAttributeChangeDateKey_VH.get(NSMetadataItemAttributeChangeDateKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemAttributeChangeDateKey_VH.set(NSMetadataItemAttributeChangeDateKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemKeywordsKey (Void)*
 */
private val NSMetadataItemKeywordsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemKeywordsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemKeywordsKey").orElseThrow() }
private val NSMetadataItemKeywordsKey_VH: VarHandle by lazy { NSMetadataItemKeywordsKey_LAYOUT.varHandle() }

var NSMetadataItemKeywordsKey: MemorySegment
    get() = NSMetadataItemKeywordsKey_VH.get(NSMetadataItemKeywordsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemKeywordsKey_VH.set(NSMetadataItemKeywordsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemTitleKey (Void)*
 */
private val NSMetadataItemTitleKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemTitleKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemTitleKey").orElseThrow() }
private val NSMetadataItemTitleKey_VH: VarHandle by lazy { NSMetadataItemTitleKey_LAYOUT.varHandle() }

var NSMetadataItemTitleKey: MemorySegment
    get() = NSMetadataItemTitleKey_VH.get(NSMetadataItemTitleKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemTitleKey_VH.set(NSMetadataItemTitleKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemAuthorsKey (Void)*
 */
private val NSMetadataItemAuthorsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemAuthorsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemAuthorsKey").orElseThrow() }
private val NSMetadataItemAuthorsKey_VH: VarHandle by lazy { NSMetadataItemAuthorsKey_LAYOUT.varHandle() }

var NSMetadataItemAuthorsKey: MemorySegment
    get() = NSMetadataItemAuthorsKey_VH.get(NSMetadataItemAuthorsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemAuthorsKey_VH.set(NSMetadataItemAuthorsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemEditorsKey (Void)*
 */
private val NSMetadataItemEditorsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemEditorsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemEditorsKey").orElseThrow() }
private val NSMetadataItemEditorsKey_VH: VarHandle by lazy { NSMetadataItemEditorsKey_LAYOUT.varHandle() }

var NSMetadataItemEditorsKey: MemorySegment
    get() = NSMetadataItemEditorsKey_VH.get(NSMetadataItemEditorsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemEditorsKey_VH.set(NSMetadataItemEditorsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemParticipantsKey (Void)*
 */
private val NSMetadataItemParticipantsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemParticipantsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemParticipantsKey").orElseThrow() }
private val NSMetadataItemParticipantsKey_VH: VarHandle by lazy { NSMetadataItemParticipantsKey_LAYOUT.varHandle() }

var NSMetadataItemParticipantsKey: MemorySegment
    get() = NSMetadataItemParticipantsKey_VH.get(NSMetadataItemParticipantsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemParticipantsKey_VH.set(NSMetadataItemParticipantsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemProjectsKey (Void)*
 */
private val NSMetadataItemProjectsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemProjectsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemProjectsKey").orElseThrow() }
private val NSMetadataItemProjectsKey_VH: VarHandle by lazy { NSMetadataItemProjectsKey_LAYOUT.varHandle() }

var NSMetadataItemProjectsKey: MemorySegment
    get() = NSMetadataItemProjectsKey_VH.get(NSMetadataItemProjectsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemProjectsKey_VH.set(NSMetadataItemProjectsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemDownloadedDateKey (Void)*
 */
private val NSMetadataItemDownloadedDateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemDownloadedDateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemDownloadedDateKey").orElseThrow() }
private val NSMetadataItemDownloadedDateKey_VH: VarHandle by lazy { NSMetadataItemDownloadedDateKey_LAYOUT.varHandle() }

var NSMetadataItemDownloadedDateKey: MemorySegment
    get() = NSMetadataItemDownloadedDateKey_VH.get(NSMetadataItemDownloadedDateKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemDownloadedDateKey_VH.set(NSMetadataItemDownloadedDateKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemWhereFromsKey (Void)*
 */
private val NSMetadataItemWhereFromsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemWhereFromsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemWhereFromsKey").orElseThrow() }
private val NSMetadataItemWhereFromsKey_VH: VarHandle by lazy { NSMetadataItemWhereFromsKey_LAYOUT.varHandle() }

var NSMetadataItemWhereFromsKey: MemorySegment
    get() = NSMetadataItemWhereFromsKey_VH.get(NSMetadataItemWhereFromsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemWhereFromsKey_VH.set(NSMetadataItemWhereFromsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemCommentKey (Void)*
 */
private val NSMetadataItemCommentKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemCommentKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemCommentKey").orElseThrow() }
private val NSMetadataItemCommentKey_VH: VarHandle by lazy { NSMetadataItemCommentKey_LAYOUT.varHandle() }

var NSMetadataItemCommentKey: MemorySegment
    get() = NSMetadataItemCommentKey_VH.get(NSMetadataItemCommentKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemCommentKey_VH.set(NSMetadataItemCommentKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemCopyrightKey (Void)*
 */
private val NSMetadataItemCopyrightKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemCopyrightKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemCopyrightKey").orElseThrow() }
private val NSMetadataItemCopyrightKey_VH: VarHandle by lazy { NSMetadataItemCopyrightKey_LAYOUT.varHandle() }

var NSMetadataItemCopyrightKey: MemorySegment
    get() = NSMetadataItemCopyrightKey_VH.get(NSMetadataItemCopyrightKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemCopyrightKey_VH.set(NSMetadataItemCopyrightKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemLastUsedDateKey (Void)*
 */
private val NSMetadataItemLastUsedDateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemLastUsedDateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemLastUsedDateKey").orElseThrow() }
private val NSMetadataItemLastUsedDateKey_VH: VarHandle by lazy { NSMetadataItemLastUsedDateKey_LAYOUT.varHandle() }

var NSMetadataItemLastUsedDateKey: MemorySegment
    get() = NSMetadataItemLastUsedDateKey_VH.get(NSMetadataItemLastUsedDateKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemLastUsedDateKey_VH.set(NSMetadataItemLastUsedDateKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemContentCreationDateKey (Void)*
 */
private val NSMetadataItemContentCreationDateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemContentCreationDateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemContentCreationDateKey").orElseThrow() }
private val NSMetadataItemContentCreationDateKey_VH: VarHandle by lazy { NSMetadataItemContentCreationDateKey_LAYOUT.varHandle() }

var NSMetadataItemContentCreationDateKey: MemorySegment
    get() = NSMetadataItemContentCreationDateKey_VH.get(NSMetadataItemContentCreationDateKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemContentCreationDateKey_VH.set(NSMetadataItemContentCreationDateKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemContentModificationDateKey (Void)*
 */
private val NSMetadataItemContentModificationDateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemContentModificationDateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemContentModificationDateKey").orElseThrow() }
private val NSMetadataItemContentModificationDateKey_VH: VarHandle by lazy { NSMetadataItemContentModificationDateKey_LAYOUT.varHandle() }

var NSMetadataItemContentModificationDateKey: MemorySegment
    get() = NSMetadataItemContentModificationDateKey_VH.get(NSMetadataItemContentModificationDateKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemContentModificationDateKey_VH.set(NSMetadataItemContentModificationDateKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemDateAddedKey (Void)*
 */
private val NSMetadataItemDateAddedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemDateAddedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemDateAddedKey").orElseThrow() }
private val NSMetadataItemDateAddedKey_VH: VarHandle by lazy { NSMetadataItemDateAddedKey_LAYOUT.varHandle() }

var NSMetadataItemDateAddedKey: MemorySegment
    get() = NSMetadataItemDateAddedKey_VH.get(NSMetadataItemDateAddedKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemDateAddedKey_VH.set(NSMetadataItemDateAddedKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemDurationSecondsKey (Void)*
 */
private val NSMetadataItemDurationSecondsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemDurationSecondsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemDurationSecondsKey").orElseThrow() }
private val NSMetadataItemDurationSecondsKey_VH: VarHandle by lazy { NSMetadataItemDurationSecondsKey_LAYOUT.varHandle() }

var NSMetadataItemDurationSecondsKey: MemorySegment
    get() = NSMetadataItemDurationSecondsKey_VH.get(NSMetadataItemDurationSecondsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemDurationSecondsKey_VH.set(NSMetadataItemDurationSecondsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemContactKeywordsKey (Void)*
 */
private val NSMetadataItemContactKeywordsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemContactKeywordsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemContactKeywordsKey").orElseThrow() }
private val NSMetadataItemContactKeywordsKey_VH: VarHandle by lazy { NSMetadataItemContactKeywordsKey_LAYOUT.varHandle() }

var NSMetadataItemContactKeywordsKey: MemorySegment
    get() = NSMetadataItemContactKeywordsKey_VH.get(NSMetadataItemContactKeywordsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemContactKeywordsKey_VH.set(NSMetadataItemContactKeywordsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemVersionKey (Void)*
 */
private val NSMetadataItemVersionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemVersionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemVersionKey").orElseThrow() }
private val NSMetadataItemVersionKey_VH: VarHandle by lazy { NSMetadataItemVersionKey_LAYOUT.varHandle() }

var NSMetadataItemVersionKey: MemorySegment
    get() = NSMetadataItemVersionKey_VH.get(NSMetadataItemVersionKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemVersionKey_VH.set(NSMetadataItemVersionKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemPixelHeightKey (Void)*
 */
private val NSMetadataItemPixelHeightKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemPixelHeightKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemPixelHeightKey").orElseThrow() }
private val NSMetadataItemPixelHeightKey_VH: VarHandle by lazy { NSMetadataItemPixelHeightKey_LAYOUT.varHandle() }

var NSMetadataItemPixelHeightKey: MemorySegment
    get() = NSMetadataItemPixelHeightKey_VH.get(NSMetadataItemPixelHeightKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemPixelHeightKey_VH.set(NSMetadataItemPixelHeightKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemPixelWidthKey (Void)*
 */
private val NSMetadataItemPixelWidthKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemPixelWidthKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemPixelWidthKey").orElseThrow() }
private val NSMetadataItemPixelWidthKey_VH: VarHandle by lazy { NSMetadataItemPixelWidthKey_LAYOUT.varHandle() }

var NSMetadataItemPixelWidthKey: MemorySegment
    get() = NSMetadataItemPixelWidthKey_VH.get(NSMetadataItemPixelWidthKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemPixelWidthKey_VH.set(NSMetadataItemPixelWidthKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemPixelCountKey (Void)*
 */
private val NSMetadataItemPixelCountKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemPixelCountKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemPixelCountKey").orElseThrow() }
private val NSMetadataItemPixelCountKey_VH: VarHandle by lazy { NSMetadataItemPixelCountKey_LAYOUT.varHandle() }

var NSMetadataItemPixelCountKey: MemorySegment
    get() = NSMetadataItemPixelCountKey_VH.get(NSMetadataItemPixelCountKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemPixelCountKey_VH.set(NSMetadataItemPixelCountKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemColorSpaceKey (Void)*
 */
private val NSMetadataItemColorSpaceKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemColorSpaceKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemColorSpaceKey").orElseThrow() }
private val NSMetadataItemColorSpaceKey_VH: VarHandle by lazy { NSMetadataItemColorSpaceKey_LAYOUT.varHandle() }

var NSMetadataItemColorSpaceKey: MemorySegment
    get() = NSMetadataItemColorSpaceKey_VH.get(NSMetadataItemColorSpaceKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemColorSpaceKey_VH.set(NSMetadataItemColorSpaceKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemBitsPerSampleKey (Void)*
 */
private val NSMetadataItemBitsPerSampleKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemBitsPerSampleKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemBitsPerSampleKey").orElseThrow() }
private val NSMetadataItemBitsPerSampleKey_VH: VarHandle by lazy { NSMetadataItemBitsPerSampleKey_LAYOUT.varHandle() }

var NSMetadataItemBitsPerSampleKey: MemorySegment
    get() = NSMetadataItemBitsPerSampleKey_VH.get(NSMetadataItemBitsPerSampleKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemBitsPerSampleKey_VH.set(NSMetadataItemBitsPerSampleKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemFlashOnOffKey (Void)*
 */
private val NSMetadataItemFlashOnOffKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemFlashOnOffKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemFlashOnOffKey").orElseThrow() }
private val NSMetadataItemFlashOnOffKey_VH: VarHandle by lazy { NSMetadataItemFlashOnOffKey_LAYOUT.varHandle() }

var NSMetadataItemFlashOnOffKey: MemorySegment
    get() = NSMetadataItemFlashOnOffKey_VH.get(NSMetadataItemFlashOnOffKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemFlashOnOffKey_VH.set(NSMetadataItemFlashOnOffKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemFocalLengthKey (Void)*
 */
private val NSMetadataItemFocalLengthKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemFocalLengthKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemFocalLengthKey").orElseThrow() }
private val NSMetadataItemFocalLengthKey_VH: VarHandle by lazy { NSMetadataItemFocalLengthKey_LAYOUT.varHandle() }

var NSMetadataItemFocalLengthKey: MemorySegment
    get() = NSMetadataItemFocalLengthKey_VH.get(NSMetadataItemFocalLengthKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemFocalLengthKey_VH.set(NSMetadataItemFocalLengthKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemAcquisitionMakeKey (Void)*
 */
private val NSMetadataItemAcquisitionMakeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemAcquisitionMakeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemAcquisitionMakeKey").orElseThrow() }
private val NSMetadataItemAcquisitionMakeKey_VH: VarHandle by lazy { NSMetadataItemAcquisitionMakeKey_LAYOUT.varHandle() }

var NSMetadataItemAcquisitionMakeKey: MemorySegment
    get() = NSMetadataItemAcquisitionMakeKey_VH.get(NSMetadataItemAcquisitionMakeKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemAcquisitionMakeKey_VH.set(NSMetadataItemAcquisitionMakeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemAcquisitionModelKey (Void)*
 */
private val NSMetadataItemAcquisitionModelKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemAcquisitionModelKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemAcquisitionModelKey").orElseThrow() }
private val NSMetadataItemAcquisitionModelKey_VH: VarHandle by lazy { NSMetadataItemAcquisitionModelKey_LAYOUT.varHandle() }

var NSMetadataItemAcquisitionModelKey: MemorySegment
    get() = NSMetadataItemAcquisitionModelKey_VH.get(NSMetadataItemAcquisitionModelKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemAcquisitionModelKey_VH.set(NSMetadataItemAcquisitionModelKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemISOSpeedKey (Void)*
 */
private val NSMetadataItemISOSpeedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemISOSpeedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemISOSpeedKey").orElseThrow() }
private val NSMetadataItemISOSpeedKey_VH: VarHandle by lazy { NSMetadataItemISOSpeedKey_LAYOUT.varHandle() }

var NSMetadataItemISOSpeedKey: MemorySegment
    get() = NSMetadataItemISOSpeedKey_VH.get(NSMetadataItemISOSpeedKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemISOSpeedKey_VH.set(NSMetadataItemISOSpeedKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemOrientationKey (Void)*
 */
private val NSMetadataItemOrientationKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemOrientationKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemOrientationKey").orElseThrow() }
private val NSMetadataItemOrientationKey_VH: VarHandle by lazy { NSMetadataItemOrientationKey_LAYOUT.varHandle() }

var NSMetadataItemOrientationKey: MemorySegment
    get() = NSMetadataItemOrientationKey_VH.get(NSMetadataItemOrientationKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemOrientationKey_VH.set(NSMetadataItemOrientationKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemLayerNamesKey (Void)*
 */
private val NSMetadataItemLayerNamesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemLayerNamesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemLayerNamesKey").orElseThrow() }
private val NSMetadataItemLayerNamesKey_VH: VarHandle by lazy { NSMetadataItemLayerNamesKey_LAYOUT.varHandle() }

var NSMetadataItemLayerNamesKey: MemorySegment
    get() = NSMetadataItemLayerNamesKey_VH.get(NSMetadataItemLayerNamesKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemLayerNamesKey_VH.set(NSMetadataItemLayerNamesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemWhiteBalanceKey (Void)*
 */
private val NSMetadataItemWhiteBalanceKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemWhiteBalanceKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemWhiteBalanceKey").orElseThrow() }
private val NSMetadataItemWhiteBalanceKey_VH: VarHandle by lazy { NSMetadataItemWhiteBalanceKey_LAYOUT.varHandle() }

var NSMetadataItemWhiteBalanceKey: MemorySegment
    get() = NSMetadataItemWhiteBalanceKey_VH.get(NSMetadataItemWhiteBalanceKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemWhiteBalanceKey_VH.set(NSMetadataItemWhiteBalanceKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemApertureKey (Void)*
 */
private val NSMetadataItemApertureKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemApertureKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemApertureKey").orElseThrow() }
private val NSMetadataItemApertureKey_VH: VarHandle by lazy { NSMetadataItemApertureKey_LAYOUT.varHandle() }

var NSMetadataItemApertureKey: MemorySegment
    get() = NSMetadataItemApertureKey_VH.get(NSMetadataItemApertureKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemApertureKey_VH.set(NSMetadataItemApertureKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemProfileNameKey (Void)*
 */
private val NSMetadataItemProfileNameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemProfileNameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemProfileNameKey").orElseThrow() }
private val NSMetadataItemProfileNameKey_VH: VarHandle by lazy { NSMetadataItemProfileNameKey_LAYOUT.varHandle() }

var NSMetadataItemProfileNameKey: MemorySegment
    get() = NSMetadataItemProfileNameKey_VH.get(NSMetadataItemProfileNameKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemProfileNameKey_VH.set(NSMetadataItemProfileNameKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemResolutionWidthDPIKey (Void)*
 */
private val NSMetadataItemResolutionWidthDPIKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemResolutionWidthDPIKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemResolutionWidthDPIKey").orElseThrow() }
private val NSMetadataItemResolutionWidthDPIKey_VH: VarHandle by lazy { NSMetadataItemResolutionWidthDPIKey_LAYOUT.varHandle() }

var NSMetadataItemResolutionWidthDPIKey: MemorySegment
    get() = NSMetadataItemResolutionWidthDPIKey_VH.get(NSMetadataItemResolutionWidthDPIKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemResolutionWidthDPIKey_VH.set(NSMetadataItemResolutionWidthDPIKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemResolutionHeightDPIKey (Void)*
 */
private val NSMetadataItemResolutionHeightDPIKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemResolutionHeightDPIKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemResolutionHeightDPIKey").orElseThrow() }
private val NSMetadataItemResolutionHeightDPIKey_VH: VarHandle by lazy { NSMetadataItemResolutionHeightDPIKey_LAYOUT.varHandle() }

var NSMetadataItemResolutionHeightDPIKey: MemorySegment
    get() = NSMetadataItemResolutionHeightDPIKey_VH.get(NSMetadataItemResolutionHeightDPIKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemResolutionHeightDPIKey_VH.set(NSMetadataItemResolutionHeightDPIKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemExposureModeKey (Void)*
 */
private val NSMetadataItemExposureModeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemExposureModeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemExposureModeKey").orElseThrow() }
private val NSMetadataItemExposureModeKey_VH: VarHandle by lazy { NSMetadataItemExposureModeKey_LAYOUT.varHandle() }

var NSMetadataItemExposureModeKey: MemorySegment
    get() = NSMetadataItemExposureModeKey_VH.get(NSMetadataItemExposureModeKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemExposureModeKey_VH.set(NSMetadataItemExposureModeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemExposureTimeSecondsKey (Void)*
 */
private val NSMetadataItemExposureTimeSecondsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemExposureTimeSecondsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemExposureTimeSecondsKey").orElseThrow() }
private val NSMetadataItemExposureTimeSecondsKey_VH: VarHandle by lazy { NSMetadataItemExposureTimeSecondsKey_LAYOUT.varHandle() }

var NSMetadataItemExposureTimeSecondsKey: MemorySegment
    get() = NSMetadataItemExposureTimeSecondsKey_VH.get(NSMetadataItemExposureTimeSecondsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemExposureTimeSecondsKey_VH.set(NSMetadataItemExposureTimeSecondsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemEXIFVersionKey (Void)*
 */
private val NSMetadataItemEXIFVersionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemEXIFVersionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemEXIFVersionKey").orElseThrow() }
private val NSMetadataItemEXIFVersionKey_VH: VarHandle by lazy { NSMetadataItemEXIFVersionKey_LAYOUT.varHandle() }

var NSMetadataItemEXIFVersionKey: MemorySegment
    get() = NSMetadataItemEXIFVersionKey_VH.get(NSMetadataItemEXIFVersionKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemEXIFVersionKey_VH.set(NSMetadataItemEXIFVersionKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemCameraOwnerKey (Void)*
 */
private val NSMetadataItemCameraOwnerKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemCameraOwnerKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemCameraOwnerKey").orElseThrow() }
private val NSMetadataItemCameraOwnerKey_VH: VarHandle by lazy { NSMetadataItemCameraOwnerKey_LAYOUT.varHandle() }

var NSMetadataItemCameraOwnerKey: MemorySegment
    get() = NSMetadataItemCameraOwnerKey_VH.get(NSMetadataItemCameraOwnerKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemCameraOwnerKey_VH.set(NSMetadataItemCameraOwnerKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemFocalLength35mmKey (Void)*
 */
private val NSMetadataItemFocalLength35mmKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemFocalLength35mmKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemFocalLength35mmKey").orElseThrow() }
private val NSMetadataItemFocalLength35mmKey_VH: VarHandle by lazy { NSMetadataItemFocalLength35mmKey_LAYOUT.varHandle() }

var NSMetadataItemFocalLength35mmKey: MemorySegment
    get() = NSMetadataItemFocalLength35mmKey_VH.get(NSMetadataItemFocalLength35mmKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemFocalLength35mmKey_VH.set(NSMetadataItemFocalLength35mmKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemLensModelKey (Void)*
 */
private val NSMetadataItemLensModelKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemLensModelKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemLensModelKey").orElseThrow() }
private val NSMetadataItemLensModelKey_VH: VarHandle by lazy { NSMetadataItemLensModelKey_LAYOUT.varHandle() }

var NSMetadataItemLensModelKey: MemorySegment
    get() = NSMetadataItemLensModelKey_VH.get(NSMetadataItemLensModelKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemLensModelKey_VH.set(NSMetadataItemLensModelKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemEXIFGPSVersionKey (Void)*
 */
private val NSMetadataItemEXIFGPSVersionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemEXIFGPSVersionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemEXIFGPSVersionKey").orElseThrow() }
private val NSMetadataItemEXIFGPSVersionKey_VH: VarHandle by lazy { NSMetadataItemEXIFGPSVersionKey_LAYOUT.varHandle() }

var NSMetadataItemEXIFGPSVersionKey: MemorySegment
    get() = NSMetadataItemEXIFGPSVersionKey_VH.get(NSMetadataItemEXIFGPSVersionKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemEXIFGPSVersionKey_VH.set(NSMetadataItemEXIFGPSVersionKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemAltitudeKey (Void)*
 */
private val NSMetadataItemAltitudeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemAltitudeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemAltitudeKey").orElseThrow() }
private val NSMetadataItemAltitudeKey_VH: VarHandle by lazy { NSMetadataItemAltitudeKey_LAYOUT.varHandle() }

var NSMetadataItemAltitudeKey: MemorySegment
    get() = NSMetadataItemAltitudeKey_VH.get(NSMetadataItemAltitudeKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemAltitudeKey_VH.set(NSMetadataItemAltitudeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemLatitudeKey (Void)*
 */
private val NSMetadataItemLatitudeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemLatitudeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemLatitudeKey").orElseThrow() }
private val NSMetadataItemLatitudeKey_VH: VarHandle by lazy { NSMetadataItemLatitudeKey_LAYOUT.varHandle() }

var NSMetadataItemLatitudeKey: MemorySegment
    get() = NSMetadataItemLatitudeKey_VH.get(NSMetadataItemLatitudeKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemLatitudeKey_VH.set(NSMetadataItemLatitudeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemLongitudeKey (Void)*
 */
private val NSMetadataItemLongitudeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemLongitudeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemLongitudeKey").orElseThrow() }
private val NSMetadataItemLongitudeKey_VH: VarHandle by lazy { NSMetadataItemLongitudeKey_LAYOUT.varHandle() }

var NSMetadataItemLongitudeKey: MemorySegment
    get() = NSMetadataItemLongitudeKey_VH.get(NSMetadataItemLongitudeKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemLongitudeKey_VH.set(NSMetadataItemLongitudeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemSpeedKey (Void)*
 */
private val NSMetadataItemSpeedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemSpeedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemSpeedKey").orElseThrow() }
private val NSMetadataItemSpeedKey_VH: VarHandle by lazy { NSMetadataItemSpeedKey_LAYOUT.varHandle() }

var NSMetadataItemSpeedKey: MemorySegment
    get() = NSMetadataItemSpeedKey_VH.get(NSMetadataItemSpeedKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemSpeedKey_VH.set(NSMetadataItemSpeedKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemTimestampKey (Void)*
 */
private val NSMetadataItemTimestampKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemTimestampKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemTimestampKey").orElseThrow() }
private val NSMetadataItemTimestampKey_VH: VarHandle by lazy { NSMetadataItemTimestampKey_LAYOUT.varHandle() }

var NSMetadataItemTimestampKey: MemorySegment
    get() = NSMetadataItemTimestampKey_VH.get(NSMetadataItemTimestampKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemTimestampKey_VH.set(NSMetadataItemTimestampKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemGPSTrackKey (Void)*
 */
private val NSMetadataItemGPSTrackKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemGPSTrackKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemGPSTrackKey").orElseThrow() }
private val NSMetadataItemGPSTrackKey_VH: VarHandle by lazy { NSMetadataItemGPSTrackKey_LAYOUT.varHandle() }

var NSMetadataItemGPSTrackKey: MemorySegment
    get() = NSMetadataItemGPSTrackKey_VH.get(NSMetadataItemGPSTrackKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemGPSTrackKey_VH.set(NSMetadataItemGPSTrackKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemImageDirectionKey (Void)*
 */
private val NSMetadataItemImageDirectionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemImageDirectionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemImageDirectionKey").orElseThrow() }
private val NSMetadataItemImageDirectionKey_VH: VarHandle by lazy { NSMetadataItemImageDirectionKey_LAYOUT.varHandle() }

var NSMetadataItemImageDirectionKey: MemorySegment
    get() = NSMetadataItemImageDirectionKey_VH.get(NSMetadataItemImageDirectionKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemImageDirectionKey_VH.set(NSMetadataItemImageDirectionKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemNamedLocationKey (Void)*
 */
private val NSMetadataItemNamedLocationKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemNamedLocationKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemNamedLocationKey").orElseThrow() }
private val NSMetadataItemNamedLocationKey_VH: VarHandle by lazy { NSMetadataItemNamedLocationKey_LAYOUT.varHandle() }

var NSMetadataItemNamedLocationKey: MemorySegment
    get() = NSMetadataItemNamedLocationKey_VH.get(NSMetadataItemNamedLocationKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemNamedLocationKey_VH.set(NSMetadataItemNamedLocationKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemGPSStatusKey (Void)*
 */
private val NSMetadataItemGPSStatusKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemGPSStatusKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemGPSStatusKey").orElseThrow() }
private val NSMetadataItemGPSStatusKey_VH: VarHandle by lazy { NSMetadataItemGPSStatusKey_LAYOUT.varHandle() }

var NSMetadataItemGPSStatusKey: MemorySegment
    get() = NSMetadataItemGPSStatusKey_VH.get(NSMetadataItemGPSStatusKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemGPSStatusKey_VH.set(NSMetadataItemGPSStatusKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemGPSMeasureModeKey (Void)*
 */
private val NSMetadataItemGPSMeasureModeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemGPSMeasureModeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemGPSMeasureModeKey").orElseThrow() }
private val NSMetadataItemGPSMeasureModeKey_VH: VarHandle by lazy { NSMetadataItemGPSMeasureModeKey_LAYOUT.varHandle() }

var NSMetadataItemGPSMeasureModeKey: MemorySegment
    get() = NSMetadataItemGPSMeasureModeKey_VH.get(NSMetadataItemGPSMeasureModeKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemGPSMeasureModeKey_VH.set(NSMetadataItemGPSMeasureModeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemGPSDOPKey (Void)*
 */
private val NSMetadataItemGPSDOPKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemGPSDOPKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemGPSDOPKey").orElseThrow() }
private val NSMetadataItemGPSDOPKey_VH: VarHandle by lazy { NSMetadataItemGPSDOPKey_LAYOUT.varHandle() }

var NSMetadataItemGPSDOPKey: MemorySegment
    get() = NSMetadataItemGPSDOPKey_VH.get(NSMetadataItemGPSDOPKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemGPSDOPKey_VH.set(NSMetadataItemGPSDOPKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemGPSMapDatumKey (Void)*
 */
private val NSMetadataItemGPSMapDatumKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemGPSMapDatumKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemGPSMapDatumKey").orElseThrow() }
private val NSMetadataItemGPSMapDatumKey_VH: VarHandle by lazy { NSMetadataItemGPSMapDatumKey_LAYOUT.varHandle() }

var NSMetadataItemGPSMapDatumKey: MemorySegment
    get() = NSMetadataItemGPSMapDatumKey_VH.get(NSMetadataItemGPSMapDatumKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemGPSMapDatumKey_VH.set(NSMetadataItemGPSMapDatumKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemGPSDestLatitudeKey (Void)*
 */
private val NSMetadataItemGPSDestLatitudeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemGPSDestLatitudeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemGPSDestLatitudeKey").orElseThrow() }
private val NSMetadataItemGPSDestLatitudeKey_VH: VarHandle by lazy { NSMetadataItemGPSDestLatitudeKey_LAYOUT.varHandle() }

var NSMetadataItemGPSDestLatitudeKey: MemorySegment
    get() = NSMetadataItemGPSDestLatitudeKey_VH.get(NSMetadataItemGPSDestLatitudeKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemGPSDestLatitudeKey_VH.set(NSMetadataItemGPSDestLatitudeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemGPSDestLongitudeKey (Void)*
 */
private val NSMetadataItemGPSDestLongitudeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemGPSDestLongitudeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemGPSDestLongitudeKey").orElseThrow() }
private val NSMetadataItemGPSDestLongitudeKey_VH: VarHandle by lazy { NSMetadataItemGPSDestLongitudeKey_LAYOUT.varHandle() }

var NSMetadataItemGPSDestLongitudeKey: MemorySegment
    get() = NSMetadataItemGPSDestLongitudeKey_VH.get(NSMetadataItemGPSDestLongitudeKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemGPSDestLongitudeKey_VH.set(NSMetadataItemGPSDestLongitudeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemGPSDestBearingKey (Void)*
 */
private val NSMetadataItemGPSDestBearingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemGPSDestBearingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemGPSDestBearingKey").orElseThrow() }
private val NSMetadataItemGPSDestBearingKey_VH: VarHandle by lazy { NSMetadataItemGPSDestBearingKey_LAYOUT.varHandle() }

var NSMetadataItemGPSDestBearingKey: MemorySegment
    get() = NSMetadataItemGPSDestBearingKey_VH.get(NSMetadataItemGPSDestBearingKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemGPSDestBearingKey_VH.set(NSMetadataItemGPSDestBearingKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemGPSDestDistanceKey (Void)*
 */
private val NSMetadataItemGPSDestDistanceKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemGPSDestDistanceKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemGPSDestDistanceKey").orElseThrow() }
private val NSMetadataItemGPSDestDistanceKey_VH: VarHandle by lazy { NSMetadataItemGPSDestDistanceKey_LAYOUT.varHandle() }

var NSMetadataItemGPSDestDistanceKey: MemorySegment
    get() = NSMetadataItemGPSDestDistanceKey_VH.get(NSMetadataItemGPSDestDistanceKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemGPSDestDistanceKey_VH.set(NSMetadataItemGPSDestDistanceKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemGPSProcessingMethodKey (Void)*
 */
private val NSMetadataItemGPSProcessingMethodKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemGPSProcessingMethodKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemGPSProcessingMethodKey").orElseThrow() }
private val NSMetadataItemGPSProcessingMethodKey_VH: VarHandle by lazy { NSMetadataItemGPSProcessingMethodKey_LAYOUT.varHandle() }

var NSMetadataItemGPSProcessingMethodKey: MemorySegment
    get() = NSMetadataItemGPSProcessingMethodKey_VH.get(NSMetadataItemGPSProcessingMethodKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemGPSProcessingMethodKey_VH.set(NSMetadataItemGPSProcessingMethodKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemGPSAreaInformationKey (Void)*
 */
private val NSMetadataItemGPSAreaInformationKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemGPSAreaInformationKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemGPSAreaInformationKey").orElseThrow() }
private val NSMetadataItemGPSAreaInformationKey_VH: VarHandle by lazy { NSMetadataItemGPSAreaInformationKey_LAYOUT.varHandle() }

var NSMetadataItemGPSAreaInformationKey: MemorySegment
    get() = NSMetadataItemGPSAreaInformationKey_VH.get(NSMetadataItemGPSAreaInformationKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemGPSAreaInformationKey_VH.set(NSMetadataItemGPSAreaInformationKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemGPSDateStampKey (Void)*
 */
private val NSMetadataItemGPSDateStampKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemGPSDateStampKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemGPSDateStampKey").orElseThrow() }
private val NSMetadataItemGPSDateStampKey_VH: VarHandle by lazy { NSMetadataItemGPSDateStampKey_LAYOUT.varHandle() }

var NSMetadataItemGPSDateStampKey: MemorySegment
    get() = NSMetadataItemGPSDateStampKey_VH.get(NSMetadataItemGPSDateStampKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemGPSDateStampKey_VH.set(NSMetadataItemGPSDateStampKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemGPSDifferentalKey (Void)*
 */
private val NSMetadataItemGPSDifferentalKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemGPSDifferentalKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemGPSDifferentalKey").orElseThrow() }
private val NSMetadataItemGPSDifferentalKey_VH: VarHandle by lazy { NSMetadataItemGPSDifferentalKey_LAYOUT.varHandle() }

var NSMetadataItemGPSDifferentalKey: MemorySegment
    get() = NSMetadataItemGPSDifferentalKey_VH.get(NSMetadataItemGPSDifferentalKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemGPSDifferentalKey_VH.set(NSMetadataItemGPSDifferentalKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemCodecsKey (Void)*
 */
private val NSMetadataItemCodecsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemCodecsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemCodecsKey").orElseThrow() }
private val NSMetadataItemCodecsKey_VH: VarHandle by lazy { NSMetadataItemCodecsKey_LAYOUT.varHandle() }

var NSMetadataItemCodecsKey: MemorySegment
    get() = NSMetadataItemCodecsKey_VH.get(NSMetadataItemCodecsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemCodecsKey_VH.set(NSMetadataItemCodecsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemMediaTypesKey (Void)*
 */
private val NSMetadataItemMediaTypesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemMediaTypesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemMediaTypesKey").orElseThrow() }
private val NSMetadataItemMediaTypesKey_VH: VarHandle by lazy { NSMetadataItemMediaTypesKey_LAYOUT.varHandle() }

var NSMetadataItemMediaTypesKey: MemorySegment
    get() = NSMetadataItemMediaTypesKey_VH.get(NSMetadataItemMediaTypesKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemMediaTypesKey_VH.set(NSMetadataItemMediaTypesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemStreamableKey (Void)*
 */
private val NSMetadataItemStreamableKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemStreamableKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemStreamableKey").orElseThrow() }
private val NSMetadataItemStreamableKey_VH: VarHandle by lazy { NSMetadataItemStreamableKey_LAYOUT.varHandle() }

var NSMetadataItemStreamableKey: MemorySegment
    get() = NSMetadataItemStreamableKey_VH.get(NSMetadataItemStreamableKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemStreamableKey_VH.set(NSMetadataItemStreamableKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemTotalBitRateKey (Void)*
 */
private val NSMetadataItemTotalBitRateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemTotalBitRateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemTotalBitRateKey").orElseThrow() }
private val NSMetadataItemTotalBitRateKey_VH: VarHandle by lazy { NSMetadataItemTotalBitRateKey_LAYOUT.varHandle() }

var NSMetadataItemTotalBitRateKey: MemorySegment
    get() = NSMetadataItemTotalBitRateKey_VH.get(NSMetadataItemTotalBitRateKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemTotalBitRateKey_VH.set(NSMetadataItemTotalBitRateKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemVideoBitRateKey (Void)*
 */
private val NSMetadataItemVideoBitRateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemVideoBitRateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemVideoBitRateKey").orElseThrow() }
private val NSMetadataItemVideoBitRateKey_VH: VarHandle by lazy { NSMetadataItemVideoBitRateKey_LAYOUT.varHandle() }

var NSMetadataItemVideoBitRateKey: MemorySegment
    get() = NSMetadataItemVideoBitRateKey_VH.get(NSMetadataItemVideoBitRateKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemVideoBitRateKey_VH.set(NSMetadataItemVideoBitRateKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemAudioBitRateKey (Void)*
 */
private val NSMetadataItemAudioBitRateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemAudioBitRateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemAudioBitRateKey").orElseThrow() }
private val NSMetadataItemAudioBitRateKey_VH: VarHandle by lazy { NSMetadataItemAudioBitRateKey_LAYOUT.varHandle() }

var NSMetadataItemAudioBitRateKey: MemorySegment
    get() = NSMetadataItemAudioBitRateKey_VH.get(NSMetadataItemAudioBitRateKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemAudioBitRateKey_VH.set(NSMetadataItemAudioBitRateKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemDeliveryTypeKey (Void)*
 */
private val NSMetadataItemDeliveryTypeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemDeliveryTypeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemDeliveryTypeKey").orElseThrow() }
private val NSMetadataItemDeliveryTypeKey_VH: VarHandle by lazy { NSMetadataItemDeliveryTypeKey_LAYOUT.varHandle() }

var NSMetadataItemDeliveryTypeKey: MemorySegment
    get() = NSMetadataItemDeliveryTypeKey_VH.get(NSMetadataItemDeliveryTypeKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemDeliveryTypeKey_VH.set(NSMetadataItemDeliveryTypeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemAlbumKey (Void)*
 */
private val NSMetadataItemAlbumKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemAlbumKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemAlbumKey").orElseThrow() }
private val NSMetadataItemAlbumKey_VH: VarHandle by lazy { NSMetadataItemAlbumKey_LAYOUT.varHandle() }

var NSMetadataItemAlbumKey: MemorySegment
    get() = NSMetadataItemAlbumKey_VH.get(NSMetadataItemAlbumKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemAlbumKey_VH.set(NSMetadataItemAlbumKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemHasAlphaChannelKey (Void)*
 */
private val NSMetadataItemHasAlphaChannelKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemHasAlphaChannelKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemHasAlphaChannelKey").orElseThrow() }
private val NSMetadataItemHasAlphaChannelKey_VH: VarHandle by lazy { NSMetadataItemHasAlphaChannelKey_LAYOUT.varHandle() }

var NSMetadataItemHasAlphaChannelKey: MemorySegment
    get() = NSMetadataItemHasAlphaChannelKey_VH.get(NSMetadataItemHasAlphaChannelKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemHasAlphaChannelKey_VH.set(NSMetadataItemHasAlphaChannelKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemRedEyeOnOffKey (Void)*
 */
private val NSMetadataItemRedEyeOnOffKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemRedEyeOnOffKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemRedEyeOnOffKey").orElseThrow() }
private val NSMetadataItemRedEyeOnOffKey_VH: VarHandle by lazy { NSMetadataItemRedEyeOnOffKey_LAYOUT.varHandle() }

var NSMetadataItemRedEyeOnOffKey: MemorySegment
    get() = NSMetadataItemRedEyeOnOffKey_VH.get(NSMetadataItemRedEyeOnOffKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemRedEyeOnOffKey_VH.set(NSMetadataItemRedEyeOnOffKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemMeteringModeKey (Void)*
 */
private val NSMetadataItemMeteringModeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemMeteringModeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemMeteringModeKey").orElseThrow() }
private val NSMetadataItemMeteringModeKey_VH: VarHandle by lazy { NSMetadataItemMeteringModeKey_LAYOUT.varHandle() }

var NSMetadataItemMeteringModeKey: MemorySegment
    get() = NSMetadataItemMeteringModeKey_VH.get(NSMetadataItemMeteringModeKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemMeteringModeKey_VH.set(NSMetadataItemMeteringModeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemMaxApertureKey (Void)*
 */
private val NSMetadataItemMaxApertureKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemMaxApertureKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemMaxApertureKey").orElseThrow() }
private val NSMetadataItemMaxApertureKey_VH: VarHandle by lazy { NSMetadataItemMaxApertureKey_LAYOUT.varHandle() }

var NSMetadataItemMaxApertureKey: MemorySegment
    get() = NSMetadataItemMaxApertureKey_VH.get(NSMetadataItemMaxApertureKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemMaxApertureKey_VH.set(NSMetadataItemMaxApertureKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemFNumberKey (Void)*
 */
private val NSMetadataItemFNumberKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemFNumberKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemFNumberKey").orElseThrow() }
private val NSMetadataItemFNumberKey_VH: VarHandle by lazy { NSMetadataItemFNumberKey_LAYOUT.varHandle() }

var NSMetadataItemFNumberKey: MemorySegment
    get() = NSMetadataItemFNumberKey_VH.get(NSMetadataItemFNumberKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemFNumberKey_VH.set(NSMetadataItemFNumberKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemExposureProgramKey (Void)*
 */
private val NSMetadataItemExposureProgramKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemExposureProgramKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemExposureProgramKey").orElseThrow() }
private val NSMetadataItemExposureProgramKey_VH: VarHandle by lazy { NSMetadataItemExposureProgramKey_LAYOUT.varHandle() }

var NSMetadataItemExposureProgramKey: MemorySegment
    get() = NSMetadataItemExposureProgramKey_VH.get(NSMetadataItemExposureProgramKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemExposureProgramKey_VH.set(NSMetadataItemExposureProgramKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemExposureTimeStringKey (Void)*
 */
private val NSMetadataItemExposureTimeStringKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemExposureTimeStringKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemExposureTimeStringKey").orElseThrow() }
private val NSMetadataItemExposureTimeStringKey_VH: VarHandle by lazy { NSMetadataItemExposureTimeStringKey_LAYOUT.varHandle() }

var NSMetadataItemExposureTimeStringKey: MemorySegment
    get() = NSMetadataItemExposureTimeStringKey_VH.get(NSMetadataItemExposureTimeStringKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemExposureTimeStringKey_VH.set(NSMetadataItemExposureTimeStringKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemHeadlineKey (Void)*
 */
private val NSMetadataItemHeadlineKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemHeadlineKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemHeadlineKey").orElseThrow() }
private val NSMetadataItemHeadlineKey_VH: VarHandle by lazy { NSMetadataItemHeadlineKey_LAYOUT.varHandle() }

var NSMetadataItemHeadlineKey: MemorySegment
    get() = NSMetadataItemHeadlineKey_VH.get(NSMetadataItemHeadlineKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemHeadlineKey_VH.set(NSMetadataItemHeadlineKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemInstructionsKey (Void)*
 */
private val NSMetadataItemInstructionsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemInstructionsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemInstructionsKey").orElseThrow() }
private val NSMetadataItemInstructionsKey_VH: VarHandle by lazy { NSMetadataItemInstructionsKey_LAYOUT.varHandle() }

var NSMetadataItemInstructionsKey: MemorySegment
    get() = NSMetadataItemInstructionsKey_VH.get(NSMetadataItemInstructionsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemInstructionsKey_VH.set(NSMetadataItemInstructionsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemCityKey (Void)*
 */
private val NSMetadataItemCityKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemCityKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemCityKey").orElseThrow() }
private val NSMetadataItemCityKey_VH: VarHandle by lazy { NSMetadataItemCityKey_LAYOUT.varHandle() }

var NSMetadataItemCityKey: MemorySegment
    get() = NSMetadataItemCityKey_VH.get(NSMetadataItemCityKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemCityKey_VH.set(NSMetadataItemCityKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemStateOrProvinceKey (Void)*
 */
private val NSMetadataItemStateOrProvinceKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemStateOrProvinceKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemStateOrProvinceKey").orElseThrow() }
private val NSMetadataItemStateOrProvinceKey_VH: VarHandle by lazy { NSMetadataItemStateOrProvinceKey_LAYOUT.varHandle() }

var NSMetadataItemStateOrProvinceKey: MemorySegment
    get() = NSMetadataItemStateOrProvinceKey_VH.get(NSMetadataItemStateOrProvinceKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemStateOrProvinceKey_VH.set(NSMetadataItemStateOrProvinceKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemCountryKey (Void)*
 */
private val NSMetadataItemCountryKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemCountryKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemCountryKey").orElseThrow() }
private val NSMetadataItemCountryKey_VH: VarHandle by lazy { NSMetadataItemCountryKey_LAYOUT.varHandle() }

var NSMetadataItemCountryKey: MemorySegment
    get() = NSMetadataItemCountryKey_VH.get(NSMetadataItemCountryKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemCountryKey_VH.set(NSMetadataItemCountryKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemTextContentKey (Void)*
 */
private val NSMetadataItemTextContentKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemTextContentKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemTextContentKey").orElseThrow() }
private val NSMetadataItemTextContentKey_VH: VarHandle by lazy { NSMetadataItemTextContentKey_LAYOUT.varHandle() }

var NSMetadataItemTextContentKey: MemorySegment
    get() = NSMetadataItemTextContentKey_VH.get(NSMetadataItemTextContentKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemTextContentKey_VH.set(NSMetadataItemTextContentKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemAudioSampleRateKey (Void)*
 */
private val NSMetadataItemAudioSampleRateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemAudioSampleRateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemAudioSampleRateKey").orElseThrow() }
private val NSMetadataItemAudioSampleRateKey_VH: VarHandle by lazy { NSMetadataItemAudioSampleRateKey_LAYOUT.varHandle() }

var NSMetadataItemAudioSampleRateKey: MemorySegment
    get() = NSMetadataItemAudioSampleRateKey_VH.get(NSMetadataItemAudioSampleRateKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemAudioSampleRateKey_VH.set(NSMetadataItemAudioSampleRateKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemAudioChannelCountKey (Void)*
 */
private val NSMetadataItemAudioChannelCountKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemAudioChannelCountKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemAudioChannelCountKey").orElseThrow() }
private val NSMetadataItemAudioChannelCountKey_VH: VarHandle by lazy { NSMetadataItemAudioChannelCountKey_LAYOUT.varHandle() }

var NSMetadataItemAudioChannelCountKey: MemorySegment
    get() = NSMetadataItemAudioChannelCountKey_VH.get(NSMetadataItemAudioChannelCountKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemAudioChannelCountKey_VH.set(NSMetadataItemAudioChannelCountKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemTempoKey (Void)*
 */
private val NSMetadataItemTempoKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemTempoKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemTempoKey").orElseThrow() }
private val NSMetadataItemTempoKey_VH: VarHandle by lazy { NSMetadataItemTempoKey_LAYOUT.varHandle() }

var NSMetadataItemTempoKey: MemorySegment
    get() = NSMetadataItemTempoKey_VH.get(NSMetadataItemTempoKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemTempoKey_VH.set(NSMetadataItemTempoKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemKeySignatureKey (Void)*
 */
private val NSMetadataItemKeySignatureKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemKeySignatureKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemKeySignatureKey").orElseThrow() }
private val NSMetadataItemKeySignatureKey_VH: VarHandle by lazy { NSMetadataItemKeySignatureKey_LAYOUT.varHandle() }

var NSMetadataItemKeySignatureKey: MemorySegment
    get() = NSMetadataItemKeySignatureKey_VH.get(NSMetadataItemKeySignatureKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemKeySignatureKey_VH.set(NSMetadataItemKeySignatureKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemTimeSignatureKey (Void)*
 */
private val NSMetadataItemTimeSignatureKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemTimeSignatureKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemTimeSignatureKey").orElseThrow() }
private val NSMetadataItemTimeSignatureKey_VH: VarHandle by lazy { NSMetadataItemTimeSignatureKey_LAYOUT.varHandle() }

var NSMetadataItemTimeSignatureKey: MemorySegment
    get() = NSMetadataItemTimeSignatureKey_VH.get(NSMetadataItemTimeSignatureKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemTimeSignatureKey_VH.set(NSMetadataItemTimeSignatureKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemAudioEncodingApplicationKey (Void)*
 */
private val NSMetadataItemAudioEncodingApplicationKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemAudioEncodingApplicationKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemAudioEncodingApplicationKey").orElseThrow() }
private val NSMetadataItemAudioEncodingApplicationKey_VH: VarHandle by lazy { NSMetadataItemAudioEncodingApplicationKey_LAYOUT.varHandle() }

var NSMetadataItemAudioEncodingApplicationKey: MemorySegment
    get() = NSMetadataItemAudioEncodingApplicationKey_VH.get(NSMetadataItemAudioEncodingApplicationKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemAudioEncodingApplicationKey_VH.set(NSMetadataItemAudioEncodingApplicationKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemComposerKey (Void)*
 */
private val NSMetadataItemComposerKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemComposerKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemComposerKey").orElseThrow() }
private val NSMetadataItemComposerKey_VH: VarHandle by lazy { NSMetadataItemComposerKey_LAYOUT.varHandle() }

var NSMetadataItemComposerKey: MemorySegment
    get() = NSMetadataItemComposerKey_VH.get(NSMetadataItemComposerKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemComposerKey_VH.set(NSMetadataItemComposerKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemLyricistKey (Void)*
 */
private val NSMetadataItemLyricistKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemLyricistKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemLyricistKey").orElseThrow() }
private val NSMetadataItemLyricistKey_VH: VarHandle by lazy { NSMetadataItemLyricistKey_LAYOUT.varHandle() }

var NSMetadataItemLyricistKey: MemorySegment
    get() = NSMetadataItemLyricistKey_VH.get(NSMetadataItemLyricistKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemLyricistKey_VH.set(NSMetadataItemLyricistKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemAudioTrackNumberKey (Void)*
 */
private val NSMetadataItemAudioTrackNumberKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemAudioTrackNumberKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemAudioTrackNumberKey").orElseThrow() }
private val NSMetadataItemAudioTrackNumberKey_VH: VarHandle by lazy { NSMetadataItemAudioTrackNumberKey_LAYOUT.varHandle() }

var NSMetadataItemAudioTrackNumberKey: MemorySegment
    get() = NSMetadataItemAudioTrackNumberKey_VH.get(NSMetadataItemAudioTrackNumberKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemAudioTrackNumberKey_VH.set(NSMetadataItemAudioTrackNumberKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemRecordingDateKey (Void)*
 */
private val NSMetadataItemRecordingDateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemRecordingDateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemRecordingDateKey").orElseThrow() }
private val NSMetadataItemRecordingDateKey_VH: VarHandle by lazy { NSMetadataItemRecordingDateKey_LAYOUT.varHandle() }

var NSMetadataItemRecordingDateKey: MemorySegment
    get() = NSMetadataItemRecordingDateKey_VH.get(NSMetadataItemRecordingDateKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemRecordingDateKey_VH.set(NSMetadataItemRecordingDateKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemMusicalGenreKey (Void)*
 */
private val NSMetadataItemMusicalGenreKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemMusicalGenreKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemMusicalGenreKey").orElseThrow() }
private val NSMetadataItemMusicalGenreKey_VH: VarHandle by lazy { NSMetadataItemMusicalGenreKey_LAYOUT.varHandle() }

var NSMetadataItemMusicalGenreKey: MemorySegment
    get() = NSMetadataItemMusicalGenreKey_VH.get(NSMetadataItemMusicalGenreKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemMusicalGenreKey_VH.set(NSMetadataItemMusicalGenreKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemIsGeneralMIDISequenceKey (Void)*
 */
private val NSMetadataItemIsGeneralMIDISequenceKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemIsGeneralMIDISequenceKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemIsGeneralMIDISequenceKey").orElseThrow() }
private val NSMetadataItemIsGeneralMIDISequenceKey_VH: VarHandle by lazy { NSMetadataItemIsGeneralMIDISequenceKey_LAYOUT.varHandle() }

var NSMetadataItemIsGeneralMIDISequenceKey: MemorySegment
    get() = NSMetadataItemIsGeneralMIDISequenceKey_VH.get(NSMetadataItemIsGeneralMIDISequenceKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemIsGeneralMIDISequenceKey_VH.set(NSMetadataItemIsGeneralMIDISequenceKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemRecordingYearKey (Void)*
 */
private val NSMetadataItemRecordingYearKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemRecordingYearKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemRecordingYearKey").orElseThrow() }
private val NSMetadataItemRecordingYearKey_VH: VarHandle by lazy { NSMetadataItemRecordingYearKey_LAYOUT.varHandle() }

var NSMetadataItemRecordingYearKey: MemorySegment
    get() = NSMetadataItemRecordingYearKey_VH.get(NSMetadataItemRecordingYearKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemRecordingYearKey_VH.set(NSMetadataItemRecordingYearKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemOrganizationsKey (Void)*
 */
private val NSMetadataItemOrganizationsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemOrganizationsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemOrganizationsKey").orElseThrow() }
private val NSMetadataItemOrganizationsKey_VH: VarHandle by lazy { NSMetadataItemOrganizationsKey_LAYOUT.varHandle() }

var NSMetadataItemOrganizationsKey: MemorySegment
    get() = NSMetadataItemOrganizationsKey_VH.get(NSMetadataItemOrganizationsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemOrganizationsKey_VH.set(NSMetadataItemOrganizationsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemLanguagesKey (Void)*
 */
private val NSMetadataItemLanguagesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemLanguagesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemLanguagesKey").orElseThrow() }
private val NSMetadataItemLanguagesKey_VH: VarHandle by lazy { NSMetadataItemLanguagesKey_LAYOUT.varHandle() }

var NSMetadataItemLanguagesKey: MemorySegment
    get() = NSMetadataItemLanguagesKey_VH.get(NSMetadataItemLanguagesKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemLanguagesKey_VH.set(NSMetadataItemLanguagesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemRightsKey (Void)*
 */
private val NSMetadataItemRightsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemRightsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemRightsKey").orElseThrow() }
private val NSMetadataItemRightsKey_VH: VarHandle by lazy { NSMetadataItemRightsKey_LAYOUT.varHandle() }

var NSMetadataItemRightsKey: MemorySegment
    get() = NSMetadataItemRightsKey_VH.get(NSMetadataItemRightsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemRightsKey_VH.set(NSMetadataItemRightsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemPublishersKey (Void)*
 */
private val NSMetadataItemPublishersKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemPublishersKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemPublishersKey").orElseThrow() }
private val NSMetadataItemPublishersKey_VH: VarHandle by lazy { NSMetadataItemPublishersKey_LAYOUT.varHandle() }

var NSMetadataItemPublishersKey: MemorySegment
    get() = NSMetadataItemPublishersKey_VH.get(NSMetadataItemPublishersKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemPublishersKey_VH.set(NSMetadataItemPublishersKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemContributorsKey (Void)*
 */
private val NSMetadataItemContributorsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemContributorsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemContributorsKey").orElseThrow() }
private val NSMetadataItemContributorsKey_VH: VarHandle by lazy { NSMetadataItemContributorsKey_LAYOUT.varHandle() }

var NSMetadataItemContributorsKey: MemorySegment
    get() = NSMetadataItemContributorsKey_VH.get(NSMetadataItemContributorsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemContributorsKey_VH.set(NSMetadataItemContributorsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemCoverageKey (Void)*
 */
private val NSMetadataItemCoverageKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemCoverageKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemCoverageKey").orElseThrow() }
private val NSMetadataItemCoverageKey_VH: VarHandle by lazy { NSMetadataItemCoverageKey_LAYOUT.varHandle() }

var NSMetadataItemCoverageKey: MemorySegment
    get() = NSMetadataItemCoverageKey_VH.get(NSMetadataItemCoverageKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemCoverageKey_VH.set(NSMetadataItemCoverageKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemSubjectKey (Void)*
 */
private val NSMetadataItemSubjectKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemSubjectKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemSubjectKey").orElseThrow() }
private val NSMetadataItemSubjectKey_VH: VarHandle by lazy { NSMetadataItemSubjectKey_LAYOUT.varHandle() }

var NSMetadataItemSubjectKey: MemorySegment
    get() = NSMetadataItemSubjectKey_VH.get(NSMetadataItemSubjectKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemSubjectKey_VH.set(NSMetadataItemSubjectKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemThemeKey (Void)*
 */
private val NSMetadataItemThemeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemThemeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemThemeKey").orElseThrow() }
private val NSMetadataItemThemeKey_VH: VarHandle by lazy { NSMetadataItemThemeKey_LAYOUT.varHandle() }

var NSMetadataItemThemeKey: MemorySegment
    get() = NSMetadataItemThemeKey_VH.get(NSMetadataItemThemeKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemThemeKey_VH.set(NSMetadataItemThemeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemDescriptionKey (Void)*
 */
private val NSMetadataItemDescriptionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemDescriptionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemDescriptionKey").orElseThrow() }
private val NSMetadataItemDescriptionKey_VH: VarHandle by lazy { NSMetadataItemDescriptionKey_LAYOUT.varHandle() }

var NSMetadataItemDescriptionKey: MemorySegment
    get() = NSMetadataItemDescriptionKey_VH.get(NSMetadataItemDescriptionKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemDescriptionKey_VH.set(NSMetadataItemDescriptionKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemIdentifierKey (Void)*
 */
private val NSMetadataItemIdentifierKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemIdentifierKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemIdentifierKey").orElseThrow() }
private val NSMetadataItemIdentifierKey_VH: VarHandle by lazy { NSMetadataItemIdentifierKey_LAYOUT.varHandle() }

var NSMetadataItemIdentifierKey: MemorySegment
    get() = NSMetadataItemIdentifierKey_VH.get(NSMetadataItemIdentifierKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemIdentifierKey_VH.set(NSMetadataItemIdentifierKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemAudiencesKey (Void)*
 */
private val NSMetadataItemAudiencesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemAudiencesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemAudiencesKey").orElseThrow() }
private val NSMetadataItemAudiencesKey_VH: VarHandle by lazy { NSMetadataItemAudiencesKey_LAYOUT.varHandle() }

var NSMetadataItemAudiencesKey: MemorySegment
    get() = NSMetadataItemAudiencesKey_VH.get(NSMetadataItemAudiencesKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemAudiencesKey_VH.set(NSMetadataItemAudiencesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemNumberOfPagesKey (Void)*
 */
private val NSMetadataItemNumberOfPagesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemNumberOfPagesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemNumberOfPagesKey").orElseThrow() }
private val NSMetadataItemNumberOfPagesKey_VH: VarHandle by lazy { NSMetadataItemNumberOfPagesKey_LAYOUT.varHandle() }

var NSMetadataItemNumberOfPagesKey: MemorySegment
    get() = NSMetadataItemNumberOfPagesKey_VH.get(NSMetadataItemNumberOfPagesKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemNumberOfPagesKey_VH.set(NSMetadataItemNumberOfPagesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemPageWidthKey (Void)*
 */
private val NSMetadataItemPageWidthKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemPageWidthKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemPageWidthKey").orElseThrow() }
private val NSMetadataItemPageWidthKey_VH: VarHandle by lazy { NSMetadataItemPageWidthKey_LAYOUT.varHandle() }

var NSMetadataItemPageWidthKey: MemorySegment
    get() = NSMetadataItemPageWidthKey_VH.get(NSMetadataItemPageWidthKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemPageWidthKey_VH.set(NSMetadataItemPageWidthKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemPageHeightKey (Void)*
 */
private val NSMetadataItemPageHeightKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemPageHeightKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemPageHeightKey").orElseThrow() }
private val NSMetadataItemPageHeightKey_VH: VarHandle by lazy { NSMetadataItemPageHeightKey_LAYOUT.varHandle() }

var NSMetadataItemPageHeightKey: MemorySegment
    get() = NSMetadataItemPageHeightKey_VH.get(NSMetadataItemPageHeightKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemPageHeightKey_VH.set(NSMetadataItemPageHeightKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemSecurityMethodKey (Void)*
 */
private val NSMetadataItemSecurityMethodKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemSecurityMethodKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemSecurityMethodKey").orElseThrow() }
private val NSMetadataItemSecurityMethodKey_VH: VarHandle by lazy { NSMetadataItemSecurityMethodKey_LAYOUT.varHandle() }

var NSMetadataItemSecurityMethodKey: MemorySegment
    get() = NSMetadataItemSecurityMethodKey_VH.get(NSMetadataItemSecurityMethodKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemSecurityMethodKey_VH.set(NSMetadataItemSecurityMethodKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemCreatorKey (Void)*
 */
private val NSMetadataItemCreatorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemCreatorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemCreatorKey").orElseThrow() }
private val NSMetadataItemCreatorKey_VH: VarHandle by lazy { NSMetadataItemCreatorKey_LAYOUT.varHandle() }

var NSMetadataItemCreatorKey: MemorySegment
    get() = NSMetadataItemCreatorKey_VH.get(NSMetadataItemCreatorKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemCreatorKey_VH.set(NSMetadataItemCreatorKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemEncodingApplicationsKey (Void)*
 */
private val NSMetadataItemEncodingApplicationsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemEncodingApplicationsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemEncodingApplicationsKey").orElseThrow() }
private val NSMetadataItemEncodingApplicationsKey_VH: VarHandle by lazy { NSMetadataItemEncodingApplicationsKey_LAYOUT.varHandle() }

var NSMetadataItemEncodingApplicationsKey: MemorySegment
    get() = NSMetadataItemEncodingApplicationsKey_VH.get(NSMetadataItemEncodingApplicationsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemEncodingApplicationsKey_VH.set(NSMetadataItemEncodingApplicationsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemDueDateKey (Void)*
 */
private val NSMetadataItemDueDateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemDueDateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemDueDateKey").orElseThrow() }
private val NSMetadataItemDueDateKey_VH: VarHandle by lazy { NSMetadataItemDueDateKey_LAYOUT.varHandle() }

var NSMetadataItemDueDateKey: MemorySegment
    get() = NSMetadataItemDueDateKey_VH.get(NSMetadataItemDueDateKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemDueDateKey_VH.set(NSMetadataItemDueDateKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemStarRatingKey (Void)*
 */
private val NSMetadataItemStarRatingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemStarRatingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemStarRatingKey").orElseThrow() }
private val NSMetadataItemStarRatingKey_VH: VarHandle by lazy { NSMetadataItemStarRatingKey_LAYOUT.varHandle() }

var NSMetadataItemStarRatingKey: MemorySegment
    get() = NSMetadataItemStarRatingKey_VH.get(NSMetadataItemStarRatingKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemStarRatingKey_VH.set(NSMetadataItemStarRatingKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemPhoneNumbersKey (Void)*
 */
private val NSMetadataItemPhoneNumbersKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemPhoneNumbersKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemPhoneNumbersKey").orElseThrow() }
private val NSMetadataItemPhoneNumbersKey_VH: VarHandle by lazy { NSMetadataItemPhoneNumbersKey_LAYOUT.varHandle() }

var NSMetadataItemPhoneNumbersKey: MemorySegment
    get() = NSMetadataItemPhoneNumbersKey_VH.get(NSMetadataItemPhoneNumbersKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemPhoneNumbersKey_VH.set(NSMetadataItemPhoneNumbersKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemEmailAddressesKey (Void)*
 */
private val NSMetadataItemEmailAddressesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemEmailAddressesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemEmailAddressesKey").orElseThrow() }
private val NSMetadataItemEmailAddressesKey_VH: VarHandle by lazy { NSMetadataItemEmailAddressesKey_LAYOUT.varHandle() }

var NSMetadataItemEmailAddressesKey: MemorySegment
    get() = NSMetadataItemEmailAddressesKey_VH.get(NSMetadataItemEmailAddressesKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemEmailAddressesKey_VH.set(NSMetadataItemEmailAddressesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemInstantMessageAddressesKey (Void)*
 */
private val NSMetadataItemInstantMessageAddressesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemInstantMessageAddressesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemInstantMessageAddressesKey").orElseThrow() }
private val NSMetadataItemInstantMessageAddressesKey_VH: VarHandle by lazy { NSMetadataItemInstantMessageAddressesKey_LAYOUT.varHandle() }

var NSMetadataItemInstantMessageAddressesKey: MemorySegment
    get() = NSMetadataItemInstantMessageAddressesKey_VH.get(NSMetadataItemInstantMessageAddressesKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemInstantMessageAddressesKey_VH.set(NSMetadataItemInstantMessageAddressesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemKindKey (Void)*
 */
private val NSMetadataItemKindKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemKindKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemKindKey").orElseThrow() }
private val NSMetadataItemKindKey_VH: VarHandle by lazy { NSMetadataItemKindKey_LAYOUT.varHandle() }

var NSMetadataItemKindKey: MemorySegment
    get() = NSMetadataItemKindKey_VH.get(NSMetadataItemKindKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemKindKey_VH.set(NSMetadataItemKindKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemRecipientsKey (Void)*
 */
private val NSMetadataItemRecipientsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemRecipientsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemRecipientsKey").orElseThrow() }
private val NSMetadataItemRecipientsKey_VH: VarHandle by lazy { NSMetadataItemRecipientsKey_LAYOUT.varHandle() }

var NSMetadataItemRecipientsKey: MemorySegment
    get() = NSMetadataItemRecipientsKey_VH.get(NSMetadataItemRecipientsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemRecipientsKey_VH.set(NSMetadataItemRecipientsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemFinderCommentKey (Void)*
 */
private val NSMetadataItemFinderCommentKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemFinderCommentKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemFinderCommentKey").orElseThrow() }
private val NSMetadataItemFinderCommentKey_VH: VarHandle by lazy { NSMetadataItemFinderCommentKey_LAYOUT.varHandle() }

var NSMetadataItemFinderCommentKey: MemorySegment
    get() = NSMetadataItemFinderCommentKey_VH.get(NSMetadataItemFinderCommentKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemFinderCommentKey_VH.set(NSMetadataItemFinderCommentKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemFontsKey (Void)*
 */
private val NSMetadataItemFontsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemFontsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemFontsKey").orElseThrow() }
private val NSMetadataItemFontsKey_VH: VarHandle by lazy { NSMetadataItemFontsKey_LAYOUT.varHandle() }

var NSMetadataItemFontsKey: MemorySegment
    get() = NSMetadataItemFontsKey_VH.get(NSMetadataItemFontsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemFontsKey_VH.set(NSMetadataItemFontsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemAppleLoopsRootKeyKey (Void)*
 */
private val NSMetadataItemAppleLoopsRootKeyKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemAppleLoopsRootKeyKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemAppleLoopsRootKeyKey").orElseThrow() }
private val NSMetadataItemAppleLoopsRootKeyKey_VH: VarHandle by lazy { NSMetadataItemAppleLoopsRootKeyKey_LAYOUT.varHandle() }

var NSMetadataItemAppleLoopsRootKeyKey: MemorySegment
    get() = NSMetadataItemAppleLoopsRootKeyKey_VH.get(NSMetadataItemAppleLoopsRootKeyKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemAppleLoopsRootKeyKey_VH.set(NSMetadataItemAppleLoopsRootKeyKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemAppleLoopsKeyFilterTypeKey (Void)*
 */
private val NSMetadataItemAppleLoopsKeyFilterTypeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemAppleLoopsKeyFilterTypeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemAppleLoopsKeyFilterTypeKey").orElseThrow() }
private val NSMetadataItemAppleLoopsKeyFilterTypeKey_VH: VarHandle by lazy { NSMetadataItemAppleLoopsKeyFilterTypeKey_LAYOUT.varHandle() }

var NSMetadataItemAppleLoopsKeyFilterTypeKey: MemorySegment
    get() = NSMetadataItemAppleLoopsKeyFilterTypeKey_VH.get(NSMetadataItemAppleLoopsKeyFilterTypeKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemAppleLoopsKeyFilterTypeKey_VH.set(NSMetadataItemAppleLoopsKeyFilterTypeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemAppleLoopsLoopModeKey (Void)*
 */
private val NSMetadataItemAppleLoopsLoopModeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemAppleLoopsLoopModeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemAppleLoopsLoopModeKey").orElseThrow() }
private val NSMetadataItemAppleLoopsLoopModeKey_VH: VarHandle by lazy { NSMetadataItemAppleLoopsLoopModeKey_LAYOUT.varHandle() }

var NSMetadataItemAppleLoopsLoopModeKey: MemorySegment
    get() = NSMetadataItemAppleLoopsLoopModeKey_VH.get(NSMetadataItemAppleLoopsLoopModeKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemAppleLoopsLoopModeKey_VH.set(NSMetadataItemAppleLoopsLoopModeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemAppleLoopDescriptorsKey (Void)*
 */
private val NSMetadataItemAppleLoopDescriptorsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemAppleLoopDescriptorsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemAppleLoopDescriptorsKey").orElseThrow() }
private val NSMetadataItemAppleLoopDescriptorsKey_VH: VarHandle by lazy { NSMetadataItemAppleLoopDescriptorsKey_LAYOUT.varHandle() }

var NSMetadataItemAppleLoopDescriptorsKey: MemorySegment
    get() = NSMetadataItemAppleLoopDescriptorsKey_VH.get(NSMetadataItemAppleLoopDescriptorsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemAppleLoopDescriptorsKey_VH.set(NSMetadataItemAppleLoopDescriptorsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemMusicalInstrumentCategoryKey (Void)*
 */
private val NSMetadataItemMusicalInstrumentCategoryKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemMusicalInstrumentCategoryKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemMusicalInstrumentCategoryKey").orElseThrow() }
private val NSMetadataItemMusicalInstrumentCategoryKey_VH: VarHandle by lazy { NSMetadataItemMusicalInstrumentCategoryKey_LAYOUT.varHandle() }

var NSMetadataItemMusicalInstrumentCategoryKey: MemorySegment
    get() = NSMetadataItemMusicalInstrumentCategoryKey_VH.get(NSMetadataItemMusicalInstrumentCategoryKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemMusicalInstrumentCategoryKey_VH.set(NSMetadataItemMusicalInstrumentCategoryKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemMusicalInstrumentNameKey (Void)*
 */
private val NSMetadataItemMusicalInstrumentNameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemMusicalInstrumentNameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemMusicalInstrumentNameKey").orElseThrow() }
private val NSMetadataItemMusicalInstrumentNameKey_VH: VarHandle by lazy { NSMetadataItemMusicalInstrumentNameKey_LAYOUT.varHandle() }

var NSMetadataItemMusicalInstrumentNameKey: MemorySegment
    get() = NSMetadataItemMusicalInstrumentNameKey_VH.get(NSMetadataItemMusicalInstrumentNameKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemMusicalInstrumentNameKey_VH.set(NSMetadataItemMusicalInstrumentNameKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemCFBundleIdentifierKey (Void)*
 */
private val NSMetadataItemCFBundleIdentifierKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemCFBundleIdentifierKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemCFBundleIdentifierKey").orElseThrow() }
private val NSMetadataItemCFBundleIdentifierKey_VH: VarHandle by lazy { NSMetadataItemCFBundleIdentifierKey_LAYOUT.varHandle() }

var NSMetadataItemCFBundleIdentifierKey: MemorySegment
    get() = NSMetadataItemCFBundleIdentifierKey_VH.get(NSMetadataItemCFBundleIdentifierKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemCFBundleIdentifierKey_VH.set(NSMetadataItemCFBundleIdentifierKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemInformationKey (Void)*
 */
private val NSMetadataItemInformationKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemInformationKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemInformationKey").orElseThrow() }
private val NSMetadataItemInformationKey_VH: VarHandle by lazy { NSMetadataItemInformationKey_LAYOUT.varHandle() }

var NSMetadataItemInformationKey: MemorySegment
    get() = NSMetadataItemInformationKey_VH.get(NSMetadataItemInformationKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemInformationKey_VH.set(NSMetadataItemInformationKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemDirectorKey (Void)*
 */
private val NSMetadataItemDirectorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemDirectorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemDirectorKey").orElseThrow() }
private val NSMetadataItemDirectorKey_VH: VarHandle by lazy { NSMetadataItemDirectorKey_LAYOUT.varHandle() }

var NSMetadataItemDirectorKey: MemorySegment
    get() = NSMetadataItemDirectorKey_VH.get(NSMetadataItemDirectorKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemDirectorKey_VH.set(NSMetadataItemDirectorKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemProducerKey (Void)*
 */
private val NSMetadataItemProducerKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemProducerKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemProducerKey").orElseThrow() }
private val NSMetadataItemProducerKey_VH: VarHandle by lazy { NSMetadataItemProducerKey_LAYOUT.varHandle() }

var NSMetadataItemProducerKey: MemorySegment
    get() = NSMetadataItemProducerKey_VH.get(NSMetadataItemProducerKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemProducerKey_VH.set(NSMetadataItemProducerKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemGenreKey (Void)*
 */
private val NSMetadataItemGenreKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemGenreKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemGenreKey").orElseThrow() }
private val NSMetadataItemGenreKey_VH: VarHandle by lazy { NSMetadataItemGenreKey_LAYOUT.varHandle() }

var NSMetadataItemGenreKey: MemorySegment
    get() = NSMetadataItemGenreKey_VH.get(NSMetadataItemGenreKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemGenreKey_VH.set(NSMetadataItemGenreKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemPerformersKey (Void)*
 */
private val NSMetadataItemPerformersKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemPerformersKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemPerformersKey").orElseThrow() }
private val NSMetadataItemPerformersKey_VH: VarHandle by lazy { NSMetadataItemPerformersKey_LAYOUT.varHandle() }

var NSMetadataItemPerformersKey: MemorySegment
    get() = NSMetadataItemPerformersKey_VH.get(NSMetadataItemPerformersKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemPerformersKey_VH.set(NSMetadataItemPerformersKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemOriginalFormatKey (Void)*
 */
private val NSMetadataItemOriginalFormatKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemOriginalFormatKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemOriginalFormatKey").orElseThrow() }
private val NSMetadataItemOriginalFormatKey_VH: VarHandle by lazy { NSMetadataItemOriginalFormatKey_LAYOUT.varHandle() }

var NSMetadataItemOriginalFormatKey: MemorySegment
    get() = NSMetadataItemOriginalFormatKey_VH.get(NSMetadataItemOriginalFormatKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemOriginalFormatKey_VH.set(NSMetadataItemOriginalFormatKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemOriginalSourceKey (Void)*
 */
private val NSMetadataItemOriginalSourceKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemOriginalSourceKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemOriginalSourceKey").orElseThrow() }
private val NSMetadataItemOriginalSourceKey_VH: VarHandle by lazy { NSMetadataItemOriginalSourceKey_LAYOUT.varHandle() }

var NSMetadataItemOriginalSourceKey: MemorySegment
    get() = NSMetadataItemOriginalSourceKey_VH.get(NSMetadataItemOriginalSourceKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemOriginalSourceKey_VH.set(NSMetadataItemOriginalSourceKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemAuthorEmailAddressesKey (Void)*
 */
private val NSMetadataItemAuthorEmailAddressesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemAuthorEmailAddressesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemAuthorEmailAddressesKey").orElseThrow() }
private val NSMetadataItemAuthorEmailAddressesKey_VH: VarHandle by lazy { NSMetadataItemAuthorEmailAddressesKey_LAYOUT.varHandle() }

var NSMetadataItemAuthorEmailAddressesKey: MemorySegment
    get() = NSMetadataItemAuthorEmailAddressesKey_VH.get(NSMetadataItemAuthorEmailAddressesKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemAuthorEmailAddressesKey_VH.set(NSMetadataItemAuthorEmailAddressesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemRecipientEmailAddressesKey (Void)*
 */
private val NSMetadataItemRecipientEmailAddressesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemRecipientEmailAddressesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemRecipientEmailAddressesKey").orElseThrow() }
private val NSMetadataItemRecipientEmailAddressesKey_VH: VarHandle by lazy { NSMetadataItemRecipientEmailAddressesKey_LAYOUT.varHandle() }

var NSMetadataItemRecipientEmailAddressesKey: MemorySegment
    get() = NSMetadataItemRecipientEmailAddressesKey_VH.get(NSMetadataItemRecipientEmailAddressesKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemRecipientEmailAddressesKey_VH.set(NSMetadataItemRecipientEmailAddressesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemAuthorAddressesKey (Void)*
 */
private val NSMetadataItemAuthorAddressesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemAuthorAddressesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemAuthorAddressesKey").orElseThrow() }
private val NSMetadataItemAuthorAddressesKey_VH: VarHandle by lazy { NSMetadataItemAuthorAddressesKey_LAYOUT.varHandle() }

var NSMetadataItemAuthorAddressesKey: MemorySegment
    get() = NSMetadataItemAuthorAddressesKey_VH.get(NSMetadataItemAuthorAddressesKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemAuthorAddressesKey_VH.set(NSMetadataItemAuthorAddressesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemRecipientAddressesKey (Void)*
 */
private val NSMetadataItemRecipientAddressesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemRecipientAddressesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemRecipientAddressesKey").orElseThrow() }
private val NSMetadataItemRecipientAddressesKey_VH: VarHandle by lazy { NSMetadataItemRecipientAddressesKey_LAYOUT.varHandle() }

var NSMetadataItemRecipientAddressesKey: MemorySegment
    get() = NSMetadataItemRecipientAddressesKey_VH.get(NSMetadataItemRecipientAddressesKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemRecipientAddressesKey_VH.set(NSMetadataItemRecipientAddressesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemIsLikelyJunkKey (Void)*
 */
private val NSMetadataItemIsLikelyJunkKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemIsLikelyJunkKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemIsLikelyJunkKey").orElseThrow() }
private val NSMetadataItemIsLikelyJunkKey_VH: VarHandle by lazy { NSMetadataItemIsLikelyJunkKey_LAYOUT.varHandle() }

var NSMetadataItemIsLikelyJunkKey: MemorySegment
    get() = NSMetadataItemIsLikelyJunkKey_VH.get(NSMetadataItemIsLikelyJunkKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemIsLikelyJunkKey_VH.set(NSMetadataItemIsLikelyJunkKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemExecutableArchitecturesKey (Void)*
 */
private val NSMetadataItemExecutableArchitecturesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemExecutableArchitecturesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemExecutableArchitecturesKey").orElseThrow() }
private val NSMetadataItemExecutableArchitecturesKey_VH: VarHandle by lazy { NSMetadataItemExecutableArchitecturesKey_LAYOUT.varHandle() }

var NSMetadataItemExecutableArchitecturesKey: MemorySegment
    get() = NSMetadataItemExecutableArchitecturesKey_VH.get(NSMetadataItemExecutableArchitecturesKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemExecutableArchitecturesKey_VH.set(NSMetadataItemExecutableArchitecturesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemExecutablePlatformKey (Void)*
 */
private val NSMetadataItemExecutablePlatformKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemExecutablePlatformKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemExecutablePlatformKey").orElseThrow() }
private val NSMetadataItemExecutablePlatformKey_VH: VarHandle by lazy { NSMetadataItemExecutablePlatformKey_LAYOUT.varHandle() }

var NSMetadataItemExecutablePlatformKey: MemorySegment
    get() = NSMetadataItemExecutablePlatformKey_VH.get(NSMetadataItemExecutablePlatformKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemExecutablePlatformKey_VH.set(NSMetadataItemExecutablePlatformKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemApplicationCategoriesKey (Void)*
 */
private val NSMetadataItemApplicationCategoriesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemApplicationCategoriesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemApplicationCategoriesKey").orElseThrow() }
private val NSMetadataItemApplicationCategoriesKey_VH: VarHandle by lazy { NSMetadataItemApplicationCategoriesKey_LAYOUT.varHandle() }

var NSMetadataItemApplicationCategoriesKey: MemorySegment
    get() = NSMetadataItemApplicationCategoriesKey_VH.get(NSMetadataItemApplicationCategoriesKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemApplicationCategoriesKey_VH.set(NSMetadataItemApplicationCategoriesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataItemIsApplicationManagedKey (Void)*
 */
private val NSMetadataItemIsApplicationManagedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataItemIsApplicationManagedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataItemIsApplicationManagedKey").orElseThrow() }
private val NSMetadataItemIsApplicationManagedKey_VH: VarHandle by lazy { NSMetadataItemIsApplicationManagedKey_LAYOUT.varHandle() }

var NSMetadataItemIsApplicationManagedKey: MemorySegment
    get() = NSMetadataItemIsApplicationManagedKey_VH.get(NSMetadataItemIsApplicationManagedKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataItemIsApplicationManagedKey_VH.set(NSMetadataItemIsApplicationManagedKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataQueryDidStartGatheringNotification typedef const NSNotificationName = (Void)*
 */
private val NSMetadataQueryDidStartGatheringNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataQueryDidStartGatheringNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataQueryDidStartGatheringNotification").orElseThrow() }
private val NSMetadataQueryDidStartGatheringNotification_VH: VarHandle by lazy { NSMetadataQueryDidStartGatheringNotification_LAYOUT.varHandle() }

var NSMetadataQueryDidStartGatheringNotification: MemorySegment
    get() = NSMetadataQueryDidStartGatheringNotification_VH.get(NSMetadataQueryDidStartGatheringNotification_SEGMENT) as MemorySegment
    set(value) = NSMetadataQueryDidStartGatheringNotification_VH.set(NSMetadataQueryDidStartGatheringNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataQueryGatheringProgressNotification typedef const NSNotificationName = (Void)*
 */
private val NSMetadataQueryGatheringProgressNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataQueryGatheringProgressNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataQueryGatheringProgressNotification").orElseThrow() }
private val NSMetadataQueryGatheringProgressNotification_VH: VarHandle by lazy { NSMetadataQueryGatheringProgressNotification_LAYOUT.varHandle() }

var NSMetadataQueryGatheringProgressNotification: MemorySegment
    get() = NSMetadataQueryGatheringProgressNotification_VH.get(NSMetadataQueryGatheringProgressNotification_SEGMENT) as MemorySegment
    set(value) = NSMetadataQueryGatheringProgressNotification_VH.set(NSMetadataQueryGatheringProgressNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataQueryDidFinishGatheringNotification typedef const NSNotificationName = (Void)*
 */
private val NSMetadataQueryDidFinishGatheringNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataQueryDidFinishGatheringNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataQueryDidFinishGatheringNotification").orElseThrow() }
private val NSMetadataQueryDidFinishGatheringNotification_VH: VarHandle by lazy { NSMetadataQueryDidFinishGatheringNotification_LAYOUT.varHandle() }

var NSMetadataQueryDidFinishGatheringNotification: MemorySegment
    get() = NSMetadataQueryDidFinishGatheringNotification_VH.get(NSMetadataQueryDidFinishGatheringNotification_SEGMENT) as MemorySegment
    set(value) = NSMetadataQueryDidFinishGatheringNotification_VH.set(NSMetadataQueryDidFinishGatheringNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataQueryDidUpdateNotification typedef const NSNotificationName = (Void)*
 */
private val NSMetadataQueryDidUpdateNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataQueryDidUpdateNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataQueryDidUpdateNotification").orElseThrow() }
private val NSMetadataQueryDidUpdateNotification_VH: VarHandle by lazy { NSMetadataQueryDidUpdateNotification_LAYOUT.varHandle() }

var NSMetadataQueryDidUpdateNotification: MemorySegment
    get() = NSMetadataQueryDidUpdateNotification_VH.get(NSMetadataQueryDidUpdateNotification_SEGMENT) as MemorySegment
    set(value) = NSMetadataQueryDidUpdateNotification_VH.set(NSMetadataQueryDidUpdateNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataQueryUpdateAddedItemsKey (Void)*
 */
private val NSMetadataQueryUpdateAddedItemsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataQueryUpdateAddedItemsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataQueryUpdateAddedItemsKey").orElseThrow() }
private val NSMetadataQueryUpdateAddedItemsKey_VH: VarHandle by lazy { NSMetadataQueryUpdateAddedItemsKey_LAYOUT.varHandle() }

var NSMetadataQueryUpdateAddedItemsKey: MemorySegment
    get() = NSMetadataQueryUpdateAddedItemsKey_VH.get(NSMetadataQueryUpdateAddedItemsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataQueryUpdateAddedItemsKey_VH.set(NSMetadataQueryUpdateAddedItemsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataQueryUpdateChangedItemsKey (Void)*
 */
private val NSMetadataQueryUpdateChangedItemsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataQueryUpdateChangedItemsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataQueryUpdateChangedItemsKey").orElseThrow() }
private val NSMetadataQueryUpdateChangedItemsKey_VH: VarHandle by lazy { NSMetadataQueryUpdateChangedItemsKey_LAYOUT.varHandle() }

var NSMetadataQueryUpdateChangedItemsKey: MemorySegment
    get() = NSMetadataQueryUpdateChangedItemsKey_VH.get(NSMetadataQueryUpdateChangedItemsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataQueryUpdateChangedItemsKey_VH.set(NSMetadataQueryUpdateChangedItemsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataQueryUpdateRemovedItemsKey (Void)*
 */
private val NSMetadataQueryUpdateRemovedItemsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataQueryUpdateRemovedItemsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataQueryUpdateRemovedItemsKey").orElseThrow() }
private val NSMetadataQueryUpdateRemovedItemsKey_VH: VarHandle by lazy { NSMetadataQueryUpdateRemovedItemsKey_LAYOUT.varHandle() }

var NSMetadataQueryUpdateRemovedItemsKey: MemorySegment
    get() = NSMetadataQueryUpdateRemovedItemsKey_VH.get(NSMetadataQueryUpdateRemovedItemsKey_SEGMENT) as MemorySegment
    set(value) = NSMetadataQueryUpdateRemovedItemsKey_VH.set(NSMetadataQueryUpdateRemovedItemsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataQueryResultContentRelevanceAttribute (Void)*
 */
private val NSMetadataQueryResultContentRelevanceAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataQueryResultContentRelevanceAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataQueryResultContentRelevanceAttribute").orElseThrow() }
private val NSMetadataQueryResultContentRelevanceAttribute_VH: VarHandle by lazy { NSMetadataQueryResultContentRelevanceAttribute_LAYOUT.varHandle() }

var NSMetadataQueryResultContentRelevanceAttribute: MemorySegment
    get() = NSMetadataQueryResultContentRelevanceAttribute_VH.get(NSMetadataQueryResultContentRelevanceAttribute_SEGMENT) as MemorySegment
    set(value) = NSMetadataQueryResultContentRelevanceAttribute_VH.set(NSMetadataQueryResultContentRelevanceAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataQueryUserHomeScope (Void)*
 */
private val NSMetadataQueryUserHomeScope_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataQueryUserHomeScope_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataQueryUserHomeScope").orElseThrow() }
private val NSMetadataQueryUserHomeScope_VH: VarHandle by lazy { NSMetadataQueryUserHomeScope_LAYOUT.varHandle() }

var NSMetadataQueryUserHomeScope: MemorySegment
    get() = NSMetadataQueryUserHomeScope_VH.get(NSMetadataQueryUserHomeScope_SEGMENT) as MemorySegment
    set(value) = NSMetadataQueryUserHomeScope_VH.set(NSMetadataQueryUserHomeScope_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataQueryLocalComputerScope (Void)*
 */
private val NSMetadataQueryLocalComputerScope_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataQueryLocalComputerScope_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataQueryLocalComputerScope").orElseThrow() }
private val NSMetadataQueryLocalComputerScope_VH: VarHandle by lazy { NSMetadataQueryLocalComputerScope_LAYOUT.varHandle() }

var NSMetadataQueryLocalComputerScope: MemorySegment
    get() = NSMetadataQueryLocalComputerScope_VH.get(NSMetadataQueryLocalComputerScope_SEGMENT) as MemorySegment
    set(value) = NSMetadataQueryLocalComputerScope_VH.set(NSMetadataQueryLocalComputerScope_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataQueryNetworkScope (Void)*
 */
private val NSMetadataQueryNetworkScope_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataQueryNetworkScope_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataQueryNetworkScope").orElseThrow() }
private val NSMetadataQueryNetworkScope_VH: VarHandle by lazy { NSMetadataQueryNetworkScope_LAYOUT.varHandle() }

var NSMetadataQueryNetworkScope: MemorySegment
    get() = NSMetadataQueryNetworkScope_VH.get(NSMetadataQueryNetworkScope_SEGMENT) as MemorySegment
    set(value) = NSMetadataQueryNetworkScope_VH.set(NSMetadataQueryNetworkScope_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataQueryIndexedLocalComputerScope (Void)*
 */
private val NSMetadataQueryIndexedLocalComputerScope_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataQueryIndexedLocalComputerScope_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataQueryIndexedLocalComputerScope").orElseThrow() }
private val NSMetadataQueryIndexedLocalComputerScope_VH: VarHandle by lazy { NSMetadataQueryIndexedLocalComputerScope_LAYOUT.varHandle() }

var NSMetadataQueryIndexedLocalComputerScope: MemorySegment
    get() = NSMetadataQueryIndexedLocalComputerScope_VH.get(NSMetadataQueryIndexedLocalComputerScope_SEGMENT) as MemorySegment
    set(value) = NSMetadataQueryIndexedLocalComputerScope_VH.set(NSMetadataQueryIndexedLocalComputerScope_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataQueryIndexedNetworkScope (Void)*
 */
private val NSMetadataQueryIndexedNetworkScope_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataQueryIndexedNetworkScope_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataQueryIndexedNetworkScope").orElseThrow() }
private val NSMetadataQueryIndexedNetworkScope_VH: VarHandle by lazy { NSMetadataQueryIndexedNetworkScope_LAYOUT.varHandle() }

var NSMetadataQueryIndexedNetworkScope: MemorySegment
    get() = NSMetadataQueryIndexedNetworkScope_VH.get(NSMetadataQueryIndexedNetworkScope_SEGMENT) as MemorySegment
    set(value) = NSMetadataQueryIndexedNetworkScope_VH.set(NSMetadataQueryIndexedNetworkScope_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataQueryUbiquitousDocumentsScope (Void)*
 */
private val NSMetadataQueryUbiquitousDocumentsScope_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataQueryUbiquitousDocumentsScope_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataQueryUbiquitousDocumentsScope").orElseThrow() }
private val NSMetadataQueryUbiquitousDocumentsScope_VH: VarHandle by lazy { NSMetadataQueryUbiquitousDocumentsScope_LAYOUT.varHandle() }

var NSMetadataQueryUbiquitousDocumentsScope: MemorySegment
    get() = NSMetadataQueryUbiquitousDocumentsScope_VH.get(NSMetadataQueryUbiquitousDocumentsScope_SEGMENT) as MemorySegment
    set(value) = NSMetadataQueryUbiquitousDocumentsScope_VH.set(NSMetadataQueryUbiquitousDocumentsScope_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataQueryUbiquitousDataScope (Void)*
 */
private val NSMetadataQueryUbiquitousDataScope_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataQueryUbiquitousDataScope_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataQueryUbiquitousDataScope").orElseThrow() }
private val NSMetadataQueryUbiquitousDataScope_VH: VarHandle by lazy { NSMetadataQueryUbiquitousDataScope_LAYOUT.varHandle() }

var NSMetadataQueryUbiquitousDataScope: MemorySegment
    get() = NSMetadataQueryUbiquitousDataScope_VH.get(NSMetadataQueryUbiquitousDataScope_SEGMENT) as MemorySegment
    set(value) = NSMetadataQueryUbiquitousDataScope_VH.set(NSMetadataQueryUbiquitousDataScope_SEGMENT, value)

/**
 * {@snippet lang=c : NSMetadataQueryAccessibleUbiquitousExternalDocumentsScope (Void)*
 */
private val NSMetadataQueryAccessibleUbiquitousExternalDocumentsScope_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMetadataQueryAccessibleUbiquitousExternalDocumentsScope_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMetadataQueryAccessibleUbiquitousExternalDocumentsScope").orElseThrow() }
private val NSMetadataQueryAccessibleUbiquitousExternalDocumentsScope_VH: VarHandle by lazy { NSMetadataQueryAccessibleUbiquitousExternalDocumentsScope_LAYOUT.varHandle() }

var NSMetadataQueryAccessibleUbiquitousExternalDocumentsScope: MemorySegment
    get() = NSMetadataQueryAccessibleUbiquitousExternalDocumentsScope_VH.get(NSMetadataQueryAccessibleUbiquitousExternalDocumentsScope_SEGMENT) as MemorySegment
    set(value) = NSMetadataQueryAccessibleUbiquitousExternalDocumentsScope_VH.set(NSMetadataQueryAccessibleUbiquitousExternalDocumentsScope_SEGMENT, value)

/**
 * {@snippet lang=c : NSNetServicesErrorCode (Void)*
 */
private val NSNetServicesErrorCode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSNetServicesErrorCode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSNetServicesErrorCode").orElseThrow() }
private val NSNetServicesErrorCode_VH: VarHandle by lazy { NSNetServicesErrorCode_LAYOUT.varHandle() }

var NSNetServicesErrorCode: MemorySegment
    get() = NSNetServicesErrorCode_VH.get(NSNetServicesErrorCode_SEGMENT) as MemorySegment
    set(value) = NSNetServicesErrorCode_VH.set(NSNetServicesErrorCode_SEGMENT, value)

/**
 * {@snippet lang=c : NSNetServicesErrorDomain typedef const NSErrorDomain = (Void)*
 */
private val NSNetServicesErrorDomain_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSNetServicesErrorDomain_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSNetServicesErrorDomain").orElseThrow() }
private val NSNetServicesErrorDomain_VH: VarHandle by lazy { NSNetServicesErrorDomain_LAYOUT.varHandle() }

var NSNetServicesErrorDomain: MemorySegment
    get() = NSNetServicesErrorDomain_VH.get(NSNetServicesErrorDomain_SEGMENT) as MemorySegment
    set(value) = NSNetServicesErrorDomain_VH.set(NSNetServicesErrorDomain_SEGMENT, value)

/**
 * {@snippet lang=c : NSUbiquitousKeyValueStoreDidChangeExternallyNotification typedef const NSNotificationName = (Void)*
 */
private val NSUbiquitousKeyValueStoreDidChangeExternallyNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUbiquitousKeyValueStoreDidChangeExternallyNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUbiquitousKeyValueStoreDidChangeExternallyNotification").orElseThrow() }
private val NSUbiquitousKeyValueStoreDidChangeExternallyNotification_VH: VarHandle by lazy { NSUbiquitousKeyValueStoreDidChangeExternallyNotification_LAYOUT.varHandle() }

var NSUbiquitousKeyValueStoreDidChangeExternallyNotification: MemorySegment
    get() = NSUbiquitousKeyValueStoreDidChangeExternallyNotification_VH.get(NSUbiquitousKeyValueStoreDidChangeExternallyNotification_SEGMENT) as MemorySegment
    set(value) = NSUbiquitousKeyValueStoreDidChangeExternallyNotification_VH.set(NSUbiquitousKeyValueStoreDidChangeExternallyNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSUbiquitousKeyValueStoreChangeReasonKey (Void)*
 */
private val NSUbiquitousKeyValueStoreChangeReasonKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUbiquitousKeyValueStoreChangeReasonKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUbiquitousKeyValueStoreChangeReasonKey").orElseThrow() }
private val NSUbiquitousKeyValueStoreChangeReasonKey_VH: VarHandle by lazy { NSUbiquitousKeyValueStoreChangeReasonKey_LAYOUT.varHandle() }

var NSUbiquitousKeyValueStoreChangeReasonKey: MemorySegment
    get() = NSUbiquitousKeyValueStoreChangeReasonKey_VH.get(NSUbiquitousKeyValueStoreChangeReasonKey_SEGMENT) as MemorySegment
    set(value) = NSUbiquitousKeyValueStoreChangeReasonKey_VH.set(NSUbiquitousKeyValueStoreChangeReasonKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSUbiquitousKeyValueStoreChangedKeysKey (Void)*
 */
private val NSUbiquitousKeyValueStoreChangedKeysKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUbiquitousKeyValueStoreChangedKeysKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUbiquitousKeyValueStoreChangedKeysKey").orElseThrow() }
private val NSUbiquitousKeyValueStoreChangedKeysKey_VH: VarHandle by lazy { NSUbiquitousKeyValueStoreChangedKeysKey_LAYOUT.varHandle() }

var NSUbiquitousKeyValueStoreChangedKeysKey: MemorySegment
    get() = NSUbiquitousKeyValueStoreChangedKeysKey_VH.get(NSUbiquitousKeyValueStoreChangedKeysKey_SEGMENT) as MemorySegment
    set(value) = NSUbiquitousKeyValueStoreChangedKeysKey_VH.set(NSUbiquitousKeyValueStoreChangedKeysKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSUndoManagerGroupIsDiscardableKey (Void)*
 */
private val NSUndoManagerGroupIsDiscardableKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUndoManagerGroupIsDiscardableKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUndoManagerGroupIsDiscardableKey").orElseThrow() }
private val NSUndoManagerGroupIsDiscardableKey_VH: VarHandle by lazy { NSUndoManagerGroupIsDiscardableKey_LAYOUT.varHandle() }

var NSUndoManagerGroupIsDiscardableKey: MemorySegment
    get() = NSUndoManagerGroupIsDiscardableKey_VH.get(NSUndoManagerGroupIsDiscardableKey_SEGMENT) as MemorySegment
    set(value) = NSUndoManagerGroupIsDiscardableKey_VH.set(NSUndoManagerGroupIsDiscardableKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSUndoManagerCheckpointNotification typedef const NSNotificationName = (Void)*
 */
private val NSUndoManagerCheckpointNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUndoManagerCheckpointNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUndoManagerCheckpointNotification").orElseThrow() }
private val NSUndoManagerCheckpointNotification_VH: VarHandle by lazy { NSUndoManagerCheckpointNotification_LAYOUT.varHandle() }

var NSUndoManagerCheckpointNotification: MemorySegment
    get() = NSUndoManagerCheckpointNotification_VH.get(NSUndoManagerCheckpointNotification_SEGMENT) as MemorySegment
    set(value) = NSUndoManagerCheckpointNotification_VH.set(NSUndoManagerCheckpointNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSUndoManagerWillUndoChangeNotification typedef const NSNotificationName = (Void)*
 */
private val NSUndoManagerWillUndoChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUndoManagerWillUndoChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUndoManagerWillUndoChangeNotification").orElseThrow() }
private val NSUndoManagerWillUndoChangeNotification_VH: VarHandle by lazy { NSUndoManagerWillUndoChangeNotification_LAYOUT.varHandle() }

var NSUndoManagerWillUndoChangeNotification: MemorySegment
    get() = NSUndoManagerWillUndoChangeNotification_VH.get(NSUndoManagerWillUndoChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSUndoManagerWillUndoChangeNotification_VH.set(NSUndoManagerWillUndoChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSUndoManagerWillRedoChangeNotification typedef const NSNotificationName = (Void)*
 */
private val NSUndoManagerWillRedoChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUndoManagerWillRedoChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUndoManagerWillRedoChangeNotification").orElseThrow() }
private val NSUndoManagerWillRedoChangeNotification_VH: VarHandle by lazy { NSUndoManagerWillRedoChangeNotification_LAYOUT.varHandle() }

var NSUndoManagerWillRedoChangeNotification: MemorySegment
    get() = NSUndoManagerWillRedoChangeNotification_VH.get(NSUndoManagerWillRedoChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSUndoManagerWillRedoChangeNotification_VH.set(NSUndoManagerWillRedoChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSUndoManagerDidUndoChangeNotification typedef const NSNotificationName = (Void)*
 */
private val NSUndoManagerDidUndoChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUndoManagerDidUndoChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUndoManagerDidUndoChangeNotification").orElseThrow() }
private val NSUndoManagerDidUndoChangeNotification_VH: VarHandle by lazy { NSUndoManagerDidUndoChangeNotification_LAYOUT.varHandle() }

var NSUndoManagerDidUndoChangeNotification: MemorySegment
    get() = NSUndoManagerDidUndoChangeNotification_VH.get(NSUndoManagerDidUndoChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSUndoManagerDidUndoChangeNotification_VH.set(NSUndoManagerDidUndoChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSUndoManagerDidRedoChangeNotification typedef const NSNotificationName = (Void)*
 */
private val NSUndoManagerDidRedoChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUndoManagerDidRedoChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUndoManagerDidRedoChangeNotification").orElseThrow() }
private val NSUndoManagerDidRedoChangeNotification_VH: VarHandle by lazy { NSUndoManagerDidRedoChangeNotification_LAYOUT.varHandle() }

var NSUndoManagerDidRedoChangeNotification: MemorySegment
    get() = NSUndoManagerDidRedoChangeNotification_VH.get(NSUndoManagerDidRedoChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSUndoManagerDidRedoChangeNotification_VH.set(NSUndoManagerDidRedoChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSUndoManagerDidOpenUndoGroupNotification typedef const NSNotificationName = (Void)*
 */
private val NSUndoManagerDidOpenUndoGroupNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUndoManagerDidOpenUndoGroupNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUndoManagerDidOpenUndoGroupNotification").orElseThrow() }
private val NSUndoManagerDidOpenUndoGroupNotification_VH: VarHandle by lazy { NSUndoManagerDidOpenUndoGroupNotification_LAYOUT.varHandle() }

var NSUndoManagerDidOpenUndoGroupNotification: MemorySegment
    get() = NSUndoManagerDidOpenUndoGroupNotification_VH.get(NSUndoManagerDidOpenUndoGroupNotification_SEGMENT) as MemorySegment
    set(value) = NSUndoManagerDidOpenUndoGroupNotification_VH.set(NSUndoManagerDidOpenUndoGroupNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSUndoManagerWillCloseUndoGroupNotification typedef const NSNotificationName = (Void)*
 */
private val NSUndoManagerWillCloseUndoGroupNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUndoManagerWillCloseUndoGroupNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUndoManagerWillCloseUndoGroupNotification").orElseThrow() }
private val NSUndoManagerWillCloseUndoGroupNotification_VH: VarHandle by lazy { NSUndoManagerWillCloseUndoGroupNotification_LAYOUT.varHandle() }

var NSUndoManagerWillCloseUndoGroupNotification: MemorySegment
    get() = NSUndoManagerWillCloseUndoGroupNotification_VH.get(NSUndoManagerWillCloseUndoGroupNotification_SEGMENT) as MemorySegment
    set(value) = NSUndoManagerWillCloseUndoGroupNotification_VH.set(NSUndoManagerWillCloseUndoGroupNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSUndoManagerDidCloseUndoGroupNotification typedef const NSNotificationName = (Void)*
 */
private val NSUndoManagerDidCloseUndoGroupNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUndoManagerDidCloseUndoGroupNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUndoManagerDidCloseUndoGroupNotification").orElseThrow() }
private val NSUndoManagerDidCloseUndoGroupNotification_VH: VarHandle by lazy { NSUndoManagerDidCloseUndoGroupNotification_LAYOUT.varHandle() }

var NSUndoManagerDidCloseUndoGroupNotification: MemorySegment
    get() = NSUndoManagerDidCloseUndoGroupNotification_VH.get(NSUndoManagerDidCloseUndoGroupNotification_SEGMENT) as MemorySegment
    set(value) = NSUndoManagerDidCloseUndoGroupNotification_VH.set(NSUndoManagerDidCloseUndoGroupNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLSessionTransferSizeUnknown typedef const int64_t = LongLong
 */
private val NSURLSessionTransferSizeUnknown_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_LONG }
private val NSURLSessionTransferSizeUnknown_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLSessionTransferSizeUnknown").orElseThrow() }
private val NSURLSessionTransferSizeUnknown_VH: VarHandle by lazy { NSURLSessionTransferSizeUnknown_LAYOUT.varHandle() }

var NSURLSessionTransferSizeUnknown: Long
    get() = NSURLSessionTransferSizeUnknown_VH.get(NSURLSessionTransferSizeUnknown_SEGMENT) as Long
    set(value) = NSURLSessionTransferSizeUnknown_VH.set(NSURLSessionTransferSizeUnknown_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLSessionTaskPriorityDefault Float
 */
private val NSURLSessionTaskPriorityDefault_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_FLOAT }
private val NSURLSessionTaskPriorityDefault_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLSessionTaskPriorityDefault").orElseThrow() }
private val NSURLSessionTaskPriorityDefault_VH: VarHandle by lazy { NSURLSessionTaskPriorityDefault_LAYOUT.varHandle() }

var NSURLSessionTaskPriorityDefault: Float
    get() = NSURLSessionTaskPriorityDefault_VH.get(NSURLSessionTaskPriorityDefault_SEGMENT) as Float
    set(value) = NSURLSessionTaskPriorityDefault_VH.set(NSURLSessionTaskPriorityDefault_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLSessionTaskPriorityLow Float
 */
private val NSURLSessionTaskPriorityLow_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_FLOAT }
private val NSURLSessionTaskPriorityLow_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLSessionTaskPriorityLow").orElseThrow() }
private val NSURLSessionTaskPriorityLow_VH: VarHandle by lazy { NSURLSessionTaskPriorityLow_LAYOUT.varHandle() }

var NSURLSessionTaskPriorityLow: Float
    get() = NSURLSessionTaskPriorityLow_VH.get(NSURLSessionTaskPriorityLow_SEGMENT) as Float
    set(value) = NSURLSessionTaskPriorityLow_VH.set(NSURLSessionTaskPriorityLow_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLSessionTaskPriorityHigh Float
 */
private val NSURLSessionTaskPriorityHigh_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_FLOAT }
private val NSURLSessionTaskPriorityHigh_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLSessionTaskPriorityHigh").orElseThrow() }
private val NSURLSessionTaskPriorityHigh_VH: VarHandle by lazy { NSURLSessionTaskPriorityHigh_LAYOUT.varHandle() }

var NSURLSessionTaskPriorityHigh: Float
    get() = NSURLSessionTaskPriorityHigh_VH.get(NSURLSessionTaskPriorityHigh_SEGMENT) as Float
    set(value) = NSURLSessionTaskPriorityHigh_VH.set(NSURLSessionTaskPriorityHigh_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLSessionDownloadTaskResumeData (Void)*
 */
private val NSURLSessionDownloadTaskResumeData_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLSessionDownloadTaskResumeData_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLSessionDownloadTaskResumeData").orElseThrow() }
private val NSURLSessionDownloadTaskResumeData_VH: VarHandle by lazy { NSURLSessionDownloadTaskResumeData_LAYOUT.varHandle() }

var NSURLSessionDownloadTaskResumeData: MemorySegment
    get() = NSURLSessionDownloadTaskResumeData_VH.get(NSURLSessionDownloadTaskResumeData_SEGMENT) as MemorySegment
    set(value) = NSURLSessionDownloadTaskResumeData_VH.set(NSURLSessionDownloadTaskResumeData_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLSessionUploadTaskResumeData (Void)*
 */
private val NSURLSessionUploadTaskResumeData_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLSessionUploadTaskResumeData_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLSessionUploadTaskResumeData").orElseThrow() }
private val NSURLSessionUploadTaskResumeData_VH: VarHandle by lazy { NSURLSessionUploadTaskResumeData_LAYOUT.varHandle() }

var NSURLSessionUploadTaskResumeData: MemorySegment
    get() = NSURLSessionUploadTaskResumeData_VH.get(NSURLSessionUploadTaskResumeData_SEGMENT) as MemorySegment
    set(value) = NSURLSessionUploadTaskResumeData_VH.set(NSURLSessionUploadTaskResumeData_SEGMENT, value)

/**
 * {@snippet lang=c : NSUserActivityTypeBrowsingWeb (Void)*
 */
private val NSUserActivityTypeBrowsingWeb_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUserActivityTypeBrowsingWeb_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUserActivityTypeBrowsingWeb").orElseThrow() }
private val NSUserActivityTypeBrowsingWeb_VH: VarHandle by lazy { NSUserActivityTypeBrowsingWeb_LAYOUT.varHandle() }

var NSUserActivityTypeBrowsingWeb: MemorySegment
    get() = NSUserActivityTypeBrowsingWeb_VH.get(NSUserActivityTypeBrowsingWeb_SEGMENT) as MemorySegment
    set(value) = NSUserActivityTypeBrowsingWeb_VH.set(NSUserActivityTypeBrowsingWeb_SEGMENT, value)

/**
 * {@snippet lang=c : NSAppleScriptErrorMessage (Void)*
 */
private val NSAppleScriptErrorMessage_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAppleScriptErrorMessage_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAppleScriptErrorMessage").orElseThrow() }
private val NSAppleScriptErrorMessage_VH: VarHandle by lazy { NSAppleScriptErrorMessage_LAYOUT.varHandle() }

var NSAppleScriptErrorMessage: MemorySegment
    get() = NSAppleScriptErrorMessage_VH.get(NSAppleScriptErrorMessage_SEGMENT) as MemorySegment
    set(value) = NSAppleScriptErrorMessage_VH.set(NSAppleScriptErrorMessage_SEGMENT, value)

