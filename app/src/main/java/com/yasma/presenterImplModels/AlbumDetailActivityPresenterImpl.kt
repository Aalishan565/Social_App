package com.yasma.presenterImplModels

import com.yasma.dto.AlbumDetail
import com.yasma.gateway.CommunicationManager
import com.yasma.listeners.AlbumDetailActivityViewListener
import com.yasma.presenters.AlbumDetailActivityPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumDetailActivityPresenterImpl(private val albumDetailActivityViewListener: AlbumDetailActivityViewListener) :
    AlbumDetailActivityPresenter {

    override fun getAlbumDetailFromApi(albumId: Int) {

        val communicationManager = CommunicationManager().getInstance()
        val call = communicationManager.getAlbumDetailListReq(albumId)
        call?.enqueue(object : Callback<List<AlbumDetail>> {

            override fun onResponse(
                call: Call<List<AlbumDetail>>,
                response: Response<List<AlbumDetail>>?
            ) {
                albumDetailActivityViewListener.successResponse(response?.body())
            }

            override fun onFailure(call: Call<List<AlbumDetail>>, t: Throwable) {

                albumDetailActivityViewListener.failureResponse(t.toString())
            }
        })
    }

}