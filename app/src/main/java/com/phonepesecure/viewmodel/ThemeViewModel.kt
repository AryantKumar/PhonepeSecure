package com.phonepesecure.viewmodel

import android.content.res.Configuration
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ThemeViewModel : ViewModel() {
    // Null = follow system, true/false = manual override
    val isDarkMode = mutableStateOf<Boolean?>(null)

    fun toggleTheme(currentSystemDark: Boolean) {
        isDarkMode.value = when (isDarkMode.value) {
            null -> !currentSystemDark
            true -> false
            false -> null // Back to system default
        }
    }

    fun resolveDarkMode(systemInDark: Boolean): Boolean {
        return isDarkMode.value ?: systemInDark
    }
}
