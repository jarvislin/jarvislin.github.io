package com.jarvislin.resume.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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