package com.tws.moments.data.api.response

import com.tws.moments.data.api.entry.TweetBean

data class TweetResponse(
    val sender: SenderResponse? = null,
    val content: String? = null,
    val images: List<ImageResponse>? = null,
    val comments: List<CommentResponse>? = null,
    val error: String? = null
)

data class SenderResponse(
    val nick: String? = null,
    val username: String? = null,
    val avatar: String? = null
)

data class CommentResponse(
    val content: String? = null,
    val sender: SenderResponse? = null
)

data class ImageResponse(
    val url: String? = null
)

fun TweetResponse.toTweetBean(): TweetBean{
    return TweetBean(
        content = content
    )
}
