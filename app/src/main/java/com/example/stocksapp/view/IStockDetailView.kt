package com.example.stocksapp.view

interface IStockDetailView {
    fun onStockDetailResult(symbol: String, latestPrice:String, openPrice: String, closePrice: String)
}