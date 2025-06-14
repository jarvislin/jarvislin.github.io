package com.jarvislin.resume.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.logo


@Composable
fun ResponsiveMenuBar(menuItems: List<MenuItem>, listState: LazyListState, useMobileLayout: Boolean) {
    val density = LocalDensity.current.density
    val screenWidth = LocalWindowInfo.current.containerSize.width / density
    val maxMenuBarWidth = 1200.dp.value
    val scope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }
    var currentIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                val firstVisibleItemIndex = visibleItems.firstOrNull()?.index ?: 0
                val lastVisibleItemIndex = visibleItems.lastOrNull()?.index ?: 0

                currentIndex = when {
                    lastVisibleItemIndex >= menuItems.lastIndex -> menuItems.lastIndex
                    else -> firstVisibleItemIndex
                }
            }
    }

    Box(
        Modifier.fillMaxWidth().shadow(elevation = 4.dp)
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = if (screenWidth >= maxMenuBarWidth) ((screenWidth - maxMenuBarWidth) / 2).dp else 16.dp,
                vertical = 8.dp
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painterResource(Res.drawable.logo),
                "logo",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
            )
            Spacer(modifier = Modifier.weight(1f))

            if (useMobileLayout) {
                OutlinedButton(onClick = { showDialog = true }, shape = RoundedCornerShape(4.dp)) {
                    Text("MENU")
                }
                if (showDialog) {
                    MenuDialog(onDismissRequest = { showDialog = false }, menuItems, scope, listState)
                }
            } else {
                menuItems.forEach {
                    TextButton(onClick = { scope.launch { listState.animateScrollToItem(it.index) } }) {
                        Text(
                            it.text,
                            fontWeight = if (currentIndex == it.index) FontWeight.ExtraBold else FontWeight.Thin,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MenuDialog(
    onDismissRequest: () -> Unit,
    menuItems: List<MenuItem>,
    scope: CoroutineScope,
    listState: LazyListState
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card {
            Column(Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                Text("Navigate to", fontWeight = FontWeight.Bold)
                menuItems.forEachIndexed { index, item ->
                    TextButton(onClick = {
                        scope.launch {
                            onDismissRequest()
                            listState.animateScrollToItem(item.index)
                        }
                    }, modifier = Modifier.widthIn(200.dp)) {
                        Text(item.text, color = MaterialTheme.colorScheme.onSecondaryContainer)
                    }
                    if (index != menuItems.lastIndex) {
                        HorizontalDivider(modifier = Modifier.widthIn(max = 200.dp))
                    }
                }
                TextButton(onClick = { onDismissRequest() }, modifier = Modifier.align(Alignment.End)) {
                    Text("Dismiss")
                }
            }
        }
    }
}

data class MenuItem(val text: String, val index: Int)