@file:OptIn(ExperimentalComposeUiApi::class)

package com.jarvislin.resume.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import resume.composeapp.generated.resources.*

@Composable
fun PortfolioSection(useMobileLayout: Boolean, onClickLoad: () -> Unit, countOfLoadedProjects: Int) {
    var selectedTab by remember { mutableStateOf<Project.Category>(Project.Category.All) }
    val tabs = Project.Category.getAll()
    var isHovered by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (isHovered) 90f else 0f,
        label = "icon rotation"
    )
    val projects = listOf(
        Project(
            "KMP Hub",
            category = Project.Category.Side,
            links = listOf(Project.Link("GitHub", Project.Link.Type.GitHub, "https://github.com/KMP-Hub")),
            image = Res.drawable.kmp_hub,
            tags = listOf("Web", "Compose Multiplatform", "Kotlin"),
            description = "KMP Hub is a curated platform for discovering Kotlin Multiplatform libraries and tools. We aim to help developers explore the KMP ecosystem, accelerate cross-platform development, and foster community collaboration."
        ),
        Project(
            "Hacker News KMP",
            category = Project.Category.Side,
            links = listOf(
                Project.Link(
                    "GitHub",
                    Project.Link.Type.GitHub,
                    "https://github.com/jarvislin/HackerNews-KMP"
                )
            ),
            image = Res.drawable.hackernews,
            tags = listOf("App", "Android", "iOS", "Compose Multiplatform", "Kotlin"),
            description = "This app is an open-source project, designed to showcase the capabilities of Kotlin Multiplatform Compose, delivering a seamless reading experience across Android and iOS platforms."
        ),
        Project(
            "Jetpack",
            category = Project.Category.Work,
            links = listOf(
                Project.Link(
                    "GitHub",
                    Project.Link.Type.GitHub,
                    "https://github.com/jarvislin/HackerNews-KMP"
                ),
                Project.Link(
                    "Google Play",
                    Project.Link.Type.PlayStore,
                    "https://play.google.com/store/apps/details?id=com.jetpack.android"
                )
            ),
            tags = listOf("App", "Kotlin"),
            image = Res.drawable.jetpack,
            description = "Jetpack is an app that provides powerful tools and features to enhance WordPress sites, including site security, performance optimization, and content management. It allows users to monitor their site’s activity, manage plugins, and view detailed statistics, all from their devices."
        ),
        Project(
            "WordPress",
            category = Project.Category.Work,
            links = listOf(
                Project.Link(
                    "GitHub",
                    Project.Link.Type.GitHub,
                    "https://github.com/jarvislin/HackerNews-KMP"
                ),
                Project.Link(
                    "Google Play",
                    Project.Link.Type.PlayStore,
                    "https://play.google.com/store/apps/details?id=org.wordpress.android"
                )
            ),
            tags = listOf("App", "Android", "Kotlin"),
            image = Res.drawable.wordpress,
            description = "WordPress is a mobile app that allows users to create, manage, and publish content on their WordPress blogs or websites directly from their devices. It offers features for writing posts, uploading photos, managing comments, and viewing site statistics, making it a powerful tool for bloggers and website owners on the go."
        ),
        Project(
            "Tumblr",
            category = Project.Category.Work,
            links = listOf(
                Project.Link(
                    "Google Play",
                    Project.Link.Type.PlayStore,
                    "https://play.google.com/store/apps/details?id=com.tumblr"
                )
            ),
            tags = listOf("App", "Android", "Kotlin"),
            image = Res.drawable.tumblr,
            description = "Tumblr is a microblogging and social networking app where users can post multimedia content and follow other users' blogs. It combines elements of blogging and social networking, allowing for a wide range of creative expression."
        ),
        Project(
            "BikesHere",
            category = Project.Category.Side,
            tags = listOf("App", "Android", "Kotlin"),
            image = Res.drawable.bike,
            description = "Provides geographic information for over 6,000 public bikes across Taiwan, including YouBike, YouBike 2.0, iBike, T-Bike, Pbike, K-bike, and more."
        ),
        Project(
            "Life in Taiwan",
            category = Project.Category.Side,
            tags = listOf("App", "Android", "Kotlin"),
            image = Res.drawable.life,
            description = "It is a comprehensive science education app that provides detailed information on various species, including animals, plants, and insects, to enhance biodiversity knowledge. It offers visual and written content in both English and Chinese, sourced from reputable institutions like the Biodiversity Research Center and Academia Sinica."
        ),
        Project(
            "Mask Map",
            category = Project.Category.Side,
            links = listOf(
                Project.Link(
                    "GitHub",
                    Project.Link.Type.GitHub,
                    "https://github.com/jarvislin/drugstores"
                ),
                Project.Link(
                    "Google Play",
                    Project.Link.Type.PlayStore,
                    "https://play.google.com/store/apps/details?id=com.jarvislin.drugstores"
                )
            ),
            tags = listOf("App", "Android", "Kotlin"),
            image = Res.drawable.mask,
            description = "When the epidemic broke out, the Taiwanese government restricted the quantity of masks that each citizen was able to buy. Also, the pharmacies are the only place where the public can purchase the mask. Therefore, this information can be helpful for those people who want to buy masks from surrounding pharmacies. It took Top 1 in Free Apps on Google Play in March 2020 in Taiwan."
        ),
        Project(
            "Adoptly",
            category = Project.Category.Side,
            tags = listOf("App", "Android", "Kotlin"),
            image = Res.drawable.adoptly,
            description = "Adoptly provides a way to adopt animals which live in shelters. It also shows information about shelters."
        ),
        Project(
            "IndoChat",
            category = Project.Category.Work,
            links = listOf(
                Project.Link(
                    "Google Play",
                    Project.Link.Type.PlayStore,
                    "https://play.google.com/store/apps/details?id=id.co.indochat.app"
                )
            ),
            tags = listOf("App", "Android", "Kotlin"),
            image = Res.drawable.indochat,
            description = "This is a comprehensive mobile application developed for the Indonesian government that offers a suite of features including chat, video/audio calling, social media integration, news feeds, and invoice scanning capabilities."
        ),
        Project(
            "Autopass",
            category = Project.Category.Work,
            links = listOf(
                Project.Link(
                    "Google Play",
                    Project.Link.Type.PlayStore,
                    "https://play.google.com/store/apps/details?id=com.pklotcorp.autopass"
                )
            ),
            tags = listOf("App", "Android", "Kotlin"),
            image = Res.drawable.autopass,
            description = "This is a location-based platform offering a range of services for drivers, including finding parking and refueling options."
        ),
        Project(
            "PKLOT App",
            category = Project.Category.Work,
            links = listOf(
                Project.Link(
                    "Google Play",
                    Project.Link.Type.PlayStore,
                    "https://play.google.com/store/apps/details?id=com.alfred.parkinglot"
                )
            ),
            tags = listOf("App", "Android", "Kotlin"),
            image = Res.drawable.pklotapp,
            description = "This app is a parking and refueling assistant that allows users to locate available parking spaces and make payments. It was recognized as one of the best apps on Google Play in Taiwan in both 2016 and 2017."
        ),
        Project(
            "Calories 100",
            category = Project.Category.Work,
            tags = listOf("App", "Android", "Kotlin"),
            image = Res.drawable.calories100,
            description = "It's an app for calculating calorie content in foods which also provides features of social media platform which integrated Facebook API. Our nutritionists will reply to users by using this function."
        ),
        Project(
            "Crops Price Checker",
            links = listOf(
                Project.Link(
                    "Google Play",
                    Project.Link.Type.PlayStore,
                    "https://play.google.com/store/apps/details?id=com.jarvislin.producepricechecker"
                ),
                Project.Link(
                    "App Store",
                    Project.Link.Type.AppStore,
                    "https://apps.apple.com/us/app/%E8%94%AC%E6%9E%9C%E8%8A%B1%E5%8D%89%E8%A1%8C%E6%83%85%E7%AB%99-%E6%9C%80%E6%96%B0%E5%B8%82%E5%A0%B4%E4%BA%A4%E6%98%93%E8%A1%8C%E6%83%85-%E6%AD%B7%E5%8F%B2%E5%83%B9%E6%A0%BC-%E8%A1%8C%E6%83%85%E8%B6%A8%E5%8B%A2/id6661027883"
                )
            ),
            category = Project.Category.Side,
            tags = listOf("App", "Android", "iOS", "Compose Multiplatform", "Kotlin", "Java"),
            image = Res.drawable.crops_price_checker,
            description = "It is a tool that retrieves open data from Taiwan's Council of Agriculture to display current and historical prices of fruits and vegetables."
        ),
        Project(
            "Water Restriction",
            category = Project.Category.Side,
            image = Res.drawable.water_rectriction_info,
            tags = listOf("App", "Android", "Java"),
            description = "It's an app which fetches information about water restriction from our government because we had a serious drought in 2016. It also provided reservoirs' water levels to remind people to save water resource."
        ),
        Project(
            "Car Finder",
            links = listOf(
                Project.Link(
                    "Google Play",
                    Project.Link.Type.PlayStore,
                    "https://play.google.com/store/apps/details?id=com.jarvislin.carfinder"
                )
            ),
            category = Project.Category.Side,
            tags = listOf("App", "Android", "Kotlin", "Java"),
            image = Res.drawable.car_finder,
            description = "Car Finder is for people who can't find their car just like me. It uses GPS to detect location, and takes pictures to assist in recording parking information. It also provides navigation so we can find our car easily."
        ),
        Project(
            "Baby Formula",
            category = Project.Category.Side,
            tags = listOf("App", "Backend", "Android", "Java", "PHP"),
            image = Res.drawable.baby_formula,
            description = "This application was developed for a programming contest hosted by Taiwan's Food and Drug Administration, Ministry of Health and Welfare. It utilizes a milk formula dataset approved for sale in Taiwan. To enhance the dataset's utility, I expanded its scope and integrated a platform for user information sharing."
        ),
        Project(
            "Small Trade",
            category = Project.Category.Work,
            tags = listOf("App", "Android", "Java"),
            image = Res.drawable.small_trade,
            description = "It's a C2C platform, sellers can post their products online, upload pictures and videos that make their products authentic."
        ),
        Project(
            "VR for 3D",
            category = Project.Category.Work,
            tags = listOf("App", "Android", "Java"),
            image = Res.drawable.vr_for_3d,
            description = "This is a 3D model viewer designed to showcase models created with Ortery's'hardware. Users can explore a variety of sample models through this app."
        ),
    )

    Column(modifier = Modifier.padding(horizontal = 16.dp).widthIn(max = maxWebComponentWidth)) {

        // Tabs
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            tabs.forEach { tab ->
                Box(contentAlignment = Alignment.TopCenter) {
                    TextButton(onClick = { selectedTab = tab }) {
                        Text(
                            text = tab.displayName,
                            color = if (selectedTab == tab) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                    if (selectedTab == tab) {
                        HorizontalDivider(
                            thickness = 2.dp,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .padding(top = MaterialTheme.typography.bodyMedium.fontSize.value.dp + 24.dp)
                                .width(24.dp)
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
            val filtered = if (selectedTab == Project.Category.All) projects.take(countOfLoadedProjects)
            else projects.filter { it.category == selectedTab }
            items(filtered) { item ->
                PortfolioCard(item, useMobileLayout)
            }
        }

        if (countOfLoadedProjects < projects.size && selectedTab == Project.Category.All) {
            Spacer(modifier = Modifier.height(24.dp))
            AssistChip(
                onClick = { onClickLoad() },
                label = { Text("LOAD MORE") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(Res.drawable.add),
                        contentDescription = "Load more",
                        modifier = Modifier.rotate(rotation)
                    )
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .onPointerEvent(eventType = PointerEventType.Enter) { isHovered = true }
                    .onPointerEvent(eventType = PointerEventType.Exit) { isHovered = false }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun PortfolioCard(item: Project, useMobileLayout: Boolean) {
    var showDialog by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .clickable { showDialog = true }
    ) {
        Image(
            painter = painterResource(item.image),
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        // mask
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Brush.verticalGradient(colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f))))
        )

        Column(
            modifier = Modifier
                .align(if (useMobileLayout) Alignment.BottomCenter else Alignment.BottomStart)
                .padding(12.dp)
        ) {
            Text(text = item.title, color = Color.White, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            if (useMobileLayout.not()) {
                Text(text = item.category.displayName, color = Color.LightGray, fontSize = 12.sp)
            }
        }
        if (showDialog) {
            PortfolioDialog(project = item) { showDialog = false }
        }
    }
}

data class Project(
    val title: String,
    val description: String,
    val tags: List<String> = emptyList(),
    val category: Category,
    val image: DrawableResource,
    val links: List<Link> = emptyList(),
    val screenShots: List<DrawableResource> = emptyList(),
) {
    data class Link(
        val title: String,
        val type: Type,
        val url: String,
    ) {
        enum class Type {
            GitHub, PlayStore, AppStore
        }
    }

    sealed class Category(val displayName: String) {
        data object All : Category("All")
        data object Work : Category("Work")
        data object Side : Category("Side Hustle")

        companion object {
            fun getAll() = listOf(All, Work, Side)
        }
    }
}


