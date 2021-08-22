package com.example.harvesterapidemo.repository

import com.example.harvesterapidemo.api.pick.PickApiHelper
import javax.inject.Inject

class PickRepository @Inject constructor(private val pickApiHelper: PickApiHelper) {

    suspend fun getPickOrderHeaders() = pickApiHelper.getPickOrderHeaders()

}