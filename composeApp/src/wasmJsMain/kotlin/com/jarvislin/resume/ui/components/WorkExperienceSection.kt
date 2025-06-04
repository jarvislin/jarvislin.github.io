package com.jarvislin.resume.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun WorkExperienceSection(items: List<WorkExperience>) {
    val leftItems = items.filterIndexed { i, _ -> i % 2 == 0 }
    val rightItems = items.filterIndexed { i, _ -> i % 2 != 0 }

    val leftAttrs = remember { mutableStateListOf<CardAttribute>() }
    val rightAttrs = remember { mutableStateListOf<CardAttribute>() }
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
                .padding(end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            leftItems.forEachIndexed { index, item ->
                WorkCard(
                    experience = item,
                    onPositioned = { attr ->
                        if (leftAttrs.size > index) {
                            leftAttrs[index] = attr
                        } else {
                            leftAttrs.add(attr)
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
                val dividerX = size.width / 2f
                val dividerXOffset = 20f
                val triangleBaseHeight = 40f
                val offsetX = 40f

                val minY = (leftAttrs + rightAttrs).minBy { it.trianglePeakY }.trianglePeakY
                val maxY = (leftAttrs + rightAttrs).maxBy { it.trianglePeakY }.trianglePeakY

                // vertical divider
                drawRect(Color.Black, topLeft = Offset(0f, minY), size = Size(width = 2.dp.toPx(), maxY - minY))

                leftAttrs.forEach { attr ->
                    val path = Path().apply {
                        moveTo(-offsetX, attr.trianglePeakY - triangleBaseHeight)         // 左上角
                        lineTo(-offsetX, attr.trianglePeakY + triangleBaseHeight)         // 左下角
                        lineTo(dividerX - dividerXOffset, attr.trianglePeakY)           // 中間尖角
                        close()
                    }
                    drawPath(path, Color.Gray)
                }

                rightAttrs.forEach { attr ->
                    val path = Path().apply {
                        moveTo(size.width + offsetX, attr.trianglePeakY - triangleBaseHeight) // 右上角
                        lineTo(size.width + offsetX, attr.trianglePeakY + triangleBaseHeight) // 右下角
                        lineTo(dividerX + dividerXOffset, attr.trianglePeakY)               // 中間尖角
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
                .padding(start = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            rightItems.forEachIndexed { index, item ->
                if (index == 0) {
                    Spacer(Modifier.height(24.dp))
                }
                WorkCard(
                    experience = item,
                    onPositioned = { attr ->
                        if (rightAttrs.size > index) {
                            rightAttrs[index] = attr
                        } else {
                            rightAttrs.add(attr)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun WorkCard(
    experience: WorkExperience,
    onPositioned: (CardAttribute) -> Unit,
    modifier: Modifier = Modifier.padding(horizontal = 16.dp).padding(top = 12.dp)
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .onGloballyPositioned { layoutCoordinates ->
                val y = layoutCoordinates.positionInParent().y
                val height = layoutCoordinates.size.height.toFloat()
                onPositioned(CardAttribute(y, height))
            }
    ) {
        Spacer(Modifier.fillMaxWidth().height(8.dp).background(MaterialTheme.colorScheme.onPrimaryContainer))
        experience.duration?.let {
            Text(
                it,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = modifier.align(Alignment.CenterHorizontally)
            )
        }
        experience.company?.let {
            Text(
                it,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = modifier.align(Alignment.CenterHorizontally)
            )
        }
        experience.title?.let {
            Text(
                it,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = modifier.align(Alignment.CenterHorizontally)
            )
        }
        experience.description?.let {
            Text(
                it,
                style = MaterialTheme.typography.bodyMedium,
                modifier = modifier.align(Alignment.CenterHorizontally)
            )
        }
        Spacer(Modifier.size(12.dp))
    }
}

class CardAttribute(val y: Float, val height: Float) {
    val trianglePeakY: Float
        get() = y + 80f
}

class WorkExperience(
    val duration: String?,
    val title: String?,
    val company: String?,
    val description: String?
)