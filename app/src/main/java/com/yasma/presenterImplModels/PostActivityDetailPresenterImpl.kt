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
    override fun getPostDetailFromApi(postId: Int) {

        var mCompositeDisposable = CompositeDisposable()
        CommunicationManager().getInstance().getPostDetailListReq(postId)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())?.subscribe(this::handleResponse, this::handleError)
            ?.let {
                mCompositeDisposable?.add(
                    it
                )
            }
    }

    private fun handleResponse(post: List<PostDetail>) {
        postDetailActivityViewListener.successResponse(post)
    }

    private fun handleError(error: Throwable) {
        postDetailActivityViewListener.failureResponse(error.toString())
    }
}