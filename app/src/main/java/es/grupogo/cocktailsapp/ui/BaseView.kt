package com.example.carlosolmedo.kotlinapp.mvp

import android.content.Context

/**
 * Created by carlosolmedo on 23/8/17.
 */
interface BaseView<T> {

    fun getContext() : Context
    fun setPresenter(presenter: T)
    fun handleError(t: Throwable)
    fun showLoader()
    fun hideLoader()
}