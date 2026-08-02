package com.example.torchvault.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.torchvault.R
import com.example.torchvault.viewmodel.VaultViewModel

@Composable
fun PinEntryScreen(
    viewModel: VaultViewModel,
    onPinCorrect: () -> Unit,
    onBack: () -> Unit
) {
    LaunchedEffect(viewModel.enteredPin) {
        if (viewModel.enteredPin.length == 4 && viewModel.pinError == null) {
            onPinCorrect()
        }
    }

    LaunchedEffect(viewModel.pinError) {
        if (viewModel.pinError != null) {
            kotlinx.coroutines.delay(1000)
            viewModel.resetEntry()
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
                text = "Enter Vault PIN",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "Enter your 4-digit PIN to access vault",
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                repeat(4) { index ->
                    val isFilled = index < viewModel.enteredPin.length
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
                onDigitClick = { viewModel.addEntryDigit(it) },
                onBackspace = { viewModel.backspaceEntry() }
            )
        }
    }
}
