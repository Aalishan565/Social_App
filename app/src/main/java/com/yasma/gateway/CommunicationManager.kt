package com.yasma.gateway

import com.yasma.appUtils.MyAppConstant
import com.yasma.dto.*
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
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
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                api = retrofit.create(RetrofitAPI::class.java)

            }
        } catch (e: Exception) {
            e.printStackTrace()

        }
        return api
    }

    fun getPostListReq(): Observable<List<Post>>? {
        return try {
            getRetrofitInstance()?.postResponse()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getPostDetailListReq(postId: Int): Observable<List<PostDetail>>? {
        return try {
            getRetrofitInstance()?.postDetailResponse(postId)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getAlbumListReq(): Observable<List<Album>>? {
        return try {
            getRetrofitInstance()?.albumResponse()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getAlbumDetailListReq(albumId: Int): Observable<List<AlbumDetail>>? {
        return try {
            getRetrofitInstance()?.albumDetailResponse(albumId)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    fun getUserDetailReq(userId: Int): Observable<User>? {
        return try {
            getRetrofitInstance()?.userDetailResponse(userId)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}
