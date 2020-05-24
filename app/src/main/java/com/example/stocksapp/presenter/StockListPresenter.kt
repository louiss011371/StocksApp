package com.example.stocksapp.presenter

import com.example.stocksapp.model.Stock
import com.example.stocksapp.view.IStockListView
import retrofit2.Response

class StockListPresenter(val iStockListView: IStockListView) : IStockListPresenter {
    override fun loadResponse(response:Response<Map<String,Stock>>?) {
       val list = (response?.body()?.values)
        iStockListView.onStockListResult(list?.toList())
    }
}

