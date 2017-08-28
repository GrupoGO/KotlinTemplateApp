package es.grupogo.cocktailsapp.data.database

import es.grupogo.cocktailsapp.domain.Cocktail
import io.realm.Realm
import io.realm.RealmResults

/**
 * Created by jorge_cmata on 25/8/17.
 */
class DatabaseManager : DatabaseInterface{

    //Singleton
    companion object DatabaseManagerProvider {
        fun provideDatabaseManager(): DatabaseManager {
            return DatabaseManager()
        }
    }

    override fun saveCocktails(realm: Realm, cocktails: List<Cocktail>) {
        realm.beginTransaction()
        for (cocktail in cocktails) realm.copyToRealmOrUpdate(cocktail)
        realm.commitTransaction()
    }

    override fun retrieveCocktails(realm: Realm): RealmResults<Cocktail> {
        realm.beginTransaction()
        val cocktails = realm.where(Cocktail::class.java).findAll()
        realm.commitTransaction()
        return cocktails
    }

}