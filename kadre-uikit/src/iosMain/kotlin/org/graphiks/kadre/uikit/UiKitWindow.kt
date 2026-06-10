package org.graphiks.kadre.uikit

import org.graphiks.kadre.core.ButtonSource
import org.graphiks.kadre.core.ImePurpose
import org.graphiks.kadre.core.KeyEvent
import org.graphiks.kadre.core.KeyPlatform
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.KeyboardModifierState
import org.graphiks.kadre.core.CursorGrabMode
import org.graphiks.kadre.core.CursorIcon
import org.graphiks.kadre.core.FingerId
import org.graphiks.kadre.core.Fullscreen
import org.graphiks.kadre.core.Insets
import org.graphiks.kadre.core.Icon
import org.graphiks.kadre.core.InputCapabilities
import org.graphiks.kadre.core.MonitorHandle
import org.graphiks.kadre.core.NativeKeyInfo
import org.graphiks.kadre.core.NativeKeyCode
import org.graphiks.kadre.core.NativeLogicalKey
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.PointerKind
import org.graphiks.kadre.core.PointerSource
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.RequestError
import org.graphiks.kadre.core.Theme
import org.graphiks.kadre.core.TouchPhase
import org.graphiks.kadre.core.VideoMode
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId
import org.graphiks.kadre.core.WindowLevel
import org.graphiks.kadre.core.WindowRequestResult
import org.graphiks.kadre.core.location
import org.graphiks.kadre.core.defaultLogicalKey
import org.graphiks.kadre.core.defaultText
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.ObjCSignatureOverride
import kotlinx.cinterop.objcPtr
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGPoint
import platform.CoreGraphics.CGRect
import platform.CoreGraphics.CGRectMake
import platform.CoreGraphics.CGSizeMake
import platform.Foundation.NSClassFromString
import platform.Foundation.NSNotFound
import platform.Foundation.NSRunLoop
import platform.Foundation.NSRunLoopCommonModes
import platform.Foundation.NSSelectorFromString
import platform.Foundation._NSRange
import platform.QuartzCore.CADisplayLink
import platform.QuartzCore.CAMetalLayer
import platform.UIKit.UIEvent
import platform.UIKit.UIGestureRecognizer
import platform.UIKit.UIGestureRecognizerDelegateProtocol
import platform.UIKit.UIGestureRecognizerStateBegan
import platform.UIKit.UIGestureRecognizerStateCancelled
import platform.UIKit.UIGestureRecognizerStateChanged
import platform.UIKit.UIGestureRecognizerStateEnded
import platform.UIKit.UIGestureRecognizerStateFailed
import platform.UIKit.UIKey
import platform.UIKit.UIKeyInputProtocol
import platform.UIKit.UIPanGestureRecognizer
import platform.UIKit.UIPinchGestureRecognizer
import platform.UIKit.UIPress
import platform.UIKit.UIPressesEvent
import platform.UIKit.UIRotationGestureRecognizer
import platform.UIKit.UIScreen
import platform.UIKit.UITapGestureRecognizer
import platform.UIKit.UIDragItem
import platform.UIKit.UIDropInteraction
import platform.UIKit.UIDropInteractionDelegateProtocol
import platform.UIKit.UIDropProposal
import platform.UIKit.UIDropOperationCopy
import platform.UIKit.UIDropSessionProtocol
import platform.darwin.NSObjectProtocol
import platform.UIKit.UITextInputProtocol
import platform.UIKit.UITextInputDelegateProtocol
import platform.UIKit.UITextInputTokenizerProtocol
import platform.UIKit.UITextInputStringTokenizer
import platform.UIKit.UITextPosition
import platform.UIKit.UITextRange
import platform.UIKit.UITouch
import platform.UIKit.UIEdgeInsets
import platform.UIKit.UITraitCollection
import platform.UIKit.UIUserInterfaceStyle
import platform.UIKit.UIView
import platform.UIKit.UIViewController
import platform.UIKit.UIViewMeta
import platform.UIKit.UIWindow
import platform.darwin.NSObject

/**
 * KadreMetalView : UIView backed by CAMetalLayer.
 *
 * +layerClass override ensures UIKit uses CAMetalLayer as the backing store
 * from the very first layout pass — no sublayer attachment needed.
 *
 * UIResponder touch callbacks forward all contacts to [onEvent] as
 * touch pointer events. Bounds changes (rotation, split-view, status-bar layout)
 * are detected in [layoutSubviews]: the CAMetalLayer `drawableSize` is updated
 * and a [WindowEvent.Resized] is emitted when the physical size actually changes.
 * Display-scale changes (e.g. moving to an external screen) update
 * `contentsScale` and emit [WindowEvent.ScaleFactorChanged].
 */
