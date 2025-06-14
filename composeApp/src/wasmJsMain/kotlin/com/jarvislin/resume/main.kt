package com.jarvislin.resume

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import kotlinx.browser.window

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        App()
    }

    // fade out and remove loading screen
    val loading = document.getElementById("loading-screen")
    loading?.let {
        it.setAttribute("style", it.getAttribute("style") + "; opacity: 0;")
        window.setTimeout({
            it.remove()
            null
        }, 1000)
    }
}