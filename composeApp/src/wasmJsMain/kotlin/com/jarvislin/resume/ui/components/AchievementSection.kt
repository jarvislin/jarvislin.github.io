package com.jarvislin.resume.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
            article = "Today at the Taipei 101 project showcase, I pitched my AI-powered translation solution to a room full of officials and industry leaders. Despite a technical hiccup cutting my presentation short and a shaky Q&A, a tourism association representative approached me during the break and said, “Your solution directly addresses the challenges our industry faces. We need more people like you.”\n" +
                    "\n" +
                    "That moment meant everything.\n" +
                    "\n" +
                    "Later, I was named MVP—an unexpected honor as a one-person team among well-established teams. The recognition affirmed that my product isn’t just about technology, but about solving real-world problems in the tourism sector."
        ),
        Achievement(
            title = "A Celebration of Victory",
            description = "As the Co-founder and Technical Director, I participated in the startup competition among 141 teams, and we emerged as the champions.",
            image = painterResource(Res.drawable.competition),
            article = "Out of 141 competing teams, we emerged as champions in Taiwan’s prestigious Economic Daily startup competition — a recognition that placed us on the front page of one of the country’s most influential newspapers. Our proposal to introduce a centralized e-invoice system for the Indonesian government received high praise from the judges for its bold vision and potential to streamline taxation at a national scale.\n" +
                    "\n" +
                    "What began as a small, cross-border effort grew into a mission-driven startup dedicated to public sector innovation. Despite early challenges and pandemic disruptions, our team remained resilient, collaborating across cultures and geographies to deliver impactful solutions. This award marked a turning point in our journey — validating not just our technical capabilities, but also our commitment to meaningful, systemic change."
        ),
        Achievement(
            title = "Google Play Conquest",
            description = "My self-crafted app soared to the top of the play store’s free rankings, a testament to my passion and dedication.",
            image = painterResource(Res.drawable.top),
            article = "During the height of the COVID-19 pandemic, one of the most urgent challenges was the global shortage of protective masks. In Taiwan, the government implemented a mask rationing system to ensure fair distribution. In response, I independently designed and developed a mobile app to help people easily check real-time mask availability at nearby pharmacies.\n" +
                    "\n" +
                    "Within just one month, the app rose to the #1 spot on the Google Play Free Apps chart in Taiwan. It became a widely used tool that provided clarity and accessibility during a time of uncertainty. This project remains one of my proudest accomplishments — blending technical execution with meaningful social impact."
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
    Card(
        modifier = modifier.clickable { showDialog = true },
    ) {
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
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 12.dp, bottom = 6.dp).padding(horizontal = 16.dp)
        )
        Text(
            text = achievement.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(12.dp))
    }

    if (showDialog) AchievementDialog(achievement) { showDialog = false }
}

class Achievement(
    val title: String,
    val description: String,
    val article: String,
    val image: Painter,
)