package com.jarvislin.resume.modules

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.functions.Functions
import org.koin.dsl.module

val dataModule = module {
    single<SupabaseClient> {
        createSupabaseClient(
            supabaseUrl = "https://tlerictpiyfhgquvpbfs.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRsZXJpY3RwaXlmaGdxdXZwYmZzIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2MjMxNTksImV4cCI6MjA2NTE5OTE1OX0.7eI2PGX0BbQQFSt-1_C-_xLKfKf47vi7oRPsvaCcXTE"
        ) {
            install(Auth)
            install(Functions)
        }
    }
}