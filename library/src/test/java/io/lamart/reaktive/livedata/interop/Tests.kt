package io.lamart.reaktive.livedata.interop

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.badoo.reaktive.observable.observableByEmitter
import com.badoo.reaktive.observable.observableOf
import com.badoo.reaktive.utils.reaktiveUncaughtErrorHandler
import com.jraska.livedata.test
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertSame

class Tests {

    @get:Rule
    val testRule = InstantTaskExecutorRule()
    lateinit var owner: TestLifeCycleOwner

    @Before
    fun before() {
        owner = TestLifeCycleOwner().onResume()
    }

    @After
    fun after() {
        owner.onDestroy()
    }

    @Test
    fun toLiveData() {
        observableOf(1, 2, 3)
            .toLiveData()
            .test()
            .assertValueHistory(1, 2, 3)
    }

    @Test
    fun toLiveDataWithError() {
        val error = Throwable()
        val handler = reaktiveUncaughtErrorHandler

        reaktiveUncaughtErrorHandler = { assertSame(error, it) }
        observableByEmitter<Int> { it.onError(error) }
            .toLiveData()
            .test()
            .assertNoValue()
        reaktiveUncaughtErrorHandler = handler
    }

    @Test
    fun toObservable() {
        val data = MutableLiveData<Int>()
        val observer = data.toObservable(owner).test()

        data.value = 1
        data.value = 2
        data.value = 3

        observer.assertValues(1, 2, 3)
    }
}


