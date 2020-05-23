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
import com.example.stocksapp.presenter.MainContract
import com.example.stocksapp.presenter.MainPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainFragment : Fragment(), MainContract.View {
    private lateinit var presenter: MainPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val stockListView : View = inflater.inflate(R.layout.fragment_main, null, false)
        Log.d("log on onCreateView","test")
        presenter = MainPresenter().also {
            it.attachView(this)
        }
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
            override fun onResponse(call: Call<Map<String, Stock>>, response: Response<Map<String, Stock>>
            ) {
                println("called")
                //  presenter.loadResponse(response)
                println(Gson().toJson(response))
                println(response.body()?.get("AAPL")?.quote?.companyName)
                println(response.body()?.get("FB")?.quote?.companyName)
                println(response.body()?.get("IBM")?.quote?.companyName)
            }

            override fun onFailure(call: Call<Map<String, Stock>>, t: Throwable) {

            }
        })

//    override fun onStockLoaded(symbolNameStr: String, openValueStr: String, closeValueStr: String) {
//        symbolName?.text = symbolNameStr
//        openValue?.text = openValueStr
//        closeValue?.text = closeValueStr
//        println("openValue.text = (${openValue.text})")
//    }
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
