package com.example.travel.presentation.screens.navigation.view
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.travel.presentation.screens.home.view.HomeView
import com.example.travel.presentation.screens.home.view_model.HomeViewModel
import com.example.travel.presentation.screens.navigation.view_model.NavigationViewModel
import com.example.travel.presentation.ui.theme.TravelTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagerApi
@AndroidEntryPoint
class NavigationActivity : ComponentActivity() {
    private val viewModel: NavigationViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel.getData()

        setContent {
            TravelTheme() {
                val systemUiController = rememberSystemUiController()
                val isDark = isSystemInDarkTheme()
                val background = colors.background
                SideEffect {
                    systemUiController.setNavigationBarColor(
                        color = background,
                        darkIcons = !isDark
                    )
                }


                Scaffold(bottomBar = {
                    BottomNavigationX()
                }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        HomeView(homeViewModel)
                    }
                }
            }
        }
    }
    @Composable
    private fun BottomNavigationX(){
        val currentPage by viewModel.currentPage.observeAsState()
        BottomNavigation(backgroundColor = colors.background, elevation = 0.dp) {
            Row() {
                viewModel.pages.map { page ->
                    val isSelected = currentPage?.id == page.id
                    val color = if (isSelected) colors.primary else colors.onBackground
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { viewModel.currentPage.value = page }) {
                        Icon(
                            imageVector = page.icon,
                            contentDescription = null,
                            modifier = Modifier.weight(1f),
                            tint = color
                        )
                        Icon(
                            imageVector = Icons.Rounded.Circle,
                            contentDescription = null,
                            modifier = Modifier.size(5.dp),
                            tint = if(isSelected) color else Color.Transparent
                        )
                    }
                }
            }
        }
    }

}

