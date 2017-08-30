package es.grupogo.cocktailsapp.ui.activities.signup

import es.grupogo.cocktailsapp.domain.DataManager

/**
 * Created by jorge_cmata on 28/8/17.
 */
class SignUpPresenter(val view : SignUpContract.View) : SignUpContract.Presenter {

    val dataManager : DataManager by lazy { DataManager.provideDataManager() }

    init {
        this.view.setPresenter(this)
    }

    override fun start() {

    }

    override fun doSignUp(username : String, password: String) {

        dataManager.signUp(username, password, {
            view.enterApp()
        }, {
            view.handleError(it)
        })
    }
}