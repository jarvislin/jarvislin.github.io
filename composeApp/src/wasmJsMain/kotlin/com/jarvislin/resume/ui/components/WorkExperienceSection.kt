package com.jarvislin.resume.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun WaterfallDemo(items: List<String>) {
    val leftItems = items.filterIndexed { i, _ -> i % 2 == 0 }
    val rightItems = items.filterIndexed { i, _ -> i % 2 != 0 }

    val leftY = remember { mutableStateListOf<Float>() }
    val rightY = remember { mutableStateListOf<Float>() }
    val offsetY = 60f
    val tipOffset = 12f     // 尖端偏移（越大越遠離中線）
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .zIndex(1f)
                .padding(end = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            leftItems.forEachIndexed { index, item ->
                WaterfallCard(
                    text = item,
                    onPositioned = { y ->
                        if (leftY.size > index) {
                            leftY[index] = y
                        } else {
                            leftY.add(y)
                        }
                    }
                )
            }
        }

        // Divider + Triangle Path
        Box(
            modifier = Modifier
                .width(2.dp)
                .fillMaxHeight()
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                // 垂直黑線
                drawRect(Color.Black)

                val dividerX = size.width / 2f
                val dividerXOffset = 20f

                leftY.forEach { y ->
                    val path = Path().apply {
                        moveTo(-40f, y - 10f)         // 左上角
                        lineTo(-40f, y + 10f)         // 左下角
                        lineTo(dividerX, y)           // 中間尖角
                        close()
                    }
                    drawPath(path, Color.Gray)
                }

                rightY.forEach { y ->
                    val path = Path().apply {
                        moveTo(size.width + 40f, y - 10f) // 右上角
                        lineTo(size.width + 40f, y + 10f) // 右下角
                        lineTo(dividerX, y)               // 中間尖角
                        close()
                    }
                    drawPath(path, Color.Gray)
                }
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .zIndex(1f)
                .padding(start = 4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            rightItems.forEachIndexed { index, item ->
                if (index == 0) {
                    Spacer(Modifier.height(24.dp))
                }
                WaterfallCard(
                    text = item,
                    onPositioned = { y ->
                        if (rightY.size > index) {
                            rightY[index] = y
                        } else {
                            rightY.add(y)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun WaterfallCard(
    text: String,
    onPositioned: (Float) -> Unit
) {
    val height = remember { (80..200).random().dp }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .onGloballyPositioned { layoutCoordinates ->
                val y = layoutCoordinates.positionInParent().y
                onPositioned(y)
            }
    ) {
        Box(
            modifier = Modifier
                .height(height)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text)
        }
    }
}