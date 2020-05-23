package com.example.stocksapp.presenter

import com.example.stocksapp.model.Stock

interface MainContract {
    interface View {
      // fun onStockLoaded(symbolNameStr:String, openValueStr: String, closeValueStr: String)
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
       // fun loadResponse(response: retrofit2.Response<Stock>?)
    }
}