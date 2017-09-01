package es.grupogo.cocktailsapp.ui.fragments.feed

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
        dataManager.getCocktails({
            view.setItemsOnView(it)
        }, {
            it.printStackTrace()
            view.handleError(it)
        })
    }

    override fun getCocktailsDB(): List<Cocktail>{
        return dataManager.getCocktailsDB()
    }

    override fun onCocktailClicked(position: Int) {

    }
}