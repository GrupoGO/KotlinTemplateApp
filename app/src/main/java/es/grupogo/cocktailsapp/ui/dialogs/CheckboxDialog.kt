package es.grupogo.cocktailsapp.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.widget.CheckBox
import es.grupogo.cocktailsapp.extensions.toPx
import android.widget.TextView
import android.widget.LinearLayout
import android.widget.ScrollView
import es.grupogo.cocktailsapp.R


/**
 * Created by jorge_cmata on 4/9/17.
 */
class CheckboxDialog: DialogFragment(){

    interface CheckBoxDialogListener{
        fun onPositiveClick(selectedOptions:List<String>)
        fun onNegativeClick()
    }

    //New instance
    companion object {
        private val PARAM_TITLE: String = "param_title"
        private val PARAM_OPTIONS: String = "param_options"
        private val PARAM_OLD_VALUES: String = "param_old_values"
        private val PARAM_POSITIVE: String = "param_positive"
        private val PARAM_NEGATIVE: String = "param_negative"
        private var mCallback: CheckBoxDialogListener? = null

        fun newInstance(title: String, options: ArrayList<String>, oldValues: ArrayList<String>?, positiveButtonText: String, negativeButtonText: String?): CheckboxDialog {
            val fragment = CheckboxDialog()
            with(Bundle()) {
                putString(PARAM_TITLE, title)
                putStringArrayList(PARAM_OPTIONS, options)
                putStringArrayList(PARAM_OLD_VALUES, oldValues)
                putString(PARAM_POSITIVE, positiveButtonText)
                putString(PARAM_NEGATIVE, negativeButtonText)
                fragment.arguments = this
            }
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        //CheckBox Layout
        val options = arguments.getStringArrayList(PARAM_OPTIONS)
        val oldValues: List<String>? = arguments.getStringArrayList(PARAM_OLD_VALUES)
        val scrollView = ScrollView(context)
        val linearLayout = LinearLayout(context)
        linearLayout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.setPadding(16.toPx(), 16.toPx(), 16.toPx(), 16.toPx())
        scrollView.addView(linearLayout)
        for (i in 0..options.size - 1) {
            val view = activity.layoutInflater.inflate(R.layout.row_checkbox, null)
            val tv = view.findViewById<TextView>(R.id.checkbox_textview)
            tv.text = options[i]
            linearLayout.addView(view)
        }

        //Set old values
        for (i in 0..options.size - 1) {
            val view = linearLayout.getChildAt(i)
            val checkbox = view.findViewById<CheckBox>(R.id.checkbox_item)
            oldValues?.map {
                val tv = view.findViewById<TextView>(R.id.checkbox_textview)
                if(it == tv.text) {
                    checkbox.isChecked = true
                }
            }
        }


        //Builder
        with(AlertDialog.Builder(context)) {
            setTitle(arguments.getString(CheckboxDialog.PARAM_TITLE))
            setView(scrollView)
            setPositiveButton(arguments.getString(CheckboxDialog.PARAM_POSITIVE), { dialogInterface, i ->
                val selectedOptions: MutableList<String> = arrayListOf()
                for (i in 0..options.size - 1) {
                    val view = linearLayout.getChildAt(i)
                    val checkbox = view.findViewById<CheckBox>(R.id.checkbox_item)
                    if(checkbox.isChecked) {
                        val tv = view.findViewById<TextView>(R.id.checkbox_textview)
                        selectedOptions.add(tv.text.toString())
                    }
                }
                CheckboxDialog.mCallback?.onPositiveClick(selectedOptions)
            })
            setNegativeButton(arguments.getString(CheckboxDialog.PARAM_NEGATIVE), { dialogInterface, i ->
                CheckboxDialog.mCallback?.onNegativeClick()
            })

            return create()
        }

    }

    fun setCallback(callback: CheckBoxDialogListener){
        mCallback = callback
    }

}