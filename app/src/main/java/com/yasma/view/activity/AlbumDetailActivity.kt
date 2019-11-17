package com.yasma.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.yasma.R
import com.yasma.appUtils.MyAppConstant
import com.yasma.dto.Album
import com.yasma.dto.AlbumDetail
import com.yasma.listeners.AlbumDetailActivityViewListener
import com.yasma.presenterImplModels.AlbumDetailActivityPresenterImpl
import com.yasma.view.adapters.AlbumDetailAdapter
import kotlinx.android.synthetic.main.activity_album_detail.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_post_detail.*
import kotlinx.android.synthetic.main.activity_post_detail.progress_circular
import kotlinx.android.synthetic.main.row_item_post.*

class AlbumDetailActivity : AppCompatActivity(), AlbumDetailActivityViewListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)
        toolbar.title = getString(R.string.album_detail_activity_title)
        val bundle: Bundle? = intent.extras
        val album = bundle?.getParcelable<Album>(MyAppConstant.SINGLE_ALBUM)
        tvTitle.text = album!!.title

        rvPhotos.layoutManager = GridLayoutManager(this,2)
        val albumDetailActivityPresenter = AlbumDetailActivityPresenterImpl(this)
        albumDetailActivityPresenter.getAlbumDetailFromApi(album.id)
        progress_circular.visibility = View.VISIBLE
    }

    override fun successResponse(body: List<AlbumDetail>?) {
        progress_circular.visibility = View.GONE
        val listData = body as ArrayList<AlbumDetail>
        rvPhotos.adapter = AlbumDetailAdapter(listData, this)
    }

    override fun failureResponse(message: String) {
        progress_circular.visibility = View.GONE
        Toast.makeText(this, "" + message, Toast.LENGTH_LONG).show()
    }
}