@OptIn(ExperimentalForeignApi::class, kotlinx.cinterop.BetaInteropApi::class)
class KadreMetalView(
    frame: CValue<CGRect>,
    private val onEvent: (WindowEvent) -> Unit = {},
) : UIView(frame = frame), UIKeyInputProtocol, UITextInputProtocol {
    companion object : UIViewMeta() {
        override fun layerClass(): ObjCClass = CAMetalLayer.`class`()!!
    }

    val metalLayer: CAMetalLayer get() = layer as CAMetalLayer

    /** Last emitted physical size, to avoid duplicate Resized events. */
    private var lastWidth: Int = -1
    private var lastHeight: Int = -1
    private var primaryFingerId: FingerId? = null
    private var lastKeyboardModifierState = UiKitKeyMapper.initialModifierState()
    private val gestureProxy = UIKitGestureRecognizerProxy(this, onEvent)
    private var pinchRecognizer: UIPinchGestureRecognizer? = null
    private var panRecognizer: UIPanGestureRecognizer? = null
    private var rotationRecognizer: UIRotationGestureRecognizer? = null
    private var doubleTapRecognizer: UITapGestureRecognizer? = null

    /**
     * Last emitted scale factor. Initialized to the main screen scale to match
     * the `contentsScale` set at window creation, so no spurious
     * ScaleFactorChanged is emitted on the first layout pass.
     */
    private var lastScale: Double = UIScreen.mainScreen.scale

    // ── IME state ──────────────────────────────────────────────────────────
    internal var imeCursorRect: CValue<CGRect> = CGRectMake(0.0, 0.0, 0.0, 0.0)
    private val imeTokenizer: UITextInputTokenizerProtocol =
        UITextInputStringTokenizer(textInput = this)
    private var imeMarkedTextRange: UITextRange? = null
    private val imeBeginningOfDocument = KadreTextPosition(0)
    private val imeEndOfDocument = KadreTextPosition(0)

    fun recognizePinchGesture(shouldRecognize: Boolean) {
        if (shouldRecognize) {
            if (pinchRecognizer != null) return
            pinchRecognizer = UIPinchGestureRecognizer(
                target = gestureProxy,
                action = NSSelectorFromString("handlePinch:"),
            ).also(::installGestureRecognizer)
        } else {
            pinchRecognizer?.let(::removeGestureRecognizer)
            pinchRecognizer = null
        }
    }

    fun recognizePanGesture(
        shouldRecognize: Boolean,
        minimumNumberOfTouches: Int,
        maximumNumberOfTouches: Int,
    ) {
        if (shouldRecognize) {
            panRecognizer?.let(::removeGestureRecognizer)
            panRecognizer = UIPanGestureRecognizer(
                target = gestureProxy,
                action = NSSelectorFromString("handlePan:"),
            ).also { recognizer ->
                recognizer.minimumNumberOfTouches = minimumNumberOfTouches.toULong()
                recognizer.maximumNumberOfTouches = maximumNumberOfTouches.toULong()
                installGestureRecognizer(recognizer)
            }
        } else {
            panRecognizer?.let(::removeGestureRecognizer)
            panRecognizer = null
        }
    }

    fun recognizeDoubleTapGesture(shouldRecognize: Boolean) {
        if (shouldRecognize) {
            if (doubleTapRecognizer != null) return
            doubleTapRecognizer = UITapGestureRecognizer(
                target = gestureProxy,
                action = NSSelectorFromString("handleDoubleTap:"),
            ).also { recognizer ->
                recognizer.numberOfTapsRequired = 2u
                recognizer.numberOfTouchesRequired = 1u
                installGestureRecognizer(recognizer)
            }
        } else {
            doubleTapRecognizer?.let(::removeGestureRecognizer)
            doubleTapRecognizer = null
        }
    }

    fun recognizeRotationGesture(shouldRecognize: Boolean) {
        if (shouldRecognize) {
            if (rotationRecognizer != null) return
            rotationRecognizer = UIRotationGestureRecognizer(
                target = gestureProxy,
                action = NSSelectorFromString("handleRotation:"),
            ).also(::installGestureRecognizer)
        } else {
            rotationRecognizer?.let(::removeGestureRecognizer)
            rotationRecognizer = null
        }
    }

    private fun installGestureRecognizer(recognizer: UIGestureRecognizer) {
        recognizer.delegate = gestureProxy
        addGestureRecognizer(recognizer)
    }

    override fun touchesBegan(touches: Set<*>, withEvent: UIEvent?) =
        dispatchTouches(touches, TouchPhase.Started)

    override fun touchesMoved(touches: Set<*>, withEvent: UIEvent?) =
        dispatchTouches(touches, TouchPhase.Moved)

    override fun touchesEnded(touches: Set<*>, withEvent: UIEvent?) =
        dispatchTouches(touches, TouchPhase.Ended)

    override fun touchesCancelled(touches: Set<*>, withEvent: UIEvent?) =
        dispatchTouches(touches, TouchPhase.Cancelled)

    // ── IME: become/resign first responder → Enabled/Disabled ──────────────

    override fun becomeFirstResponder(): Boolean {
        val result = super.becomeFirstResponder()
        if (result) {
            onEvent(WindowEvent.Ime(WindowEvent.Ime.ImeEvent.Enabled))
        }
        return result
    }

    override fun resignFirstResponder(): Boolean {
        val result = super.resignFirstResponder()
        if (result) {
            onEvent(WindowEvent.Ime(WindowEvent.Ime.ImeEvent.Disabled))
        }
        return result
    }

    // ── UIKeyInputProtocol ─────────────────────────────────────────────────

    override fun hasText(): Boolean = true

    override fun insertText(text: String) {
        onEvent(WindowEvent.Ime(WindowEvent.Ime.ImeEvent.Commit(text)))
    }

    override fun deleteBackward() {
        onEvent(WindowEvent.Ime(WindowEvent.Ime.ImeEvent.DeleteSurrounding(1, 0)))
    }

    // ── UITextInputProtocol — IME lifecycle ─────────────────────────────────

    override fun setMarkedText(markedText: String?, selectedRange: CValue<_NSRange>) {
        val text = markedText ?: ""
        val cursorRange: Pair<Int, Int>? = selectedRange.useContents {
            if (location.toLong() != NSNotFound) {
                Pair(location.toInt(), (location + length).toInt())
            } else null
        }
        onEvent(WindowEvent.Ime(WindowEvent.Ime.ImeEvent.Preedit(text, cursorRange)))
    }

    override fun unmarkText() {
        onEvent(WindowEvent.Ime(WindowEvent.Ime.ImeEvent.Disabled))
    }

    override fun replaceRange(range: UITextRange, withText: String) {
        onEvent(WindowEvent.Ime(WindowEvent.Ime.ImeEvent.Commit(withText)))
    }

    override fun textInRange(range: UITextRange): String? = null

    override fun textRangeFromPosition(fromPosition: UITextPosition, toPosition: UITextPosition): UITextRange? {
        return null
    }

    override fun positionFromPosition(position: UITextPosition, offset: Long): UITextPosition? {
        val pos = (position as? KadreTextPosition)?.offset ?: return null
        return KadreTextPosition(pos + offset.toInt())
    }

    override fun positionFromPosition(position: UITextPosition, inDirection: Long, offset: Long): UITextPosition? {
        return positionFromPosition(position, offset)
    }

    override fun comparePosition(position: UITextPosition, toPosition: UITextPosition): Long {
        val a = (position as? KadreTextPosition)?.offset ?: return 0
        val b = (toPosition as? KadreTextPosition)?.offset ?: return 0
        return (a - b).toLong()
    }

    override fun offsetFromPosition(from: UITextPosition, toPosition: UITextPosition): Long {
        val a = (from as? KadreTextPosition)?.offset ?: return 0
        val b = (toPosition as? KadreTextPosition)?.offset ?: return 0
        return (b - a).toLong()
    }

    override fun positionWithinRange(range: UITextRange, farthestInDirection: Long): UITextPosition? {
        return if (farthestInDirection != 0L) range.end else range.start
    }

    override fun characterRangeByExtendingPosition(position: UITextPosition, inDirection: Long): UITextRange? {
        return null
    }

    override fun baseWritingDirectionForPosition(position: UITextPosition, inDirection: Long): Long = 0L

    override fun setBaseWritingDirection(writingDirection: Long, forRange: UITextRange) {}

    override fun firstRectForRange(range: UITextRange): CValue<CGRect> {
        return convertRect(imeCursorRect, toView = null)
    }

    override fun caretRectForPosition(position: UITextPosition): CValue<CGRect> {
        return imeCursorRect
    }

    override fun selectionRectsForRange(range: UITextRange): List<*> = emptyList<Any>()

    override fun closestPositionToPoint(point: CValue<CGPoint>): UITextPosition? {
        return imeBeginningOfDocument
    }

    override fun closestPositionToPoint(point: CValue<CGPoint>, withinRange: UITextRange): UITextPosition? {
        return withinRange.start
    }

    override fun characterRangeAtPoint(point: CValue<CGPoint>): UITextRange? {
        return null
    }

    // ── UITextInputProtocol — property accessors (required by interface) ────

    private var imeSelectedTextRange: UITextRange? = null
    override fun selectedTextRange(): UITextRange? = imeSelectedTextRange
    override fun setSelectedTextRange(selectedTextRange: UITextRange?) {
        imeSelectedTextRange = selectedTextRange
    }

    override fun markedTextRange(): UITextRange? = imeMarkedTextRange
    override fun setMarkedTextStyle(markedTextStyle: Map<Any?, *>?) {}

    override fun markedTextStyle(): Map<Any?, *>? = null

    private var imeInputDelegate: UITextInputDelegateProtocol? = null
    override fun inputDelegate(): UITextInputDelegateProtocol? = imeInputDelegate
    override fun setInputDelegate(inputDelegate: UITextInputDelegateProtocol?) {
        imeInputDelegate = inputDelegate
    }

    override fun tokenizer(): UITextInputTokenizerProtocol = imeTokenizer

    override fun beginningOfDocument(): UITextPosition = imeBeginningOfDocument

    override fun endOfDocument(): UITextPosition = imeEndOfDocument

    // ── Hardware keyboard / game controller keys (iOS 13.4+) ──────────────────

    /** The view must be first responder to receive key presses. */
    override fun canBecomeFirstResponder(): Boolean = true

    override fun pressesBegan(presses: Set<*>, withEvent: UIPressesEvent?) {
        if (!dispatchPresses(presses, KeyState.Pressed)) super.pressesBegan(presses, withEvent)
    }

    override fun pressesEnded(presses: Set<*>, withEvent: UIPressesEvent?) {
        if (!dispatchPresses(presses, KeyState.Released)) super.pressesEnded(presses, withEvent)
    }

    override fun pressesCancelled(presses: Set<*>, withEvent: UIPressesEvent?) {
        if (!dispatchPresses(presses, KeyState.Released)) super.pressesCancelled(presses, withEvent)
    }

    /**
     * Translates UIPress key data into [WindowEvent.KeyInput].
     *
     * @return `true` if at least one press mapped to a known key (and was
     *   consumed); `false` so the caller forwards the event up the chain.
     */
    private fun dispatchPresses(presses: Set<*>, state: KeyState): Boolean {
        var handled = false
        presses.forEach { element ->
            val press = element as? UIPress ?: return@forEach
            val uiKey = press.key ?: return@forEach
            val mappedCode = UiKitKeyMapper.keyCode(uiKey.keyCode) ?: return@forEach
            handled = true
            // R4: UIKey.characters is the text produced by the key (may be nil/empty)
            val characters = uiKey.characters
            val text: String? = if (!characters.isNullOrEmpty() && characters[0] >= ' ') characters else null
            val native = NativeKeyInfo(
                platform = KeyPlatform.UIKit,
                scanCode = uiKey.keyCode,
                nativeCode = NativeKeyCode.UIKit(uiKey.keyCode),
                nativeKey = NativeLogicalKey.UIKit(uiKey.keyCode, characters = text),
            )
            val modifierState = if (UiKitKeyMapper.isModifierKey(uiKey.keyCode)) {
                UiKitKeyMapper.modifierStateFrom(lastKeyboardModifierState, uiKey.keyCode, state)
            } else {
                null
            }
            val modifiers = modifierState?.logical ?: UiKitKeyMapper.modifiersFrom(uiKey.modifierFlags)
            dispatchModifiersChangedIfNeeded(modifierState)
            val logicalKey = mappedCode.defaultLogicalKey()
            onEvent(
                WindowEvent.KeyInput(
                    event = KeyEvent(
                        physicalKey = UiKitKeyMapper.physicalKey(uiKey.keyCode),
                        logicalKey = logicalKey,
                        state = state,
                        modifiers = modifiers,
                        location = UiKitKeyMapper.physicalKey(uiKey.keyCode).location(),
                        repeat = false,
                        text = text ?: mappedCode.defaultText(),
                        textWithAllModifiers = text,
                        keyWithoutModifiers = mappedCode.defaultText(),
                        native = native,
                    ),
                    deviceId = null,
                ),
            )
        }
        return handled
    }

    private fun dispatchModifiersChangedIfNeeded(modifierState: KeyboardModifierState?) {
        modifierState ?: return
        if (modifierState == lastKeyboardModifierState) return
        lastKeyboardModifierState = modifierState
        onEvent(WindowEvent.ModifiersChanged(modifierState))
    }

    internal fun resetKeyboardModifiersIfNeeded() {
        dispatchModifiersChangedIfNeeded(UiKitKeyMapper.initialModifierState())
    }

    /**
     * Called by UIKit on every layout pass (initial display, device rotation,
     * split-view resize, safe-area changes).
     *
     * Computes the new size in physical pixels, updates the CAMetalLayer
     * `drawableSize` so the Metal surface follows the new bounds, and emits a
     * [WindowEvent.Resized] only when the physical size changed.
     */
    override fun layoutSubviews() {
        super.layoutSubviews()
        syncScaleIfChanged()
        val scale = currentScale()
        val physW = bounds.useContents { size.width * scale }
        val physH = bounds.useContents { size.height * scale }
        val w = physW.toInt()
        val h = physH.toInt()
        if (w <= 0 || h <= 0) return

        // Keep the Metal drawable in sync with the view bounds (every layout pass).
        metalLayer.setDrawableSize(CGSizeMake(physW, physH))

        // Emit Resized only on an actual change to mirror winit semantics.
        if (w != lastWidth || h != lastHeight) {
            lastWidth = w
            lastHeight = h
            onEvent(WindowEvent.Resized(PhysicalSize(w, h)))
        }
    }

    /**
     * Detects a change of `displayScale` (moving to a screen with a different
     * pixel ratio, e.g. an external display). `displayScale` is a UITraitCollection
     * trait, so this fires when it changes.
     */
    override fun traitCollectionDidChange(previousTraitCollection: UITraitCollection?) {
        super.traitCollectionDidChange(previousTraitCollection)
        syncScaleIfChanged()
    }

    /** Effective scale of the view's screen, falling back to the main screen. */
    private fun currentScale(): Double = window?.screen?.scale ?: UIScreen.mainScreen.scale

    /**
     * Updates `CAMetalLayer.contentsScale` and emits [WindowEvent.ScaleFactorChanged]
     * when the effective scale factor changes.
     */
    private fun syncScaleIfChanged() {
        val scale = currentScale()
        if (scale > 0.0 && scale != lastScale) {
            lastScale = scale
            metalLayer.setContentsScale(scale)
            onEvent(WindowEvent.ScaleFactorChanged(scale))
        }
    }

    private fun dispatchTouches(touches: Set<*>, phase: TouchPhase) {
        val scale = UIScreen.mainScreen.scale
        touches.forEach { touch ->
            val uiTouch = touch as? UITouch ?: return@forEach
            val loc = uiTouch.locationInView(this)
            val x = loc.useContents { x * scale }
            val y = loc.useContents { y * scale }
            val location = PhysicalPosition(x, y)
            val fingerId = FingerId(uiTouch.objcPtr().toLong())
            if (phase == TouchPhase.Started && primaryFingerId == null) {
                primaryFingerId = fingerId
            }
            val primary = primaryFingerId == fingerId
            when (phase) {
                TouchPhase.Started -> {
                    onEvent(WindowEvent.PointerEntered(null, location, primary, PointerKind.Touch))
                    onEvent(WindowEvent.PointerButton(null, KeyState.Pressed, location, primary, ButtonSource.Touch(fingerId)))
                }
                TouchPhase.Moved -> onEvent(WindowEvent.PointerMoved(null, location, primary, source = PointerSource.Touch(fingerId)))
                TouchPhase.Ended -> {
                    onEvent(WindowEvent.PointerButton(null, KeyState.Released, location, primary, ButtonSource.Touch(fingerId)))
                    onEvent(WindowEvent.PointerLeft(null, location, primary, PointerKind.Touch))
                    if (primary) primaryFingerId = null
                }
                TouchPhase.Cancelled -> {
                    onEvent(WindowEvent.PointerButton(null, KeyState.Released, location, primary, ButtonSource.Touch(fingerId)))
                    onEvent(WindowEvent.PointerLeft(null, location, primary, PointerKind.Touch))
                    if (primary) primaryFingerId = null
                }
            }
        }
    }
}

