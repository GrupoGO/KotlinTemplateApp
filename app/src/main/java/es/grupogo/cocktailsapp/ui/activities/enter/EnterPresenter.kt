package es.grupogo.cocktailsapp.ui.activities.enter

/**
 * Created by jorge_cmata on 28/8/17.
 */
class EnterPresenter(val view : EnterContract.View) : EnterContract.Presenter {

    init {
        this.view.setPresenter(this)
    }

    override fun start() {

    }

    override fun onSignIn() {
        view.goToSignIn()
    }

    override fun onSignUp() {
        view.goToSignUp()
    }

}