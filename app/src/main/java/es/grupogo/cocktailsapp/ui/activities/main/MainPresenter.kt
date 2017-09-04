package es.grupogo.cocktailsapp.ui.activities.main

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