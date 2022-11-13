package com.example.travel.presentation.screens.navigation.view_model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NavigationViewModel: ViewModel() {
    val pages = NavigationScreen.Items.list

    val currentPage = MutableLiveData<NavigationScreen>(NavigationScreen.Home)


}

sealed class NavigationScreen(
    val id: String,
    val icon: ImageVector,
){
    object Home: NavigationScreen("home", Icons.Outlined.Home)
    object Search: NavigationScreen("search", Icons.Outlined.Search)
    object Explore: NavigationScreen("explore", Icons.Outlined.Explore)
    object Save: NavigationScreen("save", Icons.Outlined.Bookmark)
    object Profile: NavigationScreen("profile", Icons.Outlined.Person)
    object Items{
        val list= listOf(
            Home, Search, Explore, Save, Profile
        )
    }
}