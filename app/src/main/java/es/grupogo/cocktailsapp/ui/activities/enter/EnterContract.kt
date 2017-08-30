package es.grupogo.cocktailsapp.ui.activities.enter

import com.example.carlosolmedo.kotlinapp.mvp.BasePresenter
import com.example.carlosolmedo.kotlinapp.mvp.BaseView
import es.grupogo.cocktailsapp.domain.Cocktail

/**
 * Created by jorge_cmata on 28/8/17.
 */
interface EnterContract {

    interface View : BaseView<Presenter> {

        fun goToSignIn()
        fun goToSignUp()
    }

    interface Presenter : BasePresenter {

        fun onSignIn()
        fun onSignUp()
    }
}