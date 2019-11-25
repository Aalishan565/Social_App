package com.yasma.presenterImplModels

import com.yasma.dto.Post
import com.yasma.gateway.CommunicationManager
import com.yasma.listeners.PostFragmentViewListener
import com.yasma.presenters.PostFragmentPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PostFragmentPresenterImpl(private val postFragmentViewListener: PostFragmentViewListener) :
    PostFragmentPresenter {
    var mCompositeDisposable = CompositeDisposable()
    override fun getPostsFromApi() {

        CommunicationManager().getInstance().getPostListReq()
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())?.subscribe(this::handleResponse, this::handleError)
            ?.let {
                mCompositeDisposable?.add(
                    it
                )
            }
    }

    private fun handleResponse(post: List<Post>) {
        postFragmentViewListener.successResponse(post)
        disposeCompositeDisposable()
    }

    fun disposeCompositeDisposable() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed) {
            mCompositeDisposable.dispose()
        }

    }

    private fun handleError(error: Throwable) {
        postFragmentViewListener.failureResponse(error.toString())
        disposeCompositeDisposable()
    }
}