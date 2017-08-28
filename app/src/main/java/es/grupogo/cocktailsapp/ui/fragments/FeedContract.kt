package es.grupogo.cocktailsapp.ui.fragments

import com.example.carlosolmedo.kotlinapp.mvp.BasePresenter
import com.example.carlosolmedo.kotlinapp.mvp.BaseView
import es.grupogo.cocktailsapp.domain.Cocktail
import io.realm.RealmResults

/**
 * Created by jorge_cmata on 24/8/17.
 */
interface FeedContract {

    interface View : BaseView<Presenter> {

        fun setRecyclerItems(items: List<Cocktail>)

        fun toast(message: String?)

    }

    interface Presenter : BasePresenter{

        fun getCocktails()

        fun getCocktailsDB(): List<Cocktail>

    }
}