package com.yasma.view.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.yasma.R
import com.yasma.dto.AlbumDetail
import kotlinx.android.synthetic.main.row_item_albums_detail.view.*

class AlbumDetailAdapter(private val albumList: List<AlbumDetail>, private val context: Context) :
    RecyclerView.Adapter<AlbumDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.row_item_albums_detail,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(albumList[position].url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.progress_circular.visibility = View.GONE
                    holder.ivPhoto.visibility = View.VISIBLE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.progress_circular.visibility = View.GONE
                    holder.ivPhoto.visibility = View.VISIBLE
                    return false
                }
            })
            .into(holder.ivPhoto)
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivPhoto = view.ivPhoto!!
        val progress_circular = view.progress_circular!!

    }
}