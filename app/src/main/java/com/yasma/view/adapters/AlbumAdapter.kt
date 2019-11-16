package com.yasma.view.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yasma.R
import com.yasma.appUtils.MyAppConstant
import com.yasma.dto.Album
import com.yasma.view.activity.AlbumDetailActivity
import kotlinx.android.synthetic.main.row_item_post.view.*

class AlbumAdapter(private val albumList: List<Album>, private val context: Context) :
    RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.row_item_albums,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = albumList[position].title
        holder.cardPost.setOnClickListener {
            val intent = Intent(context, AlbumDetailActivity::class.java)
            intent.putExtra(MyAppConstant.SINGLE_ALBUM, albumList[position])
            context.startActivity(intent)
        }

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle = view.tvTitle!!
        val cardPost = view.cardPost!!

    }
}