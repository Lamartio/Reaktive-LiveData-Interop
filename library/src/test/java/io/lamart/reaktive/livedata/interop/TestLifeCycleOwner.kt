package io.lamart.reaktive.livedata.interop

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

class TestLifeCycleOwner : LifecycleOwner {

    private val registry = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle = registry

    fun onCreate() = apply { registry.currentState = Lifecycle.State.CREATED }
    fun onStart() = apply { registry.currentState = Lifecycle.State.STARTED }
    fun onResume() = apply { registry.currentState = Lifecycle.State.RESUMED }
    fun onDestroy() = apply { registry.currentState = Lifecycle.State.DESTROYED }

}