package com.example.stocksapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.stocksapp.model.Stock
import com.example.stocksapp.presenter.StockListContact
import com.example.stocksapp.presenter.StockListPresenter
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before
import retrofit2.Response

@RunWith(AndroidJUnit4::class)

class StockListPresenterTest {
    private var presenter : StockListPresenter?= null

    @RelaxedMockK
    lateinit var view: StockListContact.stockView
    lateinit var model : Response<Map<String,Stock>>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        presenter = StockListPresenter(view)
    }
//    @Test
//    fun testCallApi() {
//        presenter!!.loadResponse(response = model)
//    }

}