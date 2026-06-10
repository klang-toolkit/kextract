package org.graphiks.kadre.samples.compose.ios

import androidx.compose.ui.window.ComposeUIViewController
import org.graphiks.kadre.samples.compose.showcase.PlatformContext
import org.graphiks.kadre.samples.compose.showcase.ShowcaseApp

fun MainViewController() = ComposeUIViewController { ShowcaseApp(PlatformContext()) }
