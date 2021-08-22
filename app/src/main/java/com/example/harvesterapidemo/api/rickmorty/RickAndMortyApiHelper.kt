package com.example.harvesterapidemo.api.rickmorty

import com.example.harvesterapidemo.model.rickmorty.CharacterResponse
import com.example.harvesterapidemo.model.rickmorty.Results
import retrofit2.Response

interface RickAndMortyApiHelper {

    suspend fun getCharacters(page: Int): Response<CharacterResponse>

    suspend fun getCharacterById(charId: Int): Response<Results>

}