/**
 * Objective-C target for a [CADisplayLink].
 *
 * `CADisplayLink` requires a target/selector pair; this NSObject subclass
 * exposes [onFrame] (an `@ObjCAction`) as that selector and forwards each
 * vsync tick to the Kotlin lambda.
 */
@OptIn(ExperimentalForeignApi::class, kotlinx.cinterop.BetaInteropApi::class)
private class DisplayLinkProxy(private val onFrame: () -> Unit) : NSObject() {
    @ObjCAction
    fun handleDisplayLink() = onFrame()
}

@OptIn(ExperimentalForeignApi::class, kotlinx.cinterop.BetaInteropApi::class)
private class UIKitGestureRecognizerProxy(
    private val view: UIView,
    private val onEvent: (WindowEvent) -> Unit,
) : NSObject(), UIGestureRecognizerDelegateProtocol {
    private val mapper = UIKitGestureMapper()

    @ObjCAction
    fun handlePinch(recognizer: UIPinchGestureRecognizer) {
        val state = gestureState(recognizer.state) ?: return
        onEvent(mapper.pinch(state, recognizer.scale))
    }

    @ObjCAction
    fun handlePan(recognizer: UIPanGestureRecognizer) {
        val translation = recognizer.translationInView(view)
        val state = gestureState(recognizer.state) ?: return
        translation.useContents {
            onEvent(mapper.pan(state, x, y))
        }
    }

    @ObjCAction
    fun handleRotation(recognizer: UIRotationGestureRecognizer) {
        val state = gestureState(recognizer.state) ?: return
        onEvent(mapper.rotation(state, recognizer.rotation))
    }

    @ObjCAction
    fun handleDoubleTap(recognizer: UITapGestureRecognizer) {
        if (recognizer.state == UIGestureRecognizerStateEnded) {
            onEvent(WindowEvent.DoubleTapGesture(deviceId = null))
        }
    }

    private fun gestureState(state: Long): UIKitGestureState? = when (state) {
        UIGestureRecognizerStateBegan -> UIKitGestureState.Began
        UIGestureRecognizerStateChanged -> UIKitGestureState.Changed
        UIGestureRecognizerStateEnded -> UIKitGestureState.Ended
        UIGestureRecognizerStateCancelled -> UIKitGestureState.Cancelled
        UIGestureRecognizerStateFailed -> UIKitGestureState.Failed
        else -> null
    }

    override fun gestureRecognizer(
        gestureRecognizer: UIGestureRecognizer,
        shouldRecognizeSimultaneouslyWithGestureRecognizer: UIGestureRecognizer,
    ): Boolean = true
}

