package com.yasma.gateway

import com.yasma.dto.Album
import com.yasma.dto.AlbumDetail
import com.yasma.dto.Post
import com.yasma.dto.PostDetail
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitAPI {

    @GET("posts")
    fun postResponse(): Observable<List<Post>>

    @GET("posts/{post_id}/comments")
    fun postDetailResponse(@Path("post_id") userId: Int): Observable<List<PostDetail>>

    @GET("albums")
    fun albumResponse(): Observable<List<Album>>

    @GET("albums/{album_id}/photos")
    fun albumDetailResponse(@Path("album_id") userId: Int): Observable<List<AlbumDetail>>
}