package com.tws.moments.data.api

import com.tws.moments.data.api.entry.TweetBean
import com.tws.moments.data.api.entry.UserBean
import com.tws.moments.data.api.response.TweetResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MomentService {

    /**
     * https://thoughtworks-ios.herokuapp.com/user/jsmith
     */
    @GET("user/{name}")
    suspend fun user(@Path("name") user: String): UserBean

    /**
     * https://thoughtworks-ios.herokuapp.com/user/jsmith/tweets
     */
    @GET("user/{name}/tweets")
//    suspend fun tweets(@Path("name") user: String): List<TweetBean>
    suspend fun tweets(@Path("name") user: String): List<TweetResponse>
}
