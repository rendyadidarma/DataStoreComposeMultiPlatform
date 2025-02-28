package com.rainday.datastorecmp.di

import com.rainday.datastorecmp.createDataStore
import org.koin.core.module.Module
import org.koin.dsl.module

actual val dataStoreModule: Module
    get() = module { single { createDataStore() } }