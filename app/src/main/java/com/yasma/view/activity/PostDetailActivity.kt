package com.yasma.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.yasma.R
import com.yasma.appUtils.MyAppConstant
import com.yasma.dto.Post
import com.yasma.dto.PostDetail
import com.yasma.listeners.PostDetailActivityViewListener
import com.yasma.presenterImplModels.PostActivityDetailPresenterImpl
import com.yasma.view.adapters.PostDetailAdapter
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_post_detail.*
import kotlinx.android.synthetic.main.fragment_post.progress_circular
import kotlinx.android.synthetic.main.row_item_albums.tvTitle
import kotlinx.android.synthetic.main.row_item_post.*

class PostDetailActivity : AppCompatActivity(), PostDetailActivityViewListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
        toolbar.title = getString(R.string.detail_activity_title)
        val bundle: Bundle? = intent.extras
        val post = bundle?.getParcelable<Post>(MyAppConstant.SINGLE_POST)
        tvTitle.text = post!!.title
        tvBody.text = post.body
        rvPostDetail.layoutManager = LinearLayoutManager(this)
        val postDetailActivityPresenter = PostActivityDetailPresenterImpl(this)
        post.id.let { postDetailActivityPresenter.getPostDetailFromApi(it) }
        progress_circular.visibility = View.VISIBLE
    }

    override fun successResponse(body: List<PostDetail>?) {
        progress_circular.visibility = View.GONE
        val listData = body as ArrayList<PostDetail>
        rvPostDetail.adapter = PostDetailAdapter(listData, this)
    }

    override fun failureResponse(message: String) {
        progress_circular.visibility = View.GONE
        Toast.makeText(this, "" + message, Toast.LENGTH_LONG).show()
    }
}
