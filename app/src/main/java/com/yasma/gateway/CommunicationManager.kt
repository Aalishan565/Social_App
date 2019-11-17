package com.yasma.gateway

import com.yasma.appUtils.MyAppConstant
import com.yasma.dto.Album
import com.yasma.dto.AlbumDetail
import com.yasma.dto.Post
import com.yasma.dto.PostDetail
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class CommunicationManager {

    private var mCommunicationManager: CommunicationManager? = null

    fun getInstance(): CommunicationManager {
        if (mCommunicationManager == null) {
            mCommunicationManager = CommunicationManager()
        }
        return mCommunicationManager as CommunicationManager
    }


    private fun getRetrofitInstance(): RetrofitAPI? {
        var api: RetrofitAPI? = null
        val url: String?
        try {
            //Need to put in build config file
            url = "https://jsonplaceholder.typicode.com/"
            if (null != url) {
                val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                    .readTimeout(MyAppConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(MyAppConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                api = retrofit.create(RetrofitAPI::class.java)

            }
        } catch (e: Exception) {
            e.printStackTrace()

        }
        return api
    }

    fun getPostListReq(): Call<List<Post>>? {
        return try {
            getRetrofitInstance()?.postResponse()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getPostDetailListReq(postId: Int): Call<List<PostDetail>>? {
        return try {
            getRetrofitInstance()?.postDetailResponse(postId)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getAlbumListReq(): Call<List<Album>>? {
        return try {
            getRetrofitInstance()?.albumResponse()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getAlbumDetailListReq(albumId: Int): Call<List<AlbumDetail>>? {
        return try {
            getRetrofitInstance()?.albumDetailResponse(albumId)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}
