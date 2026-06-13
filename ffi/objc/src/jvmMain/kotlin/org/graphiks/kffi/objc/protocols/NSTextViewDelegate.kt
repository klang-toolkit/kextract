package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSTextViewDelegate
 * Inherits protocols: NSTextDelegate
 */
interface NSTextViewDelegate : NSTextDelegate {
    // @optional
    fun textView_clickedOnLink_atIndex(textView: MemorySegment, link: MemorySegment, charIndex: Long): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'textView:clickedOnLink:atIndex:' not implemented")
    
    // @optional
    fun textView_clickedOnCell_inRect_atIndex(textView: MemorySegment, cell: MemorySegment, cellFrame: MemorySegment, charIndex: Long): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'textView:clickedOnCell:inRect:atIndex:' not implemented")
    
    // @optional
    fun textView_doubleClickedOnCell_inRect_atIndex(textView: MemorySegment, cell: MemorySegment, cellFrame: MemorySegment, charIndex: Long): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'textView:doubleClickedOnCell:inRect:atIndex:' not implemented")
    
    // @optional
    fun textView_draggedCell_inRect_event_atIndex(view: MemorySegment, cell: MemorySegment, rect: MemorySegment, event: MemorySegment, charIndex: Long): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'textView:draggedCell:inRect:event:atIndex:' not implemented")
    
    /** @return NSArray<NSPasteboardType> * */
    // @optional
    fun textView_writablePasteboardTypesForCell_atIndex(view: MemorySegment, cell: MemorySegment, charIndex: Long): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'textView:writablePasteboardTypesForCell:atIndex:' not implemented")
    
