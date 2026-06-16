package com.jarvislin.resume.data

import androidx.compose.ui.graphics.painter.Painter
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.competition
import resume.composeapp.generated.resources.mvp
import resume.composeapp.generated.resources.top

class Achievement(
    val title: String,
    val description: String,
    val paragraphs: List<String>,
    val image: DrawableResource,
)

object AchievementData {
    fun getAchievements() = listOf(
        Achievement(
            title = "From Doubt to MVP",
            description = "Named MVP at the Taipei 101 Tourism and Technology Showcase for impactful solo innovations in the tourism sector.",
            image = Res.drawable.mvp,
            paragraphs = listOf(
                "At the Taipei 101 project showcase, I pitched my AI-powered translation tool to a room of industry leaders. Despite a technical hiccup, a tourism rep told me, \"Your solution directly addresses the challenges we face.\"",
                "Later, I was named MVP — a solo creator recognized among established teams. It affirmed that this work isn't just about tech, but real-world impact."
            )
        ),
        Achievement(
            title = "A Celebration of Victory",
            description = "As the Co-founder, I participated in the startup competition among 141 teams, and we emerged as the champions.",
            image = Res.drawable.competition,
            paragraphs = listOf(
                "Out of 141 teams, we won first place in Taiwan's Economic Daily startup competition—earning front-page coverage for our bold proposal to modernize Indonesia's tax system with a centralized e-invoice platform.",
                "What began as a small cross-border effort became a mission-driven startup for public sector innovation. This award marked a turning point in our journey."
            )
        ),
        Achievement(
            title = "Google Play Conquest",
            description = "My self-crafted app soared to the top of the play store's free rankings, a testament to my passion and dedication.",
            image = Res.drawable.top,
            paragraphs = listOf(
                "Amid Taiwan's COVID-19 mask shortage, I independently built a real-time pharmacy mask tracker.",
                "Within a month, it reached #1 on Google Play and became a vital public tool during the crisis—blending tech with real-world impact."
            )
        )
    )
}