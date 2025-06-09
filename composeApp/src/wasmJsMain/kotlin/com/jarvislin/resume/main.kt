package com.jarvislin.resume

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import kotlinx.browser.window

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport("compose-container") {
        App()
    }

    val loading = document.getElementById("loading-screen")
    loading?.let {
        it.setAttribute("style", it.getAttribute("style") + "; opacity: 0;")

        // remove loading screen
        window.setTimeout({
            it.remove()
            null
        }, 1000)
    }
}