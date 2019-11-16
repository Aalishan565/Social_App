package com.yasma.presenterImplModels

import com.yasma.dto.Album
import com.yasma.dto.Post
import com.yasma.gateway.CommunicationManager
import com.yasma.listeners.AlbumViewListener
import com.yasma.listeners.PostViewListener
import com.yasma.presenters.AlbumPresenter
import com.yasma.presenters.PostPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumPresenterImpl(private val albumViewListener: AlbumViewListener) : AlbumPresenter {

    override fun getAlbumsFromApi() {

        val communicationManager = CommunicationManager().getInstance()
        val call = communicationManager.getAlbumListReq()
        call?.enqueue(object : Callback<List<Album>> {

            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>?) {
                albumViewListener.successResponse(response?.body())
            }

            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                albumViewListener.failureResponse(t.toString())
            }
        })
    }
}