package com.challenge.repos.data.remote

import com.challenge.repos.model.Repo
import com.challenge.repos.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Mahmoud Hamwi on 16-Jul-22.
 */
interface ApiService {

    @GET("orgs/{org}/repos")
    suspend fun getReposResponse(
        @Path("org") organizationName: String = Constants.DEFAULT_ORGANIZATION_REPOS,
        @Query("page") page: Int = 1,
        @Query("per_page") prePage: Int = 30
    ): Response<List<Repo>>

}