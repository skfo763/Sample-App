package skfo763.ably.base.extension

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

val ioSchedulers: Scheduler = Schedulers.io()
val mainSchedulers: Scheduler = AndroidSchedulers.mainThread()

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}

fun <T> Observable<T>.baseCompose(): Observable<T> {
    return this.subscribeOn(ioSchedulers)
        .observeOn(mainSchedulers)
}
fun <T> Single<T>.baseCompose(): Single<T> {
    return this.subscribeOn(ioSchedulers)
        .observeOn(mainSchedulers)
}
fun <T> Flowable<T>.baseCompose(): Flowable<T> {
    return this.subscribeOn(ioSchedulers)
        .observeOn(mainSchedulers)
}
fun <T> Observable<T>.bindToLiveData(liveData: MutableLiveData<T>) {
    this.subscribe({ liveData.safeValue = it }) { it.printStackTrace() }
}

fun <T> Single<T>.bindToLiveData(liveData: MutableLiveData<T>): Disposable {
    return this.subscribe({ liveData.safeValue = it }) { it.printStackTrace() }
}