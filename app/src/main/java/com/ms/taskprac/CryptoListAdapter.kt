package com.ms.taskprac

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ms.taskprac.databinding.CryptoDataRowBinding
import com.ms.taskprac.model.response.GetCryptoDataListResp

class CryptoListAdapter(private var cryptoDataList: List<GetCryptoDataListResp.Data?>?): RecyclerView.Adapter<CryptoListAdapter.CryptoViewHolder>() {

    class CryptoViewHolder(val binding: CryptoDataRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        return CryptoViewHolder(CryptoDataRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return cryptoDataList?.size ?: 0
    }
    
    
    @SuppressLint("NotifyDataSetChanged")
    fun setCryptoData(data: List<GetCryptoDataListResp.Data?>?) {
        cryptoDataList = data
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val binding = holder.binding

        with(binding) {
            tvRank.text         = cryptoDataList!![position]?.rank ?: "--"
            tvCryptoName.text   = cryptoDataList!![position]?.name ?: "--"
            tvAmount.text       = "%.2f".format(((cryptoDataList!![position]?.priceUsd) ?: "0").toFloat())
                // convert this into other  currency to.
        }

    }
}