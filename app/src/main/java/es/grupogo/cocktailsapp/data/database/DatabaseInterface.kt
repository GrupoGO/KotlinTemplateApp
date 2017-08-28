package es.grupogo.cocktailsapp.data.database

import es.grupogo.cocktailsapp.domain.Cocktail
import io.realm.Realm
import io.realm.RealmResults

/**
 * Created by jorge_cmata on 25/8/17.
 */
interface DatabaseInterface{

    fun saveCocktails(realm: Realm , cocktails: List<Cocktail>)

    fun retrieveCocktails(realm: Realm) : RealmResults<Cocktail>
}