package com.jarvislin.resume

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
        // Step 1: create loading
        val loading = document.createElement("div").apply {
            id = "loading-screen"
            setAttribute("style", """
                position: fixed;
                top: 0;
                left: 0;
                width: 100vw;
                height: 100vh;
                background: #FF0E1514;
                display: flex;
                align-items: center;
                justify-content: center;
                z-index: 9999;
            """.trimIndent())
        }

        val img = document.createElement("img").apply {
            setAttribute("src", "loading.gif")
            setAttribute("alt", "Loading...")
            setAttribute("style", "width: 200px; height: 200px;")
        }

        loading.appendChild(img)
        document.body!!.appendChild(loading)

        // Step 2: create Compose container
        val container = document.createElement("div").apply {
            id = "compose-container"
            setAttribute("style", "width: 100vw; height: 100vh;")
        }
        document.body!!.appendChild(container)

        // Step 3: setup Compose App
        ComposeViewport("compose-container") {
            App()
        }

        // Step 4: remove loading
        document.getElementById("loading-screen")?.remove()
}