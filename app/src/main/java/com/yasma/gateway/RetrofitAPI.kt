package com.yasma.gateway

import com.yasma.dto.Album
import com.yasma.dto.Post
import com.yasma.dto.PostDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitAPI {

    @GET("posts")
    fun postResponse(): Call<List<Post>>

    @GET("posts/{post_id}/comments")
    fun postDetailResponse(@Path("post_id") userId: Int): Call<List<PostDetail>>

    @GET("albums")
    fun albumResponse(): Call<List<Album>>
}