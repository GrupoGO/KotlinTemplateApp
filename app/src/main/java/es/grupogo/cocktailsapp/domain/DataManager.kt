package es.grupogo.cocktailsapp.domain

import android.content.Context
import es.grupogo.cocktailsapp.data.database.CocktailRealm
import es.grupogo.cocktailsapp.data.database.DatabaseManager
import es.grupogo.cocktailsapp.data.database.DatabaseMapper
import es.grupogo.cocktailsapp.data.server.*
import es.grupogo.cocktailsapp.extensions.execute
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmResults

/**
 * Created by jorge_cmata on 25/8/17.
 */
class DataManager(val context: Context) {

    //Singleton
    companion object DataManagerProvider {
        fun provideDataManager(context: Context): DataManager {
            return DataManager(context)
        }
    }

    //Initialize managers (lazy -> The initialisation of the property is delayed up to the moment. We can save memory and skip the initialisation until the property is required)'
    val requestManager : RequestManager by lazy {RequestManager.provideRequestManager()}
    val databaseManager : DatabaseManager by lazy {DatabaseManager.provideDatabaseManager()}
    val sessionManager : SessionManager by lazy {SessionManager.provideSessionManager(context)}


    //Functions
    //---- Request Manager functions ----//

    fun getCocktails(onSuccess: (items: List<Cocktail>, isCache: Boolean) -> Unit, onError: (t: Throwable) -> Unit){
        //Observable cache
        val observableCache = Observable.just(databaseManager.retrieveCocktails())

        //Observable online request
        val observableOnline = requestManager.requestCocktails()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map { databaseManager.saveCocktails(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .map { databaseManager.retrieveCocktails() }

        /*//Merge
        observableOnline.mergeWith(observableCache)
                .subscribe({onSuccess(it)},{onError(it)})*/

        //Merge
        observableCache.subscribe({onSuccess(it, true)},{onError(it)})
        observableOnline.subscribe({onSuccess(it, false)},{onError(it)})
    }

    //---- Database Manager functions ----//

    fun getCocktailsDB(onSuccess: (items: List<Cocktail>) -> Unit, onError: (t: Throwable) -> Unit){
        Observable.just(databaseManager.retrieveCocktails())
                .subscribe({onSuccess(it)},{onError(it)})
    }


    fun getCocktailDetailDB(cocktailId: String, onSuccess: (cocktail: Cocktail) -> Unit, onError: (t: Throwable) -> Unit){
        Observable.just(databaseManager.retrieveCocktail(cocktailId))
                .subscribe({onSuccess(it)},{onError(it)})
    }

    fun updateCocktailName(id: String, newName: String, onSuccess: (Unit) -> Unit, onError: (t: Throwable) -> Unit){
        Observable.just(databaseManager.updateCocktail(id, newName))
                .subscribe({onSuccess(it)},{onError(it)})
    }


    fun signIn(username: String, password: String, onSuccess: () -> Unit, onError: (t: Throwable) -> Unit) {

        when {
            username.isEmpty() -> onError(Exception("Username is empty"))
            password.isEmpty() -> onError(Exception("Password is empty"))
            else -> {
                sessionManager.createSession(username)
                onSuccess()
            }
        }
    }
    fun signUp(username: String, password: String, onSuccess: () -> Unit, onError: (t: Throwable) -> Unit) {

        when {
            username.isEmpty() -> onError(Exception("Username is empty"))
            password.isEmpty() -> onError(Exception("Password is empty"))
            else -> {
                sessionManager.createSession(username)
                onSuccess()
            }
        }
    }

    fun isLoggedIn() : Boolean = sessionManager.isLoggedIn()

    fun logout() {
        sessionManager.destroySession()
    }
}