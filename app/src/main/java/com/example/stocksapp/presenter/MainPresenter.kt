package com.example.stocksapp.presenter

import com.example.stocksapp.model.Stock
import retrofit2.Response

class MainPresenter : MainContract.Presenter{

    private  var view: MainContract.View? = null

    override fun attachView(view: MainContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

//    override fun loadResponse(response: Response<Stock>?){
//        val symbolName = response?.body()?.aapl?.quote?.symbol
//        val openValue = response?.body()?.aapl?.chart?.lastIndex?.let {
//            response.body()?.aapl?.chart?.get(it)?.open
//        }
//        val closeValue = response?.body()?.aapl?.chart?.lastIndex?.let {
//            response.body()?.aapl?.chart?.get(it)?.close
//        }
//        this.view?.onStockLoaded(symbolName.toString(), openValue.toString(),closeValue.toString())
//    }
}