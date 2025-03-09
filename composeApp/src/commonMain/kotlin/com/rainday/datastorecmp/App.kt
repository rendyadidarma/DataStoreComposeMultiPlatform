package com.rainday.datastorecmp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rainday.datastorecmp.di.dataStoreModule
import com.rainday.datastorecmp.di.viewModelModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.KoinApplication
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.context.startKoin

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinContext {
            val viewModel = koinViewModel<AppViewModel>()

            val name by viewModel.name.collectAsStateWithLifecycle()

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    TextField(
                        value = name,
                        onValueChange = viewModel::updateName,
                        label = { Text("Name") }
                    )

                    Button(onClick = viewModel::storeToDataStore, modifier = Modifier.padding(top = 8.dp)) {
                        Text("Store")
                    }
                }
            }
        }
    }
}

fun initKoin(
    config: (KoinApplication.() -> Unit)? = null
) {
    startKoin {
        config?.invoke(this)

        modules(viewModelModule, dataStoreModule)
    }
}