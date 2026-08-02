package com.example.torchvault.security

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SecurityPreferences(context: Context) {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        "vault_secure_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    companion object {
        private const val KEY_PIN = "vault_pin"
        private const val KEY_IS_FIRST_TIME = "is_first_time"
        private const val KEY_BIOMETRIC_ENABLED = "biometric_enabled"
    }

    fun setPin(pin: String) {
        prefs.edit().putString(KEY_PIN, pin).apply()
        prefs.edit().putBoolean(KEY_IS_FIRST_TIME, false).apply()
    }

    fun getPin(): String? = prefs.getString(KEY_PIN, null)

    fun isFirstTime(): Boolean = prefs.getBoolean(KEY_IS_FIRST_TIME, true)

    fun clearPin() {
        prefs.edit().remove(KEY_PIN).apply()
        prefs.edit().putBoolean(KEY_IS_FIRST_TIME, true).apply()
    }

    fun setBiometricEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_BIOMETRIC_ENABLED, enabled).apply()
    }

    fun isBiometricEnabled(): Boolean = prefs.getBoolean(KEY_BIOMETRIC_ENABLED, false)
}
