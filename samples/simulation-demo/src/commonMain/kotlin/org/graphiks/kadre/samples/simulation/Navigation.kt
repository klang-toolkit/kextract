package org.graphiks.kadre.samples.simulation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable

enum class Screen {
    MENU,
    SCENARIO,
    INFO
}

class NavigationState {
    private val _backStack = mutableListOf<Screen>()
    var currentScreen: Screen = Screen.MENU
        private set

    val canGoBack: Boolean
        get() = _backStack.isNotEmpty()

    fun navigateTo(screen: Screen) {
        _backStack.add(currentScreen)
        currentScreen = screen
    }

    fun goBack() {
        if (_backStack.isNotEmpty()) {
            currentScreen = _backStack.removeAt(_backStack.size - 1)
        }
    }

    fun reset() {
        _backStack.clear()
        currentScreen = Screen.MENU
    }
}

@Composable
fun rememberNavigationState(): NavigationState {
    return rememberSaveable { NavigationState() }
}
