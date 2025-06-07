package com.jarvislin.resume.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun ContactSection() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    Card(Modifier.padding(horizontal = 16.dp).widthIn(max = maxWebComponentWidth)) {
        Spacer(Modifier.height(12.dp))
        Text(
            text = "Consulting Inquiry",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            name, { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            singleLine = true,
        )
        Spacer(Modifier.size(8.dp))
        OutlinedTextField(
            email, { email = it },
            label = { Text("Email") }, modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(Modifier.size(8.dp))
        OutlinedTextField(
            message,
            { message = it },
            label = { Text("Message") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            minLines = 3,
            maxLines = 6
        )
        Spacer(Modifier.size(12.dp))
        Button(onClick = { }, modifier = Modifier.padding(horizontal = 16.dp)) {
            Text("Submit")
        }
        Spacer(Modifier.height(12.dp))
    }
}