package com.example.harvesterapidemo.model.pick

import com.google.gson.annotations.SerializedName

data class Data (

    @SerializedName("pick-lists") val pickLists : List<PickLists>
)