package com.example.rickandmortydemo.api.rickmorty

import com.example.rickandmortydemo.model.rickmorty.CharacterResponse
import com.example.rickandmortydemo.model.rickmorty.Results
import retrofit2.Response

interface RickAndMortyApiHelper {

    suspend fun getCharacters(page: Int): Response<CharacterResponse>

    suspend fun getCharacterById(charId: Int): Response<Results>

}