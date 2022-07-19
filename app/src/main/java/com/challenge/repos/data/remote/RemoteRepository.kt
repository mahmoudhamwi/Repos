package com.challenge.repos.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.challenge.repos.model.Repo
import com.challenge.repos.utils.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Mahmoud Hamwi on 16-Jul-22.
 */
class RemoteRepository @Inject constructor() {

    fun getRepos(): Flow<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.MAX_REPOS_PAGES,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                DataSource(ApiClient.apiService)
            }
        ).flow
    }
}