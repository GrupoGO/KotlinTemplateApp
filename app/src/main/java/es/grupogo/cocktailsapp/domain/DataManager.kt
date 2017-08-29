package es.grupogo.cocktailsapp.domain

import es.grupogo.cocktailsapp.data.database.DatabaseManager
import es.grupogo.cocktailsapp.data.server.*
import es.grupogo.cocktailsapp.extensions.execute
import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmResults

/**
 * Created by jorge_cmata on 25/8/17.
 */
class DataManager {

    //Singleton
    companion object DataManagerProvider {
        fun provideDataManager(): DataManager {
            return DataManager()
        }
    }

    //Initialize managers (lazy -> The initialisation of the property is delayed up to the moment. We can save memory and skip the initialisation until the property is required)'
    val requestManager : RequestManager by lazy {RequestManager.provideRequestManager()}
    val databaseManager : DatabaseManager by lazy {DatabaseManager.provideDatabaseManager()}
    val realm : Realm by lazy {Realm.getDefaultInstance()}


    //Functions
    //---- Request Manager functions ----//

    fun getCocktails(onSuccess: (List<Cocktail>) -> Unit, onError: (t: Throwable) -> Unit){
        requestManager.requestCocktails().execute({
            //Save data in realm
            databaseManager.saveCocktails(realm, it)
            //retrieve realm objects
            onSuccess(databaseManager.retrieveCocktails(realm))
        }, {
            onError(it)
        })
    }


    //---- Database Manager functions ----//

    fun getCocktailsDB(): List<Cocktail>{
        return databaseManager.retrieveCocktails(realm)
    }
}