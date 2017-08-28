package es.grupogo.cocktailsapp.ui.fragments

import es.grupogo.cocktailsapp.domain.Cocktail
import es.grupogo.cocktailsapp.extensions.execute
import es.grupogo.cocktailsapp.domain.DataManager
import io.realm.RealmChangeListener
import io.realm.RealmResults

/**
 * Created by jorge_cmata on 24/8/17.
 */
class FeedPresenter(val view : FeedContract.View) : FeedContract.Presenter {

    val dataManager : DataManager by lazy { DataManager.DataManagerProvider.provideDataManager() }

    init {
        this.view.setPresenter(this)
    }

    override fun start() {
        //Getting cocktails from DB and setting a listener
        val cocktailsList: RealmResults<Cocktail> = getCocktailsDB() as RealmResults<Cocktail>
        cocktailsList.addChangeListener(RealmChangeListener<RealmResults<Cocktail>> {
            view.setRecyclerItems(it)
        })
        //Update view with saved values
        view.setRecyclerItems(cocktailsList)
        //Online call
        getCocktails()
    }

    override fun getCocktails(){
        dataManager.getCocktails({
          //  view.setRecyclerItems(it)
        }, {
            it.printStackTrace()
            view.toast(it.message)
        })
    }

    override fun getCocktailsDB(): List<Cocktail>{
        return dataManager.getCocktailsDB()
    }
}