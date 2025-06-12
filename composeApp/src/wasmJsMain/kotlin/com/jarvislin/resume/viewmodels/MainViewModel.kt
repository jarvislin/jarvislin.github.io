package com.jarvislin.resume.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jarvislin.resume.data.AppError
import com.jarvislin.resume.data.EmailFormatError
import com.jarvislin.resume.data.EmptyMessageError
import com.jarvislin.resume.data.EmptyNameError
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
        if (EmailValidator.isValidEmail(email).not()) {
            _uiState.value = uiState.value.copy(messageError = EmailFormatError)
            return
        }
        if (name.trim().isEmpty()) {
            _uiState.value = uiState.value.copy(messageError = EmptyNameError)
            return
        }
        if (message.trim().isEmpty()) {
            _uiState.value = uiState.value.copy(messageError = EmptyMessageError)
            return
        }
        viewModelScope.launch {
            repository.sendMessage(name, message, email)
        }
    }

    fun saveEmail(email: String) {
        if (EmailValidator.isValidEmail(email).not()) {
            _uiState.value = uiState.value.copy(emailError = EmailFormatError)
            return
        }
        viewModelScope.launch { repository.follow(email) }
    }

    fun switchTheme() {
        _uiState.value = uiState.value.copy(useDarkTheme = uiState.value.useDarkTheme.not())
    }
}

data class MainUiState(
    val useDarkTheme: Boolean = true,
    val messageLoading: Boolean = false,
    val messageError: AppError? = null,
    val emailLoading: Boolean = false,
    val emailError: AppError? = null,
)

object EmailValidator {
    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        return emailRegex.matches(email)
    }
}