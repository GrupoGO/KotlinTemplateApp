package es.grupogo.cocktailsapp.domain

import android.app.Activity
import android.content.Context
import es.grupogo.cocktailsapp.data.database.DatabaseManager
import es.grupogo.cocktailsapp.data.server.RequestManager
import io.realm.Realm

/**
 * Created by jorge_cmata on 25/8/17.
 */
class SessionManager(context: Context) {

    //Singleton
    companion object SessionManagerProvider {
        fun provideSessionManager(context: Context): SessionManager {
            return SessionManager(context)
        }
    }

    private val PREF_NAME = "prefs_session"
    private val sharedPreferences by lazy {context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE)}
    private val editor by lazy { sharedPreferences.edit() }

    //KEYS
    private val KEY_USERNAME = "key_username"
    private val KEY_LOGGED = "key_logged"

    //Functions
    fun createSession(username: String) {
        editor
                .putString(KEY_USERNAME, username)
                .putBoolean(KEY_LOGGED, true)
                .apply()
    }

    fun destroySession() = editor.clear().apply()

    fun isLoggedIn(): Boolean = sharedPreferences.getBoolean(KEY_LOGGED,false)


}