package com.jarvislin.resume.data

import org.jetbrains.compose.resources.DrawableResource
import resume.composeapp.generated.resources.*
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.barchart
import resume.composeapp.generated.resources.category
import resume.composeapp.generated.resources.chat

class About(val description: String, val resource: DrawableResource)

object AboutData {
    const val RESUME_URL = "https://drive.google.com/file/d/19rTCemHp7vG50n-AOHgcrF5jJC7Vsuii/view?usp=sharing"
    
    const val INTRO_TEXT = "Tech lead with 13+ years of experience building end-to-end software solutions, driving cross-team execution, and aligning product vision with engineering excellence."
    
    val points = listOf(
        About(
            "Specialized in taking products from concept to launch, including defining specs and analyzing metrics.",
            Res.drawable.barchart
        ),
        About(
            "Effective in both independent and cross-functional team environments with clear communication.",
            Res.drawable.chat
        ),
        About(
            "Delivered 20+ app products, including those with 100M+ downloads and Editor's Choice awards.",
            Res.drawable.category
        ),
        About(
            "Experienced in supervising engineering and product teams globally across international settings.",
            Res.drawable.group
        ),
    )
}