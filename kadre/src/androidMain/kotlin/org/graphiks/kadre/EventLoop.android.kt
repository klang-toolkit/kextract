/**
 * Android implementation of the event loop — delegates to kadre-android.
 *
 * On Android, the entry point is the Activity. [runApp] registers the
 * [ApplicationHandler] in [org.graphiks.kadre.android.AndroidKadreRuntime]
 * so that it is retrieved by [org.graphiks.kadre.android.KadreActivity].
 */
package org.graphiks.kadre

import org.graphiks.kadre.android.AndroidKadreRuntime

actual class EventLoop actual constructor() {

    actual fun runApp(handler: ApplicationHandler) {
        AndroidKadreRuntime.currentHandler = handler
        // On Android the Activity lifecycle is the entry point.
        // The handler is retrieved by KadreActivity.createHandler() via AndroidKadreRuntime.
    }
}
