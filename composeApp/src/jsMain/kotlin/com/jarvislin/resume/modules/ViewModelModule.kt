package com.jarvislin.resume.modules

import com.jarvislin.resume.viewmodels.MainViewModel
import org.koin.dsl.module

val viewModelModule= module {
    single<MainViewModel> { MainViewModel(get()) }
}