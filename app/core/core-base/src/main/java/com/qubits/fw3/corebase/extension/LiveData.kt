package com.qubits.fw3.corebase.extension

import androidx.lifecycle.MutableLiveData
import com.qubits.fw3.corebase.coroutines.Completable
import com.qubits.fw3.corebase.coroutines.Result

typealias LiveResult<T> = MutableLiveData<Result<T>>
typealias LiveCompletable = MutableLiveData<Completable>

@JvmName("postCompleteResult")
fun <T> LiveResult<T>.postSuccess(value: T) = postValue(
    Result.OnSuccess(value)
)

@JvmName("postThrowableResult")
fun <T> LiveResult<T>.postThrowable(throwable: Throwable) = postValue(
    Result.OnError(throwable)
)

@JvmName("postLoadingResult")
fun <T> LiveResult<T>.postLoading() = postValue(
    Result.OnLoading()
)

@JvmName("postCancelResult")
fun <T> LiveResult<T>.postCancel() = postValue(
    Result.OnCancel()
)

@JvmName("postEmptyResult")
fun <T> LiveResult<T>.postEmpty() = postValue(
    Result.OnEmpty()
)

/* LiveCompletable */
@JvmName("postCompleteCompletable")
fun LiveCompletable.postComplete() = postValue(
    Completable.OnComplete
)

@JvmName("postThrowableCompletable")
fun LiveCompletable.postThrowable(throwable: Throwable) = postValue(
    Completable.OnError(throwable)
)

@JvmName("postLoadingCompletable")
fun LiveCompletable.postLoading() = postValue(
    Completable.OnLoading
)

@JvmName("postCancelCompletable")
fun LiveCompletable.postCancel() = postValue(
    Completable.OnCancel
)
