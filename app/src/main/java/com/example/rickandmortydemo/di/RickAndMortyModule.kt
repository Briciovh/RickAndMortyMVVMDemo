package com.example.rickandmortydemo.di

import com.example.rickandmortydemo.api.rickmorty.RickAndMortyApiHelper
import com.example.rickandmortydemo.api.rickmorty.RickAndMortyApiHelperImp
import com.example.rickandmortydemo.api.rickmorty.RickAndMortyApiService
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