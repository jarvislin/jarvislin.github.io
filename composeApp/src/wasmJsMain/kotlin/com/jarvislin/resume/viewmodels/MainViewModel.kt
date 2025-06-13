package com.jarvislin.resume.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jarvislin.resume.data.*
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

    fun submit() {
        _uiState.value = uiState.value.copy(error = null) // reset
        if (uiState.value.email.trim().isEmpty()) {
            _uiState.value = uiState.value.copy(error = EmptyEmailError)
            return
        }
        if (EmailValidator.isValidEmail(uiState.value.email).not()) {
            _uiState.value = uiState.value.copy(error = EmailFormatError)
            return
        }
        if (uiState.value.subscribe.not() && uiState.value.message.trim().isEmpty()) {
            _uiState.value = uiState.value.copy(error = EmptyMessageError)
            return
        }

        viewModelScope.launch {
            _uiState.value = uiState.value.copy(loading = true)
            repository.submit(uiState.value.name, uiState.value.email, uiState.value.message, uiState.value.subscribe)
                .onSuccess {
                    _uiState.value = uiState.value.copy(loading = false, showSuccessDialog = true)
                }
                .onFailure {
                    _uiState.value = uiState.value.copy(loading = false, showFailureDialog = true)
                }
        }
    }

    fun switchTheme() {
        _uiState.value = uiState.value.copy(useDarkTheme = uiState.value.useDarkTheme.not())
    }

    fun dismissSuccessDialog() {
        _uiState.value = uiState.value.copy(showSuccessDialog = false)
    }

    fun dismissFailureDialog() {
        _uiState.value = uiState.value.copy(showFailureDialog = false)
    }

    fun onNameChange(name: String) {
        _uiState.value = uiState.value.copy(name = name)
    }

    fun onEmailChange(email: String) {
        _uiState.value = uiState.value.copy(email = email)
    }

    fun onMessageChange(message: String) {
        _uiState.value = uiState.value.copy(message = message)
    }

    fun onSubscribeChange() {
        _uiState.value = uiState.value.copy(subscribe = uiState.value.subscribe.not())
    }
}

data class MainUiState(
    val useDarkTheme: Boolean = true,
    val loading: Boolean = false,
    val error: AppError? = null,
    val showSuccessDialog: Boolean = false,
    val showFailureDialog: Boolean = false,
    val name: String = "",
    val email: String = "",
    val message: String = "",
    val subscribe: Boolean = false,
)

object EmailValidator {
    private val emailRegex = Regex(
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    )

    fun isValidEmail(email: String): Boolean {
        return email.isNotBlank() &&
                email.length <= 254 &&
                !email.contains("..") &&
                !email.startsWith(".") &&
                !email.endsWith(".") &&
                email.count { it == '@' } == 1 &&
                emailRegex.matches(email)
    }
}