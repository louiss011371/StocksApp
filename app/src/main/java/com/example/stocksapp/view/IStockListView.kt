package com.example.stocksapp.view

import com.example.stocksapp.model.Stock

interface IStockListView {
    fun onStockListResult(list: List<Stock>?)
//    fun onStockListResult()
}