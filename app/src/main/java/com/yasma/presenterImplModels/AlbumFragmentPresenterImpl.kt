package com.yasma.presenterImplModels

import com.yasma.dto.Album
import com.yasma.gateway.CommunicationManager
import com.yasma.listeners.AlbumFragmentViewListener
import com.yasma.presenters.AlbumFragmentPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumFragmentPresenterImpl(private val albumFragmentViewListener: AlbumFragmentViewListener) : AlbumFragmentPresenter {

    override fun getAlbumsFromApi() {

        val communicationManager = CommunicationManager().getInstance()
        val call = communicationManager.getAlbumListReq()
        call?.enqueue(object : Callback<List<Album>> {

            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>?) {
                albumFragmentViewListener.successResponse(response?.body())
            }

            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                albumFragmentViewListener.failureResponse(t.toString())
            }
        })
    }
}