package es.grupogo.cocktailsapp.ui.activities.detail

import com.example.carlosolmedo.kotlinapp.mvp.BasePresenter
import com.example.carlosolmedo.kotlinapp.mvp.BaseView
import es.grupogo.cocktailsapp.domain.Cocktail

/**
 * Created by jorge_cmata on 4/9/17.
 */
interface DetailContract {

    interface View : BaseView<Presenter> {

        fun showData(cocktail: Cocktail)

        fun refreshView()

    }

    interface Presenter : BasePresenter {

        fun getDetailData(id: String)

        fun updateCocktail(id:String, newName: String)
    }
}