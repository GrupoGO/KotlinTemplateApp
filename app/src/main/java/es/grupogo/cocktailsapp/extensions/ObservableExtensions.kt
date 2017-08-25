package es.grupogo.cocktailsapp.extensions

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by jorge_cmata on 25/8/17.
 */
inline fun <T> Observable<T>.execute(crossinline codeOk: (T) -> Unit, crossinline codeError: (t: Throwable) -> Unit) {

    this
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe({
                codeOk(it)
            }, {
                codeError(it)
            })

}