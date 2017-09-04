package es.grupogo.cocktailsapp.ui.fragments.feed

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import es.grupogo.cocktailsapp.R
import es.grupogo.cocktailsapp.domain.Cocktail
import es.grupogo.cocktailsapp.extensions.hide
import es.grupogo.cocktailsapp.extensions.show
import es.grupogo.cocktailsapp.ui.adapters.CockailsRecyclerAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter
import kotlinx.android.synthetic.main.fragment_feed.*
import org.jetbrains.anko.toast
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController



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

    private lateinit var mPresenter : FeedContract.Presenter
    private lateinit var mRecycler : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        val adapter = CockailsRecyclerAdapter(items, {context.toast(it.name.toString())})
        mRecycler.adapter = SlideInBottomAnimationAdapter(adapter)
        //mRecycler.adapter = adapter
    }

    override fun handleError(t: Throwable) {
        context.toast(t.message.toString())
    }

    override fun showItemDetail(c: Cocktail) {
        context.toast("Open Detail")
    }
}