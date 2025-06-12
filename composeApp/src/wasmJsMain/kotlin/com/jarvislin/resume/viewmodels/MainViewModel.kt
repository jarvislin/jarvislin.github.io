package com.jarvislin.resume.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jarvislin.resume.repositories.ContactRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ContactRepository) : ViewModel() {
    private val _uiState = mutableStateOf(MainUiState())
    val uiState: State<MainUiState> = _uiState

    fun signIn() {
        viewModelScope.launch {
            repository.signIn()
        }
    }

    fun sendMessage(name: String, email: String, message: String) {
        viewModelScope.launch {
            repository.sendMessage(name, message, email)
        }
    }

    fun follow(email: String) {
        viewModelScope.launch { repository.follow(email) }
    }
}

data class MainUiState(
    val isLoading: Boolean = false,
    val useDarkTheme: Boolean = true,
    val messageLoading: Boolean = false,
    val messageError: String? = null,
    val emailLoading: Boolean = false,
    val emailError: String? = null,
)