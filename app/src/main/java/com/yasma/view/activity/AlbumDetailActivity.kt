package com.yasma.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yasma.R
import com.yasma.appUtils.MyAppConstant
import com.yasma.dto.Album
import com.yasma.dto.Post
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_post_detail.*
import kotlinx.android.synthetic.main.row_item_post.*

class AlbumDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)
        toolbar.title = getString(R.string.album_detail_activity_title)
        val bundle: Bundle? = intent.extras
        val album = bundle?.getParcelable<Album>(MyAppConstant.SINGLE_ALBUM)
        tvPostId.text = album!!.id.toString()
        tvTitle.text = album.title
    }
}
