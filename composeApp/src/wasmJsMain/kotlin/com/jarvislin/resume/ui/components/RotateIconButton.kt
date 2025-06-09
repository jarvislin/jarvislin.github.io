@file:OptIn(ExperimentalComposeUiApi::class)

package com.jarvislin.resume.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent

@Composable
fun RotateIconButton(onClick: () -> Unit, icon: Painter, modifier: Modifier = Modifier, iconDesc: String? = null) {
    var isHovered by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (isHovered) 180f else 0f,
        label = "icon rotation"
    )
    IconButton(
        modifier = modifier
            .onPointerEvent(PointerEventType.Enter) { isHovered = true }
            .onPointerEvent(PointerEventType.Exit) { isHovered = false },
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.inverseSurface,
            contentColor = MaterialTheme.colorScheme.inverseOnSurface,
        )
    ) {
        Icon(icon, iconDesc,
            modifier = Modifier.graphicsLayer {
                rotationZ = rotation
            }
        )
    }
}