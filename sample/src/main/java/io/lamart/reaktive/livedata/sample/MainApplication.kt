package io.lamart.reaktive.livedata.sample

import android.app.Application
import android.os.Handler
import android.os.Looper
import com.badoo.reaktive.subject.behavior.behaviorSubject
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MainApplication : Application(), ViewModelFactory.Owner {


    private val subject = behaviorSubject(1)
    override val factory = ViewModelFactory {
        viewModel { MainViewModel(subject) }
    }

    init {
        Executors
            .newSingleThreadScheduledExecutor()
            .scheduleAtFixedRate(
                {
                    mainHandler.post {
                        subject.onNext(subject.value + 1)
                    }
                },
                1L,
                1L,
                TimeUnit.SECONDS
            )
    }


}


