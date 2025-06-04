package com.jarvislin.resume.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.compose_multiplatform

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