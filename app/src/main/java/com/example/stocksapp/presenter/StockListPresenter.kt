package com.example.stocksapp.presenter

import com.example.stocksapp.model.Stock
import com.example.stocksapp.model.StockRepo
import retrofit2.Response

class StockListPresenter(stockView: StockListContact.stockView) : StockListContact.stockPresenter,
    StockListContact {
    private val view : StockListContact.stockView = stockView
    private val model : StockListContact.stockModel = StockRepo()
    private val response : Response<Map<String, Stock>>? = null

    override fun networkcall() {
        model.loadResponse(response,this)
    }

    override fun uiAutoUpdate() {
        view.onStockListResult()
    }

    override fun showStock(): List<Stock>? = model.getStock()
}