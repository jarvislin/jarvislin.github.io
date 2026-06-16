package com.jarvislin.resume.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jarvislin.resume.data.Skill
import kotlinx.coroutines.delay

@Composable
fun SkillsSection(useMobileLayout: Boolean) {
    val allSkills: List<Skill> = listOf(
        Skill("Fullstack Development", 0.9f),
        Skill("Mobile Development", 1.0f),
        Skill("Scalable Architecture", 0.9f),
        Skill("CI/CD Automation", 0.9f),
        Skill("Cross-functional Collaboration", 1.0f),
        Skill("Product Strategy", 0.8f),
        Skill("Analytics Integration", 0.85f),
        Skill("UI/UX Collaboration", 0.9f),
        Skill("Location-Based Services", 0.85f),
        Skill("Project Management", 0.85f)
    )

    val midpoint = (allSkills.size + 1) / 2
    val skillsLeft = allSkills.subList(0, midpoint)
    val skillsRight = allSkills.subList(midpoint, allSkills.size)

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .widthIn(max = maxWebComponentWidth)
    ) {
        Card {
            if (useMobileLayout) {
                Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    allSkills.map { SkillBar(it) }
                }
            } else {
                Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    Column(modifier = Modifier.weight(1f)) { skillsLeft.map { SkillBar(it) } }
                    Spacer(modifier = Modifier.width(24.dp))
                    Column(modifier = Modifier.weight(1f)) { skillsRight.map { SkillBar(it) } }
                }
            }
        }
    }
}

@Composable
fun SkillBar(skill: Skill) {
    var startAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay((0..600).random().toLong())
        startAnimation = true
    }

    val animatedProgress by animateFloatAsState(
        targetValue = if (startAnimation) skill.level else 0f,
        animationSpec = tween(durationMillis = 600),
        label = "Skill Progress"
    )

    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = skill.label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { animatedProgress },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

