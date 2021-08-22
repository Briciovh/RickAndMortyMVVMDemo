package com.example.harvesterapidemo.api.pick

import com.example.harvesterapidemo.model.pick.PickOrderHeadersResponse
import retrofit2.Response
import javax.inject.Inject

class PickApiHelperImpl @Inject constructor(private val pickApi: PickApiService) : PickApiHelper {
    override suspend fun getPickOrderHeaders(): Response<PickOrderHeadersResponse> =
        pickApi.getPickOrderHeaders()
}