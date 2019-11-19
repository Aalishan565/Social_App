package com.yasma.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.yasma.R
import com.yasma.dto.Post
import com.yasma.gateway.CommunicationManager
import com.yasma.listeners.PostFragmentViewListener
import com.yasma.presenterImplModels.PostFragmentPresenterImpl
import com.yasma.view.adapters.PostAdapter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_post.*

/**
 * A simple [Fragment] subclass.
 */
class PostFragment : Fragment(), PostFragmentViewListener {
    private var listData: ArrayList<Post> = ArrayList()
    private var mCompositeDisposable: CompositeDisposable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvPost.layoutManager = activity?.let { LinearLayoutManager(it) }
        val postPresenter = PostFragmentPresenterImpl(this)
        postPresenter.getPostsFromApi()
        progress_circular.visibility = View.VISIBLE
    }

    override fun successResponse(body: List<Post>?) {
        progress_circular.visibility = View.GONE
        listData = body as ArrayList<Post>
        rvPost.adapter = activity?.let { PostAdapter(listData, it) }
    }

    override fun failureResponse(message: String) {
        progress_circular.visibility = View.GONE
        Toast.makeText(activity, "" + message, Toast.LENGTH_LONG).show()
    }

}
