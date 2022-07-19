package com.challenge.repos.data

import androidx.paging.PagingData
import com.challenge.repos.data.remote.RemoteRepository
import com.challenge.repos.model.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Mahmoud Hamwi on 16-Jul-22.
 */
class Repository @Inject constructor(
    private val remoteRepository: RemoteRepository
) {
    fun getRepo(): Flow<PagingData<Repo>> = remoteRepository.getRepos()
}