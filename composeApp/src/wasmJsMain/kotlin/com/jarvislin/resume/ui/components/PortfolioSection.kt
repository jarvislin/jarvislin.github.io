package com.jarvislin.resume.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.avatar_16_9

@Composable
fun PortfolioSection() {

    var selectedTab by remember { mutableStateOf("ALL") }
    val tabs = listOf("ALL", "SIDE PROJECT", "WORK")

    val projects = listOf(
        Project("HACKER NEWS KMP", "SIDE PROJECT", Res.drawable.avatar_16_9),
        Project("JETPACK", "WORK", Res.drawable.avatar_16_9),
        Project("WORDPRESS", "WORK", Res.drawable.avatar_16_9),
        Project("TUMBLR", "WORK", Res.drawable.avatar_16_9)
    )

    Column(modifier = Modifier.padding(horizontal = 16.dp).widthIn(max = maxWebComponentWidth)) {

        // Tabs
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            tabs.forEach { tab ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = tab,
                        color = if (selectedTab == tab) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .clickable { selectedTab = tab }
                    )
                    if (selectedTab == tab) {
                        Box(
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .height(2.dp)
                                .width(24.dp)
                                .background(MaterialTheme.colorScheme.tertiary)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
                .heightIn(max = 1000.dp), // 限高避免內部無限展開
        ) {
            val filtered = if (selectedTab == "ALL") projects else projects.filter { it.category == selectedTab }
            items(filtered) { item ->
                PortfolioCard(item)
            }
        }
    }
}

@Composable
fun PortfolioCard(item: Project) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.DarkGray)
    ) {
        Image(
            painter = painterResource(item.imageRes),
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        // 遮罩
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Brush.verticalGradient(colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f))))
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        ) {
            Text(text = item.title, color = Color.White, fontWeight = FontWeight.Bold)
            Text(text = item.category, color = Color.LightGray, fontSize = 12.sp)
        }
    }
}

data class Project(
    val title: String,
    val category: String,
    val imageRes: DrawableResource
)
