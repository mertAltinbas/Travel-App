package com.example.travel.presentation.screens.home.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travel.domain.entities.Category
import com.example.travel.domain.entities.TopTrip
import com.example.travel.domain.repositories.IPlaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository : IPlaceRepository
) : ViewModel() {

    val topTrips = MutableLiveData<List<TopTrip>>(listOf())
    val categories = MutableLiveData<List<Category>>(listOf())
    val sliderImages = MutableLiveData<List<String>>(listOf())

    val selectedCategory = MutableLiveData<Category>(null)

    fun getData(){
        viewModelScope.launch {
//            repository.getHomePage(object: HttpCallBack<HomePageFeed> {
//                override fun onSuccess(data: HomePageFeed) {
//                    topTrips.value = data.topTrips
//                    categories.value = data.categories
//                    sliderImages.value = data.sliderImages
//                }
//
//                override fun onFailure(exception: CustomHttpException) {
//                    Log.d("HttpError", exception.message.toString())
//                }
//            })


            try {
                val data = repository.getHomePage()
                topTrips.value = data.topTrips
                categories.value = data.categories
                sliderImages.value = data.sliderImages
                if(selectedCategory.value == null) selectedCategory.value = data.categories.firstOrNull()
            }catch(e: HttpException) {
                Log.e("HttpError", e.message())
            }catch(e:Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }
}