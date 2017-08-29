package es.grupogo.cocktailsapp.ui.fragments.settings


/**
 * Created by jorge_cmata on 28/8/17.
 */
class SettingsPresenter(val view : SettingsContract.View) : SettingsContract.Presenter {

    init {
        this.view.setPresenter(this)
    }

    override fun start() {
    }

}