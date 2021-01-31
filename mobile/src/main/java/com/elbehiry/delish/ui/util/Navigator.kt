package com.elbehiry.delish.ui.util

import android.os.Parcelable
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.*
import androidx.compose.runtime.savedinstancestate.listSaver

/**
 * A simple navigator which maintains a back stack.
 */
class Navigator<T : Parcelable> private constructor(
    initialBackStack: List<T>,
    backDispatcher: OnBackPressedDispatcher
) {

    constructor(
        initial: T,
        backDispatcher: OnBackPressedDispatcher,
    ) : this(listOf(initial), backDispatcher)

    private val backStack = initialBackStack.toMutableStateList()
    private val backCallback = object : OnBackPressedCallback(canGoBack()) {
        override fun handleOnBackPressed() {
            back()
        }

    }.also { callBack ->
        backDispatcher.addCallback(callBack)
    }

    val current: T get() = backStack.last()

    fun back() {
        backStack.removeAt(backStack.lastIndex)
        backCallback.isEnabled = canGoBack()
    }

    fun navigate(destination: T) {
        backStack += destination
        backCallback.isEnabled = canGoBack()
    }

    private fun canGoBack(): Boolean = backStack.size > 1

    companion object {
        /**
         * Serialize the back stack to save to instance state.
         */
        fun <T : Parcelable> saver(backDispatcher: OnBackPressedDispatcher) =
            listSaver<Navigator<T>, T>(
                save = { navigator -> navigator.backStack.toList() },
                restore = { backstack -> Navigator(backstack, backDispatcher) }
            )
    }
}

/**
 * An effect for handling presses of the device back button.
 */
@Composable
fun backHandler(
    enabled: Boolean = true,
    onBack: () -> Unit
) {
    val backCallback = remember(onBack) {
        object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                onBack()
            }
        }
    }
    onCommit(enabled) {
        backCallback.isEnabled = enabled
    }

    val dispatcher = AmbientBackDispatcher.current
    onCommit(backCallback) {
        dispatcher.addCallback(backCallback)
        onDispose {
            backCallback.remove()
        }
    }
}

/**
 * An [androidx.compose.runtime.Ambient] providing the current [OnBackPressedDispatcher]. You must
 * [provide][androidx.compose.runtime.Providers] a value before use.
 */
internal val AmbientBackDispatcher = staticAmbientOf<OnBackPressedDispatcher> {
    error("No Back Dispatcher provided")
}
