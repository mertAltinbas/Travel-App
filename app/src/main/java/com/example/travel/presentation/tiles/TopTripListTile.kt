package com.example.travel.presentation.tiles
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.travel.domain.entities.TopTrip


@Composable
fun TopTripListTile(data: TopTrip, onTap: (() -> Unit) = { }) {
    val imagerPainter = rememberAsyncImagePainter(model = data.image)
    Surface(
        modifier = Modifier
            .aspectRatio(.75f),
        shape = RoundedCornerShape(corner = CornerSize(20.dp))
    )
    {
        Column(modifier = Modifier
            .clickable(onClick = onTap)
            .padding(8.dp)
        ){
            Image(
                painter = imagerPainter,
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(1.2f)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.padding(horizontal = 5.dp)) {
                Text(
                    text = data.title,
                    style = MaterialTheme.typography.subtitle1.copy(color = Color.Black),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = data.category,
                    style = MaterialTheme.typography.body2.copy(color = Color.Gray),
                    maxLines = 1,
                    fontSize = 12.sp,
                )
                Spacer(modifier = Modifier.weight(1.5f))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                    Text(
                        text = "$"+data.price.toString(),
                        textAlign = TextAlign.Start,
                        color = colors.primary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )


                        Icon(
                            imageVector = if (data.isLiked) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                            contentDescription = "favorite",
                            modifier = Modifier.size(15.dp),
                            tint = colors.primary

                        )

                }
            }
        }
    }
}