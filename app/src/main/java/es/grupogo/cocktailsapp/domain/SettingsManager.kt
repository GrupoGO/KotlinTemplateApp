package es.grupogo.cocktailsapp.domain

import android.app.Activity
import android.content.Context
import java.security.AccessControlContext

/**
 * Created by jorge_cmata on 29/8/17.
 */
class SettingsManager(context: Context) {

    //Singleton
    companion object SettingsManagerProvider {
        fun provideSettingsManager(context: Context): SettingsManager {
            return SettingsManager(context)
        }
    }

    private val PREF_NAME = "preferences"
    private val sharedPreferences by lazy {context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE)}
    private val editor by lazy { sharedPreferences.edit() }

    //KEYS
    private val KEY_USERNAME = "key_username"
    private val KEY_GENDER = "key_gender"
    private val KEY_BIRTHDAY = "key_birthday"

    //Functions
    fun setUsername(username: String) = editor.putString(KEY_USERNAME, username).apply()
    fun getUsername(): String = sharedPreferences.getString(KEY_USERNAME,"")

    fun setGender(gender: String) = editor.putString(KEY_GENDER, gender).apply()
    fun getGender(): String = sharedPreferences.getString(KEY_GENDER,"")

    fun setBirthday(birthday: String) = editor.putString(KEY_BIRTHDAY, birthday).apply()
    fun getBirthday(): String = sharedPreferences.getString(KEY_BIRTHDAY,"")


}