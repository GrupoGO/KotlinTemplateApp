package es.grupogo.cocktailsapp.data.database

import es.grupogo.cocktailsapp.domain.Cocktail

/**
 * Created by jorge_cmata on 25/8/17.
 */
interface DatabaseInterface{

    fun saveCocktails(cocktails: List<Cocktail>)

    fun retrieveCocktails() : List<Cocktail>
}