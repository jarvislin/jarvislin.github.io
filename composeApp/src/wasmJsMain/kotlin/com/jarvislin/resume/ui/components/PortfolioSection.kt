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
import resume.composeapp.generated.resources.*
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.avatar_16_9
import resume.composeapp.generated.resources.hackernews
import resume.composeapp.generated.resources.jetpack

@Composable
fun PortfolioSection(useMobileLayout: Boolean) {

    var selectedTab by remember { mutableStateOf<ProjectCategory>(ProjectCategory.All) }
    val tabs = ProjectCategory.getAll()

    val projects = listOf(
        Project("KMP Hub", ProjectCategory.Side, Res.drawable.kmp_hub),
        Project("Hacker News KMP", ProjectCategory.Side, Res.drawable.hackernews),
        Project("Jetpack", ProjectCategory.Work, Res.drawable.jetpack),
        Project("WordPress", ProjectCategory.Work, Res.drawable.wordpress),
        Project("Tumblr", ProjectCategory.Work, Res.drawable.tumblr),
        Project("BikesHere", ProjectCategory.Side, Res.drawable.bike),
        Project("Life in Taiwan", ProjectCategory.Side, Res.drawable.life),
        Project("Mask Map", ProjectCategory.Side, Res.drawable.mask),
        Project("Adoptly", ProjectCategory.Side, Res.drawable.adoptly),
        Project("IndoChat", ProjectCategory.Work, Res.drawable.indochat),
        Project("Autopass", ProjectCategory.Work, Res.drawable.autopass),
        Project("PKLOT App", ProjectCategory.Work, Res.drawable.pklotapp),
        Project("Calories 100", ProjectCategory.Work, Res.drawable.calories100),
        Project("Crops Price Checker", ProjectCategory.Side, Res.drawable.crops_price_checker),
        Project("Water Restriction Info", ProjectCategory.Side, Res.drawable.water_rectriction_info),
        Project("Car Finder", ProjectCategory.Side, Res.drawable.car_finder),
        Project("Baby Formula", ProjectCategory.Side, Res.drawable.baby_formula),
        Project("Small Trade", ProjectCategory.Work, Res.drawable.small_trade),
        Project("VR for 3D", ProjectCategory.Work, Res.drawable.vr_for_3d),
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
                        text = tab.displayName,
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
            columns = GridCells.Fixed(if (useMobileLayout) 3 else 4),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth().heightIn(max = 3000.dp)
        ) {
            val filtered =
                if (selectedTab == ProjectCategory.All) projects else projects.filter { it.category == selectedTab }
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
            Text(text = item.category.displayName, color = Color.LightGray, fontSize = 12.sp)
        }
    }
}

data class Project(
    val title: String,
    val category: ProjectCategory,
    val imageRes: DrawableResource
)

sealed class ProjectCategory(val displayName: String) {
    data object All : ProjectCategory("All")
    data object Work : ProjectCategory("Work")
    data object Side : ProjectCategory("Side Project")

    companion object {
        fun getAll() = listOf(All, Work, Side)
    }
}
