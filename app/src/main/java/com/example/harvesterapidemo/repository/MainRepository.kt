package com.example.harvesterapidemo.repository

import com.example.harvesterapidemo.api.rickmorty.RickAndMortyApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val rickAndMortyApiHelper: RickAndMortyApiHelper) {

    suspend fun getCharacters(page: Int) = rickAndMortyApiHelper.getCharacters(page)

    suspend fun getCharacterById(charId: Int) = rickAndMortyApiHelper.getCharacterById(charId)

}