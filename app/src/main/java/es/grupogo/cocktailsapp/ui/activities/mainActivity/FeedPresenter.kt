package es.grupogo.cocktailsapp.ui.activities.mainActivity

import android.util.Log
import es.grupogo.cocktailsapp.data.extensions.execute
import es.grupogo.cocktailsapp.domain.Cocktail
import es.grupogo.cocktailsapp.domain.DataManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by jorge_cmata on 24/8/17.
 */
class FeedPresenter(val view : FeedContract.View) : FeedContract.Presenter {

    val dataManager : DataManager by lazy { DataManager.DataManagerProvider.provideDataManager() }

    init {
        this.view.setPresenter(this)
    }

    override fun start() {
        //The presenter is started
        getCocktails()
    }

    override fun getCocktails(){

        dataManager.getCocktails().execute({
            view.setRecyclerItems(it)
        }, {
            it.printStackTrace()
            view.toast(it.message)
        })
    }
}