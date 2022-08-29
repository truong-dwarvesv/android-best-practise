package com.df.android

import com.df.android.utils.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@ExperimentalCoroutinesApi
class TestDispatcherProvider : DispatcherProvider {
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    override fun main(): MainCoroutineDispatcher {
        Dispatchers.setMain(testCoroutineDispatcher)
        return Dispatchers.Main
    }

    override fun default(): CoroutineDispatcher = testCoroutineDispatcher
    override fun io(): CoroutineDispatcher = testCoroutineDispatcher
    override fun unconfined(): CoroutineDispatcher = testCoroutineDispatcher

    fun cleanup() {
        Dispatchers.resetMain()
    }
}