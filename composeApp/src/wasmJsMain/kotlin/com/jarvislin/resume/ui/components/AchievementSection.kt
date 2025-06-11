package com.jarvislin.resume.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jarvislin.resume.data.Achievement
import com.jarvislin.resume.data.AchievementData
import com.jarvislin.resume.utils.UIConstants.SPACING_DP_16
import com.jarvislin.resume.utils.UIConstants.SPACING_DP_8
import org.jetbrains.compose.resources.painterResource

@Composable
fun AchievementSection(useMobileLayout: Boolean) {
    val achievements = remember { AchievementData.getAchievements() }

    LazyVerticalGrid(
        columns = GridCells.Fixed(if (useMobileLayout) 1 else 3),
        verticalArrangement = Arrangement.spacedBy(SPACING_DP_8.dp),
        modifier = Modifier
            .widthIn(max = maxWebComponentWidth + 32.dp)
            .heightIn(max = 3000.dp) // for preventing error
            .padding(horizontal = SPACING_DP_16.dp),
        contentPadding = PaddingValues(vertical = SPACING_DP_8.dp)
    ) {
        // 將成就按每行分組
        val chunkedAchievements = achievements.chunked(if (useMobileLayout) 1 else 3)

        chunkedAchievements.forEach { rowAchievements ->
            item(span = { GridItemSpan(maxLineSpan) }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max), // make card's height the same
                    horizontalArrangement = Arrangement.spacedBy(SPACING_DP_8.dp)
                ) {
                    rowAchievements.forEach { achievement ->
                        AchievementCard(
                            achievement = achievement,
                            modifier = Modifier
                                .weight(1f) // make each item's width the same
                                .fillMaxHeight()
                        )
                    }
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
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    Card(
        onClick = { showDialog = true },
        modifier = modifier
            .scale(scale)
            .pointerInput(Unit) {
                detectTapGestures(onPress = {
                    isPressed = true
                    tryAwaitRelease()
                    isPressed = false
                })
            }
            .semantics {
                contentDescription = "Achievement: ${achievement.title}. Tap to read more details."
                role = Role.Button
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isPressed) 8.dp else 4.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            AchievementImage(
                painter = painterResource(achievement.image),
                contentDescription = achievement.title
            )

            AchievementContent(
                title = achievement.title,
                description = achievement.description,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        }
    }

    if (showDialog) {
        AchievementDialog(
            achievement = achievement,
            onDismiss = { showDialog = false }
        )
    }
}

@Composable
private fun AchievementImage(
    painter: Painter,
    contentDescription: String
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
            .clip(RoundedCornerShape(12.dp)),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun AchievementContent(
    title: String,
    description: String,
    modifier: Modifier
) {
    Column(
        modifier = modifier.padding(SPACING_DP_16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = SPACING_DP_8.dp)
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

