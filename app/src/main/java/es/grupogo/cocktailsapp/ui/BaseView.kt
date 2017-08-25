package com.example.carlosolmedo.kotlinapp.mvp

/**
 * Created by carlosolmedo on 23/8/17.
 */
interface BaseView<T> {

    fun setPresenter(presenter: T)
}