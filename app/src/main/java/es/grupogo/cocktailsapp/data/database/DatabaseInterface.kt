package es.grupogo.cocktailsapp.data.database

import es.grupogo.cocktailsapp.domain.Cocktail
import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmResults

/**
 * Created by jorge_cmata on 25/8/17.
 */
interface DatabaseInterface{

    fun saveCocktails(cocktails: List<CocktailRealm>)

    fun retrieveCocktails() : List<Cocktail>
}