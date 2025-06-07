package com.jarvislin.resume.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp

@Composable
fun ResponsiveLayout() {
    val screenWidth = LocalWindowInfo.current.containerSize.width
    if (screenWidth <= 768) {
        MobileLayout()
    } else {
        WebLayout()
    }
}
@Composable
fun MobileLayout() {

}

@Composable
fun WebLayout() {

}