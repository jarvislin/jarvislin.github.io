package com.jarvislin.resume.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jarvislin.resume.data.WorkExperience
import com.jarvislin.resume.ui.components.*

@Composable
fun MainScreen() {
    val density = LocalDensity.current.density
    val screenWidth = LocalWindowInfo.current.containerSize.width / density
    val useMobileLayout = screenWidth < 768 // dp
    val listState = rememberLazyListState()
    var countOfLoadedProjects by remember { mutableStateOf(12) }
    val menuItems = listOf(
        MenuItem("ABOUT", 0),
        MenuItem("SKILLS", 1),
        MenuItem("ACHIEVEMENTS", 2),
        MenuItem("PROJECTS", 3),
        MenuItem("EXPERIENCES", 4),
        MenuItem("REFERENCES", 5),
//        MenuItem("CONTACT", 6),
    )
    Scaffold(topBar = { ResponsiveMenuBar(menuItems, listState, useMobileLayout) }) { paddingValues ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .padding(paddingValues)
                .safeContentPadding()
                .fillMaxSize()
                .widthIn(min = 240.dp)
                .background(MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Spacer(modifier = Modifier.height(32.dp))
                ProfileSection(useMobileLayout)
                AboutSection(useMobileLayout)
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    "Professional Skills",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(16.dp))
                SkillsSection(useMobileLayout)
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    "Achievements",
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Spacer(modifier = Modifier.height(16.dp))
                AchievementSection(useMobileLayout)
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Projects",
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Spacer(modifier = Modifier.height(16.dp))
                PortfolioSection(useMobileLayout, { countOfLoadedProjects = 100 }, countOfLoadedProjects)
            }
            item {
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
                            description = "Led engineers to identify root causes and implement scalable technical fixes.\n\n" +
                                    "Streamlined IT issue resolution across classified and unclassified systems, lowering infra complexity and cutting stack costs.\n\n" +
                                    "Re-architected audio/video call features, reducing production costs by over 85% and saving 8%+ of annual company expenses.\n\n" +
                                    "Spearheaded integration with external partners, successfully connecting 3+ platforms including Tokopedia and Juiker."
                        ),
                        WorkExperience(
                            duration = "Nov 2020 - Dec 2021",
                            title = "Project Technical Director",
                            company = "QTCOMM/PT. ICE Messenger Indonesia",
                            description = "Supervised teams across Taiwan and Indonesia, streamlining workflows based on team feedback—improving efficiency by 40%+.\n\n" +
                                    "Led end-to-end product development, delivering mobile app updates, data encryption features, QA testing, and critical bug fixes.\n\n" +
                                    "Owned the IndoChat product roadmap from concept to launch, defining strategy, closing feature gaps, and aligning execution with user needs.\n\n"+
                                    "Built event tracking systems and provided real-time product insights; iterated sign-up flow to achieve 70%+ conversion rate."
                        ),
                        WorkExperience(
                            duration = "Jun 2019 - Nov 2020",
                            title = "Android Team Leader",
                            company = "QTCOMM/PT. ICE Messenger Indonesia",
                            description = "Led Android engineers to deliver features across multiple apps, refining architecture and product design based on evolving specs.\n\n" +
                                    "Authored technical documentation to clearly outline app functionality and operational workflows.\n\n" +
                                    "Oversaw end-to-end development of 3+ Android apps—from research and planning to launch and post-release support.\n\n" +
                                    "Explored and applied emerging Android trends to keep products aligned with market standards.\n\n" +
                                    "Built CI/CD pipelines to automate testing and deployment, cutting manual build time by over 50 minutes."
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
                            description ="Designed and implemented services, applications, and frameworks to power core tech integrations.\n\n" +
                                    "Built software for license plate recognition, enabling seamless fuel payment matching at gas stations.\n\n" +
                                    "Collaborated with backend engineers to resolve issues and ensure a smooth, accurate app launch."
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
                            description = "Developed a proof of concept for Zinwell’s e-commerce app, Small Trade, in collaboration with UI designers and engineers, delivering an innovative, high-quality solution aligned with stakeholder goals."
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
            }
            item {
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
            }
                item {
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