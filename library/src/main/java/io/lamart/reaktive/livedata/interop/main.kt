package io.lamart.reaktive.livedata.interop

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import com.badoo.reaktive.disposable.Disposable
import com.badoo.reaktive.disposable.DisposableWrapper
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.observableByEmitter
import com.badoo.reaktive.observable.subscribe
import com.badoo.reaktive.utils.reaktiveUncaughtErrorHandler

fun <T> Observable<T>.toLiveData(
    onError: (error: Throwable) -> Unit = reaktiveUncaughtErrorHandler,
    onNextFactory: (data: MutableLiveData<T>) -> (value: T) -> Unit = { (it::setValue) }
): LiveData<T> = object : MutableLiveData<T>() {

    val observable = this@toLiveData
    var disposable: Disposable? = null

    override fun onActive() {
        super.onActive()
        disposable = observable.subscribe(
            onNext = onNextFactory(this),
            onError = onError
        )
    }

    override fun onInactive() {
        super.onInactive()
        disposable?.dispose()
    }

}

fun <T> LiveData<T>.toObservable(owner: LifecycleOwner): Observable<T> =
    observableByEmitter { emitter ->
        val observer = observe(owner, emitter::onNext)
        val disposable = disposableOf { removeObserver(observer) }

        emitter.setDisposable(disposable)
    }

private fun disposableOf(dispose: () -> Unit) = DisposableWrapper().also { wrapper ->
    wrapper.set(object : Disposable {

        override val isDisposed: Boolean
            get() = wrapper.isDisposed

        override fun dispose() = dispose.invoke()

    })
}

