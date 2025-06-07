package com.jarvislin.resume.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun ContactSection() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    Column(Modifier.padding(horizontal = 16.dp).widthIn(max = maxWebComponentWidth)) {
        TextField(
            name, { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )
        Spacer(Modifier.size(16.dp))
        TextField(
            email, { email = it },
            label = { Text("Email") }, modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(Modifier.size(16.dp))
        TextField(
            message,
            { message = it },
            label = { Text("Message") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3,
            maxLines = 6
        )
        Spacer(Modifier.size(16.dp))
        Button(onClick = { }, modifier = Modifier.padding(horizontal = 16.dp).align(CenterHorizontally)) {
            Text("Submit")
        }
    }
}