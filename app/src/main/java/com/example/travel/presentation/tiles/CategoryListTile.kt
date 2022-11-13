package com.example.travel.presentation.tiles

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travel.domain.entities.Category

@Composable
fun CategoryListTile(data: Category, isSelected: Boolean, onTap: () -> Unit) {
    var color = colors.surface
    var textColor = colors.onBackground

    if (isSelected) {
        color = colors.primary
        textColor = colors.surface
    }

    Surface(color = color, shape = RoundedCornerShape(30.dp)) {
        Row(
            modifier = Modifier
                .clickable(onClick = onTap)
                .padding(start = 7.dp, top = 7.dp, bottom = 7.dp, end = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(color = colors.surface, shape = CircleShape) {
                Icon(
                    imageVector = Icons.Rounded.Image,
                    contentDescription = null,
                    tint = colors.onBackground,
                    modifier = Modifier
                        .size(25.dp)
                        .padding(5.dp)
                )
            }
            Spacer(modifier = Modifier.width(7.dp))
            Text(
                text = data.title,
                style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold, color = textColor)
            )
        }
    }
}