package com.example.travel.di

import com.example.travel.domain.repositories.IPlaceRepository
import com.example.travel.infrastructure.data_source.place.LocalPlaceDataSource
import com.example.travel.infrastructure.data_source.place.RemotePlaceDataSource
import com.example.travel.infrastructure.repositories.PlaceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    @ViewModelScoped
    fun providePlaceRepository() : IPlaceRepository = PlaceRepository(LocalPlaceDataSource(), RemotePlaceDataSource.getInstance())

}