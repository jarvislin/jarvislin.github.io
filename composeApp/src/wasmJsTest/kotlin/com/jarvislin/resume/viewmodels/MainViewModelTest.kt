package com.jarvislin.resume.viewmodels

import com.jarvislin.resume.data.EmailFormatError
import com.jarvislin.resume.data.EmptyMessageError
import com.jarvislin.resume.data.EmptyNameError
import com.jarvislin.resume.repositories.ContactRepository
import dev.mokkery.mock
import dev.mokkery.verify.VerifyMode.Companion.exhaustiveOrder
import dev.mokkery.verifySuspend
import kotlin.test.Test
import kotlin.test.assertEquals

class MainViewModelTest {
    private val repository = mock<ContactRepository>()
    private val viewModel = MainViewModel(repository)

    @Test
    fun `invalid email should trigger EmailFormatError in sendMessage`() {
        viewModel.sendMessage(name = "John", email = "invalid", message = "Hi")
        assertEquals(EmailFormatError, viewModel.uiState.value.messageError)
    }

    @Test
    fun `empty name should trigger EmptyNameError in sendMessage`() {
        viewModel.sendMessage(name = " ", email = "test@example.com", message = "Hello")
        assertEquals(EmptyNameError, viewModel.uiState.value.messageError)
    }

    @Test
    fun `empty message should trigger EmptyMessageError in sendMessage`() {
        viewModel.sendMessage(name = "Alice", email = "test@example.com", message = " ")
        assertEquals(EmptyMessageError, viewModel.uiState.value.messageError)
    }

    @Test
    fun `valid sendMessage should call repository`() {
        viewModel.sendMessage(name = "Alice", email = "test@example.com", message = "Hello")
        verifySuspend(exhaustiveOrder) {
            repository.sendMessage("Alice", "Hello", "test@example.com")
        }
    }

    @Test
    fun `invalid email in saveEmail should set emailError`() {
        viewModel.saveEmail("not-an-email")
        assertEquals(EmailFormatError, viewModel.uiState.value.emailError)
    }

    @Test
    fun `valid saveEmail should call follow`() {
        viewModel.saveEmail("test@example.com")
        verifySuspend {
            repository.follow("test@example.com")
        }
    }

    @Test
    fun `switchTheme toggles dark mode`() {
        val initial = viewModel.uiState.value.useDarkTheme
        viewModel.switchTheme()
        assertEquals(initial.not(), viewModel.uiState.value.useDarkTheme)
    }
}