package es.grupogo.cocktailsapp.ui.fragments.feed

import android.util.Log
import es.grupogo.cocktailsapp.domain.Cocktail
import es.grupogo.cocktailsapp.domain.DataManager

/**
 * Created by jorge_cmata on 24/8/17.
 */
class FeedPresenter(val view : FeedContract.View) : FeedContract.Presenter {

    val dataManager : DataManager by lazy { DataManager.provideDataManager(view.getContext()) }
    var items: List<Cocktail>? = null

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
            view.showItems(items)
            this.items = items
        }, {
            view.handleError(it)
            view.hideLoader()
        })
    }

    override fun getCocktailsDB(){
        view.showLoader()
        dataManager.getCocktailsDB({
            view.hideLoader()
            view.showItems(it)
            this.items = it
        }, {
            view.handleError(it)
            view.hideLoader()
        })
    }


    override fun applyFilter(selectedValues: List<String>) {
        var filteredItems: List<Cocktail>? = items
        try{
            selectedValues.map {
                when(it){
                    "drop(15)" -> filteredItems = filteredItems?.drop(15)
                    "filter{ cocktail.name.contains(\"a\")}" -> filteredItems = filteredItems?.filter{ it.name!!.contains("a")}
                    "slice(listOf(1,3,17,30)" -> filteredItems = filteredItems?.slice(listOf(1,3,17,30))
                    "take(40)" -> filteredItems = filteredItems?.take(40)
                    else -> filteredItems = items
                }
            }
            filteredItems?.let { view.showItems(it) }

        }catch (e: IndexOutOfBoundsException){
            e.printStackTrace()
            view.handleError(e)
        }
    }
}