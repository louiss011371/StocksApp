package com.example.stocksapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.stocksapp.R
import com.example.stocksapp.helpers.Api
import com.example.stocksapp.model.Stock
import com.example.stocksapp.presenter.IStockPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainFragment : Fragment(), IStockDetailView{

    internal lateinit var stockPresenter: IStockPresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val stockListView : View = inflater.inflate(R.layout.fragment_main, null, false)

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

        println("no called")

        api.fetchAllStock().enqueue(object : Callback<Map<String, Stock>> {
            @SuppressLint("LongLogTag")
            override fun onResponse(
                call: Call<Map<String, Stock>>, response: Response<Map<String, Stock>>
            ) {
                println("called")
                println("Main + ${response.body()?.get("AAPL")?.quote?.companyName}")
            }
            override fun onFailure(call: Call<Map<String, Stock>>, t: Throwable) {

            }
        })
    }

    override fun onStockResult(symbol: String) {
        // show symbolName -> symbolName Text
    }


//        val recyclerView = view?.findViewById<RecyclerView>(R.id.stockList)
//        println(recyclerView)
//        recyclerView?.hasFixedSize()
//        val layoutManager = LinearLayoutManager(this.context)
//        layoutManager.orientation = LinearLayoutManager.VERTICAL
//        recyclerView?.layoutManager = layoutManager
//        adapter = StockListAdapter(stockList)
//        recyclerView?.adapter = adapter

}
