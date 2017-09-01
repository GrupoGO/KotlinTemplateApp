package es.grupogo.cocktailsapp.data.database

import es.grupogo.cocktailsapp.domain.Cocktail
import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmChangeListener
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

    override fun saveCocktails(cocktails: List<CocktailRealm>)  {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        for (cocktail in cocktails) realm.copyToRealmOrUpdate(cocktail)
        realm.commitTransaction()
    }

    override fun retrieveCocktails(): List<Cocktail> {

        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val cocktails = realm.where(CocktailRealm::class.java).findAll()
        realm.commitTransaction()
        return DatabaseMapper.convertToCocktailList(cocktails)
    }

}