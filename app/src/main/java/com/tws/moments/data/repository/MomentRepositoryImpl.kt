package com.tws.moments.data.repository

import com.tws.moments.data.api.MomentService
import com.tws.moments.data.api.entry.TweetBean
import com.tws.moments.data.api.entry.UserBean
import javax.inject.Inject

class MomentRepositoryImpl @Inject constructor(
    private val service: MomentService
): MomentRepository {

    override suspend fun fetchUser(): UserBean {
        return service.user("jsmith")
    }

    override suspend fun fetchTweets(): List<TweetBean> {
        return service.tweets("jsmith")
    }
}