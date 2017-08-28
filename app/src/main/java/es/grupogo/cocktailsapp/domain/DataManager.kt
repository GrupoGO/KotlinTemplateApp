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
    object DataManagerProvider {
        fun provideDataManager(): DataManager {
            return DataManager()
        }
    }

    //Initialize managers
    val requestManager : RequestManager by lazy {RequestManager.provideRequestManager()}
    val databaseManager : DatabaseManager by lazy {DatabaseManager.provideDatabaseManager()}
    val realm = Realm.getDefaultInstance()


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

    fun getCocktailsDB(): RealmResults<Cocktail>{
        return databaseManager.retrieveCocktails(realm)
    }
}