/**
 * Objective-C delegate for [UIDropInteraction] (iOS 11+).
 *
 * Translates iOS drag-and-drop session callbacks into [WindowEvent] DnD events:
 *   DragEntered, DragMoved, DragLeft, DragDropped.
 */
@OptIn(ExperimentalForeignApi::class, kotlinx.cinterop.BetaInteropApi::class)
private class KadreDropDelegate(
    private val onEvent: (WindowEvent) -> Unit,
) : NSObject(), UIDropInteractionDelegateProtocol {

    @ObjCSignatureOverride
    override fun dropInteraction(interaction: UIDropInteraction, canHandleSession: UIDropSessionProtocol): Boolean {
        return canHandleSession.hasItemsConformingToTypeIdentifiers(
            listOf("public.file-url", "public.plain-text", "public.image")
        )
    }

    @ObjCSignatureOverride
    override fun dropInteraction(interaction: UIDropInteraction, sessionDidEnter: UIDropSessionProtocol) {
        val pos = sessionPosition(interaction, sessionDidEnter)
        onEvent(WindowEvent.DragEntered(pos, emptyList()))
    }

    @ObjCSignatureOverride
    override fun dropInteraction(interaction: UIDropInteraction, sessionDidUpdate: UIDropSessionProtocol): UIDropProposal {
        val pos = sessionPosition(interaction, sessionDidUpdate)
        onEvent(WindowEvent.DragMoved(pos))
        return UIDropProposal(UIDropOperationCopy)
    }

    @ObjCSignatureOverride
    override fun dropInteraction(interaction: UIDropInteraction, sessionDidExit: UIDropSessionProtocol) {
        onEvent(WindowEvent.DragLeft)
    }

    @ObjCSignatureOverride
    override fun dropInteraction(interaction: UIDropInteraction, performDrop: UIDropSessionProtocol) {
        val pos = sessionPosition(interaction, performDrop)
        onEvent(WindowEvent.DragDropped(pos, emptyList()))
    }

    /**
     * Pulls the drag location from the session. Uses [useContents] to unpack
     * the [CGPoint] returned by [UIDropSessionProtocol.locationInView].
     *
     * TODO: Asynchronously load dropped file paths via
     *   NSItemProvider.loadObjectOfClass / UIDropSessionProtocol.loadObjectsOfClass
     *   and emit a follow-up event with the resolved paths.
     */
    private fun sessionPosition(
        interaction: UIDropInteraction,
        session: UIDropSessionProtocol,
    ): PhysicalPosition<Double> {
        val view = interaction.view ?: return PhysicalPosition(0.0, 0.0)
        val point = session.locationInView(view)
        return point.useContents {
            PhysicalPosition(x, y)
        }
    }
}

