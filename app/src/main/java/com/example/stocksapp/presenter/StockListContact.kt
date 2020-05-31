package com.example.stocksapp.presenter

import com.example.stocksapp.model.Stock
import retrofit2.Response

interface StockListContact {

    interface stockModel {
        fun loadResponse(response: Response<Map<String, Stock>>?, presenter: stockPresenter)
        fun getStock() : List<Stock>?
    }
    interface stockView {
        fun onStockListResult()
    }
    interface stockPresenter {
        fun networkcall()
        fun uiAutoUpdate()
        fun showStock() : List<Stock>?
    }
}