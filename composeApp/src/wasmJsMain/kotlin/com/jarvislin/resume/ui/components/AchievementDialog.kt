package com.jarvislin.resume.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
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
                    contentDescription = achievement.article,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 10f)
                        .clip(RoundedCornerShape(12.dp))
                )
                Column(Modifier.verticalScroll(scrollState)) {
                    Text(achievement.article, Modifier.padding(horizontal = 16.dp, vertical = 12.dp))
                }
            }
            IconButton(
                onClick = onDismiss,
                modifier = Modifier.align(Alignment.TopStart).padding(vertical = 32.dp, horizontal = 16.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                )
            ) {
                Icon(painterResource(Res.drawable.close), "Dismiss")
            }
        }
    }
}