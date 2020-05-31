package com.example.stocksapp.helpers

import com.example.stocksapp.model.Stock
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {
     @GET("/stable/stock/market/batch?symbols=aapl,fb,ibm&types=quote,news,chart&range=1m&last=5&token=Tsk_afaf96462ec94cf28300f2aedce6f9e5")
     fun fetchAllStock() : Call<Map<String,Stock>>
}