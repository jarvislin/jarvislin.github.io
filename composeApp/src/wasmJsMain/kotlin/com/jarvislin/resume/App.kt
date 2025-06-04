package com.jarvislin.resume

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import org.jetbrains.compose.resources.painterResource

import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.compose_multiplatform

@Composable
fun App() {
    val scrollState = rememberScrollState()
    MaterialTheme {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            WaterfallDemo(
                listOf(
                    "Specialized in taking products from concept to completion, including defining specifications and analyzing metrics, treating each project as a personal endeavor.",
                    "Speciaon, including  specifch project as a personal endeavor.",
                    "Special, including defining specitreating each project as a personal endeavor.",
                    "Specialized in taking products from concept to completion, including defining specifications and analyzing metrics, treating each project as a personal endeavor.",
                    "Specialized in taking products from concept to completion, irics, treating each project as a personal endeavor.",
                    "Spng specifications and analyzing metrics, treating each project as a personal endeavor.",
                    "Specialized in taking products from concept to completion, s and analyzing metrics, treating each project as a personal endeavor.",
                    "Specialized in taking products from concept to completion, including defining specifications and analyzing metrics, treating each project as a personal endeavor.",
                    "Specialized in taking products from concept to completion, including dech project as a personal endeavor."
                )
            )
        }
    }
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1D1E22))
            .padding(16.dp)
    ) {
        // 頭像與基本資料卡
        Card(
//            colors = CardColors(),
//            backgroundColor = Color(0xFF2A2C32),
            shape = RoundedCornerShape(8.dp),
//            elevation = 8.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(Res.drawable.compose_multiplatform), // 替換為你的圖片資源
                    contentDescription = "Jarvis Lin",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                )

                Spacer(modifier = Modifier.width(24.dp))

                Column {
                    Text("HELLO", color = Color.Cyan, fontSize = 12.sp)
                    Text("I'm Jarvis Lin", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text("Mobile Expert", color = Color.LightGray)

                    Spacer(modifier = Modifier.height(8.dp))

                    InfoRow("EMAIL", "admin@jarvislin.com")
                    InfoRow("WEBSITE", "https://jarvislin.com")
                    InfoRow("LOCATION", "Taipei, Taiwan")
                    InfoRow(
                        "EDUCATION",
                        "National Formosa University, Bachelor, Computer Science and Information Engineering"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 社群連結
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF00B5A6))
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
//            IconButton(onClick = { /* TODO: open Facebook */ }) {
//                Icon(Icons.Default.Facebook, contentDescription = "Facebook", tint = Color.White)
//            }
//            IconButton(onClick = { /* TODO: open GitHub */ }) {
//                Icon(Icons.Default.Code, contentDescription = "GitHub", tint = Color.White)
//            }
//            IconButton(onClick = { /* TODO: open LinkedIn */ }) {
//                Icon(Icons.Default.AccountBox, contentDescription = "LinkedIn", tint = Color.White)
//            }
//            IconButton(onClick = { /* TODO: open Instagram */ }) {
//                Icon(Icons.Default.Camera, contentDescription = "Instagram", tint = Color.White)
//            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 下載 CV 按鈕
        Button(
            onClick = { /* TODO: download CV */ },
            colors = ButtonDefaults.buttonColors(Color.Gray),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("DOWNLOAD CV", color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 簡介文字區塊
        Text(
            text = "With over 13 years of software development experience, proficient in creating high-quality apps.",
            color = Color.White,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        BulletPoint("Specialized in taking products from concept to completion, including defining specifications and analyzing metrics, treating each project as a personal endeavor.")
        BulletPoint("Experienced in both independent work and collaborative team environments, with a strong emphasis on high-quality communication.")
        BulletPoint("Handled over 20 app products, including those with 100M+ downloads and official Editor’s Choice awards for best app of the year.")
        BulletPoint("Possesses experience in supervising various units, including engineering and product, in a multinational environment.")
        BulletPoint("Passionate about open source, and served as an organizer for Android Taipei, the most significant monthly event for Android Developers in Taiwan.")
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 2.dp)) {
        Text(label, color = Color.Gray, fontSize = 10.sp)
        Text(value, color = Color.White, fontSize = 14.sp)
    }
}

@Composable
fun BulletPoint(text: String) {
    Row(modifier = Modifier.padding(bottom = 8.dp)) {
        Text("•", color = Color.Cyan, fontSize = 14.sp, modifier = Modifier.padding(end = 8.dp))
        Text(text, color = Color.White, fontSize = 14.sp)
    }
}

@Composable
fun SkillsSection() {
    val skillsLeft = listOf(
        "Android Development" to 0.95f,
        "Git" to 0.7f,
        "Testing" to 0.75f,
        "Java" to 0.85f,
        "SQL" to 0.55f,
        "RxJava" to 0.8f,
        "Project Mangement" to 0.6f,
        "Google Analytics / Amplitude...etc." to 0.75f
    )

    val skillsRight = listOf(
        "Material Design" to 0.85f,
        "CI / CD" to 0.8f,
        "Kotlin" to 0.95f,
        "PHP" to 0.75f,
        "Linux" to 0.6f,
        "Design Pattern" to 0.7f,
        "Product Design" to 0.65f,
        "Location-based Services" to 0.8f
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1D1E22))
            .padding(16.dp)
    ) {
        Text(
            "Professional Skills",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.LightGray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            SkillColumn(skillsLeft, Modifier.weight(1f))
            Spacer(modifier = Modifier.width(16.dp))
            SkillColumn(skillsRight, Modifier.weight(1f))
        }
    }
}

