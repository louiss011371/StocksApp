package com.example.stocksapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stocksapp.R
import com.example.stocksapp.helpers.Api
import com.example.stocksapp.helpers.StockListAdapter
import com.example.stocksapp.model.Stock
import com.example.stocksapp.presenter.IStockListPresenter
import com.example.stocksapp.presenter.StockListPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainFragment : Fragment(), IStockListView {

    override fun onStockListResult(list: List<Stock>?) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.stockList)
        println(recyclerView)
        recyclerView?.hasFixedSize()
        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView?.layoutManager = layoutManager
        val adapter = list?.let { StockListAdapter(it) { list: Stock -> stockItemClicked(list)} }
        recyclerView?.adapter = adapter
    }
    var stockListListPresenter: IStockListPresenter ?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val stockListView : View = inflater.inflate(R.layout.fragment_main, null, false)
        stockListListPresenter = StockListPresenter(this)
        callApi()
        return stockListView
    }

    fun callApi() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://sandbox.iexapis.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        Log.d("api", retrofit.toString())
        val api = retrofit.create(Api::class.java)

        api.fetchAllStock().enqueue(object : Callback<Map<String, Stock>> {
            @SuppressLint("LongLogTag")
            override fun onResponse(
                call: Call<Map<String, Stock>>, response: Response<Map<String, Stock>>
            ) {
                println("called")
                stockListListPresenter?.loadResponse(response)
            }
            override fun onFailure(call: Call<Map<String, Stock>>, t: Throwable) {
            }
        })
    }
    private fun stockItemClicked(stockItem: Stock) {
       Toast.makeText(this.context, stockItem.quote.companyName, Toast.LENGTH_LONG).show()
    }
}
