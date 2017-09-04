package es.grupogo.cocktailsapp.ui.activities.detail

import es.grupogo.cocktailsapp.domain.Cocktail
import es.grupogo.cocktailsapp.domain.DataManager
import es.grupogo.cocktailsapp.ui.activities.main.MainContract

/**
 * Created by jorge_cmata on 4/9/17.
 */
class DetailPresenter(val view : DetailContract.View) : DetailContract.Presenter {


    val dataManager : DataManager by lazy { DataManager.provideDataManager(view.getContext()) }

    init {
        this.view.setPresenter(this)
    }

    override fun start() {
    }

    override fun getDetailData(id: String) {
        dataManager.getCocktailDetailDB(id, {
            view.showData(it)
        }, {
            view.handleError(it)
        })
    }

    override fun updateCocktail(id: String, newName: String) {
        dataManager.updateCocktailName(id, newName,{
            view.refreshView()
        }, {
            view.handleError(it)
        })
    }


}