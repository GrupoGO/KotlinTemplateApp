package es.grupogo.cocktailsapp.ui.activities.main

import com.example.carlosolmedo.kotlinapp.mvp.BasePresenter
import com.example.carlosolmedo.kotlinapp.mvp.BaseView

/**
 * Created by jorge_cmata on 28/8/17.
 */
interface MainContract {

    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter {


    }
}