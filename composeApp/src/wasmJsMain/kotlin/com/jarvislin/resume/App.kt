package com.jarvislin.resume

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jarvislin.resume.ui.components.WorkExperience
import com.jarvislin.resume.ui.components.WorkExperienceSection

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
            WorkExperienceSection(
                listOf(
                    WorkExperience(
                        duration = "Nov 2024 - Present",
                        title = "Founder & CEO",
                        company = "JINNO",
                        description =
                        "Defined and executed the company’s vision, mission, and long-term strategy." +
                                "\n\n" +
                                "Built and launched AI-powered multilingual communication products used in tourism, hospitality, and social impact sectors." +
                                "\n\n"
                                + "Engaged directly with enterprise clients to identify pain points and deliver custom solutions." +
                                "\n\n" +
                                "Advocated for inclusive technology to bridge language barriers in underserved communities."
                    ),
                    WorkExperience(
                        duration = "Nov 2022 - Jun 2024",
                        title = "Mobile Engineer",
                        company = "Automattic",
                        description =
                        "Developed and implemented new features for the Android applications of Tumblr, WordPress, and Jetpack, including improvements to user experience, performance, and security." +
                                "\n\n" +
                                "Collaborated with cross-functional teams to troubleshoot and resolve technical and product issues, ensuring the Android applications met quality standards."

                    ),
                    WorkExperience(
                        duration = "Dec 2021 - Nov 2022", title = "Co-Founder & Technical Director", company = "QTCOMM",
                        description =
                        "Led engineers in identifying technical issues and performing corrective active procedures to provide optimal solutions." +
                                "\n\n" +
                                "Utilized diagnostic techniques focusing on IT problem resolution of classified/unclassified specific applications which reduced the costs of the technology stack." +
                                "\n\n" +
                                "Implemented appropriate technical solutions for the audio/video call features to reduce technology production costs by 85%, which saved over 8%+ of the company’s total annual expenses." +
                                "\n\n" +
                                "Served as the primary point of contact for company partners, integrating 3+ systems successfully, including Tokopedia and Juiker."
                    ),
                    WorkExperience(
                        duration = "Nov 2020 - Dec 2021",
                        title = "Project Technical Director",
                        company = "QTCOMM/PT. ICE Messenger Indonesia",
                        description = "Supervised various units in Taiwan and Indonesia, optimizing procedures and operational workflow based on employee needs through effective collaboration and communication, which increased efficiency by 40%+." +
                                "\n\n" +
                                "Provided exceptional product development services, including but not limited to updating mobile applications and implementing new features such as data encryption, QA test, and fixing bugs." +
                                "\n\n" +
                                "Spearheaded the product roadmap of IndoChat from conceptualization through execution, identifying gaps in product capabilities and coverage and formulating product strategic direction in addressing needs." +
                                "\n\n" +
                                "Conducted research regarding data tracking to establish event tracking systems and provide on-time decisive analysis; adjusted sign-up flow consistently, which generated a conversion rate of over 70%+."
                    ),
                    WorkExperience(
                        duration = "Jun 2019 - Nov 2020",
                        title = "Android Team Leader",
                        company = "QTCOMM/PT. ICE Messenger Indonesia",
                        description = "Led Android engineers to develop and implement features for various android applications to polish architectural and product design details for open-ended tasks based on owner specifications." +
                                "\n\n" +
                                "Devised documentation for applications, detailing operation aspects, functions, and features to ensure accuracy." +
                                "\n\n" +
                                "Supervised creation and testing of 3+ Android apps from research and planning through deployment and post-launch support." +
                                "\n\n" +
                                "Researched and developed Android apps, utilizing comprehensive knowledge of the mobile landscape and innovations to remain on the cutting edge of the Android market." +
                                "\n\n" +
                                "Built CI/CD to ensure tests and delivery can be triggered automatically which reduced manual builds for 50+ minutes."
                    ),
                    WorkExperience(
                        duration = "Jan 2018 - May 2019",
                        title = "Android Engineer",
                        company = "Autopass",
                        description = "Developed a location based Android application which defines and implements robust app architectures and complex user interfaces to provide parking lots, gas station information, and other driver related services." +
                                "\n\n" +
                                "Provided code maintenance and system upgrades to maximize performance and ensure successful application deployments." +
                                "\n\n" +
                                "Collaborated with analysts and engineers regarding project capabilities and limitations to deliver optimal functionality."
                    ),
                    WorkExperience(
                        duration = "Apr 2016 - Dec 2017",
                        title = "Android Engineer",
                        company = "PKLOT (Acquired by Acer)",
                        description = "Designed and implemented services, applications, and frameworks to integrate solutions into innovative core technologies." +
                                "\n\n" +
                                "Developed software supporting digital technologies for license plate recognition to manage and match users who want to pay the fuel cost in gas stations." +
                                "\n\n" +
                                "Provided timely issue resolution in collaboration with backend developers to ensure accuracy for a successful app launch."
                    ),
                    WorkExperience(
                        duration = "Apr 2015 - Jan 2016",
                        title = "Android Engineer",
                        company = "iFit",
                        description = "Built advanced fitness applications and SDK for the Android platform to define, design, and launch new features to create health and fitness experiences that are unique and immersive." +
                                "\n\n" +
                                "Prototyped and built innovative features using the newest APIs on the Android platform; tested codes for robustness, executed edge case, usability, and general reliability analysis."
                    ),
                    WorkExperience(
                        duration = "Oct 2014 - Nov 2014",
                        title = "Android Engineer",
                        company = "Zinwell",
                        description = "Developed a proof of concept for the E-Commerce application of Zinwell in collaboration with a UI designer and other engineers to develop the cutting-edge application to provide a valuable and innovative product with the best quality and value to all Zinwell stockholders."
                    ),
                    WorkExperience(
                        duration = "Jun 2012 - Jul 2014",
                        title = "Software Engineer",
                        company = "Ortery Technologies, Inc",
                        description = "Implemented product improvements by actively collaborating with owners to better understand an application's capabilities before testing scenarios."
                    ),
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

