package es.grupogo.cocktailsapp.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import es.grupogo.cocktailsapp.extensions.toPx
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.InputMethodManager


/**
 * Created by jorge_cmata on 28/8/17.
 */
class EditTextDialog: DialogFragment(){

    interface EditTextDialogListener{
        fun onPositiveClick(text:String)
        fun onNegativeClick()
    }

    //New instance
    companion object {
        private val PARAM_TITLE: String = "param_title"
        private val PARAM_OLD_VALUE: String = "param_old_value"
        private val PARAM_POSITIVE: String = "param_positive"
        private val PARAM_NEGATIVE: String = "param_negative"
        private var mCallback: EditTextDialogListener? = null

        fun newInstance(title: String, oldValue: String?, positiveButtonText: String, negativeButtonText: String?): EditTextDialog {
            val fragment = EditTextDialog()
            with(Bundle()) {
                putString(PARAM_TITLE, title)
                putString(PARAM_OLD_VALUE, oldValue)
                putString(PARAM_POSITIVE, positiveButtonText)
                putString(PARAM_NEGATIVE, negativeButtonText)
                fragment.arguments = this
            }
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        //LinearLayout
        val linearLayout = LinearLayout(context)
        with(linearLayout){
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            orientation = LinearLayout.VERTICAL
            setPadding(16.toPx(), 16.toPx(), 16.toPx(), 16.toPx())
        }

        //EditText
        val editText = EditText(context)
        with(editText){
            setText(arguments.getString(PARAM_OLD_VALUE))
            setSelection(editText.text.length)
            onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
                val mgr = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                mgr.showSoftInput(v, InputMethodManager.SHOW_FORCED)
            }
            linearLayout.addView(this)
        }

        //Builder
        with(AlertDialog.Builder(context)){
            setTitle(arguments.getString(PARAM_TITLE))
            setView(linearLayout)
            setPositiveButton(arguments.getString(PARAM_POSITIVE), { dialogInterface, i ->
                mCallback?.onPositiveClick(editText.text.toString().trim())
            })
            setNegativeButton(arguments.getString(PARAM_NEGATIVE), { dialogInterface, i ->
                mCallback?.onNegativeClick()
            })
            return create()
        }
    }

    fun setCallback(callback: EditTextDialogListener){
        mCallback = callback
    }
}