package es.grupogo.cocktailsapp.data.database

import es.grupogo.cocktailsapp.domain.Cocktail
import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmResults

/**
 * Created by jorge_cmata on 25/8/17.
 */
interface DatabaseInterface{

    fun saveCocktails(cocktails: List<Cocktail>)

    fun retrieveCocktails(): List<Cocktail>

    fun retrieveCocktail(id: String): Cocktail

    fun updateCocktail(id: String, newName: String)
}