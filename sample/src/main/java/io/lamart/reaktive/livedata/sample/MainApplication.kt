package io.lamart.reaktive.livedata.sample

import android.app.Application
import com.badoo.reaktive.disposable.Disposable
import com.badoo.reaktive.disposable.DisposableWrapper
import com.badoo.reaktive.observable.*
import com.badoo.reaktive.scheduler.createMainScheduler
import com.badoo.reaktive.subject.behavior.behaviorSubject
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MainApplication : Application(), ViewModelFactory.Owner {


    private val subject = behaviorSubject(1)
    override val factory = ViewModelFactory {
        viewModel { MainViewModel(subject) }
    }

    init {
        ticksObservable()
            .observeOn(createMainScheduler())
            .subscribe(onNext = { subject.onNext(it) })
    }

}

private fun ticksObservable(): Observable<Int> =
    observableByEmitter { emitter ->
        var ticks = 0
        val future = Executors
            .newSingleThreadScheduledExecutor()
            .scheduleAtFixedRate(
                { emitter.onNext(ticks++) },
                1L,
                1L,
                TimeUnit.SECONDS
            )
        val disposable = DisposableWrapper().also {
            it.set(object : Disposable {
                override val isDisposed: Boolean
                    get() = it.isDisposed

                override fun dispose() {
                    if (future.cancel(true)) {
                        it.dispose()
                        emitter.onComplete()
                    }
                }

            })
        }

        emitter.setDisposable(disposable)
    }
