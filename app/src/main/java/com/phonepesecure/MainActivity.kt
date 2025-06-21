package com.phonepesecure

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.phonepesecure.ui.navigation.AppNavigation
import com.phonepesecure.ui.theme.PhonePeSecureTheme
import com.phonepesecure.viewmodel.ThemeViewModel

class MainActivity : ComponentActivity() {

    private val SMS_PERMISSION_REQUEST_CODE = 101
    private val themeViewModel by viewModels<ThemeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkAndRequestSMSPermissions()

        setContent {
            val systemDark = isSystemInDarkTheme()
            val resolvedDark = themeViewModel.resolveDarkMode(systemDark)

            PhonePeSecureTheme(darkTheme = resolvedDark) {
                AppNavigation(themeViewModel = themeViewModel)
            }
        }
    }

    private fun checkAndRequestSMSPermissions() {
        val receiveSmsPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
        val readSmsPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)

        if (receiveSmsPermission != PackageManager.PERMISSION_GRANTED ||
            readSmsPermission != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.RECEIVE_SMS,
                    Manifest.permission.READ_SMS
                ),
                SMS_PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == SMS_PERMISSION_REQUEST_CODE) {
            if (grantResults.any { it != PackageManager.PERMISSION_GRANTED }) {
                // Optionally show a Toast or Snackbar for denied permission
            }
        }
    }
}
