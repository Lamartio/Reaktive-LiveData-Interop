package io.lamart.reaktive.livedata.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(block: Builder.() -> Unit = {}) : ViewModelProvider.NewInstanceFactory() {

    private val viewModels = Builder()
        .apply(block)
        .viewModels
        .toMap()
    private var params = Params()

    fun withParams(vararg params: Any?): ViewModelFactory =
        also {
            it.params = params.toList().let(::Params)
        }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        try {
            viewModels[modelClass]
                ?.invoke(params) as T
                ?: super.create(modelClass)
        } finally {
            params = Params()
        }

    inner class Builder {

        val viewModels = mutableMapOf<Class<*>, (Params) -> ViewModel>()

        inline fun <reified T : ViewModel> viewModel(crossinline block: (params: Params) -> T) =
            viewModels.set(T::class.java, { block(it) })

    }

    @Suppress("UNCHECKED_CAST")
    data class Params(private val params: List<Any?> = emptyList()) {

        operator fun <T> component1() = get<T>(0)
        operator fun <T> component2() = get<T>(1)
        operator fun <T> component3() = get<T>(2)
        operator fun <T> component4() = get<T>(3)
        operator fun <T> component5() = get<T>(4)
        operator fun <T> component6() = get<T>(5)

        operator fun <T> get(index: Int) = params[index] as T

    }

    interface Owner {

        val factory: ViewModelFactory

    }

}
