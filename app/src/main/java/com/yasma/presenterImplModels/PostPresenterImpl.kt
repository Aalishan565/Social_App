package com.yasma.presenterImplModels

import com.yasma.dto.Post
import com.yasma.gateway.CommunicationManager
import com.yasma.listeners.PostViewListener
import com.yasma.presenters.PostPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostPresenterImpl(private val postViewListener: PostViewListener) : PostPresenter {

    override fun getPostsFromApi() {

        val communicationManager = CommunicationManager().getInstance()
        val call = communicationManager.getPostListReq()
        call?.enqueue(object : Callback<List<Post>> {

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>?) {
                postViewListener.successResponse(response?.body())
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                postViewListener.failureResponse(t.toString())
            }
        })
    }
}