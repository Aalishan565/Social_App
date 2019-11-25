package com.yasma.presenterImplModels

import com.yasma.dto.AlbumDetail
import com.yasma.dto.User
import com.yasma.gateway.CommunicationManager
import com.yasma.listeners.UserDetailActivityViewListener
import com.yasma.presenters.UserDetailActivityPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserDetailActivityPresenterImpl(private val userDetailActivityViewListener: UserDetailActivityViewListener) :
    UserDetailActivityPresenter {
    private var mCompositeDisposable = CompositeDisposable()
    override fun callUserDetailApi(userId: Int) {
        CommunicationManager().getInstance().getUserDetailReq(userId)
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

    private fun handleResponse(userDetail: User) {
        userDetailActivityViewListener.successResponse(userDetail)
        disposeCompositeDisposable()
    }

    private fun handleError(error: Throwable) {
        userDetailActivityViewListener.failureResponse(error.toString())
        disposeCompositeDisposable()
    }
}