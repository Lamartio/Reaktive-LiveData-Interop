# Reaktive-LiveData-Interop
Interop for working with LiveData and the [https://github.com/badoo/Reaktive](KMP implementation) of Rx.


``` Kotlin
fun example(owner:LifecycleOwner) {
    observableOf(1,2,3) // Creates a Reaktive Observable
        .toLiveData () // Converts it to a LiveData
        .toObservable(owner) // Converts it back to an Observable
}

observableOf(1,2,3).toLiveData(
    onError = { error -> }, // custom handler for the Observable.onError
    onNextFactory = { liveData -> (liveData::postValue) } // Default is liveData::setValue
)

```
