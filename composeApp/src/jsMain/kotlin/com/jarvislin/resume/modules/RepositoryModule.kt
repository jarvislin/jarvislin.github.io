package com.jarvislin.resume.modules

import com.jarvislin.resume.repositories.ContactRepository
import com.jarvislin.resume.repositories.ContactRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<ContactRepository> { ContactRepositoryImpl(get()) }
}