package com.jarvislin.resume.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.avatar_16_9

@Composable
fun AchievementSection() {
    val achievements = listOf(
        Achievement(
            "From Doubt to MVP",
            "Named MVP at the Taipei 101 Tourism and Technology Showcase for impactful solo innovations in the tourism sector.",
            painterResource(Res.drawable.avatar_16_9)
        ),
        Achievement(
            "A Celebration of Victory",
            "As the Co-founder and Technical Director, I participated in the startup competition among 141 teams, and we emerged as the champions.",
            painterResource(Res.drawable.avatar_16_9)
        ),
        Achievement(
            "Google Play Conquest",
            "My self-crafted app soared to the top of the play store’s free rankings, a testament to my passion and dedication.",
            painterResource(Res.drawable.avatar_16_9)
        )
    )

    achievements.chunked(3) // todo
        .forEach { items ->
            Row(
                modifier = Modifier
                    .widthIn(max= maxWebComponentWidth)
                    .let {
                        if (items.size > 1) {
                            // make items the same height
                            it.height(IntrinsicSize.Max)
                        } else {
                            // .height() will not work when the row has only one item
                            it
                        }
                    },
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items.forEach {
                    AchievementCard(
                        it.title,
                        it.description,
                        it.image,
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    )
                }
            }
        }
}

@Composable
fun AchievementCard(
    title: String,
    description: String,
    image: Painter,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
    ) {
        Image(
            painter = image,
            contentDescription = title,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 12.dp, bottom = 6.dp).padding(horizontal = 16.dp)
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(12.dp))
    }
}

class Achievement(
    val title: String,
    val description: String,
    val image: Painter,
)