    // @optional
    fun textView_writeCell_atIndex_toPasteboard_type(view: MemorySegment, cell: MemorySegment, charIndex: Long, pboard: MemorySegment, type: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'textView:writeCell:atIndex:toPasteboard:type:' not implemented")
    
    // @optional
    fun textView_willChangeSelectionFromCharacterRange_toCharacterRange(textView: MemorySegment, oldSelectedCharRange: MemorySegment, newSelectedCharRange: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'textView:willChangeSelectionFromCharacterRange:toCharacterRange:' not implemented")
    
    /** @return NSArray<NSValue *> * */
    // @optional
    fun textView_willChangeSelectionFromCharacterRanges_toCharacterRanges(textView: MemorySegment, oldSelectedCharRanges: MemorySegment, newSelectedCharRanges: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'textView:willChangeSelectionFromCharacterRanges:toCharacterRanges:' not implemented")
    
    // @optional
    fun textView_shouldChangeTextInRanges_replacementStrings(textView: MemorySegment, affectedRanges: MemorySegment, replacementStrings: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'textView:shouldChangeTextInRanges:replacementStrings:' not implemented")
    
    /** @return NSDictionary<NSAttributedStringKey,id> * */
    // @optional
    fun textView_shouldChangeTypingAttributes_toAttributes(textView: MemorySegment, oldTypingAttributes: MemorySegment, newTypingAttributes: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'textView:shouldChangeTypingAttributes:toAttributes:' not implemented")
    
    // @optional
    fun textViewDidChangeSelection(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'textViewDidChangeSelection:' not implemented")
    
    // @optional
    fun textViewDidChangeTypingAttributes(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'textViewDidChangeTypingAttributes:' not implemented")
    
    // @optional
    fun textView_willDisplayToolTip_forCharacterAtIndex(textView: MemorySegment, tooltip: MemorySegment, characterIndex: Long): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'textView:willDisplayToolTip:forCharacterAtIndex:' not implemented")
    
    /** @return NSArray<NSString *> * */
    // @optional
    fun textView_completions_forPartialWordRange_indexOfSelectedItem(textView: MemorySegment, words: MemorySegment, charRange: MemorySegment, index: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'textView:completions:forPartialWordRange:indexOfSelectedItem:' not implemented")
    
    // @optional
    fun textView_shouldChangeTextInRange_replacementString(textView: MemorySegment, affectedCharRange: MemorySegment, replacementString: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'textView:shouldChangeTextInRange:replacementString:' not implemented")
    
    // @optional
    fun textView_doCommandBySelector(textView: MemorySegment, commandSelector: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'textView:doCommandBySelector:' not implemented")
    
    // @optional
    fun textView_shouldSetSpellingState_range(textView: MemorySegment, value: Long, affectedCharRange: MemorySegment): Long =
        throw UnsupportedOperationException("Optional ObjC method 'textView:shouldSetSpellingState:range:' not implemented")
    
    // @optional
    fun textView_menu_forEvent_atIndex(view: MemorySegment, menu: MemorySegment, event: MemorySegment, charIndex: Long): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'textView:menu:forEvent:atIndex:' not implemented")
    
    /** @return NSDictionary<NSTextCheckingOptionKey,id> * */
    // @optional
    fun textView_willCheckTextInRange_options_types(view: MemorySegment, range: MemorySegment, options: MemorySegment, checkingTypes: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'textView:willCheckTextInRange:options:types:' not implemented")
    
    /** @return NSArray<NSTextCheckingResult *> * */
    // @optional
    fun textView_didCheckTextInRange_types_options_results_orthography_wordCount(view: MemorySegment, range: MemorySegment, checkingTypes: Long, options: MemorySegment, results: MemorySegment, orthography: MemorySegment, wordCount: Long): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'textView:didCheckTextInRange:types:options:results:orthography:wordCount:' not implemented")
    
    // @optional
    fun textView_URLForContentsOfTextAttachment_atIndex(textView: MemorySegment, textAttachment: MemorySegment, charIndex: Long): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'textView:URLForContentsOfTextAttachment:atIndex:' not implemented")
    
    // @optional
    fun textView_willShowSharingServicePicker_forItems(textView: MemorySegment, servicePicker: MemorySegment, items: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'textView:willShowSharingServicePicker:forItems:' not implemented")
    
    // @optional
    fun undoManagerForTextView(view: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'undoManagerForTextView:' not implemented")
    
    /** @return NSArray<NSTouchBarItemIdentifier> * */
    // @optional
    fun textView_shouldUpdateTouchBarItemIdentifiers(textView: MemorySegment, identifiers: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'textView:shouldUpdateTouchBarItemIdentifiers:' not implemented")
    
    // @optional
    fun textView_candidatesForSelectedRange(textView: MemorySegment, selectedRange: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'textView:candidatesForSelectedRange:' not implemented")
    
    /** @return NSArray<NSTextCheckingResult *> * */
    // @optional
    fun textView_candidates_forSelectedRange(textView: MemorySegment, candidates: MemorySegment, selectedRange: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'textView:candidates:forSelectedRange:' not implemented")
    
    // @optional
    fun textView_shouldSelectCandidateAtIndex(textView: MemorySegment, index: Long): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'textView:shouldSelectCandidateAtIndex:' not implemented")
    
    // @optional
    fun textViewWritingToolsWillBegin(textView: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'textViewWritingToolsWillBegin:' not implemented")
    
    // @optional
    fun textViewWritingToolsDidEnd(textView: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'textViewWritingToolsDidEnd:' not implemented")
    
    /** @return NSArray<NSValue *> * */
    // @optional
    fun textView_writingToolsIgnoredRangesInEnclosingRange(textView: MemorySegment, enclosingRange: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'textView:writingToolsIgnoredRangesInEnclosingRange:' not implemented")
    
    // @optional
    fun textView_clickedOnLink(textView: MemorySegment, link: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'textView:clickedOnLink:' not implemented")
    
    // @optional
    fun textView_clickedOnCell_inRect(textView: MemorySegment, cell: MemorySegment, cellFrame: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'textView:clickedOnCell:inRect:' not implemented")
    
    // @optional
    fun textView_doubleClickedOnCell_inRect(textView: MemorySegment, cell: MemorySegment, cellFrame: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'textView:doubleClickedOnCell:inRect:' not implemented")
    
    // @optional
    fun textView_draggedCell_inRect_event(view: MemorySegment, cell: MemorySegment, rect: MemorySegment, event: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'textView:draggedCell:inRect:event:' not implemented")
    
}

