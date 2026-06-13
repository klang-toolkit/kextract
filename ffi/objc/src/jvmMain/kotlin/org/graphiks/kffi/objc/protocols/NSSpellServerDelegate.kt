package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSSpellServerDelegate
 * Inherits protocols: NSObject
 */
interface NSSpellServerDelegate {
    // @optional
    fun spellServer_findMisspelledWordInString_language_wordCount_countOnly(sender: MemorySegment, stringToCheck: MemorySegment, language: MemorySegment, wordCount: MemorySegment, countOnly: Boolean): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'spellServer:findMisspelledWordInString:language:wordCount:countOnly:' not implemented")
    
    /** @return NSArray<NSString *> * */
    // @optional
    fun spellServer_suggestGuessesForWord_inLanguage(sender: MemorySegment, word: MemorySegment, language: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'spellServer:suggestGuessesForWord:inLanguage:' not implemented")
    
    // @optional
    fun spellServer_didLearnWord_inLanguage(sender: MemorySegment, word: MemorySegment, language: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'spellServer:didLearnWord:inLanguage:' not implemented")
    
    // @optional
    fun spellServer_didForgetWord_inLanguage(sender: MemorySegment, word: MemorySegment, language: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'spellServer:didForgetWord:inLanguage:' not implemented")
    
    /** @return NSArray<NSString *> * */
    // @optional
    fun spellServer_suggestCompletionsForPartialWordRange_inString_language(sender: MemorySegment, range: MemorySegment, string: MemorySegment, language: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'spellServer:suggestCompletionsForPartialWordRange:inString:language:' not implemented")
    
    // @optional
    fun spellServer_checkGrammarInString_language_details(sender: MemorySegment, stringToCheck: MemorySegment, language: MemorySegment, details: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'spellServer:checkGrammarInString:language:details:' not implemented")
    
    /** @return NSArray<NSTextCheckingResult *> * */
    // @optional
    fun spellServer_checkString_offset_types_options_orthography_wordCount(sender: MemorySegment, stringToCheck: MemorySegment, offset: Long, checkingTypes: Long, options: MemorySegment, orthography: MemorySegment, wordCount: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'spellServer:checkString:offset:types:options:orthography:wordCount:' not implemented")
    
    // @optional
    fun spellServer_recordResponse_toCorrection_forWord_language(sender: MemorySegment, response: Long, correction: MemorySegment, word: MemorySegment, language: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'spellServer:recordResponse:toCorrection:forWord:language:' not implemented")
    
}

