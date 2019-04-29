package io.lamart.reaktive.livedata.interop

import com.badoo.reaktive.disposable.Disposable
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.ObservableObserver
import kotlin.test.assertEquals

fun <T> Observable<T>.test() = TestObservableObserver<T>().also(::subscribe)

class TestObservableObserver<T> : ObservableObserver<T> {

    private val values = mutableListOf<T>()

    override fun onComplete() {
    }

    override fun onError(error: Throwable) {
    }

    override fun onNext(value: T) {
        this.values += value
    }

    override fun onSubscribe(disposable: Disposable) {
    }

    fun assertValues(vararg expected: T) = apply { assertEquals(expected.toList(), values) }

}