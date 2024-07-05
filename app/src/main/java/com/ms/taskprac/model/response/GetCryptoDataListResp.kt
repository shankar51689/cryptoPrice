package com.ms.taskprac.model.response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetCryptoDataListResp(
    @SerializedName("data")
    @Expose
    val `data`: List<Data?>?,
    @SerializedName("timestamp")
    @Expose
    val timestamp: Long?
) {
    data class Data(
        @SerializedName("changePercent24Hr")
        @Expose
        val changePercent24Hr: String?,
        @SerializedName("explorer")
        @Expose
        val explorer: String?,
        @SerializedName("id")
        @Expose
        val id: String?,
        @SerializedName("marketCapUsd")
        @Expose
        val marketCapUsd: String?,
        @SerializedName("maxSupply")
        @Expose
        val maxSupply: String?,
        @SerializedName("name")
        @Expose
        val name: String?,
        @SerializedName("priceUsd")
        @Expose
        var priceUsd: String?,
        @SerializedName("rank")
        @Expose
        val rank: String?,
        @SerializedName("supply")
        @Expose
        val supply: String?,
        @SerializedName("symbol")
        @Expose
        val symbol: String?,
        @SerializedName("volumeUsd24Hr")
        @Expose
        val volumeUsd24Hr: String?,
        @SerializedName("vwap24Hr")
        @Expose
        val vwap24Hr: String?,

        val isClicked: Boolean = false
    )
}