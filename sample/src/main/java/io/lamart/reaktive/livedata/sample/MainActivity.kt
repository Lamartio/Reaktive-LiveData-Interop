package io.lamart.reaktive.livedata.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.badoo.reaktive.observable.subscribe
import io.lamart.reaktive.livedata.interop.toObservable
import io.lamart.reaktive.livedata.sample.databinding.MainBinding


class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil
            .inflate<MainBinding>(layoutInflater, R.layout.main, null, false)
            .apply {
                lifecycleOwner = this@MainActivity
                model = viewModel
            }
            .root
            .let(::setContentView)

        viewModel.ticks
            .toObservable(this)
            .subscribe { toast("ticks: $it") }
    }

}
