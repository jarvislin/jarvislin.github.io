package com.jarvislin.resume.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jarvislin.resume.data.Achievement
import org.jetbrains.compose.resources.painterResource
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.competition
import resume.composeapp.generated.resources.mvp
import resume.composeapp.generated.resources.top

@Composable
fun AchievementSection(useMobileLayout: Boolean) {
    val achievements = listOf(
        Achievement(
            title = "From Doubt to MVP",
            description = "Named MVP at the Taipei 101 Tourism and Technology Showcase for impactful solo innovations in the tourism sector.",
            image = painterResource(Res.drawable.mvp),
            paragraphs = listOf(
                "At the Taipei 101 project showcase, I pitched my AI-powered translation tool to a room of industry leaders. Despite a technical hiccup, a tourism rep told me, “Your solution directly addresses the challenges we face.”",
                "Later, I was named MVP — a solo creator recognized among established teams. It affirmed that this work isn’t just about tech, but real-world impact."
            )
        ),
        Achievement(
            title = "A Celebration of Victory",
            description = "As the Co-founder and Technical Director, I participated in the startup competition among 141 teams, and we emerged as the champions.",
            image = painterResource(Res.drawable.competition),
            paragraphs = listOf(
                "Out of 141 teams, we won first place in Taiwan’s Economic Daily startup competition—earning front-page coverage for our bold proposal to modernize Indonesia’s tax system with a centralized e-invoice platform.",
                "What began as a small cross-border effort became a mission-driven startup for public sector innovation. This award marked a turning point in our journey."
            )
        ),
        Achievement(
            title = "Google Play Conquest",
            description = "My self-crafted app soared to the top of the play store’s free rankings, a testament to my passion and dedication.",
            image = painterResource(Res.drawable.top),
            paragraphs = listOf(
                "Amid Taiwan’s COVID-19 mask shortage, I independently built a real-time pharmacy mask tracker.",
                "Within a month, it reached #1 on Google Play and became a vital public tool during the crisis—blending tech with real-world impact."
            )
        )
    )

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        achievements.chunked(if (useMobileLayout) 1 else 3)
            .forEach { items ->
                Row(
                    modifier = Modifier
                        .widthIn(max = maxWebComponentWidth + 32.dp) // todo: why?
                        .padding(horizontal = 16.dp)
                        .let {
                            if (items.size > 1) {
                                // make items the same height
                                it.height(IntrinsicSize.Max)
                            } else {
                                // .height(IntrinsicSize.Max) will not work when the row has only one item
                                it
                            }
                        },
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items.forEach {
                        AchievementCard(it, modifier = Modifier.weight(1f).fillMaxHeight())
                    }
                }
            }
    }
}

@Composable
fun AchievementCard(
    achievement: Achievement,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }
    Card(onClick = { showDialog = true }, modifier = modifier) {
        Image(
            painter = achievement.image,
            contentDescription = achievement.title,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = achievement.title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 12.dp, bottom = 6.dp).padding(horizontal = 16.dp)
        )
        Text(
            text = achievement.description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(12.dp))
    }

    if (showDialog) AchievementDialog(achievement) { showDialog = false }
}

