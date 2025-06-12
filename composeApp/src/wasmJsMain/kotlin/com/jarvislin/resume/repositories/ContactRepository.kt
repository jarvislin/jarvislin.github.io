package com.jarvislin.resume.repositories

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.functions.functions
import io.ktor.client.call.*

interface ContactRepository {
    suspend fun sendMessage(name: String, email: String, message: String): Result<Unit>
    suspend fun follow(email: String)
    suspend fun signIn()
}

class ContactRepositoryImpl(supabaseClient: SupabaseClient) : ContactRepository {
    private val auth = supabaseClient.auth
    private val functions = supabaseClient.functions

    override suspend fun follow(email: String) {
        val payload = mapOf("email" to email)
        val response = functions.invoke("store-email", payload)

        if (response.status.value != 200) {
            throw Exception("store-email failed: ${response.body<String>()}")
        }
    }

    override suspend fun sendMessage(name: String, email: String, message: String): Result<Unit> {
        val payload = mapOf(
            "name" to name,
            "email" to email,
            "message" to message
        )

        val response = functions.invoke("store-message", payload)

        return if (response.status.value != 200) {
            Result.failure(Exception("store-message failed: ${response.body<String>()}"))
        } else {
            Result.success(Unit)
        }
    }

    override suspend fun signIn() {
        auth.signInAnonymously()
    }
}