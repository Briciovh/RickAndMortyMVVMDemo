package com.example.rickandmortydemo.api.rickmorty

import com.example.rickandmortydemo.model.rickmorty.CharacterResponse
import com.example.rickandmortydemo.model.rickmorty.Results
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiService{

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): Response<CharacterResponse>

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ):Response<Results>
}