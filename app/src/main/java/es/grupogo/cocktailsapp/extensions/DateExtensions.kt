package es.grupogo.cocktailsapp.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by jorge_cmata on 29/8/17.
 */
fun Date.getYearNumber(): Int{
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.YEAR)
}

fun Date.getMonthNumber(): Int{
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.MONTH)
}

fun Date.getDayNumber(): Int{
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.DAY_OF_MONTH)
}

fun Date.getDateString(date: Date, dateFormat: String): String{
    val sdf = SimpleDateFormat(dateFormat)
    return sdf.format(date)
}

fun Date.getDateString(year: Int , month: Int , day: Int, dateFormat: String): String{
    val calendar = Calendar.getInstance()
    calendar.set(year, month, day)
    val sdf = SimpleDateFormat(dateFormat)
    return sdf.format(calendar.time)
}

fun Date.getDate(year: Int , month: Int , day: Int): Date{
    val calendar = Calendar.getInstance()
    calendar.set(year, month, day)
    return calendar.time
}
