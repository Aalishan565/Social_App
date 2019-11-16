package com.yasma.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yasma.R
import com.yasma.appUtils.MyAppConstant
import com.yasma.dto.Post
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_post_detail.*
import kotlinx.android.synthetic.main.row_item_post.*

class PostDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
        toolbar.title = getString(R.string.detail_activity_title)
        val bundle: Bundle? = intent.extras
        val post = bundle?.getParcelable<Post>(MyAppConstant.SINGLE_POST)
        tvPostId.text = post!!.id.toString()
        tvTitle.text = post.title
        tvBody.text = post.body

    }
}
