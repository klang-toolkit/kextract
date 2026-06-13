package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSColorPickingDefault
 */
interface NSColorPickingDefault {
    fun initWithPickerMask_colorPanel(mask: Long, owningColorPanel: MemorySegment): MemorySegment
    
    fun provideNewButtonImage(): MemorySegment
    
    fun insertNewButtonImage_in(newButtonImage: MemorySegment, buttonCell: MemorySegment): Unit
    
    fun viewSizeChanged(sender: MemorySegment): Unit
    
    fun alphaControlAddedOrRemoved(sender: MemorySegment): Unit
    
    fun attachColorList(colorList: MemorySegment): Unit
    
    fun detachColorList(colorList: MemorySegment): Unit
    
    fun setMode(mode: MemorySegment): Unit
    
    fun buttonToolTip(): MemorySegment
    
    fun minContentSize(): MemorySegment
    
}

