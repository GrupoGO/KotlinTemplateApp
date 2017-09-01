package es.grupogo.cocktailsapp.ui.activities.enter

import es.grupogo.cocktailsapp.domain.DataManager

/**
 * Created by jorge_cmata on 28/8/17.
 */
class EnterPresenter(val view : EnterContract.View) : EnterContract.Presenter {

    val dataManager : DataManager by lazy { DataManager.provideDataManager(view.getContext()) }

    init {
        this.view.setPresenter(this)
    }

    override fun start() {

        if (dataManager.isLoggedIn()) view.goToMain()
    }

    override fun onSignIn() {
        view.goToSignIn()
    }

    override fun onSignUp() {
        view.goToSignUp()
    }

}