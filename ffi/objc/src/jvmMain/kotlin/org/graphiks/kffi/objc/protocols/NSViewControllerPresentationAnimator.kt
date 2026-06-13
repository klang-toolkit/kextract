package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSViewControllerPresentationAnimator
 * Inherits protocols: NSObject
 */
interface NSViewControllerPresentationAnimator {
    fun animatePresentationOfViewController_fromViewController(viewController: MemorySegment, fromViewController: MemorySegment): Unit
    
    fun animateDismissalOfViewController_fromViewController(viewController: MemorySegment, fromViewController: MemorySegment): Unit
    
}

