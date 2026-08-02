package com.example.torchvault.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.torchvault.security.SecurityPreferences

class VaultViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs = SecurityPreferences(application)

    var pin by mutableStateOf("")
    var confirmPin by mutableStateOf("")
    var enteredPin by mutableStateOf("")
    var pinError by mutableStateOf<String?>(null)
    var isConfirming by mutableStateOf(false)

    fun isFirstTime(): Boolean = prefs.isFirstTime()

    fun addPinDigit(digit: String) {
        if (pin.length < 4) {
            pin += digit
            if (pin.length == 4 && !isConfirming) {
                isConfirming = true
            }
        }
    }

    fun addConfirmDigit(digit: String) {
        if (confirmPin.length < 4) {
            confirmPin += digit
            if (confirmPin.length == 4) {
                validatePins()
            }
        }
    }

    fun addEntryDigit(digit: String) {
        if (enteredPin.length < 4) {
            enteredPin += digit
            if (enteredPin.length == 4) {
                validateEntry()
            }
        }
    }

    private fun validatePins() {
        if (pin == confirmPin) {
            prefs.setPin(pin)
            pinError = null
        } else {
            pinError = "PINs do not match"
            resetSetup()
        }
    }

    private fun validateEntry() {
        if (enteredPin == prefs.getPin()) {
            pinError = null
        } else {
            pinError = "Incorrect PIN"
            enteredPin = ""
        }
    }

    fun resetSetup() {
        pin = ""
        confirmPin = ""
        isConfirming = false
    }

    fun resetEntry() {
        enteredPin = ""
        pinError = null
    }

    fun backspaceSetup() {
        if (isConfirming && confirmPin.isNotEmpty()) {
            confirmPin = confirmPin.dropLast(1)
        } else if (!isConfirming && pin.isNotEmpty()) {
            pin = pin.dropLast(1)
        }
    }

    fun backspaceEntry() {
        if (enteredPin.isNotEmpty()) {
            enteredPin = enteredPin.dropLast(1)
        }
    }

    fun clearPin() {
        prefs.clearPin()
        resetSetup()
    }
}
