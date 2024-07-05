package com.ms.taskprac

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ms.taskprac.model.response.GetCryptoDataListResp
import com.ms.taskprac.model.response.GetCryptoRateListResp
import com.ms.taskprac.network.ResponseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CryptoViewModel: ViewModel() {

    var cryptoListLiveData      = MutableLiveData<ResponseStatus<List<GetCryptoDataListResp.Data?>?>>()
    var cryptoRateListLiveData  = MutableLiveData<ResponseStatus<List<GetCryptoRateListResp.Data?>?>>()


    fun getCryptoData() {
        viewModelScope.launch(Dispatchers.IO) {
            val resp1 = Repository.getCryptoData()
            val resp2 = Repository.getCryptoRateList()

            if(resp1.isSuccessful) {
                withContext(Dispatchers.Main) {
                    val body = resp1.body()?.data
                    cryptoListLiveData.postValue(ResponseStatus.Success(body))
                }
            } else {
                cryptoListLiveData.postValue(ResponseStatus.Error(-1))
            }


            if(resp2.isSuccessful) {
                withContext(Dispatchers.Main) {
                    val body = resp2.body()?.data
                    Log.i("TaG","Response--->$body")
                    cryptoRateListLiveData.postValue(ResponseStatus.Success(body))
                }
            } else {
                cryptoRateListLiveData.postValue(ResponseStatus.Error(-1))
            }

        }

    }


}