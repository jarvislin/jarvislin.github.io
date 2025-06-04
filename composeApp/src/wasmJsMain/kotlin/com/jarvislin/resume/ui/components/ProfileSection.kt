package com.jarvislin.resume.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
                painter = painterResource(Res.drawable.avatar_16_9), // 替換為你的圖片資源
                contentDescription = "Jarvis Lin",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp))
            )

            Spacer(modifier = Modifier.height(12.dp))

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text("Jarvis Lin", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
                Text("Mobile Expert")

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

        Spacer(modifier = Modifier.height(16.dp))

        // 下載 CV 按鈕
        ElevatedButton(
            onClick = { /* TODO: download CV */ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("DOWNLOAD CV")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 簡介文字區塊
        Text(
            text = "With over 13 years of software development experience, proficient in creating high-quality apps.",
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text("• Specialized in taking products from concept to completion, including defining specifications and analyzing metrics, treating each project as a personal endeavor.")
        Text("• Experienced in both independent work and collaborative team environments, with a strong emphasis on high-quality communication.")
        Text("• Handled over 20 app products, including those with 100M+ downloads and official Editor’s Choice awards for best app of the year.")
        Text("• Possesses experience in supervising various units, including engineering and product, in a multinational environment.")
        Text("• Passionate about open source, and served as an organizer for Android Taipei, the most significant monthly event for Android Developers in Taiwan.")
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 2.dp)) {
        Text(label, style = MaterialTheme.typography.bodySmall)
        Text(value, style = MaterialTheme.typography.bodyMedium)
    }
}