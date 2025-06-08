package com.jarvislin.resume.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun WorkExperienceSection(workExps: List<WorkExperience>, useMobileLayout: Boolean) {
    if (workExps.isEmpty()) {
        return
    }

    if (useMobileLayout) {
        Column(Modifier.padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            workExps.forEachIndexed { index, workExperience ->
                WorkCard(workExperience)
                if (index != workExps.lastIndex && workExps.size > 1) {
                    VerticalDivider(
                        modifier = Modifier.height(16.dp),
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        thickness = 2.dp
                    )
                }
            }
        }
    } else {
        WebWorkLayout(workExps)
    }
}

@Composable
fun WebWorkLayout(workExps: List<WorkExperience>) {
    val leftItems = workExps.filterIndexed { i, _ -> i % 2 == 0 }
    val rightItems = workExps.filterIndexed { i, _ -> i % 2 != 0 }

    val leftAttrs = remember { mutableStateListOf<CardAttribute>() }
    val rightAttrs = remember { mutableStateListOf<CardAttribute>() }

    val colorCardBackground = MaterialTheme.colorScheme.surfaceContainerHighest
    val colorDivider = MaterialTheme.colorScheme.secondaryContainer
    val colorCircle = MaterialTheme.colorScheme.primary

    Row(
        modifier = Modifier
            .widthIn(max = maxWebComponentWidth + 32.dp) // todo: why?
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .zIndex(1f)
                .padding(end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
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


                val minY = leftAttrs.first().trianglePeakY
                val maxY = (leftAttrs + rightAttrs).maxBy { it.calculatedTrianglePeakY ?: it.trianglePeakY }.let {
                    it.calculatedTrianglePeakY ?: it.trianglePeakY
                }

                // vertical divider
                drawRect(colorDivider, topLeft = Offset(0f, minY), size = Size(width = 2.dp.toPx(), maxY - minY))

                leftAttrs.forEachIndexed { index, attr ->
                    val path = Path().apply {
                        // left top
                        moveTo(-offsetX, attr.trianglePeakY - triangleBaseHeight)
                        // left bottom
                        lineTo(-offsetX, attr.trianglePeakY + triangleBaseHeight)
                        // peak
                        lineTo(dividerX - dividerXOffset, attr.trianglePeakY)
                        close()
                    }
                    drawPath(path, colorCardBackground)
                    drawCircle(
                        color = colorCircle,
                        radius = 4.dp.toPx(),
                        center = Offset(dividerX, attr.trianglePeakY)
                    )
                }

                rightAttrs.forEachIndexed { index, attr ->
                    val path = Path().apply {
                        // right top
                        moveTo(size.width + offsetX, attr.calculatedTrianglePeakY!! - triangleBaseHeight)
                        // right bottom
                        lineTo(size.width + offsetX, attr.calculatedTrianglePeakY + triangleBaseHeight)
                        // peak
                        lineTo(dividerX + dividerXOffset, attr.calculatedTrianglePeakY)
                        close()
                    }
                    drawPath(path, colorCardBackground)
                    drawCircle(
                        color = colorCircle,
                        radius = 4.dp.toPx(),
                        center = Offset(dividerX, attr.calculatedTrianglePeakY!!)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .zIndex(1f)
                .padding(start = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            rightItems.forEachIndexed { index, item ->
                if (index == 0) {
                    Spacer(Modifier.height(32.dp))
                }
                WorkCard(
                    experience = item,
                    onPositioned = { attr ->
                        if (rightAttrs.size > index) {
                            rightAttrs[index] =
                                attr.copy(calculatedTrianglePeakY = attr.getCalculatedTrianglePeakY(leftAttrs[index].trianglePeakY))
                        } else {
                            rightAttrs.add(
                                attr.copy(
                                    calculatedTrianglePeakY = attr.getCalculatedTrianglePeakY(
                                        leftAttrs[index].trianglePeakY
                                    )
                                )
                            )
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
    onPositioned: (CardAttribute) -> Unit = {},
    textModifier: Modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth()
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
        Spacer(Modifier.fillMaxWidth().height(8.dp).background(MaterialTheme.colorScheme.primary))
        experience.duration?.let {
            Text(
                it,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = textModifier.padding(top = 12.dp)
            )
        }

        experience.title?.let {
            Text(
                it,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = textModifier.padding(top = 8.dp)
            )
        }
        experience.company?.let {
            Text(
                it,
                style = MaterialTheme.typography.titleMedium,
                modifier = textModifier
            )
        }
        experience.description?.let {
            Text(
                it,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = textModifier.padding(top = 12.dp),
            )
        }
        Spacer(Modifier.size(12.dp))
    }
}

data class CardAttribute(val y: Float, val height: Float, val calculatedTrianglePeakY: Float? = null) {
    val trianglePeakY: Float
        get() = y + 80f
}

fun CardAttribute.getCalculatedTrianglePeakY(previousY: Float): Float =
    if (trianglePeakY - previousY <= 20) previousY + 80f
    else trianglePeakY

class WorkExperience(
    val duration: String?,
    val title: String?,
    val company: String?,
    val description: String?
)