/**
 * Minimal UITextPosition subclass that wraps an integer offset.
 */
private class KadreTextPosition(val offset: Int) : UITextPosition()

private const val UIStatusBarStyleDefault = 0L
private const val UIStatusBarStyleLightContent = 1L
private const val UIStatusBarStyleDarkContent = 3L

private class UiKitRootViewController(
    var statusBarHidden: Boolean = false,
    var statusBarStyle: Long = UIStatusBarStyleDefault,
) : UIViewController(nibName = null, bundle = null) {
    override fun prefersStatusBarHidden(): Boolean = statusBarHidden
    override fun preferredStatusBarStyle(): Long = statusBarStyle
}

/**
 * UiKitWindow — implements Window for iOS.
 *
 * Creates UIWindow → UIViewController → KadreMetalView (full screen).
 * CAMetalLayer is the view's backing layer (via +layerClass).
 * Touch and resize events are dispatched to [eventLoop].handler, and a
 * [CADisplayLink] paces [WindowEvent.RedrawRequested] on every screen refresh.
 */
@OptIn(ExperimentalForeignApi::class)
internal class UiKitWindow(attrs: WindowAttributes, private val eventLoop: UIKitActiveEventLoop) : Window {

    private val uiWindow: UIWindow
    private val viewController: UIViewController
    private val metalView: KadreMetalView

