package com.jarvislin.resume.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.jarvislin.resume.data.EmailFormatError
import com.jarvislin.resume.data.EmptyEmailError
import com.jarvislin.resume.data.EmptyMessageError
import com.jarvislin.resume.data.UnknownError
import com.jarvislin.resume.utils.UIConstants.SPACING_DP_12
import com.jarvislin.resume.utils.UIConstants.SPACING_DP_16
import com.jarvislin.resume.viewmodels.MainViewModel
import org.koin.compose.koinInject

@Composable
fun ContactSection() {
    ContactCard(
        modifier = Modifier.padding(horizontal = SPACING_DP_16.dp).widthIn(max = maxWebComponentWidth)
    )
}

@Composable
fun ContactCard(modifier: Modifier = Modifier) {
    val viewModel = koinInject<MainViewModel>()
    val state by viewModel.uiState

    Card(modifier = modifier.fillMaxHeight()) {
        Column(modifier = Modifier.padding(SPACING_DP_16.dp)) {
            Text(
                "Have a question or project in mind? Leave a message — and if you're interested, check the box to get early access to my no-code resume builder.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = state.name,
                onValueChange = { viewModel.onNameChange(it) },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth(),
                supportingText = {}
            )
            OutlinedTextField(
                value = state.email,
                onValueChange = { viewModel.onEmailChange(it) },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = state.error is EmptyEmailError || state.error is EmailFormatError,
                supportingText = {
                    if (state.error is EmptyEmailError || state.error is EmailFormatError) {
                        Text(state.error!!.message)
                    }
                }
            )
            OutlinedTextField(
                value = state.message,
                onValueChange = { viewModel.onMessageChange(it) },
                label = { Text("Message") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 4,
                minLines = 3,
                isError = state.error is EmptyMessageError,
                supportingText = {
                    if (state.error is EmptyMessageError) {
                        Text(state.error!!.message)
                    }
                }
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }) { viewModel.onSubscribeChange() }) {
                Checkbox(
                    checked = state.subscribe,
                    onCheckedChange = { viewModel.onSubscribeChange() }
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text("Notify me when your no-code resume builder is ready.")
            }
            Spacer(modifier = Modifier.height(SPACING_DP_12.dp))
            Button(
                onClick = { viewModel.submit() },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                enabled = state.loading.not()
            ) {
                if (state.loading) {
                    CircularProgressIndicator(Modifier.size(14.dp), strokeWidth = 2.dp)
                } else {
                    Text("Submit")
                }
            }
        }
    }

    if (state.showSuccessDialog) {
        SuccessDialog(
            message = "Thanks for reaching out! I’ll get back to you soon.",
            onDismiss = { viewModel.dismissSuccessDialog() }
        )
    }

    if (state.showFailureDialog) {
        FailureDialog(
            message = UnknownError.message,
            onDismiss = { viewModel.dismissFailureDialog() }
        )
    }
}

@Composable
fun SuccessDialog(
    message: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Success") },
        text = { Text(message) },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}

@Composable
fun FailureDialog(
    message: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Something went wrong") },
        text = { Text(message) },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}