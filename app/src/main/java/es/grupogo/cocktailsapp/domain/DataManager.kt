package es.grupogo.cocktailsapp.domain

import es.grupogo.cocktailsapp.data.server.*
import io.reactivex.Observable

/**
 * Created by jorge_cmata on 25/8/17.
 */
class DataManager : RequestInterface {

    //Singleton
    object DataManagerProvider {
        fun provideDataManager(): DataManager {
            return DataManager()
        }
    }

    //Initialize managers
    val requestManager : RequestManager by lazy {RequestManager.provideRequestManager()}


    //Functions
    //---- Request Manager functions ----//

    override fun getCocktails(): Observable<List<Cocktail>> {
        return requestManager.getCocktails()
    }


}