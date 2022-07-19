package com.challenge.repos.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Mahmoud Hamwi on 14-Jul-22.
 */
data class Repo(
    val id: String,
    val name: String,
    val owner: Owner,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("stargazers_count")
    val stargazersCount: Int
) : Serializable
