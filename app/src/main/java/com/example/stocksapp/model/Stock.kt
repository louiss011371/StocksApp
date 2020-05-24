package com.example.stocksapp.model

data class Stock(
    val news: List<News>,
    val chart: List<Chart>,
    val quote:Quote
)
data class News (
    val headline : String
)
data class Chart (
    val date : String,
    val open : Double,
    val close : Double,
    val uHigh: String,
    val uLow: String
)
data class Quote (
    val symbol : String,
    val companyName: String,
    val iexRealtimePrice : Double
)