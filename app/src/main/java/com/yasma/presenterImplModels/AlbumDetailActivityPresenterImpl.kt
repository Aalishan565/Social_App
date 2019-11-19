package com.yasma.presenterImplModels

import com.yasma.dto.AlbumDetail
import com.yasma.dto.PostDetail
import com.yasma.gateway.CommunicationManager
import com.yasma.listeners.AlbumDetailActivityViewListener
import com.yasma.presenters.AlbumDetailActivityPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumDetailActivityPresenterImpl(private val albumDetailActivityViewListener: AlbumDetailActivityViewListener) :
    AlbumDetailActivityPresenter {

    override fun getAlbumDetailFromApi(albumId: Int) {

        var mCompositeDisposable = CompositeDisposable()
        CommunicationManager().getInstance().getAlbumDetailListReq(albumId)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())?.subscribe(this::handleResponse, this::handleError)
            ?.let {
                mCompositeDisposable?.add(
                    it
                )
            }
    }

    private fun handleResponse(albumDetail: List<AlbumDetail>) {
        albumDetailActivityViewListener.successResponse(albumDetail)
    }

    private fun handleError(error: Throwable) {
        albumDetailActivityViewListener.failureResponse(error.toString())
    }
}