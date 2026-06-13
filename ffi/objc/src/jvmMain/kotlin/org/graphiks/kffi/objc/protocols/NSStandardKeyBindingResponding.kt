package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSStandardKeyBindingResponding
 * Inherits protocols: NSObject
 */
interface NSStandardKeyBindingResponding {
    // @optional
    fun insertText(insertString: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'insertText:' not implemented")
    
    // @optional
    fun doCommandBySelector(selector: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'doCommandBySelector:' not implemented")
    
    // @optional
    fun moveForward(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveForward:' not implemented")
    
    // @optional
    fun moveRight(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveRight:' not implemented")
    
    // @optional
    fun moveBackward(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveBackward:' not implemented")
    
    // @optional
    fun moveLeft(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveLeft:' not implemented")
    
    // @optional
    fun moveUp(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveUp:' not implemented")
    
    // @optional
    fun moveDown(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveDown:' not implemented")
    
    // @optional
    fun moveWordForward(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveWordForward:' not implemented")
    
    // @optional
    fun moveWordBackward(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveWordBackward:' not implemented")
    
    // @optional
    fun moveToBeginningOfLine(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveToBeginningOfLine:' not implemented")
    
    // @optional
    fun moveToEndOfLine(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveToEndOfLine:' not implemented")
    
    // @optional
    fun moveToBeginningOfParagraph(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveToBeginningOfParagraph:' not implemented")
    
    // @optional
    fun moveToEndOfParagraph(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveToEndOfParagraph:' not implemented")
    
    // @optional
    fun moveToEndOfDocument(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveToEndOfDocument:' not implemented")
    
    // @optional
    fun moveToBeginningOfDocument(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveToBeginningOfDocument:' not implemented")
    
    // @optional
    fun pageDown(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'pageDown:' not implemented")
    
    // @optional
    fun pageUp(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'pageUp:' not implemented")
    
    // @optional
    fun centerSelectionInVisibleArea(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'centerSelectionInVisibleArea:' not implemented")
    
    // @optional
    fun moveBackwardAndModifySelection(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveBackwardAndModifySelection:' not implemented")
    
    // @optional
    fun moveForwardAndModifySelection(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveForwardAndModifySelection:' not implemented")
    
    // @optional
    fun moveWordForwardAndModifySelection(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveWordForwardAndModifySelection:' not implemented")
    
    // @optional
    fun moveWordBackwardAndModifySelection(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveWordBackwardAndModifySelection:' not implemented")
    
    // @optional
    fun moveUpAndModifySelection(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveUpAndModifySelection:' not implemented")
    
    // @optional
    fun moveDownAndModifySelection(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveDownAndModifySelection:' not implemented")
    
    // @optional
    fun moveToBeginningOfLineAndModifySelection(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveToBeginningOfLineAndModifySelection:' not implemented")
    
    // @optional
    fun moveToEndOfLineAndModifySelection(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveToEndOfLineAndModifySelection:' not implemented")
    
    // @optional
    fun moveToBeginningOfParagraphAndModifySelection(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveToBeginningOfParagraphAndModifySelection:' not implemented")
    
    // @optional
    fun moveToEndOfParagraphAndModifySelection(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveToEndOfParagraphAndModifySelection:' not implemented")
    
    // @optional
    fun moveToEndOfDocumentAndModifySelection(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveToEndOfDocumentAndModifySelection:' not implemented")
    
    // @optional
    fun moveToBeginningOfDocumentAndModifySelection(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveToBeginningOfDocumentAndModifySelection:' not implemented")
    
    // @optional
    fun pageDownAndModifySelection(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'pageDownAndModifySelection:' not implemented")
    
    // @optional
    fun pageUpAndModifySelection(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'pageUpAndModifySelection:' not implemented")
    
    // @optional
    fun moveParagraphForwardAndModifySelection(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveParagraphForwardAndModifySelection:' not implemented")
    
    // @optional
    fun moveParagraphBackwardAndModifySelection(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveParagraphBackwardAndModifySelection:' not implemented")
    
    // @optional
    fun moveWordRight(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveWordRight:' not implemented")
    
    // @optional
    fun moveWordLeft(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveWordLeft:' not implemented")
    
    // @optional
    fun moveRightAndModifySelection(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveRightAndModifySelection:' not implemented")
    
    // @optional
    fun moveLeftAndModifySelection(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveLeftAndModifySelection:' not implemented")
    
    // @optional
    fun moveWordRightAndModifySelection(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveWordRightAndModifySelection:' not implemented")
    
    // @optional
    fun moveWordLeftAndModifySelection(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveWordLeftAndModifySelection:' not implemented")
    
    // @optional
    fun moveToLeftEndOfLine(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveToLeftEndOfLine:' not implemented")
    
    // @optional
    fun moveToRightEndOfLine(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveToRightEndOfLine:' not implemented")
    
    // @optional
    fun moveToLeftEndOfLineAndModifySelection(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveToLeftEndOfLineAndModifySelection:' not implemented")
    
    // @optional
    fun moveToRightEndOfLineAndModifySelection(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'moveToRightEndOfLineAndModifySelection:' not implemented")
    
    // @optional
    fun scrollPageUp(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'scrollPageUp:' not implemented")
    
    // @optional
    fun scrollPageDown(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'scrollPageDown:' not implemented")
    
    // @optional
    fun scrollLineUp(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'scrollLineUp:' not implemented")
    
    // @optional
    fun scrollLineDown(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'scrollLineDown:' not implemented")
    
    // @optional
    fun scrollToBeginningOfDocument(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'scrollToBeginningOfDocument:' not implemented")
    
    // @optional
    fun scrollToEndOfDocument(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'scrollToEndOfDocument:' not implemented")
    
    // @optional
    fun transpose(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'transpose:' not implemented")
    
    // @optional
    fun transposeWords(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'transposeWords:' not implemented")
    
    // @optional
    fun selectAll(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'selectAll:' not implemented")
    
    // @optional
    fun selectParagraph(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'selectParagraph:' not implemented")
    
    // @optional
    fun selectLine(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'selectLine:' not implemented")
    
    // @optional
    fun selectWord(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'selectWord:' not implemented")
    
    // @optional
    fun indent(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'indent:' not implemented")
    
    // @optional
    fun insertTab(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'insertTab:' not implemented")
    
    // @optional
    fun insertBacktab(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'insertBacktab:' not implemented")
    
    // @optional
    fun insertNewline(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'insertNewline:' not implemented")
    
    // @optional
    fun insertParagraphSeparator(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'insertParagraphSeparator:' not implemented")
    
    // @optional
    fun insertNewlineIgnoringFieldEditor(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'insertNewlineIgnoringFieldEditor:' not implemented")
    
    // @optional
    fun insertTabIgnoringFieldEditor(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'insertTabIgnoringFieldEditor:' not implemented")
    
    // @optional
    fun insertLineBreak(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'insertLineBreak:' not implemented")
    
    // @optional
    fun insertContainerBreak(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'insertContainerBreak:' not implemented")
    
    // @optional
    fun insertSingleQuoteIgnoringSubstitution(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'insertSingleQuoteIgnoringSubstitution:' not implemented")
    
    // @optional
    fun insertDoubleQuoteIgnoringSubstitution(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'insertDoubleQuoteIgnoringSubstitution:' not implemented")
    
    // @optional
    fun changeCaseOfLetter(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'changeCaseOfLetter:' not implemented")
    
    // @optional
    fun uppercaseWord(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'uppercaseWord:' not implemented")
    
    // @optional
    fun lowercaseWord(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'lowercaseWord:' not implemented")
    
    // @optional
    fun capitalizeWord(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'capitalizeWord:' not implemented")
    
    // @optional
    fun deleteForward(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'deleteForward:' not implemented")
    
    // @optional
    fun deleteBackward(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'deleteBackward:' not implemented")
    
    // @optional
    fun deleteBackwardByDecomposingPreviousCharacter(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'deleteBackwardByDecomposingPreviousCharacter:' not implemented")
    
    // @optional
    fun deleteWordForward(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'deleteWordForward:' not implemented")
    
    // @optional
    fun deleteWordBackward(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'deleteWordBackward:' not implemented")
    
    // @optional
    fun deleteToBeginningOfLine(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'deleteToBeginningOfLine:' not implemented")
    
    // @optional
    fun deleteToEndOfLine(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'deleteToEndOfLine:' not implemented")
    
    // @optional
    fun deleteToBeginningOfParagraph(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'deleteToBeginningOfParagraph:' not implemented")
    
    // @optional
    fun deleteToEndOfParagraph(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'deleteToEndOfParagraph:' not implemented")
    
    // @optional
    fun yank(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'yank:' not implemented")
    
    // @optional
    fun complete(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'complete:' not implemented")
    
    // @optional
    fun setMark(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'setMark:' not implemented")
    
    // @optional
    fun deleteToMark(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'deleteToMark:' not implemented")
    
    // @optional
    fun selectToMark(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'selectToMark:' not implemented")
    
    // @optional
    fun swapWithMark(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'swapWithMark:' not implemented")
    
    // @optional
    fun cancelOperation(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'cancelOperation:' not implemented")
    
    // @optional
    fun makeBaseWritingDirectionNatural(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'makeBaseWritingDirectionNatural:' not implemented")
    
    // @optional
    fun makeBaseWritingDirectionLeftToRight(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'makeBaseWritingDirectionLeftToRight:' not implemented")
    
    // @optional
    fun makeBaseWritingDirectionRightToLeft(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'makeBaseWritingDirectionRightToLeft:' not implemented")
    
    // @optional
    fun makeTextWritingDirectionNatural(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'makeTextWritingDirectionNatural:' not implemented")
    
    // @optional
    fun makeTextWritingDirectionLeftToRight(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'makeTextWritingDirectionLeftToRight:' not implemented")
    
    // @optional
    fun makeTextWritingDirectionRightToLeft(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'makeTextWritingDirectionRightToLeft:' not implemented")
    
    // @optional
    fun quickLookPreviewItems(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'quickLookPreviewItems:' not implemented")
    
    // @optional
    fun showContextMenuForSelection(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'showContextMenuForSelection:' not implemented")
    
}

