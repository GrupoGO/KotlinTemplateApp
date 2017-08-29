package es.grupogo.cocktailsapp.extensions


import android.app.Fragment
import android.app.FragmentManager

/**
 * Created by jorge_cmata on 28/8/17.
 */
fun android.support.v4.app.FragmentManager.setFragment(id: Int, fragment: android.support.v4.app.Fragment, tag: String){
    this.beginTransaction().replace(id, fragment, tag).commit()
}

fun FragmentManager.setFragment(id: Int, fragment: Fragment, tag: String){
    this.beginTransaction().replace(id, fragment, tag).commit()
}