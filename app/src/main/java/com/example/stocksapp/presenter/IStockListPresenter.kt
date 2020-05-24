package com.example.stocksapp.presenter

import com.example.stocksapp.model.Stock
import retrofit2.Response

interface IStockListPresenter {
    fun loadResponse(response: Response<Map<String, Stock>>?)

}