package com.example.torchvault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.torchvault.navigation.Screen
import com.example.torchvault.ui.*
import com.example.torchvault.ui.theme.TorchVaultTheme
import com.example.torchvault.viewmodel.VaultViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TorchVaultTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val viewModel: VaultViewModel = viewModel()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.Torch.route,
                        enterTransition = {
                            slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Left,
                                animationSpec = tween(300)
                            )
                        },
                        exitTransition = {
                            slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.Left,
                                animationSpec = tween(300)
                            )
                        },
                        popEnterTransition = {
                            slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Right,
                                animationSpec = tween(300)
                            )
                        },
                        popExitTransition = {
                            slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.Right,
                                animationSpec = tween(300)
                            )
                        }
                    ) {
                        composable(Screen.Torch.route) {
                            TorchScreen(
                                onLongPressComplete = {
                                    if (viewModel.isFirstTime()) {
                                        navController.navigate(Screen.PinSetup.route)
                                    } else {
                                        navController.navigate(Screen.PinEntry.route)
                                    }
                                }
                            )
                        }

                        composable(Screen.PinSetup.route) {
                            PinSetupScreen(
                                viewModel = viewModel,
                                onSetupComplete = {
                                    navController.navigate(Screen.Vault.route) {
                                        popUpTo(Screen.Torch.route) { inclusive = false }
                                    }
                                },
                                onBack = { navController.popBackStack() }
                            )
                        }

                        composable(Screen.PinEntry.route) {
                            PinEntryScreen(
                                viewModel = viewModel,
                                onPinCorrect = {
                                    navController.navigate(Screen.Vault.route) {
                                        popUpTo(Screen.Torch.route) { inclusive = false }
                                    }
                                },
                                onBack = { navController.popBackStack() }
                            )
                        }

                        composable(Screen.Vault.route) {
                            VaultScreen(
                                onLogout = {
                                    navController.navigate(Screen.Torch.route) {
                                        popUpTo(0) { inclusive = true }
                                    }
                                },
                                onAddToVault = {
                                    navController.navigate(Screen.AddToVault.route)
                                }
                            )
                        }

                        composable(Screen.AddToVault.route) {
                            AddToVaultScreen(
                                onBack = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}
