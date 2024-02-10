package com.tws.moments.data.repository

import com.tws.moments.data.api.MomentService
import com.tws.moments.data.api.entry.TweetBean
import com.tws.moments.data.api.entry.UserBean
import javax.inject.Inject

interface MomentRepository {
    suspend fun fetchUser(): UserBean
    suspend fun fetchTweets(): List<TweetBean>
}