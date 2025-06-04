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
import com.jarvislin.resume.ui.components.WaterfallDemo
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

