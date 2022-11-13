package com.example.travel.presentation.components

import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun Subtitle(title:String, modifier:Modifier = Modifier) {
    Text(
        text = title,
        textAlign = TextAlign.Start,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = colors.onSurface
        ),
        modifier = Modifier.then(modifier)
    )
}