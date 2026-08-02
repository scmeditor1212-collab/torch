package com.example.torchvault.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.torchvault.R

@Composable
fun AddToVaultScreen(onBack: () -> Unit) {
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
                .padding(horizontal = 20.dp)
                .padding(top = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(
                            color = Color.White.copy(alpha = 0.08f),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable { onBack() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_chevron_left),
                        contentDescription = "Back",
                        tint = Color.White.copy(alpha = 0.7f),
                        modifier = Modifier.size(18.dp)
                    )
                }

                Text(
                    text = "Add to Vault",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            AddOption(
                icon = R.drawable.ic_image,
                iconBg = Color(0xFF3B82F6).copy(alpha = 0.15f),
                iconTint = Color(0xFF60A5FA),
                title = "Import Photos",
                subtitle = "From gallery"
            )

            Spacer(modifier = Modifier.height(10.dp))

            AddOption(
                icon = R.drawable.ic_video,
                iconBg = Color(0xFFEF4444).copy(alpha = 0.15f),
                iconTint = Color(0xFFF87171),
                title = "Import Videos",
                subtitle = "From gallery"
            )

            Spacer(modifier = Modifier.height(10.dp))

            AddOption(
                icon = R.drawable.ic_apps,
                iconBg = Color(0xFF8B5CF6).copy(alpha = 0.15f),
                iconTint = Color(0xFFA78BFA),
                title = "Hide Apps",
                subtitle = "Select apps to hide"
            )

            Spacer(modifier = Modifier.height(10.dp))

            AddOption(
                icon = R.drawable.ic_document,
                iconBg = Color(0xFFF59E0B).copy(alpha = 0.15f),
                iconTint = Color(0xFFFBBF24),
                title = "Import Documents",
                subtitle = "PDF, Word, etc."
            )
        }
    }
}

@Composable
private fun AddOption(
    icon: Int,
    iconBg: Color,
    iconTint: Color,
    title: String,
    subtitle: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White.copy(alpha = 0.06f),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { }
            .padding(18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .background(
                    color = iconBg,
                    shape = RoundedCornerShape(14.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = title,
                tint = iconTint,
                modifier = Modifier.size(22.dp)
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = subtitle,
                color = Color.White.copy(alpha = 0.4f),
                fontSize = 12.sp
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_chevron_right),
            contentDescription = null,
            tint = Color.White.copy(alpha = 0.3f),
            modifier = Modifier.size(18.dp)
        )
    }
}
