package com.jarvislin.resume.data

import org.jetbrains.compose.resources.DrawableResource

class Profile(
    val name: String,
    val title: String,
    val email: String,
    val location: String,
    val education: String,
    val avatar: DrawableResource,
    val links: List<SocialLink>
)