package com.challenge.repos.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.challenge.repos.data.Repository
import com.challenge.repos.model.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by Mahmoud Hamwi on 16-Jul-22.
 */
@HiltViewModel
class RepoViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun getRepos(): Flow<PagingData<Repo>> {
        return repository.getRepo()
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)
    }
}
