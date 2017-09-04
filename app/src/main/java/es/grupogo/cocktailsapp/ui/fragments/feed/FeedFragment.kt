package es.grupogo.cocktailsapp.ui.fragments.feed

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import es.grupogo.cocktailsapp.R
import es.grupogo.cocktailsapp.domain.Cocktail
import es.grupogo.cocktailsapp.extensions.hide
import es.grupogo.cocktailsapp.extensions.show
import es.grupogo.cocktailsapp.ui.adapters.CockailsRecyclerAdapter
import es.grupogo.cocktailsapp.ui.dialogs.CheckboxDialog
import es.grupogo.cocktailsapp.ui.dialogs.EditTextDialog
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter
import kotlinx.android.synthetic.main.fragment_feed.*
import org.jetbrains.anko.toast
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import es.grupogo.cocktailsapp.ui.activities.detail.DetailActivity


/**
 * Created by jorge_cmata on 28/8/17.
 */
class FeedFragment : Fragment() , FeedContract.View {

    //New instance
    companion object {
        fun newInstance(): FeedFragment {
            val fragment = FeedFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    private val CODE_DETAIL = 1
    private lateinit var mPresenter : FeedContract.Presenter
    private lateinit var mRecycler : RecyclerView
    private var selectedFilters: List<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        //Instantiate presenter
        FeedPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setupRecycler()

        //Kick off presenter
        mPresenter.start()
    }

    fun setupRecycler() {
        mRecycler.layoutManager = LinearLayoutManager(context)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.menu_filter, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.filter -> {
                val dialog = CheckboxDialog.newInstance("Filter", arrayListOf("drop(15)", "filter{ cocktail.name.contains(\"a\")}", "slice(listOf(1,3,17,30)", "take(40)"), selectedFilters as ArrayList<String>?,"Ok", "Cancel")
                dialog.setCallback(object : CheckboxDialog.CheckBoxDialogListener {
                    override fun onPositiveClick(selectedOptions: List<String>){
                        selectedFilters = selectedOptions
                        mPresenter.applyFilter(selectedOptions)
                    }
                    override fun onNegativeClick() {}
                })
                dialog.show(childFragmentManager, "tag")
            }
        }
        return true
    }

    private fun bindViews(view: View){
        mRecycler = view.findViewById(R.id.recycler)
    }

    override fun setPresenter(presenter: FeedContract.Presenter) {
        mPresenter = presenter
    }


    override fun showLoader() {
        progressBar.show()
    }

    override fun hideLoader() {
        progressBar.hide()
    }

    override fun showItems(items: List<Cocktail>){

        val animation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
        mRecycler.layoutAnimation = animation

        val adapter = CockailsRecyclerAdapter(items, {
            startActivityForResult(DetailActivity.newIntent(context, it.id), CODE_DETAIL)
            activity.overridePendingTransition(R.anim.activity_in, R.anim.activity_out) //animation
        })
        mRecycler.adapter = SlideInBottomAnimationAdapter(adapter)
        //mRecycler.adapter = adapter
    }

    override fun handleError(t: Throwable) {
        context.toast(t.message.toString())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == CODE_DETAIL){
            mPresenter.getCocktailsDB()
        }
    }


}