@Composable
fun SkillColumn(skills: List<Pair<String, Float>>, modifier: Modifier) {
    Column(modifier = modifier) {
        for ((label, level) in skills) {
            SkillBar(label = label, level = level)
        }
    }
}

@Composable
fun SkillBar(label: String, level: Float) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = label,
            color = Color.LightGray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { level },
            color = Color(0xFF00B5A6), // 青綠色條
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(4.dp))
        )
    }
}


@Composable
fun Header() {
    Column {
        Row {
            Avatar()
            Information()
        }
    }
}

@Composable
fun Avatar() {

}

@Composable
fun Information() {
    Column {
        InfoItem("Email", "admin@jarvislin.com")
    }
}

@Composable
fun InfoItem(title: String, value: String) {
    Row {
        Text(title)
        Text(value)
    }
}

@Composable
fun WorkExperienceSection() {
    val experiences = listOf(
        WorkExperienceItem("Mobile Engineer", "Automattic", "Nov 2022 - Jun 2024", "..."),
        WorkExperienceItem("Co-Founder & Technical Director", "QTComm", "Dec 2021 - Nov 2022", "..."),
        WorkExperienceItem(
            "Project Technical Director",
            "QTComm / PT. ICE Messenger Indonesia",
            "Nov 2020 - Dec 2021",
            "..."
        ),
        WorkExperienceItem("Android Team Leader", "QTComm / PT. ICE Messenger Indonesia", "Jun 2019 - Nov 2020", "...")
    )

    Column(
        modifier = Modifier
            .background(Color(0xFF1D1E22))
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            "Work Experience",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.LightGray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            // 中間垂直線
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .fillMaxHeight()
                    .background(Color(0xFF00B5A6))
                    .align(Alignment.Center)
            )

            Column {
                experiences.forEachIndexed { index, item ->
                    WorkExperienceTimelineItem(item, isLeft = index % 2 == 0)
                }
            }
        }
    }
}

@Composable
fun WorkExperienceTimelineItem(item: WorkExperienceItem, isLeft: Boolean) {
    val alignment = if (isLeft) Arrangement.Start else Arrangement.End
    val paddingStart = if (isLeft) 0.dp else 48.dp
    val paddingEnd = if (isLeft) 48.dp else 0.dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        horizontalArrangement = alignment
    ) {
        if (!isLeft) Spacer(modifier = Modifier.width(paddingStart))

        Box(modifier = Modifier.width(280.dp)) {
            Column(
                modifier = Modifier
                    .background(Color(0xFF2A2C32), RoundedCornerShape(8.dp))
                    .border(2.dp, Color(0xFF00B5A6), RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Text(item.duration, color = Color(0xFF00B5A6), fontWeight = FontWeight.Bold)
                Text(item.title, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(item.company.uppercase(), color = Color.Gray, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(item.description, color = Color.LightGray, fontSize = 14.sp, lineHeight = 20.sp)
            }

            // 中間的圓點
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(Color(0xFF00B5A6), shape = CircleShape)
                    .align(Alignment.CenterStart)
                    .offset(x = if (isLeft) (-24).dp else (280 + 16).dp)
            )
        }

        if (isLeft) Spacer(modifier = Modifier.width(paddingEnd))
    }
}

data class WorkExperienceItem(
    val title: String,
    val company: String,
    val duration: String,
    val description: String
)

@Composable
fun WaterfallDemo(items: List<String>) {
    val leftItems = items.filterIndexed { i, _ -> i % 2 == 0 }
    val rightItems = items.filterIndexed { i, _ -> i % 2 != 0 }

    val leftY = remember { mutableStateListOf<Float>() }
    val rightY = remember { mutableStateListOf<Float>() }
    val offsetY = 60f
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
                .padding(end = 4.dp),
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

        // Divider with Canvas: 垂直線 + 部分橫向線（根據左右）
        Box(
            modifier = Modifier
                .width(2.dp)
                .fillMaxHeight()
                .zIndex(0f)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                // 垂直中線
                drawRect(Color.Black)
                val dividerX = size.width / 2f
                val curveSize = 20f

                leftY.forEach { y ->
                    val path = Path().apply {
                        moveTo(-40f, y + curveSize+offsetY)
                        quadraticTo(
                            x1 = curveSize, y1 = y+offsetY,
                            x2 = dividerX, y2 = y+offsetY
                        )
                        quadraticTo(
                            x1 = curveSize, y1 = y+offsetY,
                            x2 = -40f, y2 = y - curveSize+offsetY
                        )
                        close()
                    }
                    drawPath(path, Color.Gray)
                }

                rightY.forEach { y ->
                    val path = Path().apply {
                        moveTo(size.width + 40f, y + curveSize+offsetY)
                        quadraticTo(
                            x1 = size.width - curveSize, y1 = y+offsetY,
                            x2 = dividerX, y2 = y+offsetY
                        )
                        quadraticTo(
                            x1 = size.width - curveSize, y1 = y+offsetY,
                            x2 = size.width + 40f, y2 = y - curveSize+offsetY
                        )
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
                if(index==0){
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