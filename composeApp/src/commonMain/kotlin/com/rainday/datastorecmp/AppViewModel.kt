package com.rainday.datastorecmp

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppViewModel(
    private val dataStore: DataStore<Preferences>
): ViewModel() {

    private val key = stringPreferencesKey("name")

    private var _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    init {
        viewModelScope.launch {
            dataStore.data.collect { storedData ->
                _name.update {
                    storedData.get(key).orEmpty()
                }
            }
        }
    }

    fun updateName(name: String) = _name.update { name }

    fun storeToDataStore() {
        viewModelScope.launch {
            dataStore.updateData {
                it.toMutablePreferences().apply {
                    set(key, name.value)
                }
            }
        }
    }
}