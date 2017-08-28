package es.grupogo.cocktailsapp.ui.activities.mainActivity

import es.grupogo.cocktailsapp.domain.Cocktail
import es.grupogo.cocktailsapp.domain.DataManager
import io.realm.RealmChangeListener
import io.realm.RealmResults

/**
 * Created by jorge_cmata on 28/8/17.
 */
class MainPresenter(val view : MainContract.View) : MainContract.Presenter {

    init {
        this.view.setPresenter(this)
    }

    override fun start() {

    }

}