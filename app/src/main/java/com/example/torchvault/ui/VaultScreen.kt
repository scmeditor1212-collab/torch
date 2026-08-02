package com.example.torchvault.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.torchvault.R

@Composable
fun VaultScreen(
    onLogout: () -> Unit,
    onAddToVault: () -> Unit
) {
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
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
                .padding(top = 16.dp, bottom = 30.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "My Vault",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Secure & encrypted",
                        color = Color.White.copy(alpha = 0.4f),
                        fontSize = 13.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(
                            color = Color.White.copy(alpha = 0.08f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clickable { onLogout() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_logout),
                        contentDescription = "Logout",
                        tint = Color.White.copy(alpha = 0.7f),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            GlassCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Storage Used",
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "4.2 GB / 16 GB",
                            color = Color(0xFF818CF8),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    LinearProgressIndicator(
                        progress = { 0.26f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = Color(0xFF6366F1),
                        trackColor = Color.White.copy(alpha = 0.05f)
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                VaultCategoryCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.ic_image,
                    iconBg = Color(0xFF3B82F6).copy(alpha = 0.15f),
                    iconTint = Color(0xFF60A5FA),
                    title = "Photos",
                    count = "128 items"
                )
                VaultCategoryCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.ic_video,
                    iconBg = Color(0xFFEF4444).copy(alpha = 0.15f),
                    iconTint = Color(0xFFF87171),
                    title = "Videos",
                    count = "42 items"
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                VaultCategoryCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.ic_audio,
                    iconBg = Color(0xFF10B981).copy(alpha = 0.15f),
                    iconTint = Color(0xFF34D399),
                    title = "Audio",
                    count = "67 items"
                )
                VaultCategoryCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.ic_document,
                    iconBg = Color(0xFFF59E0B).copy(alpha = 0.15f),
                    iconTint = Color(0xFFFBBF24),
                    title = "Documents",
                    count = "23 items"
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            GlassCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .background(
                                        color = Color(0xFF8B5CF6).copy(alpha = 0.15f),
                                        shape = RoundedCornerShape(14.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_apps),
                                    contentDescription = null,
                                    tint = Color(0xFFA78BFA),
                                    modifier = Modifier.size(22.dp)
                                )
                            }

                            Column {
                                Text(
                                    text = "Hidden Apps",
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = "5 apps hidden",
                                    color = Color.White.copy(alpha = 0.4f),
                                    fontSize = 12.sp
                                )
                            }
                        }

                        Icon(
                            painter = painterResource(id = R.drawable.ic_chevron_right),
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.4f),
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        repeat(5) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(
                                        color = Color.White.copy(alpha = 0.08f),
                                        shape = RoundedCornerShape(14.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = listOf("📷", "💬", "📁", "🎵", "📧")[it],
                                    fontSize = 20.sp
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Color(0xFF6366F1), Color(0xFF8B5CF6))
                            ),
                            shape = CircleShape
                        )
                        .clickable { onAddToVault() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = "Add",
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun GlassCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = Color.White.copy(alpha = 0.06f),
                shape = RoundedCornerShape(24.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
private fun VaultCategoryCard(
    modifier: Modifier = Modifier,
    icon: Int,
    iconBg: Color,
    iconTint: Color,
    title: String,
    count: String
) {
    Box(
        modifier = modifier
            .background(
                color = Color.White.copy(alpha = 0.06f),
                shape = RoundedCornerShape(24.dp)
            )
            .clickable { }
            .padding(20.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = iconBg,
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = title,
                    tint = iconTint,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title,
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = count,
                color = Color.White.copy(alpha = 0.4f),
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}
