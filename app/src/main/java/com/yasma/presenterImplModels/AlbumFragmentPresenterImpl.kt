package com.yasma.presenterImplModels

import com.yasma.dto.Album
import com.yasma.dto.Post
import com.yasma.gateway.CommunicationManager
import com.yasma.listeners.AlbumFragmentViewListener
import com.yasma.presenters.AlbumFragmentPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumFragmentPresenterImpl(private val albumFragmentViewListener: AlbumFragmentViewListener) :
    AlbumFragmentPresenter {
    var mCompositeDisposable = CompositeDisposable()
    override fun getAlbumsFromApi() {

        CommunicationManager().getInstance().getAlbumListReq()
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())?.subscribe(this::handleResponse, this::handleError)
            ?.let {
                mCompositeDisposable?.add(
                    it
                )
            }
    }

    fun disposeCompositeDisposable() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed) {
            mCompositeDisposable.dispose()
        }

    }

    private fun handleResponse(album: List<Album>) {
        albumFragmentViewListener.successResponse(album)
        disposeCompositeDisposable()
    }

    private fun handleError(error: Throwable) {
        albumFragmentViewListener.failureResponse(error.toString())
        disposeCompositeDisposable()
    }
}