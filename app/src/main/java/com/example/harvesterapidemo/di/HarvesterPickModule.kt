package com.example.harvesterapidemo.di

import com.example.harvesterapidemo.api.pick.PickApiHelper
import com.example.harvesterapidemo.api.pick.PickApiHelperImpl
import com.example.harvesterapidemo.api.pick.PickApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HarvesterPickModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): PickApiService = retrofit.create(PickApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: PickApiHelperImpl): PickApiHelper = apiHelper

}