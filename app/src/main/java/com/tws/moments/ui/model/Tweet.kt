package com.tws.moments.ui.model

import com.tws.moments.data.api.response.CommentResponse
import com.tws.moments.data.api.response.ImageResponse
import com.tws.moments.data.api.response.SenderResponse
import com.tws.moments.data.api.response.TweetResponse

data class Tweet(
    val sender: Sender? = null,
    val content: String? = null,
    val images: List<Image>? = null,
    val comments: List<Comment>? = null,
    val error: String? = null
)

data class Sender(
    val nick: String? = null,
    val username: String? = null,
    val avatar: String? = null
)

data class Comment(
    val content: String? = null,
    val sender: Sender? = null
)

data class Image(
    val url: String? = null
)

fun TweetResponse.toTweet(): Tweet{
    return Tweet(
        sender = sender?.toSender(),
        content = content,
        images = images?.map { it.toImages() },
        comments = comments?.map { it.toComment() },
        error = error
    )
}

fun ImageResponse.toImages(): Image{
    return Image(
        url = url
    )
}

fun CommentResponse.toComment(): Comment{
    return Comment(
        content = content,
        sender = sender?.toSender()
    )
}

fun SenderResponse.toSender(): Sender {
    return Sender(
        nick = nick,
        username = username,
        avatar = avatar
    )
}
