package es.grupogo.cocktailsapp.ui.activities.signin

import es.grupogo.cocktailsapp.domain.DataManager

/**
 * Created by jorge_cmata on 28/8/17.
 */
class SignInPresenter(val view : SignInContract.View) : SignInContract.Presenter {

    val dataManager : DataManager by lazy { DataManager.provideDataManager(view.getContext()) }

    init {
        this.view.setPresenter(this)
    }

    override fun start() {

    }

    override fun doSignIn(username : String, password: String) {

        dataManager.signIn(username, password, {
            view.enterApp()
        }, {
            view.handleError(it)
        })
    }
}