package com.challenge.repos.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Mahmoud Hamwi on 14-Jul-22.
 */
data class Owner(
    @SerializedName("login")
    val name: String,

    @SerializedName("avatar_url")
    val avatarUrl: String
)
