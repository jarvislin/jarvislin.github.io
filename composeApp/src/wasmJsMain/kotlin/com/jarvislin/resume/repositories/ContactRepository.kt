package com.jarvislin.resume.repositories

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.functions.functions
import io.ktor.client.call.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface ContactRepository {
    suspend fun submit(name: String, email: String, message: String, subscribe: Boolean): Result<Unit>
    suspend fun signIn()
}

class ContactRepositoryImpl(client: SupabaseClient) : ContactRepository {
    private val auth = client.auth
    private val functions = client.functions

    override suspend fun submit(name: String, email: String, message: String, subscribe: Boolean): Result<Unit> {
        val payload = ContactPayload(
            name,
            email,
            message,
            subscribe,
        )
        return runCatching {
            val response = functions.invoke("store-message", payload)
            if (response.status.value != 200) {
                throw Exception("store-message failed: ${response.body<String>()}")
            }
        }
    }

    override suspend fun signIn() {
        auth.signInAnonymously()
    }
}

@Serializable
data class ContactPayload(
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val email: String,
    @SerialName("message")
    val message: String,
    @SerialName("subscribe")
    val subscribe: Boolean
)