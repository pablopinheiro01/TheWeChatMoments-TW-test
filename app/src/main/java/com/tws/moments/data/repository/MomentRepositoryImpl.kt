package com.tws.moments.data.repository

import android.util.Log
import com.tws.moments.data.api.MomentService
import com.tws.moments.data.api.entry.TweetBean
import com.tws.moments.data.api.entry.UserBean
import com.tws.moments.data.api.response.toTweetBean
import javax.inject.Inject

class MomentRepositoryImpl @Inject constructor(
    private val service: MomentService
) {

    companion object{
        const val TAG = "MomentRepositoryImpl"
    }

    suspend fun fetchUser(): UserBean {
        return service.user("jsmith")
    }

    suspend fun fetchTweets(): List<TweetBean>{
        return service.tweets("jsmith").map {
            Log.i(TAG, "fetchTweets: $it")
            it.toTweetBean()
        }
    }

//    suspend fun fetchTweets(): List<TweetBean> {
//        return service.tweets("jsmith")
//    }
}