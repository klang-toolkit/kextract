package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSSpellChecker
 * Superclass: NSObject
 */
open class NSSpellChecker(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSSpellChecker") }
        
        fun uniqueSpellDocumentTag(): Long {
            val sel = ObjCRuntime.sel("uniqueSpellDocumentTag")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, _class, sel) as Long
        }
        
        fun sharedSpellChecker(): MemorySegment {
            val sel = ObjCRuntime.sel("sharedSpellChecker")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun sharedSpellCheckerExists(): Boolean {
            val sel = ObjCRuntime.sel("sharedSpellCheckerExists")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
        fun isAutomaticTextReplacementEnabled(): Boolean {
            val sel = ObjCRuntime.sel("isAutomaticTextReplacementEnabled")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
        fun isAutomaticSpellingCorrectionEnabled(): Boolean {
            val sel = ObjCRuntime.sel("isAutomaticSpellingCorrectionEnabled")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
        fun isAutomaticQuoteSubstitutionEnabled(): Boolean {
            val sel = ObjCRuntime.sel("isAutomaticQuoteSubstitutionEnabled")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
        fun isAutomaticDashSubstitutionEnabled(): Boolean {
            val sel = ObjCRuntime.sel("isAutomaticDashSubstitutionEnabled")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
        fun isAutomaticCapitalizationEnabled(): Boolean {
            val sel = ObjCRuntime.sel("isAutomaticCapitalizationEnabled")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
        fun isAutomaticPeriodSubstitutionEnabled(): Boolean {
            val sel = ObjCRuntime.sel("isAutomaticPeriodSubstitutionEnabled")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
        fun isAutomaticTextCompletionEnabled(): Boolean {
            val sel = ObjCRuntime.sel("isAutomaticTextCompletionEnabled")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
        fun isAutomaticInlinePredictionEnabled(): Boolean {
            val sel = ObjCRuntime.sel("isAutomaticInlinePredictionEnabled")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
    }
    
    open fun checkSpellingOfString_startingAt_language_wrap_inSpellDocumentWithTag_wordCount(stringToCheck: MemorySegment, startingOffset: Long, language: MemorySegment, wrapFlag: Boolean, tag: Long, wordCount: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("checkSpellingOfString:startingAt:language:wrap:inSpellDocumentWithTag:wordCount:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel, stringToCheck, startingOffset, language, wrapFlag, tag, wordCount) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun checkSpellingOfString_startingAt_language_wrap_inSpellDocumentWithTag_wordCount(stringToCheck: String, startingOffset: Long, language: String, wrapFlag: Boolean, tag: Long, wordCount: MemorySegment): MemorySegment = checkSpellingOfString_startingAt_language_wrap_inSpellDocumentWithTag_wordCount(ObjCRuntime.newNSString(Arena.global(), stringToCheck), startingOffset, ObjCRuntime.newNSString(Arena.global(), language), wrapFlag, tag, wordCount)
    
    open fun checkSpellingOfString_startingAt(stringToCheck: MemorySegment, startingOffset: Long): MemorySegment {
        val sel = ObjCRuntime.sel("checkSpellingOfString:startingAt:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel, stringToCheck, startingOffset) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun checkSpellingOfString_startingAt(stringToCheck: String, startingOffset: Long): MemorySegment = checkSpellingOfString_startingAt(ObjCRuntime.newNSString(Arena.global(), stringToCheck), startingOffset)
    
    open fun countWordsInString_language(stringToCount: MemorySegment, language: MemorySegment): Long {
        val sel = ObjCRuntime.sel("countWordsInString:language:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, stringToCount, language) as Long
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun countWordsInString_language(stringToCount: String, language: String): Long = countWordsInString_language(ObjCRuntime.newNSString(Arena.global(), stringToCount), ObjCRuntime.newNSString(Arena.global(), language))
    
    open fun checkGrammarOfString_startingAt_language_wrap_inSpellDocumentWithTag_details(stringToCheck: MemorySegment, startingOffset: Long, language: MemorySegment, wrapFlag: Boolean, tag: Long, details: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("checkGrammarOfString:startingAt:language:wrap:inSpellDocumentWithTag:details:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel, stringToCheck, startingOffset, language, wrapFlag, tag, details) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun checkGrammarOfString_startingAt_language_wrap_inSpellDocumentWithTag_details(stringToCheck: String, startingOffset: Long, language: String, wrapFlag: Boolean, tag: Long, details: MemorySegment): MemorySegment = checkGrammarOfString_startingAt_language_wrap_inSpellDocumentWithTag_details(ObjCRuntime.newNSString(Arena.global(), stringToCheck), startingOffset, ObjCRuntime.newNSString(Arena.global(), language), wrapFlag, tag, details)
    
    /** @return NSArray<NSTextCheckingResult *> * */
    open fun checkString_range_types_options_inSpellDocumentWithTag_orthography_wordCount(stringToCheck: MemorySegment, range: MemorySegment, checkingTypes: Long, options: MemorySegment, tag: Long, orthography: MemorySegment, wordCount: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("checkString:range:types:options:inSpellDocumentWithTag:orthography:wordCount:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, stringToCheck, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), checkingTypes, options, tag, orthography, wordCount) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun checkString_range_types_options_inSpellDocumentWithTag_orthography_wordCount(stringToCheck: String, range: MemorySegment, checkingTypes: Long, options: MemorySegment, tag: Long, orthography: MemorySegment, wordCount: MemorySegment): MemorySegment = checkString_range_types_options_inSpellDocumentWithTag_orthography_wordCount(ObjCRuntime.newNSString(Arena.global(), stringToCheck), range, checkingTypes, options, tag, orthography, wordCount)
    
    open fun requestCheckingOfString_range_types_options_inSpellDocumentWithTag_completionHandler(stringToCheck: MemorySegment, range: MemorySegment, checkingTypes: Long, options: MemorySegment, tag: Long, completionHandler: MemorySegment): Long {
        val sel = ObjCRuntime.sel("requestCheckingOfString:range:types:options:inSpellDocumentWithTag:completionHandler:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, stringToCheck, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), checkingTypes, options, tag, completionHandler) as Long
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun requestCheckingOfString_range_types_options_inSpellDocumentWithTag_completionHandler(stringToCheck: String, range: MemorySegment, checkingTypes: Long, options: MemorySegment, tag: Long, completionHandler: MemorySegment): Long = requestCheckingOfString_range_types_options_inSpellDocumentWithTag_completionHandler(ObjCRuntime.newNSString(Arena.global(), stringToCheck), range, checkingTypes, options, tag, completionHandler)
    
    open fun requestCandidatesForSelectedRange_inString_types_options_inSpellDocumentWithTag_completionHandler(selectedRange: MemorySegment, stringToCheck: MemorySegment, checkingTypes: Long, options: MemorySegment, tag: Long, completionHandler: MemorySegment): Long {
        val sel = ObjCRuntime.sel("requestCandidatesForSelectedRange:inString:types:options:inSpellDocumentWithTag:completionHandler:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, ObjCRuntime.ObjCStructArg(selectedRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), stringToCheck, checkingTypes, options, tag, completionHandler) as Long
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun requestCandidatesForSelectedRange_inString_types_options_inSpellDocumentWithTag_completionHandler(selectedRange: MemorySegment, stringToCheck: String, checkingTypes: Long, options: MemorySegment, tag: Long, completionHandler: MemorySegment): Long = requestCandidatesForSelectedRange_inString_types_options_inSpellDocumentWithTag_completionHandler(selectedRange, ObjCRuntime.newNSString(Arena.global(), stringToCheck), checkingTypes, options, tag, completionHandler)
    
    open fun menuForResult_string_options_atLocation_inView(result: MemorySegment, checkedString: MemorySegment, options: MemorySegment, location: MemorySegment, view: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("menuForResult:string:options:atLocation:inView:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, result, checkedString, options, ObjCRuntime.ObjCStructArg(location, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), view) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun menuForResult_string_options_atLocation_inView(result: MemorySegment, checkedString: String, options: MemorySegment, location: MemorySegment, view: MemorySegment): MemorySegment = menuForResult_string_options_atLocation_inView(result, ObjCRuntime.newNSString(Arena.global(), checkedString), options, location, view)
    
    /** @return NSArray<NSString *> * */
    open fun userQuotesArrayForLanguage(language: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("userQuotesArrayForLanguage:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, language) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun userQuotesArrayForLanguage(language: String): MemorySegment = userQuotesArrayForLanguage(ObjCRuntime.newNSString(Arena.global(), language))
    
    open fun updateSpellingPanelWithMisspelledWord(word: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("updateSpellingPanelWithMisspelledWord:")
        ObjCRuntime.msgSend(null, ptr, sel, word)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun updateSpellingPanelWithMisspelledWord(word: String): Unit = updateSpellingPanelWithMisspelledWord(ObjCRuntime.newNSString(Arena.global(), word))
    
    open fun updateSpellingPanelWithGrammarString_detail(string: MemorySegment, detail: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("updateSpellingPanelWithGrammarString:detail:")
        ObjCRuntime.msgSend(null, ptr, sel, string, detail)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun updateSpellingPanelWithGrammarString_detail(string: String, detail: MemorySegment): Unit = updateSpellingPanelWithGrammarString_detail(ObjCRuntime.newNSString(Arena.global(), string), detail)
    
    open fun updatePanels(): Unit {
        val sel = ObjCRuntime.sel("updatePanels")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun ignoreWord_inSpellDocumentWithTag(wordToIgnore: MemorySegment, tag: Long): Unit {
        val sel = ObjCRuntime.sel("ignoreWord:inSpellDocumentWithTag:")
        ObjCRuntime.msgSend(null, ptr, sel, wordToIgnore, tag)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun ignoreWord_inSpellDocumentWithTag(wordToIgnore: String, tag: Long): Unit = ignoreWord_inSpellDocumentWithTag(ObjCRuntime.newNSString(Arena.global(), wordToIgnore), tag)
    
    /** @return NSArray<NSString *> * */
    open fun ignoredWordsInSpellDocumentWithTag(tag: Long): MemorySegment {
        val sel = ObjCRuntime.sel("ignoredWordsInSpellDocumentWithTag:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, tag) as MemorySegment
    }
    
    open fun setIgnoredWords_inSpellDocumentWithTag(words: MemorySegment, tag: Long): Unit {
        val sel = ObjCRuntime.sel("setIgnoredWords:inSpellDocumentWithTag:")
        ObjCRuntime.msgSend(null, ptr, sel, words, tag)
    }
    
    /** @return NSArray<NSString *> * */
    open fun guessesForWordRange_inString_language_inSpellDocumentWithTag(range: MemorySegment, string: MemorySegment, language: MemorySegment, tag: Long): MemorySegment {
        val sel = ObjCRuntime.sel("guessesForWordRange:inString:language:inSpellDocumentWithTag:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), string, language, tag) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun guessesForWordRange_inString_language_inSpellDocumentWithTag(range: MemorySegment, string: String, language: String, tag: Long): MemorySegment = guessesForWordRange_inString_language_inSpellDocumentWithTag(range, ObjCRuntime.newNSString(Arena.global(), string), ObjCRuntime.newNSString(Arena.global(), language), tag)
    
    open fun correctionForWordRange_inString_language_inSpellDocumentWithTag(range: MemorySegment, string: MemorySegment, language: MemorySegment, tag: Long): MemorySegment {
        val sel = ObjCRuntime.sel("correctionForWordRange:inString:language:inSpellDocumentWithTag:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), string, language, tag) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun correctionForWordRange_inString_language_inSpellDocumentWithTagAsString(range: MemorySegment, string: MemorySegment, language: MemorySegment, tag: Long): String = ObjCRuntime.toJavaString(correctionForWordRange_inString_language_inSpellDocumentWithTag(range, string, language, tag))
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun correctionForWordRange_inString_language_inSpellDocumentWithTag(range: MemorySegment, string: String, language: String, tag: Long): MemorySegment = correctionForWordRange_inString_language_inSpellDocumentWithTag(range, ObjCRuntime.newNSString(Arena.global(), string), ObjCRuntime.newNSString(Arena.global(), language), tag)
    
    /** Convenience overload — [String] parameters and [String] return type. */
    fun correctionForWordRange_inString_language_inSpellDocumentWithTagAsString(range: MemorySegment, string: String, language: String, tag: Long): String = ObjCRuntime.toJavaString(correctionForWordRange_inString_language_inSpellDocumentWithTag(range, ObjCRuntime.newNSString(Arena.global(), string), ObjCRuntime.newNSString(Arena.global(), language), tag))
    
    /** @return NSArray<NSString *> * */
    open fun completionsForPartialWordRange_inString_language_inSpellDocumentWithTag(range: MemorySegment, string: MemorySegment, language: MemorySegment, tag: Long): MemorySegment {
        val sel = ObjCRuntime.sel("completionsForPartialWordRange:inString:language:inSpellDocumentWithTag:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), string, language, tag) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun completionsForPartialWordRange_inString_language_inSpellDocumentWithTag(range: MemorySegment, string: String, language: String, tag: Long): MemorySegment = completionsForPartialWordRange_inString_language_inSpellDocumentWithTag(range, ObjCRuntime.newNSString(Arena.global(), string), ObjCRuntime.newNSString(Arena.global(), language), tag)
    
    open fun languageForWordRange_inString_orthography(range: MemorySegment, string: MemorySegment, orthography: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("languageForWordRange:inString:orthography:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), string, orthography) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun languageForWordRange_inString_orthographyAsString(range: MemorySegment, string: MemorySegment, orthography: MemorySegment): String = ObjCRuntime.toJavaString(languageForWordRange_inString_orthography(range, string, orthography))
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun languageForWordRange_inString_orthography(range: MemorySegment, string: String, orthography: MemorySegment): MemorySegment = languageForWordRange_inString_orthography(range, ObjCRuntime.newNSString(Arena.global(), string), orthography)
    
    /** Convenience overload — [String] parameters and [String] return type. */
    fun languageForWordRange_inString_orthographyAsString(range: MemorySegment, string: String, orthography: MemorySegment): String = ObjCRuntime.toJavaString(languageForWordRange_inString_orthography(range, ObjCRuntime.newNSString(Arena.global(), string), orthography))
    
    open fun closeSpellDocumentWithTag(tag: Long): Unit {
        val sel = ObjCRuntime.sel("closeSpellDocumentWithTag:")
        ObjCRuntime.msgSend(null, ptr, sel, tag)
    }
    
    open fun recordResponse_toCorrection_forWord_language_inSpellDocumentWithTag(response: MemorySegment, correction: MemorySegment, word: MemorySegment, language: MemorySegment, tag: Long): Unit {
        val sel = ObjCRuntime.sel("recordResponse:toCorrection:forWord:language:inSpellDocumentWithTag:")
        ObjCRuntime.msgSend(null, ptr, sel, response, correction, word, language, tag)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun recordResponse_toCorrection_forWord_language_inSpellDocumentWithTag(response: MemorySegment, correction: String, word: String, language: String, tag: Long): Unit = recordResponse_toCorrection_forWord_language_inSpellDocumentWithTag(response, ObjCRuntime.newNSString(Arena.global(), correction), ObjCRuntime.newNSString(Arena.global(), word), ObjCRuntime.newNSString(Arena.global(), language), tag)
    
    open fun showCorrectionIndicatorOfType_primaryString_alternativeStrings_forStringInRect_view_completionHandler(type: MemorySegment, primaryString: MemorySegment, alternativeStrings: MemorySegment, rectOfTypedString: MemorySegment, view: MemorySegment, completionBlock: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("showCorrectionIndicatorOfType:primaryString:alternativeStrings:forStringInRect:view:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, type, primaryString, alternativeStrings, ObjCRuntime.ObjCStructArg(rectOfTypedString, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), view, completionBlock)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun showCorrectionIndicatorOfType_primaryString_alternativeStrings_forStringInRect_view_completionHandler(type: MemorySegment, primaryString: String, alternativeStrings: MemorySegment, rectOfTypedString: MemorySegment, view: MemorySegment, completionBlock: MemorySegment): Unit = showCorrectionIndicatorOfType_primaryString_alternativeStrings_forStringInRect_view_completionHandler(type, ObjCRuntime.newNSString(Arena.global(), primaryString), alternativeStrings, rectOfTypedString, view, completionBlock)
    
    open fun dismissCorrectionIndicatorForView(view: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("dismissCorrectionIndicatorForView:")
        ObjCRuntime.msgSend(null, ptr, sel, view)
    }
    
    open fun showInlinePredictionForCandidates_client(candidates: MemorySegment, client: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("showInlinePredictionForCandidates:client:")
        ObjCRuntime.msgSend(null, ptr, sel, candidates, client)
    }
    
    open fun preventsAutocorrectionBeforeString_language(string: MemorySegment, language: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("preventsAutocorrectionBeforeString:language:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, string, language) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun preventsAutocorrectionBeforeString_language(string: String, language: String): Boolean = preventsAutocorrectionBeforeString_language(ObjCRuntime.newNSString(Arena.global(), string), ObjCRuntime.newNSString(Arena.global(), language))
    
    open fun deletesAutospaceBetweenString_andString_language(precedingString: MemorySegment, followingString: MemorySegment, language: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("deletesAutospaceBetweenString:andString:language:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, precedingString, followingString, language) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun deletesAutospaceBetweenString_andString_language(precedingString: String, followingString: String, language: String): Boolean = deletesAutospaceBetweenString_andString_language(ObjCRuntime.newNSString(Arena.global(), precedingString), ObjCRuntime.newNSString(Arena.global(), followingString), ObjCRuntime.newNSString(Arena.global(), language))
    
    open fun setWordFieldStringValue(string: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setWordFieldStringValue:")
        ObjCRuntime.msgSend(null, ptr, sel, string)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setWordFieldStringValue(string: String): Unit = setWordFieldStringValue(ObjCRuntime.newNSString(Arena.global(), string))
    
    open fun learnWord(word: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("learnWord:")
        ObjCRuntime.msgSend(null, ptr, sel, word)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun learnWord(word: String): Unit = learnWord(ObjCRuntime.newNSString(Arena.global(), word))
    
    open fun hasLearnedWord(word: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("hasLearnedWord:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, word) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun hasLearnedWord(word: String): Boolean = hasLearnedWord(ObjCRuntime.newNSString(Arena.global(), word))
    
    open fun unlearnWord(word: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("unlearnWord:")
        ObjCRuntime.msgSend(null, ptr, sel, word)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun unlearnWord(word: String): Unit = unlearnWord(ObjCRuntime.newNSString(Arena.global(), word))
    
    open fun language(): MemorySegment {
        val sel = ObjCRuntime.sel("language")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun languageAsString(): String = ObjCRuntime.toJavaString(language())
    
    open fun setLanguage(language: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("setLanguage:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, language) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setLanguage(language: String): Boolean = setLanguage(ObjCRuntime.newNSString(Arena.global(), language))
    
    // @property sharedSpellChecker
    open fun sharedSpellChecker(): MemorySegment {
        val sel = ObjCRuntime.sel("sharedSpellChecker")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property sharedSpellCheckerExists
    open fun sharedSpellCheckerExists(): Boolean {
        val sel = ObjCRuntime.sel("sharedSpellCheckerExists")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property userReplacementsDictionary
    /** @return NSDictionary<NSString *,NSString *> * */
    open fun userReplacementsDictionary(): MemorySegment {
        val sel = ObjCRuntime.sel("userReplacementsDictionary")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property spellingPanel
    open fun spellingPanel(): MemorySegment {
        val sel = ObjCRuntime.sel("spellingPanel")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property accessoryView
    open fun accessoryView(): MemorySegment {
        val sel = ObjCRuntime.sel("accessoryView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAccessoryView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAccessoryView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property substitutionsPanel
    open fun substitutionsPanel(): MemorySegment {
        val sel = ObjCRuntime.sel("substitutionsPanel")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property substitutionsPanelAccessoryViewController
    open fun substitutionsPanelAccessoryViewController(): MemorySegment {
        val sel = ObjCRuntime.sel("substitutionsPanelAccessoryViewController")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSubstitutionsPanelAccessoryViewController(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSubstitutionsPanelAccessoryViewController:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property availableLanguages
    /** @return NSArray<NSString *> * */
    open fun availableLanguages(): MemorySegment {
        val sel = ObjCRuntime.sel("availableLanguages")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property userPreferredLanguages
    /** @return NSArray<NSString *> * */
    open fun userPreferredLanguages(): MemorySegment {
        val sel = ObjCRuntime.sel("userPreferredLanguages")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property automaticallyIdentifiesLanguages
    open fun automaticallyIdentifiesLanguages(): Boolean {
        val sel = ObjCRuntime.sel("automaticallyIdentifiesLanguages")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAutomaticallyIdentifiesLanguages(value: Boolean) {
        val sel = ObjCRuntime.sel("setAutomaticallyIdentifiesLanguages:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property automaticTextReplacementEnabled
    open fun isAutomaticTextReplacementEnabled(): Boolean {
        val sel = ObjCRuntime.sel("isAutomaticTextReplacementEnabled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property automaticSpellingCorrectionEnabled
    open fun isAutomaticSpellingCorrectionEnabled(): Boolean {
        val sel = ObjCRuntime.sel("isAutomaticSpellingCorrectionEnabled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property automaticQuoteSubstitutionEnabled
    open fun isAutomaticQuoteSubstitutionEnabled(): Boolean {
        val sel = ObjCRuntime.sel("isAutomaticQuoteSubstitutionEnabled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property automaticDashSubstitutionEnabled
    open fun isAutomaticDashSubstitutionEnabled(): Boolean {
        val sel = ObjCRuntime.sel("isAutomaticDashSubstitutionEnabled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property automaticCapitalizationEnabled
    open fun isAutomaticCapitalizationEnabled(): Boolean {
        val sel = ObjCRuntime.sel("isAutomaticCapitalizationEnabled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property automaticPeriodSubstitutionEnabled
    open fun isAutomaticPeriodSubstitutionEnabled(): Boolean {
        val sel = ObjCRuntime.sel("isAutomaticPeriodSubstitutionEnabled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property automaticTextCompletionEnabled
    open fun isAutomaticTextCompletionEnabled(): Boolean {
        val sel = ObjCRuntime.sel("isAutomaticTextCompletionEnabled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property automaticInlinePredictionEnabled
    open fun isAutomaticInlinePredictionEnabled(): Boolean {
        val sel = ObjCRuntime.sel("isAutomaticInlinePredictionEnabled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

// ── Category: NSDeprecated on NSSpellChecker ─────────────────────────────────────────

fun NSSpellChecker.guessesForWord(word: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("guessesForWord:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, word) as MemorySegment
}

fun NSSpellChecker.forgetWord(word: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("forgetWord:")
    ObjCRuntime.msgSend(null, this.ptr, sel, word)
}

