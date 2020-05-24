package com.example.stocksapp.presenter

import com.example.stocksapp.model.Stock
import com.example.stocksapp.view.IStockDetailView
import retrofit2.Response

class DetailStockPresenter(internal var iStockDetailView: IStockDetailView) : IDetailStockPresenter {
    override fun loadResponse(response: Response<Map<String, Stock>>?){
        val symbolName = response?.body()?.get("IBM")?.quote?.companyName
        iStockDetailView.onStockResult(symbolName.toString())
    }
}