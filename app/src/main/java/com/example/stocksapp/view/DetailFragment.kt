package com.example.stocksapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.stocksapp.R
import com.example.stocksapp.helpers.Api
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
    override fun onStockResult(symbol: String) {
        Toast.makeText(this.context, symbol, Toast.LENGTH_LONG).show()
        println("symbolName.text = (${symbol})")
        symbolName.text = symbol
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun callApi() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://sandbox.iexapis.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        Log.d("api", retrofit.toString())
        val api = retrofit.create(Api::class.java)

        println("no called")

        api.fetchAllStock().enqueue(object : Callback<Map<String, Stock>> {
            @SuppressLint("LongLogTag")
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
