package com.example.stocksapp.helpers

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StockApi {

    companion object {
        private var retrofit: Retrofit? = null
        val client: Retrofit
            get() {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("https://sandbox.iexapis.com")
                        .build()
                }
                return retrofit!!
            }
    }
}