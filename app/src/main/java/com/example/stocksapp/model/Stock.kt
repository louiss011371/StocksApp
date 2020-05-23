package com.example.stocksapp.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import java.sql.Array


data class Stock(
    val news: List<News>,
    val chart: List<Chart>,
    val quote:Quote
)
data class News (
    val headline : String
//    val summary: String
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
    val companyName: String
)