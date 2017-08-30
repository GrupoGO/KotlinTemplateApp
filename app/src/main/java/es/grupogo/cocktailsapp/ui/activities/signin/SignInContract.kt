package es.grupogo.cocktailsapp.ui.activities.signin

import com.example.carlosolmedo.kotlinapp.mvp.BasePresenter
import com.example.carlosolmedo.kotlinapp.mvp.BaseView
import es.grupogo.cocktailsapp.domain.Cocktail

/**
 * Created by jorge_cmata on 28/8/17.
 */
interface SignInContract {

    interface View : BaseView<Presenter> {

        fun handleError(throwable: Throwable)
        fun enterApp()
    }

    interface Presenter : BasePresenter {

        fun doSignIn(username : String, password: String)
    }
}