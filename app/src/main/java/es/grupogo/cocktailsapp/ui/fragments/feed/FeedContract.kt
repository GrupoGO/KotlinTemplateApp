package es.grupogo.cocktailsapp.ui.fragments.feed

import com.example.carlosolmedo.kotlinapp.mvp.BasePresenter
import com.example.carlosolmedo.kotlinapp.mvp.BaseView
import es.grupogo.cocktailsapp.domain.Cocktail

/**
 * Created by jorge_cmata on 24/8/17.
 */
interface FeedContract {

    interface View : BaseView<Presenter> {

        fun showItems(items: List<Cocktail>)
    }

    interface Presenter : BasePresenter{

        fun getData()
        fun getCachedData()
        fun applyFilter(selectedValues: List<String>)
    }
}