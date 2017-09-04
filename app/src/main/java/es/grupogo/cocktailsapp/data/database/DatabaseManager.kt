package es.grupogo.cocktailsapp.data.database

import android.widget.ListAdapter
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

    override fun saveCocktails(cocktails: List<Cocktail>)  {
        val realm = Realm.getDefaultInstance()

        realm.executeTransaction {
            for (cocktail in cocktails) realm.copyToRealmOrUpdate(DatabaseMapper.convertToCocktailRealm(cocktail))
        }
    }

    override fun retrieveCocktails(): List<Cocktail> {

        val realm = Realm.getDefaultInstance()
        val cocktails = realm.where(CocktailRealm::class.java).findAll()
        return DatabaseMapper.convertToCocktailList(cocktails)
    }
}