package es.grupogo.cocktailsapp

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by jorge_cmata on 25/8/17.
 */
class CocktailsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(config)
    }
}