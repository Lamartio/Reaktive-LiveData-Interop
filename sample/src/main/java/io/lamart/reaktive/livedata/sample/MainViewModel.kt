package io.lamart.reaktive.livedata.sample

import androidx.lifecycle.ViewModel
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.map
import io.lamart.reaktive.livedata.interop.toLiveData

class MainViewModel(ticks: Observable<Int>) : ViewModel() {

    val ticks = ticks.map { it.toString() }.toLiveData()

}