package com.rainday.datastorecmp.di

import com.rainday.datastorecmp.AppViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { AppViewModel(get()) }
}
