package com.example.harvesterapidemo.api.pick

import com.example.harvesterapidemo.model.pick.PickOrderHeadersResponse
import retrofit2.Response

interface PickApiHelper {
    suspend fun getPickOrderHeaders(): Response<PickOrderHeadersResponse>
}