package com.jarvislin.resume.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jarvislin.resume.data.About
import com.jarvislin.resume.data.AboutData
import com.jarvislin.resume.utils.NewTabUriHandler
import com.jarvislin.resume.utils.UIConstants
import com.jarvislin.resume.utils.UIConstants.SPACING_DP_16
import com.jarvislin.resume.utils.UIConstants.SPACING_DP_8
import org.jetbrains.compose.resources.painterResource

@Composable
fun AboutSection(useMobileLayout: Boolean) {
    Column(
        modifier = Modifier
            .padding(horizontal = SPACING_DP_16.dp)
            .widthIn(max = maxWebComponentWidth)
    ) {
        val alignModifier = Modifier.align(CenterHorizontally)
        ResumeDownloadButton(alignModifier)
        VerticalSpacer()
        IntroductionText(alignModifier)
        VerticalSpacer()
        AboutPointsGrid(useMobileLayout)
    }
}

@Composable
private fun VerticalSpacer() {
    Spacer(modifier = Modifier.height(UIConstants.SPACING_DP_12.dp))
}

@Composable
private fun ResumeDownloadButton(modifier: Modifier) {
    OutlinedButton(
        onClick = { NewTabUriHandler.openUri(AboutData.RESUME_URL) },
        modifier = modifier.padding(8.dp)
    ) {
        Text("DOWNLOAD RESUME", fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun IntroductionText(modifier: Modifier) {
    Text(
        text = AboutData.INTRO_TEXT,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.secondary,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

@Composable
private fun AboutPointsGrid(useMobileLayout: Boolean) {
    val columns = remember(useMobileLayout) {
        if (useMobileLayout) GridCells.Fixed(1) else GridCells.Adaptive(minSize = 300.dp)
    }

    LazyVerticalGrid(
        columns = columns,
        verticalArrangement = Arrangement.spacedBy(SPACING_DP_8.dp),
        horizontalArrangement = Arrangement.spacedBy(SPACING_DP_8.dp),
        modifier = Modifier.heightIn(max = UIConstants.MAX_GRID_HEIGHT_DP.dp),
        userScrollEnabled = false
    ) {
        items(
            items = AboutData.points,
            key = { it.description.hashCode() }
        ) { aboutPoint ->
            AboutCard(aboutPoint, useMobileLayout)
        }
    }
}

@Composable
fun AboutCard(about: About, useMobileLayout: Boolean) {
    Card {
        Row(
            modifier = Modifier.padding(
                horizontal = UIConstants.SPACING_DP_16.dp,
                vertical = UIConstants.SPACING_DP_12.dp
            )
        ) {
            Icon(painterResource(about.resource), null)
            Spacer(Modifier.width(SPACING_DP_8.dp))
            Text(about.description, minLines = if (useMobileLayout) 1 else 3)
        }
    }
}