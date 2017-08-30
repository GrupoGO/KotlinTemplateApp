package es.grupogo.cocktailsapp.ui.fragments.settings

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import es.grupogo.cocktailsapp.R
import es.grupogo.cocktailsapp.data.server.RequestManager
import es.grupogo.cocktailsapp.domain.Cocktail
import es.grupogo.cocktailsapp.domain.SettingsManager
import es.grupogo.cocktailsapp.extensions.*
import es.grupogo.cocktailsapp.ui.adapters.CockailsRecyclerAdapter
import es.grupogo.cocktailsapp.ui.dialogs.EditTextDialog
import es.grupogo.cocktailsapp.ui.dialogs.RadioGroupDialog
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by jorge_cmata on 28/8/17.
 */
class SettingsFragment : Fragment() , SettingsContract.View {


    //New instance
    companion object {
        fun newInstance(): SettingsFragment {
            val fragment = SettingsFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    //References
    private lateinit var mPresenter : SettingsContract.Presenter
    private lateinit var mEditTextRow : LinearLayout
    private lateinit var mEditTextRowTitle : TextView
    private lateinit var mEditTextRowValue : TextView
    private lateinit var mRadioGroupRow : LinearLayout
    private lateinit var mRadioGroupRowTitle : TextView
    private lateinit var mRadioGroupRowValue : TextView
    private lateinit var mCalendarRow : LinearLayout
    private lateinit var mCalendarRowTitle : TextView
    private lateinit var mCalendarRowValue : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Instantiate presenter
        SettingsPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)

        //Kick off presenter
        mPresenter.start()

        setListeners()
    }

    private fun bindViews(view: View){
        mEditTextRow = view.findViewById(R.id.edit_text_setting_row)
        mEditTextRowValue = mEditTextRow.findViewById(R.id.row_settings_subtitle)
        mEditTextRowTitle = mEditTextRow.findViewById(R.id.row_settings_title)

        mRadioGroupRow = view.findViewById(R.id.radio_button_setting_row)
        mRadioGroupRowValue = mRadioGroupRow.findViewById(R.id.row_settings_subtitle)
        mRadioGroupRowTitle = mRadioGroupRow.findViewById(R.id.row_settings_title)

        mCalendarRow = view.findViewById(R.id.calendar_setting_row)
        mCalendarRowValue = mCalendarRow.findViewById(R.id.row_settings_subtitle)
        mCalendarRowTitle = mCalendarRow.findViewById(R.id.row_settings_title)
    }

    override fun setPresenter(presenter: SettingsContract.Presenter) {
        mPresenter = presenter
    }

    private fun setListeners(){
        mEditTextRow.setOnClickListener({
            val dialog = EditTextDialog.newInstance(mEditTextRowTitle.text.toString(), mEditTextRowValue.text.toString(),"Ok", "Cancel")
            dialog.setCallback(object : EditTextDialog.EditTextDialogListener {
                override fun onPositiveClick(text: String) = mPresenter.updateUsername(text)
                override fun onNegativeClick() {}
            })
            dialog.show(childFragmentManager, "tag")
        })

        mRadioGroupRow.setOnClickListener {
            val dialog = RadioGroupDialog.newInstance(mRadioGroupRowTitle.text.toString(), arrayListOf("Male", "Female"), mRadioGroupRowValue.text.toString(), "Ok", "Cancel")
            dialog.setCallback(object: RadioGroupDialog.RadioGroupDialogListener{
                override fun onPositiveClick(text: String) = mPresenter.updateGender(text)
                override fun onNegativeClick() {}
            })
            dialog.show(childFragmentManager, "tag")
        }

        mCalendarRow.setOnClickListener {
            var oldDate = Date()
            if(!mCalendarRowValue.text.toString().isEmpty()){
                oldDate = SimpleDateFormat("yyyy-MM-dd").parse(mCalendarRowValue.text.toString())
            }
            DatePickerDialog(this.activity, DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                mPresenter.updateBirthdate(Date().getDateString(year, month, day, "yyyy-MM-dd"))

            }, oldDate.getYearNumber(), oldDate.getMonthNumber(), oldDate.getDayNumber()).show()
        }
    }

    override fun updateUsernameView(usernameTitle: String, usernameValue: String) {
        mEditTextRowTitle.text = usernameTitle
        mEditTextRowValue.text = usernameValue
    }

    override fun updateGenderView(genderTitle: String, genderValue: String) {
        mRadioGroupRowTitle.text = genderTitle
        mRadioGroupRowValue.text = genderValue
    }

    override fun updateBirthdateView(birthdateTitle: String, birthdateValue: String) {
        mCalendarRowTitle.text = birthdateTitle
        mCalendarRowValue.text = birthdateValue
    }
}