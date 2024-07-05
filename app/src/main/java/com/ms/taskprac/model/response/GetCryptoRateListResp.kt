package com.ms.taskprac.model.response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetCryptoRateListResp(
    @SerializedName("data")
    @Expose
    val `data`: List<Data?>?,
    @SerializedName("timeStamp")
    @Expose
    val timeStamp: Long?
) {
    data class Data(
        @SerializedName("currencySymbol")
        @Expose
        val currencySymbol: String?,
        @SerializedName("id")
        @Expose
        val id: String?,
        @SerializedName("rateUsd")
        @Expose
        val rateUsd: String?,
        @SerializedName("symbol")
        @Expose
        val symbol: String?,
        @SerializedName("type")
        @Expose
        val type: String?
    )
}