package es.grupogo.cocktailsapp.extensions

import android.content.res.Resources

/**
 * Created by jorge_cmata on 28/8/17.
 */
fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()