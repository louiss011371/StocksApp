package com.example.stocksapp.helpers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stocksapp.R
import com.example.stocksapp.model.Stock
import kotlinx.android.synthetic.main.rows.view.*

class StockListAdapter(private val stockList: List<Stock>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val stockView = LayoutInflater.from(parent.context).inflate(R.layout.rows,parent,false)
        return ViewHolder(stockView)
    }
    override fun getItemCount() = stockList.size

//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val stock = stockList[position]
//        val stockItemView = (holder as StockViewHolder).itemView
//
//        val symbol = stockItemView.findViewById<TextView>(R.id.symbolName)
//        symbol?.text = stock.quote.symbol
//
//        val companyName = stockItemView.findViewById<TextView>(R.id.companyName)
//        companyName?.text = stock.quote.companyName
//
//        val latestPrice = stockItemView.findViewById<TextView>(R.id.latestPrice)
//        latestPrice?.text = stock.quote.iexRealtimePrice.toString()
//    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        stockList[position]?.let { (holder as ViewHolder).bind(it) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(stock: Stock) {
            itemView.symbolNames.text = stock.quote.symbol
            println("itemView.symbolNames.text = ${itemView.symbolNames.text}")
//            itemView.setOnClickListener {
//                listener(movie) // this listener for catching movie which is clicked
//            }
        }
    }

}