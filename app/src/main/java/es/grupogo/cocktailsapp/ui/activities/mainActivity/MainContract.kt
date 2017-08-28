package es.grupogo.cocktailsapp.ui.activities.mainActivity

import com.example.carlosolmedo.kotlinapp.mvp.BasePresenter
import com.example.carlosolmedo.kotlinapp.mvp.BaseView
import es.grupogo.cocktailsapp.domain.Cocktail

/**
 * Created by jorge_cmata on 28/8/17.
 */
interface MainContract {

    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter {


    }
}