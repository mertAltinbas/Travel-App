package com.example.travel.presentation.screens.trip.view.subview

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TripDetailInfoCard(
    imageVector: ImageVector,
    title: String,
    iconColor: Color? = null,
) {
    Surface(
        modifier = Modifier
            .aspectRatio(.7f)
            .clip(CircleShape.copy(all = CornerSize(30.dp)))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                tint = iconColor ?: MaterialTheme.colors.primary,
                modifier = Modifier.size(25.dp)
            )
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
        }
    }
}