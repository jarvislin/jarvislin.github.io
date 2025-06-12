package com.jarvislin.resume

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.jarvislin.resume.modules.dataModule
import com.jarvislin.resume.modules.repositoryModule
import com.jarvislin.resume.modules.viewModelModule
import com.jarvislin.resume.ui.darkScheme
import com.jarvislin.resume.ui.lightScheme
import com.jarvislin.resume.ui.screens.MainScreen
import com.jarvislin.resume.viewmodels.MainViewModel
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
fun App() {
    KoinApplication(
        application = {
            modules(dataModule, repositoryModule, viewModelModule)
        }
    ) {
        val viewModel = koinInject<MainViewModel>()
        val state by viewModel.uiState

        viewModel.signIn() // sign in when app launched

        MaterialTheme(colorScheme = if (state.useDarkTheme) darkScheme else lightScheme) {
            MainScreen()
        }
    }
}
