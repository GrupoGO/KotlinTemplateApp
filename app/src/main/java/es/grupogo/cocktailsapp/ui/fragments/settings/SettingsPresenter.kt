package es.grupogo.cocktailsapp.ui.fragments.settings

import es.grupogo.cocktailsapp.domain.SettingsManager

/**
 * Created by jorge_cmata on 28/8/17.
 */
class SettingsPresenter(val view : SettingsContract.View) : SettingsContract.Presenter {

    val settingsManager : SettingsManager by lazy { SettingsManager.provideSettingsManager(view.getContext())}

    init {
        this.view.setPresenter(this)
    }

    override fun start() {
        view.updateUsernameView("Username", settingsManager.getUsername())
        view.updateGenderView("Gender", settingsManager.getGender())
        view.updateBirthdateView("Birthdate", settingsManager.getBirthdate())
    }

    override fun updateUsername(usernameValue: String) {
        settingsManager.setUsername(usernameValue)
        view.updateUsernameView("Username", usernameValue)
    }

    override fun updateGender(genderValue: String) {
        settingsManager.setGender(genderValue)
        view.updateGenderView("Gender", genderValue)
    }

    override fun updateBirthdate(birthdateValue: String) {
        settingsManager.setBirthdate(birthdateValue)
        view.updateBirthdateView("Birthdate", birthdateValue)
    }

}