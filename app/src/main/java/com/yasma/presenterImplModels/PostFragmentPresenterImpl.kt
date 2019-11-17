package com.yasma.presenterImplModels

import com.yasma.dto.Post
import com.yasma.gateway.CommunicationManager
import com.yasma.listeners.PostFragmentViewListener
import com.yasma.presenters.PostFragmentPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostFragmentPresenterImpl(private val postFragmentViewListener: PostFragmentViewListener) : PostFragmentPresenter {

    override fun getPostsFromApi() {

        val communicationManager = CommunicationManager().getInstance()
        val call = communicationManager.getPostListReq()
        call?.enqueue(object : Callback<List<Post>> {

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>?) {

                postFragmentViewListener.successResponse(response?.body())
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {

                postFragmentViewListener.failureResponse(t.toString())
            }
        })
    }
}