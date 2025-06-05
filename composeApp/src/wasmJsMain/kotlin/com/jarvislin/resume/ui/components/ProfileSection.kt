package com.jarvislin.resume.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import resume.composeapp.generated.resources.*

@Composable
fun ProfileSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // 頭像與基本資料卡
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {

            Image(
                painter = painterResource(Res.drawable.avatar_16_9),
                contentDescription = "Jarvis Lin",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp))
            )

            Spacer(modifier = Modifier.height(12.dp))

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text("Jarvis Lin", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
                Text("Founder · Developer · Impact Creator", style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    Icon(painter = painterResource(Res.drawable.mail_24px), contentDescription = "Email")
                    Text("admin@jarvislin.com", modifier = Modifier.padding(horizontal = 8.dp))
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row {
                    Icon(painter = painterResource(Res.drawable.location_on_24px), contentDescription = "Location")
                    Text("Taipei, Taiwan", Modifier.padding(horizontal = 8.dp))
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row {
                    Icon(painter = painterResource(Res.drawable.school_24px), contentDescription = "School")
                    Text(
                        "National Formosa University, Bachelor, Computer Science and Information Engineering",
                        Modifier.padding(horizontal = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            HorizontalDivider()
            // 社群連結
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp).padding(top = 8.dp, bottom = 12.dp),
            ) {

                IconButton(
                    onClick = {},
                    colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.home),
                        contentDescription = "Homepage",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Spacer(Modifier.width(8.dp))
                IconButton(
                    onClick = {},
                    colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.github),
                        contentDescription = "GitHub",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Spacer(Modifier.width(8.dp))
                IconButton(
                    onClick = {},
                    colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.linkedin),
                        contentDescription = "LinkedIn",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 2.dp)) {
        Text(label, style = MaterialTheme.typography.bodySmall)
        Text(value, style = MaterialTheme.typography.bodyMedium)
    }
}