[ ![Download](https://api.bintray.com/packages/lamartio/maven/Reaktive-LiveData-Interop/images/download.svg) ](https://bintray.com/lamartio/maven/Reaktive-LiveData-Interop/_latestVersion)
[![Gitter](https://badges.gitter.im/LiveDataUtils/Reaktive-LiveData-Interop.svg)](https://gitter.im/LiveDataUtils/Reaktive-LiveData-Interop?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)
[![License](https://img.shields.io/hexpm/l/plug.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-Reaktive--LiveData--Interop-green.svg?style=flat )]( https://android-arsenal.com/details/1/7653 )
# Reaktive-LiveData-Interop
Interop for working with LiveData and the [KMP implementation](https://github.com/badoo/Reaktive) of Rx.
``` gradle
implementation "io.lamart.livedata:reaktive-livedata-interop:+"
```

``` Kotlin
fun example(owner: LifecycleOwner) {
    observableOf(1,2,3) // Creates a Reaktive Observable
        .toLiveData() // Converts it to a LiveData
        .toObservable(owner) // Converts it back to an Observable
}

observableOf(1,2,3).toLiveData(
    onError = { error -> }, // custom handler for the Observable.onError
    onNextFactory = { liveData -> (liveData::postValue) } // Default is liveData::setValue
)

```
# License
   Copyright 2019 Danny Lamarti

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
