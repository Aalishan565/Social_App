package com.yasma.presenterImplModels

import com.yasma.dto.PostDetail
import com.yasma.gateway.CommunicationManager
import com.yasma.listeners.PostDetailActivityViewListener
import com.yasma.presenters.PostDetailActivityPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostActivityDetailPresenterImpl(private val postDetailActivityViewListener: PostDetailActivityViewListener) :
    PostDetailActivityPresenter {
    override fun getPostDetailFromApi(postId: Int) {
        val communicationManager = CommunicationManager().getInstance()
        val call = communicationManager.getPostDetailListReq(postId)
        call?.enqueue(object : Callback<List<PostDetail>> {

            override fun onResponse(
                call: Call<List<PostDetail>>,
                response: Response<List<PostDetail>>?
            ) {
                postDetailActivityViewListener.successResponse(response?.body())
            }

            override fun onFailure(call: Call<List<PostDetail>>, t: Throwable) {

                postDetailActivityViewListener.failureResponse(t.toString())
            }
        })
    }
}