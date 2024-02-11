package com.tws.moments.data.api.entry

import com.google.gson.annotations.SerializedName

data class UserBean(
    val username: String? = null,
    val nick: String? = null,
    val avatar: String? = null,
    @SerializedName("profile-image")
    val profileImage: String? = null
)