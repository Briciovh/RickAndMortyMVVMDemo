package com.example.harvesterapidemo.di

import com.example.harvesterapidemo.api.rickmorty.RickAndMortyApiHelper
import com.example.harvesterapidemo.api.rickmorty.RickAndMortyApiHelperImp
import com.example.harvesterapidemo.api.rickmorty.RickAndMortyApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RickAndMortyModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): RickAndMortyApiService = retrofit.create(RickAndMortyApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: RickAndMortyApiHelperImp): RickAndMortyApiHelper = apiHelper

}