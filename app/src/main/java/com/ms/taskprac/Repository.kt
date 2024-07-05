package com.ms.taskprac

import com.ms.taskprac.model.response.GetCryptoDataListResp
import com.ms.taskprac.model.response.GetCryptoRateListResp
import com.ms.taskprac.network.RetrofitNetwork
import retrofit2.Response
import java.util.Currency

object Repository {

    suspend fun getCryptoData(): Response<GetCryptoDataListResp> {
        return RetrofitNetwork.create().getCryptoDataList()
    }

    suspend fun getCryptoRateList(): Response<GetCryptoRateListResp> {
        return RetrofitNetwork.create().getCryptoRateList()
    }

}