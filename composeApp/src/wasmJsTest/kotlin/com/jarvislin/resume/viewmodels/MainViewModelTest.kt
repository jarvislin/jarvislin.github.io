package com.jarvislin.resume.viewmodels

import com.jarvislin.resume.data.*
import com.jarvislin.resume.repositories.ContactRepository
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify.VerifyMode.Companion.exhaustiveOrder
import dev.mokkery.verifySuspend
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.test.*

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {
    
    private val repository = mock<ContactRepository>()
    private lateinit var viewModel: MainViewModel

    @BeforeTest
    fun setup() {
        viewModel = MainViewModel(repository)
    }

    @Test
    fun `initial state should be correct`() {
        val initialState = viewModel.uiState.value
        
        assertFalse(initialState.loading)
        assertNull(initialState.error)
        assertFalse(initialState.showSuccessDialog)
        assertFalse(initialState.showFailureDialog)
        assertEquals("", initialState.name)
        assertEquals("", initialState.email)
        assertEquals("", initialState.message)
        assertFalse(initialState.subscribe)
        assertTrue(initialState.useDarkTheme)
    }

    @Test
    fun `switchTheme toggles dark mode`() {
        val initial = viewModel.uiState.value.useDarkTheme
        viewModel.switchTheme()
        assertEquals(initial.not(), viewModel.uiState.value.useDarkTheme)
    }

    @Test
    fun `onNameChange should update name`() {
        val testName = "John Doe"
        viewModel.onNameChange(testName)
        assertEquals(testName, viewModel.uiState.value.name)
    }

    @Test
    fun `onEmailChange should update email`() {
        val testEmail = "test@example.com"
        viewModel.onEmailChange(testEmail)
        assertEquals(testEmail, viewModel.uiState.value.email)
    }

    @Test
    fun `onMessageChange should update message`() {
        val testMessage = "Hello, this is a test message"
        viewModel.onMessageChange(testMessage)
        assertEquals(testMessage, viewModel.uiState.value.message)
    }

    @Test
    fun `onSubscribeChange should toggle subscribe`() {
        val initial = viewModel.uiState.value.subscribe
        viewModel.onSubscribeChange()
        assertEquals(initial.not(), viewModel.uiState.value.subscribe)
    }

    @Test
    fun `submit with empty email should trigger EmailFormatError`() {
        viewModel.onEmailChange("")
        viewModel.onMessageChange("Hi")
        
        viewModel.submit()
        
        assertEquals(EmptyEmailError, viewModel.uiState.value.error)
    }

    @Test
    fun `submit with invalid email should trigger EmailFormatError`() {
        viewModel.onEmailChange("invalid")
        viewModel.onMessageChange("Hi")
        
        viewModel.submit()
        
        assertEquals(EmailFormatError, viewModel.uiState.value.error)
    }

    @Test
    fun `submit with empty message and not subscribed should trigger EmptyMessageError`() {
        viewModel.onEmailChange("test@example.com")
        viewModel.onMessageChange("")
        // subscribe 預設為 false
        
        viewModel.submit()
        
        assertEquals(EmptyMessageError, viewModel.uiState.value.error)
    }

    @Test
    fun `submit with whitespace-only email should trigger EmptyEmailError`() {
        viewModel.onEmailChange("   ")
        viewModel.onMessageChange("Hello")
        
        viewModel.submit()
        
        assertEquals(EmptyEmailError, viewModel.uiState.value.error)
    }

    @Test
    fun `submit with whitespace-only message and not subscribed should trigger EmptyMessageError`() {
        viewModel.onEmailChange("test@example.com")
        viewModel.onMessageChange("   ")
        
        viewModel.submit()
        
        assertEquals(EmptyMessageError, viewModel.uiState.value.error)
    }

    @Test
    fun `submit with valid email and subscribe enabled should succeed even without message`() {
        // 設置訂閱模式
        viewModel.onEmailChange("test@example.com")
        viewModel.onMessageChange("")
        viewModel.onSubscribeChange() // 設為 true
        
        // Mock 成功回應
        everySuspend { repository.submit(any(), any(), any(), any()) } returns Result.success(Unit)
        
        viewModel.submit()
        
        assertNull(viewModel.uiState.value.error)
        assertFalse(viewModel.uiState.value.loading)
        assertTrue(viewModel.uiState.value.showSuccessDialog)
        
        verifySuspend(exhaustiveOrder) {
            repository.submit("", "test@example.com", "", true)
        }
    }

    @Test
    fun `valid submit should call repository with correct parameters`()  {
        viewModel.onNameChange("Alice")
        viewModel.onEmailChange("test@example.com")
        viewModel.onMessageChange("Hello")
        
        everySuspend { repository.submit(any(), any(), any(), any()) } returns Result.success(Unit)
        
        viewModel.submit()
        
        verifySuspend(exhaustiveOrder) {
            repository.submit("Alice", "test@example.com", "Hello", false)
        }
        
        assertNull(viewModel.uiState.value.error)
        assertTrue(viewModel.uiState.value.showSuccessDialog)
        assertFalse(viewModel.uiState.value.loading)
    }

    @Test
    fun `submit failure should show failure dialog`() {
        viewModel.onEmailChange("test@example.com")
        viewModel.onMessageChange("Test message")
        
        everySuspend { repository.submit(any(), any(), any(), any()) } returns Result.failure(Exception("Test failure"))
        
        viewModel.submit()
        
        assertNull(viewModel.uiState.value.error)
        assertFalse(viewModel.uiState.value.loading)
        assertFalse(viewModel.uiState.value.showSuccessDialog)
        assertTrue(viewModel.uiState.value.showFailureDialog)
    }

    @Test
    fun `submit should show loading state during execution`()  {
        viewModel.onEmailChange("test@example.com")
        viewModel.onMessageChange("Test")
        
        everySuspend { repository.submit(any(), any(), any(), any()) } returns Result.success(Unit)
        
        viewModel.submit()
        
        assertFalse(viewModel.uiState.value.loading)
        assertTrue(viewModel.uiState.value.showSuccessDialog)
    }

    @Test
    fun `submit should reset error before validation`() {
        // 先觸發一個錯誤
        viewModel.onEmailChange("")
        viewModel.submit()
        assertEquals(EmptyEmailError, viewModel.uiState.value.error)
        
        // 修正 email 但設置新的錯誤條件
        viewModel.onEmailChange("invalid-email")
        viewModel.submit()
        
        // 應該顯示新的錯誤
        assertEquals(EmailFormatError, viewModel.uiState.value.error)
    }

    // Dialog 關閉測試
    @Test
    fun `dismissSuccessDialog should hide success dialog`()  {
        viewModel.onEmailChange("test@example.com")
        viewModel.onMessageChange("Test")
        
        everySuspend { repository.submit(any(), any(), any(), any()) } returns Result.success(Unit)
        viewModel.submit()
        
        assertTrue(viewModel.uiState.value.showSuccessDialog)
        
        viewModel.dismissSuccessDialog()
        assertFalse(viewModel.uiState.value.showSuccessDialog)
    }

    @Test
    fun `dismissFailureDialog should hide failure dialog`() {
        viewModel.onEmailChange("test@example.com")
        viewModel.onMessageChange("Test")
        
        everySuspend { repository.submit(any(), any(), any(), any()) } returns Result.failure(Exception())
        viewModel.submit()
        
        assertTrue(viewModel.uiState.value.showFailureDialog)
        
        viewModel.dismissFailureDialog()
        assertFalse(viewModel.uiState.value.showFailureDialog)
    }

    // signIn 測試
    @Test
    fun `signIn should call repository signIn`()  {
        viewModel.signIn()
        
        verifySuspend {
            repository.signIn()
        }
    }
}

// EmailValidator 測試
class EmailValidatorTest {
    
    @Test
    fun `valid emails should return true`() {
        val validEmails = listOf(
            "test@example.com",
            "user.name@domain.co.uk",
            "user+tag@example.org",
            "user_name@example-domain.com",
            "123@example.com",
            "test.email@subdomain.example.com"
        )
        
        validEmails.forEach { email ->
            assertTrue(EmailValidator.isValidEmail(email), "Email should be valid: $email")
        }
    }
    
    @Test
    fun `invalid emails should return false`() {
        val invalidEmails = listOf(
            "",
            "invalid",
            "@example.com",
            "test@",
            "test.example.com", 
            "test@example",
            "test..test@example.com",
            "test@example..com",
            "test space@example.com"
        )
        
        invalidEmails.forEach { email ->
            assertFalse(EmailValidator.isValidEmail(email), "Email should be invalid: $email")
        }
    }
}