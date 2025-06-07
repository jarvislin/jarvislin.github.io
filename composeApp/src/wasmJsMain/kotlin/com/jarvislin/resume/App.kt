package com.jarvislin.resume

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jarvislin.resume.ui.components.*
import com.jarvislin.resume.ui.darkScheme
import com.jarvislin.resume.ui.lightScheme

@Composable
fun App() {
    val density = LocalDensity.current.density
    val screenWidth = LocalWindowInfo.current.containerSize.width / density
    val useMobileLayout = screenWidth <= 768
    var useDarkTheme by remember { mutableStateOf(true) }

    MaterialTheme(colorScheme = if (useDarkTheme) darkScheme else lightScheme) {
        LazyColumn(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                ProfileSection(useMobileLayout)
                AboutSection(useMobileLayout)
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    "Professional Skills",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(16.dp))
                SkillsSection(useMobileLayout)
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    "Work Experiences",
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Spacer(modifier = Modifier.height(16.dp))
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
                                    + "Engaged directly with enterprise clients to identify pain points and deliver custom solutions."
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
                            duration = "Dec 2021 - Nov 2022",
                            title = "Co-Founder & Technical Director",
                            company = "QTCOMM",
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
                            description = "Participated in the development of mobile apps and websites." +
                                    "\n\n" +
                                    "Assisted with product testing and feature validation to improve usability." +
                                    "\n\n" +
                                    "Contributed to the implementation of Scen3D and SaaSPhoto, focusing on front-end and integration tasks."
                        ),
                    ),
                    useMobileLayout
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    "Awards and Achievements",
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Spacer(modifier = Modifier.height(16.dp))
                AchievementSection(useMobileLayout)
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Portfolio",
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Spacer(modifier = Modifier.height(16.dp))
                PortfolioSection(useMobileLayout)
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "References",
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Spacer(modifier = Modifier.height(16.dp))
                ReferenceSection()
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    "Contact Me",
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Spacer(modifier = Modifier.height(16.dp))
                ContactSection()
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}