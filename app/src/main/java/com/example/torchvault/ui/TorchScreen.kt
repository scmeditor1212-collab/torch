package com.example.torchvault.ui

import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.torchvault.R

@Composable
fun TorchScreen(onLongPressComplete: () -> Unit) {
    val context = LocalContext.current
    var isTorchOn by remember { mutableStateOf(false) }
    var isPressed by remember { mutableStateOf(false) }
    var progress by remember { mutableFloatStateOf(0f) }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(3000, easing = LinearEasing),
        label = "progress"
    )

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
        Box(
            modifier = Modifier
                .size(300.dp)
                .offset(x = (-100).dp, y = (-100).dp)
                .background(Color.White.copy(alpha = 0.03f), CircleShape)
        )
        Box(
            modifier = Modifier
                .size(250.dp)
                .offset(x = 200.dp, y = 500.dp)
                .background(Color(0xFF6366F1).copy(alpha = 0.08f), CircleShape)
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .background(
                        color = Color.White.copy(alpha = 0.08f),
                        shape = CircleShape
                    )
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                isPressed = true
                                progress = 1f
                                val pressStart = System.currentTimeMillis()

                                try {
                                    awaitRelease()
                                    val pressDuration = System.currentTimeMillis() - pressStart
                                    if (pressDuration >= 3000) {
                                        onLongPressComplete()
                                    }
                                } finally {
                                    isPressed = false
                                    progress = 0f
                                }
                            },
                            onTap = {
                                isTorchOn = !isTorchOn
                                toggleTorch(context, isTorchOn)
                            }
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                if (isPressed) {
                    CircularProgressIndicator(
                        progress = { animatedProgress },
                        modifier = Modifier.fillMaxSize(),
                        color = Color(0xFF818CF8),
                        strokeWidth = 3.dp,
                        trackColor = Color.Transparent
                    )
                }

                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .scale(if (isPressed) 0.95f else 1f)
                        .background(
                            color = Color.White.copy(alpha = 0.12f),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_torch),
                        contentDescription = "Torch",
                        modifier = Modifier.size(50.dp),
                        tint = if (isTorchOn) Color(0xFFFCD34D) else Color.White.copy(alpha = 0.9f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = if (isPressed) "Keep holding..." else "Hold for 3s to unlock vault",
                color = if (isPressed) Color(0xFF818CF8) else Color.White.copy(alpha = 0.4f),
                fontSize = 13.sp,
                letterSpacing = 0.5.sp
            )

            Spacer(modifier = Modifier.height(60.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                GlassButton(icon = R.drawable.ic_brightness)
                GlassButton(icon = R.drawable.ic_lock)
                GlassButton(icon = R.drawable.ic_flash)
            }
        }
    }
}

@Composable
private fun GlassButton(icon: Int) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .background(
                color = Color.White.copy(alpha = 0.06f),
                shape = RoundedCornerShape(20.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.White.copy(alpha = 0.5f),
            modifier = Modifier.size(24.dp)
        )
    }
}

private fun toggleTorch(context: Context, on: Boolean) {
    val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    try {
        val cameraId = cameraManager.cameraIdList[0]
        cameraManager.setTorchMode(cameraId, on)
    } catch (e: CameraAccessException) {
        e.printStackTrace()
    }
}
