package com.jarvislin.resume.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jarvislin.resume.utils.NewTabUriHandler
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import resume.composeapp.generated.resources.*

@Composable
fun ProfileSection(useMobileLayout: Boolean) {
    val profile = Profile(
        name = "Jarvis Lin",
        title = "Founder · Developer · Impact Creator",
        email = "admin@jarvislin.com",
        location = "Taipei, Taiwan",
        education = "National Formosa University, Bachelor, Computer Science and Information Engineering",
        avatar = Res.drawable.avatar_16_9,
        links = listOf(
            SocialLink(
                icon = Res.drawable.home,
                contentDescription = "Homepage",
                url = "https://jarvislin.com"
            ),
            SocialLink(
                icon = Res.drawable.github,
                contentDescription = "GitHub",
                url = "https://github.com/jarvislin"
            ),
            SocialLink(
                icon = Res.drawable.linkedin,
                contentDescription = "LinkedIn",
                url = "https://linkedin.com/in/jarvislin"
            )
        )
    )
    val handler = NewTabUriHandler
    if (useMobileLayout) {
        MobileProfileCard(profile, handler)
    } else {
        WebProfileCard(profile, handler)
    }
}

@Composable
fun MobileProfileCard(profile: Profile, handler: UriHandler) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
    ) {
        Image(
            painter = painterResource(profile.avatar),
            contentDescription = profile.name,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.height(12.dp))

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(profile.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
            Text(profile.title, style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(8.dp))

            Row(Modifier.clickable(indication = null, // remove ripple / hover
                interactionSource = remember { MutableInteractionSource() }) {
                NewTabUriHandler.openUri("mailto:${profile.email}")
            }) {
                Icon(painter = painterResource(Res.drawable.mail_24px), contentDescription = "Email")
                Text(
                    profile.email,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row {
                Icon(painter = painterResource(Res.drawable.location_on_24px), contentDescription = "Location")
                Text(
                    profile.location,
                    Modifier.padding(horizontal = 8.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row {
                Icon(painter = painterResource(Res.drawable.school_24px), contentDescription = "School")
                Text(
                    profile.education,
                    Modifier.padding(horizontal = 8.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        HorizontalDivider()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp, bottom = 12.dp),
        ) {
            profile.links.forEachIndexed { index, link ->
                IconButton(
                    onClick = { handler.openUri(link.url) },
                    colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(
                        painter = painterResource(link.icon),
                        contentDescription = link.contentDescription,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                if (index != profile.links.lastIndex) Spacer(Modifier.width(8.dp))
            }
        }
    }
}


@Composable
fun WebProfileCard(profile: Profile, handler: UriHandler) {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp).widthIn(max = maxWebComponentWidth)
            .heightIn(max = 320.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Image(
                painter = painterResource(profile.avatar),
                contentDescription = profile.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .heightIn(max = 320.dp)
                    .fillMaxHeight()
                    .aspectRatio(3f / 4f)
            )
            Column(
                Modifier.padding(horizontal = 32.dp).weight(1f).fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(profile.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
                Text(profile.title, style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    Icon(painter = painterResource(Res.drawable.mail_24px), contentDescription = "Email")
                    Text(
                        profile.email,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row {
                    Icon(painter = painterResource(Res.drawable.location_on_24px), contentDescription = "Location")
                    Text(
                        profile.location,
                        Modifier.padding(horizontal = 8.dp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row {
                    Icon(painter = painterResource(Res.drawable.school_24px), contentDescription = "School")
                    Text(
                        profile.education,
                        Modifier.padding(horizontal = 8.dp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))

                HorizontalDivider()

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                ) {
                    profile.links.forEachIndexed { index, link ->
                        IconButton(
                            onClick = { handler.openUri(link.url) },
                            colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Icon(
                                painter = painterResource(link.icon),
                                contentDescription = link.contentDescription,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                        if (index != profile.links.lastIndex) Spacer(Modifier.width(12.dp))
                    }
                }
            }
        }
    }
}

val maxWebComponentWidth = 840.dp


data class Profile(
    val name: String,
    val title: String,
    val email: String,
    val location: String,
    val education: String,
    val avatar: DrawableResource,
    val links: List<SocialLink>
)

data class SocialLink(
    val icon: DrawableResource,
    val contentDescription: String,
    val url: String
)