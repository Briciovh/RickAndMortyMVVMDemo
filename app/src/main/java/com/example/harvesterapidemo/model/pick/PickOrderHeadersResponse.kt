package com.example.harvesterapidemo.model.pick

import com.google.gson.annotations.SerializedName

data class PickOrderHeadersResponse (
    @SerializedName("data") val data : Data
)