    /** Per-frame redraw driver (vsync-paced). Invalidated on [close]. */
    private var displayLink: CADisplayLink? = null
    private val displayLinkProxy = DisplayLinkProxy { emitRedraw() }

    override val id: WindowId

    init {
        val screen = UIScreen.mainScreen
        val screenBounds = screen.bounds

        // 4. UIWindow first — needed to derive the WindowId
        uiWindow = UIWindow(frame = screenBounds)
        id = WindowId(uiWindow.objcPtr().toLong())

        // Capture id in a local val so the lambda does not close over a val
        // that the compiler might consider uninitialized at lambda-definition time.
        val windowId = id

        // 1. Full-screen KadreMetalView (dispatch lambda uses windowId)
        metalView = KadreMetalView(frame = screenBounds) { event ->
            eventLoop.handler.windowEvent(eventLoop, windowId, event)
        }

        // 2. contentsScale for HiDPI / Retina
        metalView.metalLayer.setContentsScale(screen.scale)

        // 3. Root view controller hosting the metal view
        viewController = UiKitRootViewController().also { vc ->
            vc.setView(metalView)
        }

        // 5. Wire root VC and show
        uiWindow.rootViewController = viewController
        if (attrs.visible) {
            uiWindow.makeKeyAndVisible()
            // Become first responder so hardware-keyboard / controller key
            // presses reach pressesBegan/Ended.
            metalView.becomeFirstResponder()
        }

        // 6. Enable drag-and-drop via UIDropInteraction (iOS 11+).
        setupDropInteraction(windowId)

        // 7. Start the vsync-paced redraw loop.
        startDisplayLink()
    }

    internal fun resetKeyboardModifiersIfNeeded() {
        metalView.resetKeyboardModifiersIfNeeded()
    }

    internal fun contentRect(): PhysicalSize<Int> {
        val scale = UIScreen.mainScreen.scale
        return metalView.bounds.useContents {
            PhysicalSize(
                (size.width * scale).toInt(),
                (size.height * scale).toInt(),
            )
        }
    }

    internal fun setPrefersHomeIndicatorHidden(hidden: Boolean) {
        // No-op: Kotlin/Native UIKit bindings do not expose
        // prefersHomeIndicatorAutoHidden / setNeedsUpdateOfHomeIndicatorAutoHidden.
        // TODO: implement via ObjC runtime messaging if needed.
    }

    internal fun setPrefersStatusBarHidden(hidden: Boolean) {
        (viewController as? UiKitRootViewController)?.statusBarHidden = hidden
        viewController.setNeedsStatusBarAppearanceUpdate()
    }

    internal fun setPreferredStatusBarStyle(style: StatusBarStyle?) {
        (viewController as? UiKitRootViewController)?.statusBarStyle = when (style) {
            StatusBarStyle.Default -> UIStatusBarStyleDefault
            StatusBarStyle.LightContent -> UIStatusBarStyleLightContent
            StatusBarStyle.DarkContent -> UIStatusBarStyleDarkContent
            null -> UIStatusBarStyleDefault
        }
        viewController.setNeedsStatusBarAppearanceUpdate()
    }

    /**
     * Creates and schedules the [CADisplayLink] on the main run loop so
     * [emitRedraw] fires once per screen refresh.
     */
    private fun startDisplayLink() {
        if (displayLink != null) return
        val link = CADisplayLink.displayLinkWithTarget(
            target = displayLinkProxy,
            selector = NSSelectorFromString("handleDisplayLink"),
        )
        link.addToRunLoop(NSRunLoop.mainRunLoop, NSRunLoopCommonModes)
        displayLink = link
    }

    /**
     * Dispatches [WindowEvent.RedrawRequested] for this window each frame.
     *
     * Stops and releases the display link once the loop is exiting so no
     * further frame is emitted after shutdown.
     */
    private fun emitRedraw() {
        if (eventLoop.isExiting) {
            displayLink?.invalidate()
            displayLink = null
            return
        }
        eventLoop.handler.windowEvent(eventLoop, id, WindowEvent.RedrawRequested)
    }

    /**
     * Creates and attaches a [UIDropInteraction] to the metal view so the
     * window can receive drag-and-drop content (files, text, images) from
     * other apps via the iOS drag-and-drop system (iOS 11+).
     */
    @OptIn(kotlinx.cinterop.BetaInteropApi::class)
    private fun setupDropInteraction(windowId: WindowId) {
        if (NSClassFromString("UIDropInteraction") != null) {
            val dropDelegate = KadreDropDelegate { event ->
                eventLoop.handler.windowEvent(eventLoop, windowId, event)
            }
            val interaction = UIDropInteraction(delegate = dropDelegate)
            val selector = NSSelectorFromString("addInteraction:")
            metalView.performSelector(selector, withObject = interaction)
        }
    }

    override val rawWindowHandle: RawWindowHandle
        get() = RawWindowHandle.UiKit(
            uiView = metalView.objcPtr().toLong(),
            uiViewController = viewController.objcPtr().toLong(),
        )

    override val rawDisplayHandle: RawDisplayHandle
        get() = RawDisplayHandle.UiKit

    override fun inputCapabilities(): InputCapabilities =
        InputCapabilities(
            touch = true,
            pinchGesture = true,
            panGesture = true,
            rotationGesture = true,
            doubleTapGesture = true,
        )

