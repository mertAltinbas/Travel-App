package com.example.travel.presentation.screens.trip.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.StarHalf
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.travel.R
import com.example.travel.core.extensions.toSmallString
import com.example.travel.domain.entities.TopTrip
import com.example.travel.presentation.components.IconButtonX
import com.example.travel.presentation.screens.trip.view.subview.TripDetailInfoCard
import com.example.travel.presentation.ui.theme.TravelTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.gson.Gson

class TripDetailActivity : ComponentActivity() {
    var data: TopTrip? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = Gson().fromJson(intent.extras?.getString("data"), TopTrip::class.java)
        setContent {
            TravelTheme {
                val systemUiController = rememberSystemUiController()
                val isDark = isSystemInDarkTheme()
                val background = colors.background
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = background,
                        darkIcons = !isDark
                    )
                }
                Content()
            }
        }
    }

    @Composable
    private fun Content() {
        val configuration = LocalConfiguration.current
        val scrollState = rememberScrollState()
        Column() {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {

                Column(modifier = Modifier.verticalScroll(scrollState)) {
                    AsyncImage(
                        model = data!!.image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height((configuration.screenHeightDp * .55f).dp)
                            .padding(horizontal = 15.dp)
                            .clip(RoundedCornerShape(20.dp))
                    )
                    Column(modifier = Modifier.padding(horizontal = 30.dp, vertical = 15.dp)) {
                        Text(
                            text = data!!.title,
                            style = TextStyle(fontSize = 26.sp, fontWeight = FontWeight.SemiBold)
                        )

                        Row(
                            modifier = Modifier
                                .height(150.dp)
                                .padding(top = 15.dp, bottom = 15.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            TripDetailInfoCard(imageVector = Icons.Outlined.AttachMoney, title = "$" + data!!.price.toString())
                            TripDetailInfoCard(imageVector = Icons.Outlined.LocationOn, title = data!!.time.toString() + "h")
                            TripDetailInfoCard(imageVector = Icons.Outlined.StarHalf, title = data!!.reviewCount.toSmallString())
                        }

                        Text(
                            text = data!!.description,
                            style = TextStyle(color = colors.onSurface.copy(alpha = .6f))
                        )
                    }
                }

                TopAppBar(
                    modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp),
                    backgroundColor = Color.Transparent,
                    elevation = 0.dp,
                    title = {},
                    navigationIcon = {
                        IconButtonX(
                            imageVector = Icons.Rounded.ArrowBack,
                            backgroundColor = colors.background.copy(alpha = .3f),
                            foregroundColor = colors.surface
                        ) {
                            onBackPressedDispatcher.onBackPressed()
                        }
                    },
                    actions = {
                        IconButtonX(
                            imageVector = Icons.Rounded.Bookmark,
                            backgroundColor = colors.background.copy(alpha = .3f),
                            foregroundColor = colors.surface
                        ) {

                        }
                    })
            }

            BottomBar()
        }
    }


    @Composable
    private fun BottomBar() {
        Row(
            modifier = Modifier
                .height(75.dp)
                .padding(horizontal = 30.dp, vertical = 10.dp)
        ) {
            IconButton(modifier = Modifier
                .aspectRatio(1f)
                .clip(
                    RoundedCornerShape(15.dp)
                )
                .background(color = colors.surface), onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Send,
                    contentDescription = null,
                    modifier = Modifier.padding(15.dp),
                    tint = colors.primary
                )
            }

            Spacer(modifier = Modifier.width(15.dp))
            Button(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colors.primary),
                onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.book_now),
                    style = TextStyle(fontWeight = FontWeight.SemiBold, color = colors.onPrimary)
                )
            }
        }
    }
}