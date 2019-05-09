[![Gitter](https://badges.gitter.im/LiveDataUtils/Reaktive-LiveData-Interop.svg)](https://gitter.im/LiveDataUtils/Reaktive-LiveData-Interop?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)
# Reaktive-LiveData-Interop
Interop for working with LiveData and the [KMP implementation](https://github.com/badoo/Reaktive) of Rx.


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
# License
Copyright 2019 Danny Lamarti

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
