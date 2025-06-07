package com.jarvislin.resume.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AboutSection() {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .widthIn(max = maxWebComponentWidth),
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            // 簡介段
            Text(
                text = "With over 13 years of experience building high-quality mobile apps across industries and platforms.",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 條列區塊
            val points = listOf(
                "Specialized in taking products from concept to launch, including defining specs and analyzing metrics.",
                "Effective in both independent and cross-functional team environments with clear communication.",
                "Delivered 20+ app products, including those with 100M+ downloads and Editor’s Choice awards.",
                "Experienced in supervising engineering and product teams across international settings.",
                "Open source contributor and former organizer of Android Taipei, Taiwan’s leading Android developer event."
            )

            points.forEachIndexed { index, point ->
                Row(
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "•",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = point,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                // 不在最後一項才加 Divider
                if (index < points.lastIndex) {
                    Spacer(modifier = Modifier.height(8.dp))
                    HorizontalDivider(
                        modifier = Modifier.padding(start = 12.dp, top = 4.dp),
                        thickness = 0.5.dp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { /* TODO: download CV */ },
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text("DOWNLOAD CV")
            }
        }
    }
}