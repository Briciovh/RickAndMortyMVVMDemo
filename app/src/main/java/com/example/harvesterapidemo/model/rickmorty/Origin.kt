package com.example.harvesterapidemo.model.rickmorty

import com.google.gson.annotations.SerializedName

data class Origin(

    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)