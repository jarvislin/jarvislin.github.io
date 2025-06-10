package com.jarvislin.resume.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jarvislin.resume.data.Achievement
import org.jetbrains.compose.resources.painterResource
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.close

@Composable
fun AchievementDialog(achievement: Achievement, onDismiss: () -> Unit) {
    val scrollState = rememberScrollState()
    Dialog(onDismissRequest = onDismiss) {
        Box {
            Card(Modifier.padding(vertical = 16.dp)) {
                Image(
                    painter = achievement.image,
                    contentDescription = achievement.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 10f)
                        .clip(RoundedCornerShape(12.dp))
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.verticalScroll(scrollState)
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    achievement.paragraphs.forEach { paragraph ->
                        Text(paragraph)
                    }
                }
            }
            RotateIconButton(
                onClick = onDismiss,
                icon = painterResource(Res.drawable.close),
                modifier = Modifier.align(Alignment.TopStart).padding(vertical = 32.dp, horizontal = 16.dp),
                iconDesc = "Dismiss"
            )
        }
    }
}