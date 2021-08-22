package com.example.harvesterapidemo.model.pick

import com.google.gson.annotations.SerializedName

data class PickLists (

@SerializedName("id") val id : String,
@SerializedName("locationId") val locationId : Int,
@SerializedName("needBy") val needBy : String,
@SerializedName("selector") val selector : String,
@SerializedName("status") val status : String,
@SerializedName("trackingId") val trackingId : Int,
@SerializedName("additionalAttributes") val additionalAttributes : AdditionalAttributes
)