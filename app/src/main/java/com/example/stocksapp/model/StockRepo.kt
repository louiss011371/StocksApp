package com.example.stocksapp.model

import com.example.stocksapp.helpers.ApiServices
import com.example.stocksapp.helpers.StockApi
import com.example.stocksapp.presenter.StockListContact
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StockRepo: StockListContact.stockModel {
    var list : Collection<Stock> ?=null
    private var apiclient : ApiServices?= null

    override fun loadResponse(response: Response<Map<String, Stock>>?, presenter:StockListContact.stockPresenter) {
        apiclient = StockApi.client.create(ApiServices::class.java)
        val call = apiclient?.fetchAllStock()
        call?.enqueue(object : Callback<Map<String, Stock>> {
            override fun onResponse(
                call: Call<Map<String, Stock>>, response: Response<Map<String, Stock>>
            ) {
                list = (response?.body()?.values)
                presenter.uiAutoUpdate()
            }
            override fun onFailure(call: Call<Map<String, Stock>>, t: Throwable) {
            }
        })
    }

    override fun getStock(): List<Stock>? {
        return list?.toList()
    }
}