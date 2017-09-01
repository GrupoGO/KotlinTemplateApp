package es.grupogo.cocktailsapp.ui.fragments.feed

import android.util.Log
import es.grupogo.cocktailsapp.domain.Cocktail
import es.grupogo.cocktailsapp.domain.DataManager

/**
 * Created by jorge_cmata on 24/8/17.
 */
class FeedPresenter(val view : FeedContract.View) : FeedContract.Presenter {


    val dataManager : DataManager by lazy { DataManager.provideDataManager() }

    init {
        this.view.setPresenter(this)
    }

    override fun start() {
        getCocktails()
    }

    override fun getCocktails(){
        view.showLoader()
        dataManager.getCocktails({items, isCache ->
            if (!isCache) view.hideLoader()
            view.setRecyclerItems(items)
        }, {
            view.handleError(it)
            view.hideLoader()
        })
    }

    override fun onCocktailClicked(position: Int) {

    }
}