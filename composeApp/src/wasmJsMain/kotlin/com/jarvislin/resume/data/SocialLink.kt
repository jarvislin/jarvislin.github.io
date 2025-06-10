package com.jarvislin.resume.data

import org.jetbrains.compose.resources.DrawableResource

data class SocialLink(
    val icon: DrawableResource,
    val contentDescription: String,
    val url: String
)