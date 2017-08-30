package es.grupogo.cocktailsapp.ui.activities.signup

import com.example.carlosolmedo.kotlinapp.mvp.BasePresenter
import com.example.carlosolmedo.kotlinapp.mvp.BaseView
import es.grupogo.cocktailsapp.domain.Cocktail

/**
 * Created by jorge_cmata on 28/8/17.
 */
interface SignUpContract {

    interface View : BaseView<Presenter> {

        fun handleError(throwable: Throwable)
        fun enterApp()
    }

    interface Presenter : BasePresenter {

        fun doSignUp(username : String, password: String)
    }
}