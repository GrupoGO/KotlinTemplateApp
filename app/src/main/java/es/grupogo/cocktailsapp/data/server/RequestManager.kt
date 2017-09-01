package es.grupogo.cocktailsapp.data.server

import es.grupogo.cocktailsapp.domain.Cocktail
import io.reactivex.Observable

/**
 * Created by jorge_cmata on 24/8/17.
 */
class RequestManager(val apiService: CocktailApiService) : RequestInterface  {

    //Singleton
    companion object RequestManagerProvider {
        fun provideRequestManager(): RequestManager {
            return RequestManager(CocktailApiService.create())
        }
    }

    //Functions interface
    override fun requestCocktails(): Observable<List<Cocktail>> {
        val drinksJsonObj: Observable<DrinksJSONObj> = apiService.requestCocktails("Cocktail")
        return drinksJsonObj.map { ServerMapper.convertToCocktailList(it)}
    }

}