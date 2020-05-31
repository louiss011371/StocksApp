package com.example.stocksapp.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.stocksapp.R
import com.example.stocksapp.helpers.ApiServices
import com.example.stocksapp.model.Stock
import com.example.stocksapp.presenter.DetailStockPresenter
import com.example.stocksapp.presenter.IDetailStockPresenter
import kotlinx.android.synthetic.main.fragment_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailFragment : Fragment(), IStockDetailView {
    override fun onStockDetailResult(symbol: String, latestPrice: String, openPrice: String, closePrice: String) {
        symbolName.text = symbol
        latestPriceText.text = latestPrice
        openPriceText.text = openPrice
        closePriceText.text = closePrice
    }

    var detailPresenter: IDetailStockPresenter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val stockListView: View = inflater.inflate(R.layout.fragment_detail, null, false)
        detailPresenter = DetailStockPresenter(this)
        callApi()
        return stockListView
    }
    fun callApi() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://sandbox.iexapis.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(ApiServices::class.java)
        api.fetchAllStock().enqueue(object : Callback<Map<String, Stock>> {
            override fun onResponse(
                call: Call<Map<String, Stock>>, response: Response<Map<String, Stock>>
            ) {
                println("called")
                detailPresenter?.loadResponse(response)
            }
            override fun onFailure(call: Call<Map<String, Stock>>, t: Throwable) {
            }
        })
    }
}
