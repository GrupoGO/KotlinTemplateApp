package es.grupogo.cocktailsapp.domain

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by jorge_cmata on 25/8/17.
 */
// data class Cocktail(val id: String, val imageUrl: String?, val name: String)

open class Cocktail(
        @PrimaryKey open var id: String = "",
        open var name: String? = null,
        open var imageUrl: String? = null
)
    : RealmObject() {

    fun copy(
            id: String = this.id,
            name: String? = this.name,
            imageUrl: String? = this.imageUrl)
            = Cocktail(id, name, imageUrl)
}