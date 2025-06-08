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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jarvislin.resume.utils.NewTabUriHandler
import org.jetbrains.compose.resources.painterResource
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
                            painter = painterResource(project.image),
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