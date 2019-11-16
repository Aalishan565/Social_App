package com.yasma.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.yasma.R
import com.yasma.dto.Album
import com.yasma.listeners.AlbumViewListener
import com.yasma.presenterImplModels.AlbumPresenterImpl
import com.yasma.view.adapters.AlbumAdapter
import kotlinx.android.synthetic.main.fragment_album.*
import kotlinx.android.synthetic.main.fragment_post.progress_circular

/**
 * A simple [Fragment] subclass.
 */
class AlbumFragment : Fragment(), AlbumViewListener {
    private var listData: ArrayList<Album> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvAlbum.layoutManager = LinearLayoutManager(activity)
        val albumPresenter = AlbumPresenterImpl(this)
        albumPresenter.getAlbumsFromApi()
        progress_circular.visibility = View.VISIBLE
    }

    override fun successResponse(body: List<Album>?) {
        progress_circular.visibility = View.GONE
        listData = body as ArrayList<Album>
        rvAlbum.adapter = activity?.let { AlbumAdapter(listData, it) }
    }

    override fun failureResponse(message: String) {
        progress_circular.visibility = View.GONE
        Toast.makeText(activity, "" + message, Toast.LENGTH_LONG).show()
    }
}
