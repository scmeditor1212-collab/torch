package com.example.torchvault.navigation

sealed class Screen(val route: String) {
    object Torch : Screen("torch")
    object PinSetup : Screen("pin_setup")
    object PinEntry : Screen("pin_entry")
    object Vault : Screen("vault")
    object AddToVault : Screen("add_to_vault")
}
