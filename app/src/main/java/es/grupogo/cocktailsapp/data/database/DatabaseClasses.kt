package es.grupogo.cocktailsapp.data.database

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by jorge_cmata on 1/9/17.
 */
open class CocktailRealm(
        @PrimaryKey open var id: String = "",
        open var name: String? = null,
        open var imageUrl: String? = null
)
    : RealmObject() {

    fun copy(
            id: String = this.id,
            name: String? = this.name,
            imageUrl: String? = this.imageUrl)
            = CocktailRealm(id, name, imageUrl)
}