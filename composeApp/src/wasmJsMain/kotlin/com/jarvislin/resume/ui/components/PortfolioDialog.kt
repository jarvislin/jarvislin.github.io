package com.jarvislin.resume.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jarvislin.resume.utils.NewTabUriHandler
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import resume.composeapp.generated.resources.*
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.apple
import resume.composeapp.generated.resources.github
import resume.composeapp.generated.resources.play_store

@Composable
fun PortfolioDialog(project: Project, onDismiss: () -> Unit) {
    val scrollState = rememberScrollState()
    Dialog(onDismissRequest = onDismiss) {
        Box {
            Card(Modifier.padding(vertical = 16.dp)) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
                    modifier = Modifier.height(100.dp).fillMaxWidth()
                ) {
                    Row {
                        Image(
                            painter = painterResource(project.logo),
                            contentDescription = project.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(1f / 1f)
                                .clip(RoundedCornerShape(12.dp))
                        )
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(12.dp).fillMaxSize()
                        ) {
                            Text(
                                project.title,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Text(
                                project.tags.joinToString(", ") { "#$it" },
                                color = MaterialTheme.colorScheme.onSurface,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }

                Column(Modifier.verticalScroll(scrollState)) {
                    Text(
                        project.description,
                        Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    if (project.screenShots.isNotEmpty()) {
                        ThumbnailGallery(project.screenShots)
                    }
                    project.links.takeIf { it.isNotEmpty() }?.let { links ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            links.forEach {
                                AssistChip(
                                    onClick = { NewTabUriHandler.openUri(it.url) },
                                    label = { Text(it.title) },
                                    leadingIcon = {
                                        Icon(
                                            painter = painterResource(
                                                when (it.type) {
                                                    Project.Link.Type.GitHub -> Res.drawable.github
                                                    Project.Link.Type.PlayStore -> Res.drawable.play_store
                                                    Project.Link.Type.AppStore -> Res.drawable.apple
                                                }
                                            ),
                                            contentDescription = it.title
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
                TextButton(
                    onClick = { onDismiss() },
                    modifier = Modifier.align(Alignment.End).padding(horizontal = 8.dp).padding(bottom = 8.dp)
                ) {
                    Text("Dismiss")
                }
            }
        }
    }
}

@Composable
fun ThumbnailGallery(screenShots: List<DrawableResource>) {
    if (screenShots.isEmpty()) {
        throw IllegalArgumentException("Screenshots can't be empty")
    }
    var currentImageIndex by remember { mutableStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }

    Column {
        LazyVerticalGrid(
            GridCells.FixedSize(80.dp),
            modifier = Modifier.fillMaxWidth().heightIn(max = 800.dp).padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(screenShots.size) { index ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
                    onClick = {
                        currentImageIndex = index
                        showDialog = true
                    },
                    modifier = Modifier.aspectRatio(3f / 4f)
                ) {
                    Box {
                        Image(
                            painter = painterResource(screenShots[index]),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(
                            Modifier.fillMaxSize().border(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.secondary,
                                shape = RoundedCornerShape(12.dp)
                            )
                        )
                    }
                }
            }
        }
    }

    if (showDialog) {
        GalleryViewerDialog(screenShots, currentImageIndex) { showDialog = false }
    }
}

@Composable
fun GalleryViewerDialog(screenShots: List<DrawableResource>, index: Int, onDismiss: () -> Unit) {
    val density = LocalDensity.current.density
    val screenWidth = LocalWindowInfo.current.containerSize.width / density
    val useMobileLayout = screenWidth < 768 // dp
    var currentImageIndex by remember { mutableStateOf(index) }
    Dialog(onDismissRequest = { onDismiss() }) {
        Column {
            IconButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                onClick = { onDismiss() },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
            ) {
                Icon(painterResource(Res.drawable.close), contentDescription = "previous")
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .padding(horizontal = 16.dp)
            ) {
                AnimatedContent(
                    targetState = currentImageIndex,
                    transitionSpec = {
                        if (targetState > initialState) {
                            slideInHorizontally(
                                animationSpec = tween(300),
                                initialOffsetX = { fullWidth -> fullWidth }
                            ) + fadeIn() togetherWith slideOutHorizontally(
                                animationSpec = tween(300),
                                targetOffsetX = { fullWidth -> -fullWidth }
                            ) + fadeOut()
                        } else {
                            slideInHorizontally(
                                animationSpec = tween(300),
                                initialOffsetX = { fullWidth -> -fullWidth }
                            ) + fadeIn() togetherWith slideOutHorizontally(
                                animationSpec = tween(300),
                                targetOffsetX = { fullWidth -> fullWidth }
                            ) + fadeOut()
                        }.using(SizeTransform(clip = false))
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { index ->
                    Image(
                        painter = painterResource(screenShots[index]),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp))
                    )
                }

                if (currentImageIndex != 0) {
                    FloatingActionButton(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary,
                        onClick = { currentImageIndex -= 1 },
                        modifier = Modifier.align(Alignment.CenterStart)
                            .padding(horizontal = if (useMobileLayout) 12.dp else 32.dp)
                            .let {
                                if (useMobileLayout) it.size(40.dp)
                                else it
                            }
                    ) {
                        Icon(painterResource(Res.drawable.arrow_left), contentDescription = "previous")
                    }
                }

                if (currentImageIndex != screenShots.lastIndex) {
                    FloatingActionButton(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary,
                        onClick = { currentImageIndex += 1 },
                        modifier = Modifier.align(Alignment.CenterEnd)
                            .padding(horizontal = if (useMobileLayout) 12.dp else 32.dp)
                            .let {
                                if (useMobileLayout) it.size(40.dp)
                                else it
                            }
                    ) {
                        Icon(painterResource(Res.drawable.arrow_right), contentDescription = "next")
                    }
                }
            }
        }
    }
}