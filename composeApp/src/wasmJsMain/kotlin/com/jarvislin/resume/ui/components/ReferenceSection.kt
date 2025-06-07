package com.jarvislin.resume.ui.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.arrow_left
import resume.composeapp.generated.resources.arrow_right
import resume.composeapp.generated.resources.quote

@Composable
fun ReferenceSection() {
    val references = listOf(
        Reference(
            "Tyler Liu",
            "Meet.jobs, Tech Lead",
            "Jarvis is highly skilled in Android related development technology and is proficient in both front-end and back-end web technologies. He prioritizes teamwork and is willing to share his knowledge and information. He is easy to work with and never stops learning, making him an exceptional and ideal partner."
        ),
        Reference(
            "Poen Hsiao",
            "COLORFUL STYLE CO., LTD., CTO",
            "Jarvis possesses a bold spirit to tackle challenges, think creatively, and continuously seek new knowledge. He showcases remarkable coding skills and possesses a strong team-oriented mentality."
        ),
        Reference(
            "I-Ching Hsu",
            "National Formosa University, Professor",
            "Jarvis studied carefully, with the ability to solve problems."
        ),
    )

    var currentIndex by remember { mutableStateOf(0) }

    LaunchedEffect(currentIndex) {
        delay(5000L)
        currentIndex = (currentIndex + 1) % references.size
    }

    Crossfade(targetState = currentIndex, label = "TestimonialCrossfade") { index ->
        val reference = references[index]
        TestimonialCard(
            quote = reference.quote,
            author = reference.author,
            title = reference.title,
            onNext = {
                currentIndex = (index + 1) % references.size
            },
            onPrevious = {
                currentIndex = if (index == 0) references.lastIndex else index - 1
            }
        )
    }
}

@Composable
fun TestimonialCard(
    quote: String,
    author: String,
    title: String,
    onNext: () -> Unit,
    onPrevious: () -> Unit
) {
    Card(
        modifier = Modifier
            .widthIn(max = maxWebComponentWidth + 32.dp) // todo: why?
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            // Left: Quote and Author
            Column(modifier = Modifier.weight(1f).padding(end = 16.dp)) {
                Row {
                    Icon(
                        painter = painterResource(Res.drawable.quote),
                        contentDescription = "Quote",
                        tint = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = quote,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                HorizontalDivider(modifier = Modifier.padding(start = 36.dp))

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = author,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(start = 36.dp)
                )
                Text(
                    text = title.uppercase(),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 36.dp)
                )
            }

            // Right: Nav buttons
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                OutlinedIconButton(onClick = onNext) {
                    Icon(
                        painter = painterResource(Res.drawable.arrow_right),
                        contentDescription = "Next",
                    )
                }

                OutlinedIconButton(onClick = onPrevious) {
                    Icon(
                        painter = painterResource(Res.drawable.arrow_left),
                        contentDescription = "Previous",
                    )
                }
            }
        }
    }
}

data class Reference(
    val author: String,
    val title: String,
    val quote: String,
)