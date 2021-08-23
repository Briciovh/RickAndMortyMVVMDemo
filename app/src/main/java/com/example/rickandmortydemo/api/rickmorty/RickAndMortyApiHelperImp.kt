package com.example.rickandmortydemo.api.rickmorty

import com.example.rickandmortydemo.model.rickmorty.CharacterResponse
import com.example.rickandmortydemo.model.rickmorty.Results
import retrofit2.Response
import javax.inject.Inject

class RickAndMortyApiHelperImp @Inject constructor(private val rickAndMortyApiService: RickAndMortyApiService) : RickAndMortyApiHelper {

    override suspend fun getCharacters(page: Int): Response<CharacterResponse> = rickAndMortyApiService.getCharacters(page)

    override suspend fun getCharacterById(charId: Int): Response<Results> = rickAndMortyApiService.getCharacterById(charId)
}