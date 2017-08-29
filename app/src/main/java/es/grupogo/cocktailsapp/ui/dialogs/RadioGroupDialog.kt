package es.grupogo.cocktailsapp.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.widget.RadioButton
import android.widget.RadioGroup
import es.grupogo.cocktailsapp.extensions.toPx
import kotlin.collections.ArrayList

/**
 * Created by jorge_cmata on 29/8/17.
 */
class RadioGroupDialog: DialogFragment(){

    interface RadioGroupDialogListener{
        fun onPositiveClick(text:String)
        fun onNegativeClick()
    }

    //New instance
    companion object {
        private val PARAM_TITLE: String = "param_title"
        private val PARAM_OPTIONS: String = "param_options"
        private val PARAM_OLD_VALUE: String = "param_old_value"
        private val PARAM_POSITIVE: String = "param_positive"
        private val PARAM_NEGATIVE: String = "param_negative"
        private var mCallback: RadioGroupDialogListener? = null

        fun newInstance(title: String, options: ArrayList<String>, oldValue: String?, positiveButtonText: String, negativeButtonText: String?): RadioGroupDialog {
            val fragment = RadioGroupDialog()
            with(Bundle()) {
                putString(PARAM_TITLE, title)
                putStringArrayList(PARAM_OPTIONS, options)
                putString(PARAM_OLD_VALUE, oldValue)
                putString(PARAM_POSITIVE, positiveButtonText)
                putString(PARAM_NEGATIVE, negativeButtonText)
                fragment.arguments = this
            }
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        //Radio Group
        val options = arguments.getStringArrayList(PARAM_OPTIONS)
        val radioButtons = arrayOfNulls<RadioButton>(options.size)
        val radioGroup = RadioGroup(context)
        radioGroup.setPadding(16.toPx(), 16.toPx(), 16.toPx(), 16.toPx())
        for (i in 0..options.size - 1) {
            radioButtons[i] = RadioButton(context)
            radioGroup.addView(radioButtons[i])
            radioButtons[i]?.text =(options[i])
        }

        //Set old value
        radioButtons.map {
            if (it?.text == arguments.getString(PARAM_OLD_VALUE)) {
                it?.isChecked = true
            }
        }

        //Builder
        with(AlertDialog.Builder(context)) {
            setTitle(arguments.getString(PARAM_TITLE))
            setView(radioGroup)
            setPositiveButton(arguments.getString(PARAM_POSITIVE), { dialogInterface, i ->
                radioButtons.map {
                    if (it!!.isChecked) {
                        mCallback?.onPositiveClick(it.text.toString())
                    }
                }
            })
            setNegativeButton(arguments.getString(PARAM_NEGATIVE), { dialogInterface, i ->
                mCallback?.onNegativeClick()
            })

            return create()
        }

    }

    fun setCallback(callback: RadioGroupDialogListener){
        mCallback = callback
    }

}