package com.jarvislin.resume.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SkillsSection() {
    val allSkills: List<Skill> = listOf(
        "Android Development" to 0.95f,
        "Git" to 0.7f,
        "Testing" to 0.75f,
        "Java" to 0.85f,
        "SQL" to 0.55f,
        "RxJava" to 0.8f,
        "Project Mangement" to 0.6f,
        "Google Analytics / Amplitude...etc." to 0.75f,
        "Material Design" to 0.85f,
        "CI/CD" to 0.8f,
        "Kotlin" to 0.95f,
        "PHP" to 0.75f,
        "Linux" to 0.6f,
        "Design Pattern" to 0.7f,
        "Product Design" to 0.65f,
        "Location-based Services" to 0.8f
    ).map { (label, level) -> Skill(label, level) }

    val skillsLeft = allSkills.filterIndexed { index, skill -> index % 2 == 0 }
    val skillsRight = allSkills.filterIndexed { index, skill -> index % 2 == 1 }

    Card(
        modifier = Modifier
            .widthIn(max= maxWebComponentWidth)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Column(modifier = Modifier.weight(1f)) { skillsLeft.map { SkillBar(it) } }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) { skillsRight.map { SkillBar(it) } }
        }
    }
}

@Composable
fun SkillBar(skill: Skill) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = skill.label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { skill.level },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

class Skill(val label: String, val level: Float)