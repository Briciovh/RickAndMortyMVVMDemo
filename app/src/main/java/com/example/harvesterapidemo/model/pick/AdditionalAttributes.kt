package com.example.harvesterapidemo.model.pick

import com.google.gson.annotations.SerializedName

data class AdditionalAttributes (

    @SerializedName("EBT") val eBT : Boolean,
    @SerializedName("checkoutStatus") val checkoutStatus : String,
    @SerializedName("fillRate") val fillRate : Int,
    @SerializedName("firstName") val firstName : String,
    @SerializedName("internalStatus") val internalStatus : String,
    @SerializedName("itemQuantity") val itemQuantity : Double,
    @SerializedName("lastName") val lastName : String,
    @SerializedName("numberOfContainers") val numberOfContainers : Int,
    @SerializedName("orderStatus") val orderStatus : String,
    @SerializedName("orderType") val orderType : String,
    @SerializedName("paymentStatus") val paymentStatus : String,
    @SerializedName("pickedPercentage") val pickedPercentage : Int,
    @SerializedName("picker") val picker : String,
    @SerializedName("receivingNode") val receivingNode : Int,
    @SerializedName("restrictedItems") val restrictedItems : Boolean,
    @SerializedName("sellerOrganizationCode") val sellerOrganizationCode : String,
    @SerializedName("serviceCounter") val serviceCounter : Boolean,
    @SerializedName("serviceFees") val serviceFees : String,
    @SerializedName("shipmentNumber") val shipmentNumber : Int,
    @SerializedName("status") val status : String,
    @SerializedName("trackingId") val trackingId : Int,
    @SerializedName("trolleyName") val trolleyName : Int,
    @SerializedName("uiOrderStatus") val uiOrderStatus : String,
    @SerializedName("uniqueItemCount") val uniqueItemCount : Int,
    @SerializedName("visualOrderId") val visualOrderId : String
)