    override fun recognizePinchGesture(shouldRecognize: Boolean) {
        metalView.recognizePinchGesture(shouldRecognize)
    }

    override fun recognizePanGesture(
        shouldRecognize: Boolean,
        minimumNumberOfTouches: Int,
        maximumNumberOfTouches: Int,
    ) {
        metalView.recognizePanGesture(
            shouldRecognize = shouldRecognize,
            minimumNumberOfTouches = minimumNumberOfTouches,
            maximumNumberOfTouches = maximumNumberOfTouches,
        )
    }

    override fun recognizeDoubleTapGesture(shouldRecognize: Boolean) {
        metalView.recognizeDoubleTapGesture(shouldRecognize)
    }

    override fun recognizeRotationGesture(shouldRecognize: Boolean) {
        metalView.recognizeRotationGesture(shouldRecognize)
    }

    override fun requestRedraw() {
        // No-op: the CADisplayLink paces RedrawRequested on every screen refresh.
        // Kept for API parity with the desktop backends.
    }

    override val innerSize: PhysicalSize<Int>
        get() {
            val scale = UIScreen.mainScreen.scale
            return metalView.bounds.useContents {
                PhysicalSize(
                    (size.width * scale).toInt(),
                    (size.height * scale).toInt(),
                )
            }
        }

    override val outerSize: PhysicalSize<Int>
        get() {
            val scale = UIScreen.mainScreen.scale
            return uiWindow.bounds.useContents {
                PhysicalSize(
                    (size.width * scale).toInt(),
                    (size.height * scale).toInt(),
                )
            }
        }

    override val scaleFactor: Double
        get() = UIScreen.mainScreen.scale

    override val safeArea: Insets<Int>
        get() = metalView.safeAreaInsets.useContents {
            Insets(
                top = top.toInt(),
                bottom = bottom.toInt(),
                left = left.toInt(),
                right = right.toInt(),
            )
        }

    override fun setVisible(visible: Boolean) {
        uiWindow.setHidden(!visible)
        if (visible) uiWindow.makeKeyAndVisible()
    }

    override fun close() {
        displayLink?.invalidate()
        displayLink = null
        uiWindow.setHidden(true)
        uiWindow.resignKeyWindow()
    }

    // ── R1: window state & geometry — no-ops on iOS ───────────────────────────
    //
    // iOS does not support programmatic window resizing, minimization,
    // maximization, or decoration changes. UIKit manages the full-screen
    // window lifecycle. All members below are documented no-ops.

    private var _title: String = ""

    /**
     * Sets the view controller title. On iOS the window has no decoration title bar;
     * this updates the UIViewController title for navigation controller integration.
     */
    override fun setTitle(title: String) {
        _title = title
        viewController.title = title
    }

    override val title: String get() = _title

    /** UIKit does not expose a reliable winit-style window visibility state. */
    override val isVisible: Boolean? get() = null

    /**
     * iOS does not support programmatic window resizing.
     * This is a no-op — the system controls the window geometry.
     */
    override fun setResizable(resizable: Boolean) { /* no-op: iOS does not support programmatic resizing */ }

    /** iOS windows are not user-resizable. Always returns false. */
    override val isResizable: Boolean get() = false

    /**
     * iOS does not support programmatic minimization.
     * This is a no-op.
     */
    override fun setMinimized(minimized: Boolean) { /* no-op: iOS does not support programmatic minimization */ }

    /** iOS does not expose a reliable minimized state. */
    override val isMinimized: Boolean? get() = null

    /**
     * iOS does not support programmatic maximization.
     * This is a no-op — windows always fill the available screen area.
     */
    override fun setMaximized(maximized: Boolean) { /* no-op: iOS windows always fill the screen */ }

    /** iOS windows always fill the screen. Always returns false. */
    override val isMaximized: Boolean get() = false

    /**
     * iOS does not have platform window decorations (title bar, resize borders).
     * This is a no-op.
     */
    override fun setDecorations(decorated: Boolean) { /* no-op: iOS has no platform window decorations */ }

    /** iOS windows have no platform decorations. Always returns false. */
    override val isDecorated: Boolean get() = false

    /**
     * iOS does not support surface size constraints.
     * This is a no-op.
     */
    override fun setMinSurfaceSize(size: PhysicalSize<Int>?) { /* no-op: iOS does not support surface size constraints */ }

    /**
     * iOS does not support surface size constraints.
     * This is a no-op.
     */
    override fun setMaxSurfaceSize(size: PhysicalSize<Int>?) { /* no-op: iOS does not support surface size constraints */ }

    /**
     * iOS does not expose a global window position.
     * Returns PhysicalPosition(0, 0) as the window always fills the screen.
     */
    override val outerPosition: PhysicalPosition<Int> get() = PhysicalPosition(0, 0)

    /**
     * iOS does not support programmatic window positioning.
     * This is a no-op.
     */
    override fun setOuterPosition(position: PhysicalPosition<Int>) { /* no-op: iOS does not support programmatic window positioning */ }

    /**
     * No-op on iOS: there is no Wayland-style pre-commit concept on this platform.
     */
    override fun prePresentNotify() { /* no-op on iOS */ }

    // ── R2: monitor & fullscreen ──────────────────────────────────────────────

    /**
     * Returns a synthetic monitor from UIScreen.mainScreen.
     */
    override fun currentMonitor(): MonitorHandle = syntheticUiKitMonitor()

    override fun availableMonitors(): List<MonitorHandle> =
        listOf(currentMonitor())

    override fun primaryMonitor(): MonitorHandle? =
        currentMonitor()

    /** In-memory fullscreen state (R2). */
    private var _fullscreen: Fullscreen? = null

    override val fullscreen: Fullscreen? get() = _fullscreen

