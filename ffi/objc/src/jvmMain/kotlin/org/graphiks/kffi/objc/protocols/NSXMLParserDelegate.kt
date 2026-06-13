package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSXMLParserDelegate
 * Inherits protocols: NSObject
 */
interface NSXMLParserDelegate {
    // @optional
    fun parserDidStartDocument(parser: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'parserDidStartDocument:' not implemented")
    
    // @optional
    fun parserDidEndDocument(parser: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'parserDidEndDocument:' not implemented")
    
    // @optional
    fun parser_foundNotationDeclarationWithName_publicID_systemID(parser: MemorySegment, name: MemorySegment, publicID: MemorySegment, systemID: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'parser:foundNotationDeclarationWithName:publicID:systemID:' not implemented")
    
    // @optional
    fun parser_foundUnparsedEntityDeclarationWithName_publicID_systemID_notationName(parser: MemorySegment, name: MemorySegment, publicID: MemorySegment, systemID: MemorySegment, notationName: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'parser:foundUnparsedEntityDeclarationWithName:publicID:systemID:notationName:' not implemented")
    
    // @optional
    fun parser_foundAttributeDeclarationWithName_forElement_type_defaultValue(parser: MemorySegment, attributeName: MemorySegment, elementName: MemorySegment, type: MemorySegment, defaultValue: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'parser:foundAttributeDeclarationWithName:forElement:type:defaultValue:' not implemented")
    
    // @optional
    fun parser_foundElementDeclarationWithName_model(parser: MemorySegment, elementName: MemorySegment, model: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'parser:foundElementDeclarationWithName:model:' not implemented")
    
    // @optional
    fun parser_foundInternalEntityDeclarationWithName_value(parser: MemorySegment, name: MemorySegment, value: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'parser:foundInternalEntityDeclarationWithName:value:' not implemented")
    
    // @optional
    fun parser_foundExternalEntityDeclarationWithName_publicID_systemID(parser: MemorySegment, name: MemorySegment, publicID: MemorySegment, systemID: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'parser:foundExternalEntityDeclarationWithName:publicID:systemID:' not implemented")
    
    // @optional
    fun parser_didStartElement_namespaceURI_qualifiedName_attributes(parser: MemorySegment, elementName: MemorySegment, namespaceURI: MemorySegment, qName: MemorySegment, attributeDict: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'parser:didStartElement:namespaceURI:qualifiedName:attributes:' not implemented")
    
    // @optional
    fun parser_didEndElement_namespaceURI_qualifiedName(parser: MemorySegment, elementName: MemorySegment, namespaceURI: MemorySegment, qName: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'parser:didEndElement:namespaceURI:qualifiedName:' not implemented")
    
    // @optional
    fun parser_didStartMappingPrefix_toURI(parser: MemorySegment, prefix: MemorySegment, namespaceURI: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'parser:didStartMappingPrefix:toURI:' not implemented")
    
    // @optional
    fun parser_didEndMappingPrefix(parser: MemorySegment, prefix: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'parser:didEndMappingPrefix:' not implemented")
    
    // @optional
    fun parser_foundCharacters(parser: MemorySegment, string: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'parser:foundCharacters:' not implemented")
    
    // @optional
    fun parser_foundIgnorableWhitespace(parser: MemorySegment, whitespaceString: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'parser:foundIgnorableWhitespace:' not implemented")
    
    // @optional
    fun parser_foundProcessingInstructionWithTarget_data(parser: MemorySegment, target: MemorySegment, `data`: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'parser:foundProcessingInstructionWithTarget:data:' not implemented")
    
    // @optional
    fun parser_foundComment(parser: MemorySegment, comment: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'parser:foundComment:' not implemented")
    
    // @optional
    fun parser_foundCDATA(parser: MemorySegment, CDATABlock: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'parser:foundCDATA:' not implemented")
    
    // @optional
    fun parser_resolveExternalEntityName_systemID(parser: MemorySegment, name: MemorySegment, systemID: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'parser:resolveExternalEntityName:systemID:' not implemented")
    
    // @optional
    fun parser_parseErrorOccurred(parser: MemorySegment, parseError: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'parser:parseErrorOccurred:' not implemented")
    
    // @optional
    fun parser_validationErrorOccurred(parser: MemorySegment, validationError: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'parser:validationErrorOccurred:' not implemented")
    
}

