package com.yasma.presenterImplModels

import com.yasma.dto.PostDetail
import com.yasma.gateway.CommunicationManager
import com.yasma.listeners.PostDetailActivityViewListener
import com.yasma.presenters.PostDetailActivityPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostActivityDetailPresenterImpl(private val postDetailActivityViewListener: PostDetailActivityViewListener) :
    PostDetailActivityPresenter {
    var mCompositeDisposable = CompositeDisposable()
    override fun getPostDetailFromApi(postId: Int) {


        CommunicationManager().getInstance().getPostDetailListReq(postId)
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

    private fun handleResponse(post: List<PostDetail>) {
        postDetailActivityViewListener.successResponse(post)
        disposeCompositeDisposable()
    }

    private fun handleError(error: Throwable) {
        postDetailActivityViewListener.failureResponse(error.toString())
        disposeCompositeDisposable()
    }
}