    // ── R3: cursor, theme & appearance ───────────────────────────────────────

    /** No-op on iOS: there is no visible cursor on touchscreen devices. */
    override fun setCursor(cursor: CursorIcon) { /* no-op: iOS has no cursor */ }

    /** No-op on iOS. */
    override fun setCursorVisible(visible: Boolean) { /* no-op: iOS has no cursor */ }

    /** Unsupported on iOS. */
    override fun setCursorGrab(mode: CursorGrabMode): WindowRequestResult =
        WindowRequestResult.Failure(RequestError.Unsupported("iOS has no cursor to grab"))

    /** Unsupported on iOS. */
    override fun setCursorPosition(position: PhysicalPosition<Int>): WindowRequestResult =
        WindowRequestResult.Failure(RequestError.Unsupported("iOS has no cursor to warp"))

    /** Unsupported on iOS. */
    override fun setCursorHittest(hittest: Boolean): WindowRequestResult =
        WindowRequestResult.Failure(RequestError.Unsupported("iOS does not support cursor hit-testing"))

    /**
     * Returns the current theme via the view controller's traitCollection.
     */
    override val theme: Theme?
        get() = try {
            val style = viewController.traitCollection.userInterfaceStyle
            when (style) {
                UIUserInterfaceStyle.UIUserInterfaceStyleLight -> Theme.Light
                UIUserInterfaceStyle.UIUserInterfaceStyleDark  -> Theme.Dark
                else -> null
            }
        } catch (_: Throwable) { null }

    /**
     * Requests a theme override via UIViewController.overrideUserInterfaceStyle.
     *
     * Passing null restores the unspecified (system) style.
     */
    override fun setTheme(theme: Theme?) {
        try {
            val styleValue = when (theme) {
                Theme.Light -> UIUserInterfaceStyle.UIUserInterfaceStyleLight
                Theme.Dark  -> UIUserInterfaceStyle.UIUserInterfaceStyleDark
                null        -> UIUserInterfaceStyle.UIUserInterfaceStyleUnspecified
            }
            viewController.setOverrideUserInterfaceStyle(styleValue)
        } catch (_: Throwable) {}
    }

    /** No-op on iOS: Z-ordering is managed by UIKit. */
    override fun setWindowLevel(level: WindowLevel) { /* no-op: UIKit manages Z-ordering */ }

    /** No-op on iOS: transparency is a renderer concern. */
    override fun setTransparent(transparent: Boolean) { /* no-op: iOS transparency is renderer-side */ }

    /** No-op on iOS. */
    override fun setBlur(blur: Boolean) { /* no-op: iOS has no standard window blur API */ }

    /** No-op on iOS: application icon is set via the Info.plist. */
    override fun setWindowIcon(icon: Icon?) { /* no-op: iOS icon is set via the app bundle */ }

    /**
     * Enters or exits fullscreen on iOS.
     *
     * On iOS, the app is always "fullscreen" in the sense that it covers the entire screen.
     * This implementation hides/shows the status bar as a best-effort approximation
     * of a fullscreen mode change. [Fullscreen.Exclusive] is treated as [Fullscreen.Borderless].
     *
     * @param fullscreen New fullscreen state, or null to exit.
     */
    override fun setFullscreen(fullscreen: Fullscreen?) {
        _fullscreen = fullscreen
        // On iOS, the view already fills the full screen.
        // A more complete implementation would call UIViewController.prefersStatusBarHidden
        // but that requires overriding the view controller, which is out of scope for R2.
        // The state is stored so callers can read it back.
    }

    // ── R5-IME: input method ──────────────────────────────────────────────────

    /**
     * Enables or disables IME input by making the view first responder
     * (showing the keyboard) or resigning first responder (hiding it).
     *
     * When allowed, the [KadreMetalView] becomes first responder, which triggers
     * [KadreMetalView.becomeFirstResponder] → [WindowEvent.Ime.ImeEvent.Enabled].
     * When disallowed, resigning first responder triggers
     * [WindowEvent.Ime.ImeEvent.Disabled].
     */
    override fun setImeAllowed(allowed: Boolean) {
        if (allowed) {
            metalView.becomeFirstResponder()
        } else {
            metalView.resignFirstResponder()
        }
    }

    /**
     * Updates the IME cursor area used by [KadreMetalView.firstRectForRange]
     * to position the IME candidate window. When the cursor moves or text
     * layout changes, call this with the new cursor bounding box.
     */
    override fun setImeCursorArea(position: PhysicalPosition<Int>, size: PhysicalSize<Int>) {
        metalView.imeCursorRect = CGRectMake(
            position.x.toDouble(),
            position.y.toDouble(),
            size.width.toDouble(),
            size.height.toDouble(),
        )
    }

    /**
     * Hints the IME about the intended purpose of the focused text field.
     *
     * [UITextInputTraitsProtocol] properties (autocorrectionType,
     * secureTextEntry, etc.) are final in the Kotlin/Native interop and
     * cannot be set from Kotlin code directly. UIKit always uses default
     * trait values for custom UITextInput views. The purpose is recorded
     * for application use and future ObjC runtime wiring.
     */
    override fun setImePurpose(purpose: ImePurpose) {
        // no-op: UITextInputTraits properties are final in Kotlin/Native.
        // To set them, ObjC runtime method implementations would need to be
        // added via class_addMethod / objc_msgSend.
    }

    // ── R4: keyboard ──────────────────────────────────────────────────────────

    /**
     * No-op on UIKit: dead-key state is managed by UIKit's text input system.
     *
     * TODO(R4-uikit-dead-keys): call UITextInputDelegate.textDidChange to reset.
     */
    override fun resetDeadKeys() {
        // no-op: UIKit manages dead-key state internally
    }
}
