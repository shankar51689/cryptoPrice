package com.ms.taskprac.network

import com.ms.taskprac.model.response.GetCryptoDataListResp
import com.ms.taskprac.model.response.GetCryptoRateListResp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCallInterface {

    @GET("assets")
    suspend fun getCryptoDataList(): Response<GetCryptoDataListResp>

    @GET("rates")
    suspend fun getCryptoRateList(): Response<GetCryptoRateListResp>

}