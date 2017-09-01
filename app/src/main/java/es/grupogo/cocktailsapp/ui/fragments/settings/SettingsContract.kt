package es.grupogo.cocktailsapp.ui.fragments.settings

import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.example.carlosolmedo.kotlinapp.mvp.BasePresenter
import com.example.carlosolmedo.kotlinapp.mvp.BaseView

/**
 * Created by jorge_cmata on 28/8/17.
 */
interface SettingsContract {

    interface View : BaseView<Presenter> {

        fun updateUsernameView(usernameTitle: String, usernameValue: String)
        fun updateGenderView(genderTitle: String, genderValue: String)
        fun updateBirthdateView(birthdateTitle: String, birthdateValue: String)

    }

    interface Presenter : BasePresenter {

        fun updateUsername(usernameValue: String)
        fun updateGender(genderValue: String)
        fun updateBirthdate(birthdateValue: String)
    }
}