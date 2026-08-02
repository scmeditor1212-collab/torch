package com.example.torchvault.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.torchvault.R
import com.example.torchvault.viewmodel.VaultViewModel

@Composable
fun PinSetupScreen(
    viewModel: VaultViewModel,
    onSetupComplete: () -> Unit,
    onBack: () -> Unit
) {
    val title = if (viewModel.isConfirming) "Confirm PIN" else "Create Vault PIN"
    val subtitle = if (viewModel.isConfirming) 
        "Re-enter your 4-digit PIN" 
    else 
        "Set a 4-digit PIN to secure your vault"

    LaunchedEffect(viewModel.pinError) {
        if (viewModel.pinError == null && viewModel.isConfirming && viewModel.confirmPin.length == 4) {
            onSetupComplete()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0F0C29),
                        Color(0xFF302B63),
                        Color(0xFF24243E)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(
                        color = Color(0xFF6366F1).copy(alpha = 0.15f),
                        shape = RoundedCornerShape(24.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_lock),
                    contentDescription = null,
                    tint = Color(0xFF818CF8),
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = title,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = subtitle,
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val currentPin = if (viewModel.isConfirming) viewModel.confirmPin else viewModel.pin
                repeat(4) { index ->
                    val isFilled = index < currentPin.length
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(
                                color = if (isFilled) Color(0xFF818CF8) else Color.Transparent,
                                shape = CircleShape
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = viewModel.pinError ?: "",
                color = Color(0xFFEF4444),
                fontSize = 13.sp,
                modifier = Modifier.height(20.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Numpad(
                onDigitClick = { digit ->
                    if (viewModel.isConfirming) {
                        viewModel.addConfirmDigit(digit)
                    } else {
                        viewModel.addPinDigit(digit)
                    }
                },
                onBackspace = { viewModel.backspaceSetup() }
            )
        }
    }
}

@Composable
fun Numpad(
    onDigitClick: (String) -> Unit,
    onBackspace: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val digits = listOf(
            listOf("1", "2", "3"),
            listOf("4", "5", "6"),
            listOf("7", "8", "9"),
            listOf("", "0", "backspace")
        )

        digits.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                row.forEach { digit ->
                    when (digit) {
                        "" -> Spacer(modifier = Modifier.size(70.dp))
                        "backspace" -> NumpadButton(
                            onClick = onBackspace,
                            isBackspace = true
                        )
                        else -> NumpadButton(
                            onClick = { onDigitClick(digit) },
                            text = digit
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NumpadButton(
    onClick: () -> Unit,
    text: String? = null,
    isBackspace: Boolean = false
) {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.92f else 1f,
        label = "scale"
    )

    Box(
        modifier = Modifier
            .size(70.dp)
            .scale(scale)
            .background(
                color = if (isBackspace) 
                    Color.White.copy(alpha = 0.04f) 
                else 
                    Color.White.copy(alpha = 0.08f),
                shape = CircleShape
            )
            .clickable {
                isPressed = true
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        if (text != null) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )
        } else if (isBackspace) {
            Icon(
                painter = painterResource(id = R.drawable.ic_backspace),
                contentDescription = "Backspace",
                tint = Color.White.copy(alpha = 0.6f),
                modifier = Modifier.size(24.dp)
            )
        }
    }

    LaunchedEffect(isPressed) {
        if (isPressed) {
            kotlinx.coroutines.delay(100)
            isPressed = false
        }
    }
}
