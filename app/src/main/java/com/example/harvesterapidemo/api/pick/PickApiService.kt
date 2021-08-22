package com.example.harvesterapidemo.api.pick

import com.example.harvesterapidemo.model.pick.PickOrderHeadersResponse
import retrofit2.Response
import retrofit2.http.GET

interface PickApiService {

    //Missing headers and query parameters
    @GET("pick-creation/v2/pick-order-headers")
    suspend fun getPickOrderHeaders(): Response<PickOrderHeadersResponse>
}