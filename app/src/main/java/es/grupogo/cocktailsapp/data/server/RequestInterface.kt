package es.grupogo.cocktailsapp.data.server

import es.grupogo.cocktailsapp.domain.Cocktail
import io.reactivex.Observable

/**
 * Created by jorge_cmata on 25/8/17.
 */
interface RequestInterface{

    fun getCocktails() : Observable<List<Cocktail>>
}