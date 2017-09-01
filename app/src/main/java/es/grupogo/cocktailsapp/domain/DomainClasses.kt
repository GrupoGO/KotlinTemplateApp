package es.grupogo.cocktailsapp.domain

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by jorge_cmata on 25/8/17.
 */

data class Cocktail(val id: String, val name: String?, val imageUrl: String?)