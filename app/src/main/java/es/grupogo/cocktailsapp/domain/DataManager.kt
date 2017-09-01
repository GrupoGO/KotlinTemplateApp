package es.grupogo.cocktailsapp.domain

import android.content.Context
import es.grupogo.cocktailsapp.data.database.DatabaseManager
import es.grupogo.cocktailsapp.data.server.*
import es.grupogo.cocktailsapp.extensions.execute
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
    //val sessionManager : SessionManager by lazy {SessionManager.provideSessionManager(context)}


    //Functions
    //---- Request Manager functions ----//

    fun getCocktails(onSuccess: (List<Cocktail>) -> Unit, onError: (t: Throwable) -> Unit){
        val cachedResults = databaseManager.retrieveCocktails()

        val observable: Observable<List<Cocktail>>  =
        requestManager.requestCocktails()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map { databaseManager.saveCocktails(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .map { databaseManager.retrieveCocktails() }


        observable.mergeWith(Observable.just(cachedResults))
                .subscribe({onSuccess(it)},{onError(it)})
    }


    //---- Database Manager functions ----//

    fun getCocktailsDB(): List<Cocktail>{
        return databaseManager.retrieveCocktails()
    }

    fun signIn(username: String, password: String, onSuccess: () -> Unit, onError: (t: Throwable) -> Unit) {

        when {
            username.isEmpty() -> onError(Exception("Username is empty"))
            password.isEmpty() -> onError(Exception("Password is empty"))
            else -> {
                //sessionManager.createSession(username)
                onSuccess()
            }
        }
    }
    fun signUp(username: String, password: String, onSuccess: () -> Unit, onError: (t: Throwable) -> Unit) {

        when {
            username.isEmpty() -> onError(Exception("Username is empty"))
            password.isEmpty() -> onError(Exception("Password is empty"))
            else -> {
                //sessionManager.createSession(username)
                onSuccess()
            }
        }
    }

    fun logout() {
        //sessionManager.destroySession()
    }
}