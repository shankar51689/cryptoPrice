package com.ms.taskprac

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ms.taskprac.databinding.ActivityMainBinding
import com.ms.taskprac.model.response.GetCryptoDataListResp
import com.ms.taskprac.model.response.GetCryptoRateListResp
import com.ms.taskprac.network.ResponseStatus
import currencyConvert


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CryptoViewModel
    private lateinit var mAdapter: CryptoListAdapter
    private lateinit var listAdapter: ArrayAdapter<Any?>

    private var cryptoDataList: List<GetCryptoDataListResp.Data?>? = null
    private var cryptoRateList: List<GetCryptoRateListResp.Data?>? = null

    private var currencyNameList =  ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        setViewModel()
        setRecyclerView()
        setClickListeners()
    }

    private fun setClickListeners() {
        with(binding) {

            listAdapter                     = ArrayAdapter<Any?>(baseContext, R.layout.drop_down_data, currencyNameList as List<Any>)
            currencyListDropDown.adapter    = listAdapter

            currencyListDropDown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View,
                    position: Int,
                    id: Long,
                ) {

                    val currencyData = cryptoRateList?.filter {  it?.symbol == currencyNameList[position] }

                    cryptoDataList?.forEach {
                        it?.let{ data ->
                            if (currencyData?.isNotEmpty() == true) {
                                data.priceUsd =
                                    currencyData[0]?.let { cData ->
                                        currencyConvert((data.priceUsd ?: "0").toDouble(),
                                            cData
                                        ).toString()
                                    }
                            }
                            
                        }
                    }

                    mAdapter.setCryptoData(cryptoDataList)


                    // notify adapter
                    listAdapter.notifyDataSetChanged()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

            notifyChange()
        }
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this)[CryptoViewModel::class.java]

        viewModel.cryptoListLiveData.observe(this, cryptoDataObserver)
        viewModel.cryptoRateListLiveData.observe(this, cryptoRateObserver)

        viewModel.getCryptoData()
    }

    private fun setRecyclerView() {

        mAdapter = CryptoListAdapter(emptyList())

        binding.rvCrytoList.apply {
            layoutManager   = LinearLayoutManager(context)
            adapter         = mAdapter
            scheduleLayoutAnimation()
        }

    }


    // Observers

    private val cryptoDataObserver = Observer<ResponseStatus<List<GetCryptoDataListResp.Data?>?>>{ resp ->
        when(resp) {
            is ResponseStatus.Success -> {
                val dataList    = resp.response
                cryptoDataList  = dataList

                mAdapter.setCryptoData(dataList)
                dataList?.forEach { data ->
                    data?.let {

                    }

                }
            }
            is ResponseStatus.Error -> {
               Toast.makeText(this,"Something Went Wrong!", Toast.LENGTH_LONG).show()
            }
        }


    }

    private val cryptoRateObserver = Observer<ResponseStatus<List<GetCryptoRateListResp.Data?>?>>{ resp ->
        when(resp) {
            is ResponseStatus.Success -> {
                val dataList    = resp.response
                cryptoRateList  = dataList
                currencyNameList

                dataList?.forEach { data ->
                    data?.let {
                        currencyNameList.add(it.symbol ?: "--")
                    }
                }

                listAdapter.notifyDataSetChanged()
            }
            is ResponseStatus.Error -> {
               Toast.makeText(this,"Something Went Wrong!", Toast.LENGTH_LONG).show()
            }
        }


    }

}