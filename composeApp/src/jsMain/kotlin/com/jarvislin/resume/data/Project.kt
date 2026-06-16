package com.jarvislin.resume.data

import org.jetbrains.compose.resources.DrawableResource

data class Project(
    val title: String,
    val description: String,
    val tags: List<String> = emptyList(),
    val category: Category,
    val logo: DrawableResource,
    val links: List<Link> = emptyList(),
    val screenShots: List<DrawableResource> = emptyList(),
) {
    data class Link(
        val title: String,
        val type: Type,
        val url: String,
    ) {
        enum class Type {
            GitHub, PlayStore, AppStore
        }
    }

    sealed class Category(val displayName: String) {
        data object All : Category("All")
        data object Work : Category("Work")
        data object Side : Category("Side Hustle")

        companion object {
            fun getAll() = listOf(All, Work, Side)
        }
    }
}