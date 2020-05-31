package com.example.stocksapp

import com.example.stocksapp.presenter.StockListPresenter
import com.example.stocksapp.view.MainFragment
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @MockK
    lateinit var presenter : StockListPresenter
    @MockK
    lateinit var view : MainFragment

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        presenter = StockListPresenter(view)
    }

    @Test
    fun testOnStockListResult() {
        every { presenter.uiAutoUpdate() } just Runs
        view.onStockListResult()
        verify { presenter.uiAutoUpdate() }
    }

    @After
    fun tearDown() {

    }
}
