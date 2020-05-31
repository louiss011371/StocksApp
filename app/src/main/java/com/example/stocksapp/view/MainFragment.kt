package com.example.stocksapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stocksapp.R
import com.example.stocksapp.helpers.StockListAdapter
import com.example.stocksapp.model.Stock
import com.example.stocksapp.presenter.StockListContact
import com.example.stocksapp.presenter.StockListPresenter


class MainFragment : Fragment(), StockListContact.stockView  {

    override fun onStockListResult() {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.stockList)
        val list = stockListListPresenter?.showStock()
        println(recyclerView)
        recyclerView?.hasFixedSize()
        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView?.layoutManager = layoutManager
        val adapter = list?.let { StockListAdapter(it) { list: Stock -> stockItemClicked(list)} }
        recyclerView?.adapter = adapter
    }
    var stockListListPresenter: StockListPresenter ?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val stockListView : View = inflater.inflate(R.layout.fragment_main, null, false)
        stockListListPresenter = StockListPresenter(this)
        callApi()
        return stockListView
    }

    fun callApi() {
        stockListListPresenter?.networkcall()
    }
    private fun stockItemClicked(stockItem: Stock) {
       Toast.makeText(this.context, stockItem.quote.companyName, Toast.LENGTH_LONG).show()
    }
}
