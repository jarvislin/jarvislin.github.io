package com.jarvislin.resume.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import resume.composeapp.generated.resources.*
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.barchart

@Composable
fun AboutSection(useMobileLayout: Boolean) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp).widthIn(max = maxWebComponentWidth)
    ) {
        OutlinedButton(
            onClick = { /* TODO */ },
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp).align(CenterHorizontally),
        ) {
            Text("DOWNLOAD RESUME")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "With over 13 years of experience building high-quality mobile apps across industries and platforms.",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(if (useMobileLayout) 1 else 2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.heightIn(max = 1000.dp)
        ) {
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
                    "Delivered 20+ app products, including those with 100M+ downloads and Editor’s Choice awards.",
                    Res.drawable.category
                ),
                About(
                    "Experienced in supervising engineering and product teams across international settings.",
                    Res.drawable.group
                ),
            )
            items(points) {
                AboutCard(it, useMobileLayout)
            }
        }
    }
}

@Composable
fun AboutCard(about: About, useMobileLayout: Boolean) {
    Card {
        Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Icon(painterResource(about.resource), null)
            Spacer(Modifier.width(8.dp))
            Text(about.description, minLines = if (useMobileLayout) 1 else 3)
        }
    }
}

data class About(val description: String, val resource: DrawableResource)