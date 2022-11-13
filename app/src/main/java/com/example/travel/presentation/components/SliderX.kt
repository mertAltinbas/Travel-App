package com.example.travel.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalPagerApi::class)
@Composable
fun SliderX(images: List<String>, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(pageCount = images.size);

    Box(modifier = Modifier.then(modifier)) {
        HorizontalPager(state = pagerState) {
            SliderItem(image = images[it])
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = colors.surface,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 15.dp)
        )
    }

}

@Composable
private fun SliderItem(image: String) {
    AsyncImage(
        model = image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
}