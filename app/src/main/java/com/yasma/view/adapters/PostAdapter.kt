package com.yasma.view.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yasma.R
import com.yasma.appUtils.MyAppConstant
import com.yasma.dto.Post
import com.yasma.view.activity.PostDetailActivity
import kotlinx.android.synthetic.main.row_item_post.view.*

class PostAdapter(private val postList: List<Post>, private val context: Context) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_item_post, parent, false))
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = postList[position].title
        holder.tvBody.text = postList[position].body
        holder.cardPost.setOnClickListener {
            val intent = Intent(context, PostDetailActivity::class.java)
            intent.putExtra(MyAppConstant.SINGLE_POST, postList[position])
            context.startActivity(intent)
        }

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle = view.tvTitle!!
        val tvBody = view.tvBody!!
        val cardPost = view.cardPost!!

    }
}