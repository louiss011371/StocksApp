package com.example.stocksapp.presenter

import com.example.stocksapp.model.Stock
import com.example.stocksapp.view.IStockDetailView
import retrofit2.Response

class DetailStockPresenter(val iStockDetailView: IStockDetailView) : IDetailStockPresenter {
    override fun loadResponse(response: Response<Map<String, Stock>>?){
        val responseBodyIBM = response?.body()?.get("IBM")
        val symbolName = responseBodyIBM?.quote?.companyName
        val latestPrice = responseBodyIBM?.quote?.latestPrice
        val openPrice = responseBodyIBM?.chart?.lastIndex?.let {
            responseBodyIBM?.chart?.get(it)?.open
        }
        val closePrice = responseBodyIBM?.chart?.lastIndex?.let {
            responseBodyIBM?.chart?.get(it)?.close
        }
        iStockDetailView.onStockDetailResult(symbolName.toString(),latestPrice.toString(),
                                             openPrice.toString(),closePrice.toString())
    }
}