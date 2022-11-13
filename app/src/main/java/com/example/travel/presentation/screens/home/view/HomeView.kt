package com.example.travel.presentation.screens.home.view

import android.content.Intent
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.GridView
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travel.R
import com.example.travel.presentation.components.IconButtonX
import com.example.travel.presentation.components.SliderX
import com.example.travel.presentation.components.Subtitle
import com.example.travel.presentation.screens.home.view_model.HomeViewModel
import com.example.travel.presentation.screens.trip.view.TripDetailActivity
import com.example.travel.presentation.tiles.CategoryListTile
import com.example.travel.presentation.tiles.TopTripListTile
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.gson.Gson


@Composable
fun HomeView(viewModel: HomeViewModel) {
    val systemUiController = rememberSystemUiController()
    val isDark = isSystemInDarkTheme()
    val background = colors.background
    SideEffect {
        systemUiController.setStatusBarColor(
            color = background,
            darkIcons = !isDark
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 15.dp),
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                title = {},
                navigationIcon = {
                    IconButtonX(imageVector = Icons.Rounded.GridView)
                },
                actions = {
                    IconButtonX(imageVector = Icons.Rounded.Notifications)
                })
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {

            Slider(viewModel)

            Subtitle(
                title = stringResource(id = R.string.categories),
                modifier = Modifier.padding(15.dp)
            )

            Categories(viewModel)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 15.dp)
            ) {
                Subtitle(
                    title = stringResource(id = R.string.top_trips), modifier = Modifier
                        .weight(1f)
                )
                TextButton(
                    colors = ButtonDefaults.textButtonColors(contentColor = colors.onBackground),
                    contentPadding = PaddingValues(0.dp),
                    onClick = { /*TODO*/ }) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(id = R.string.explore),
                            style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Icon(
                            imageVector = Icons.Rounded.ArrowForwardIos,
                            contentDescription = null,
                            modifier = Modifier.size(10.dp)
                        )
                    }
                }
            }
            TopTrips(viewModel)
        }
    }
}

@Composable
private fun Slider(viewModel: HomeViewModel) {
    val sliderImages by viewModel.sliderImages.observeAsState()
    if (sliderImages!!.isNotEmpty())
        SliderX(
            images = sliderImages!!,
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 15.dp)
                .aspectRatio(1.75f)
                .border(width = 5.dp, color = colors.surface, shape = RoundedCornerShape(15.dp))
                .clip(shape = RoundedCornerShape(15.dp))

        )
}


@Composable
private fun Categories(viewModel: HomeViewModel) {
    val categories by viewModel.categories.observeAsState()
    val selectedCategory by viewModel.selectedCategory.observeAsState()

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 15.dp),
        content = {
            items(categories!!) { data ->
                val isSelected = selectedCategory?.id == data.id
                CategoryListTile(data = data, isSelected = isSelected) {
                    if (!isSelected) viewModel.selectedCategory.value = data
                }
            }
        })
}

@Composable
private fun TopTrips(viewModel: HomeViewModel) {
    val topTrips by viewModel.topTrips.observeAsState()
    val context = LocalContext.current

    LazyVerticalGrid(
        userScrollEnabled = false,
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        contentPadding = PaddingValues(horizontal = 15.dp),
        content = {
            items(topTrips!!) { data ->
                TopTripListTile(data) {
                    val intent = Intent(context, TripDetailActivity::class.java)
                    intent.putExtra("data", Gson().toJson(data))
                    context.startActivity(intent)
                }
            }
        })

}
