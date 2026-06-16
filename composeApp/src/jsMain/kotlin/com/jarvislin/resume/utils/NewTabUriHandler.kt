package com.jarvislin.resume.utils

import androidx.compose.ui.platform.UriHandler
import kotlinx.browser.window

object NewTabUriHandler : UriHandler {
    override fun openUri(uri: String) {
        window.open(uri, "_blank")
    }
}