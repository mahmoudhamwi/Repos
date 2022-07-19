package com.challenge.repos.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.challenge.repos.App
import com.challenge.repos.R
import com.challenge.repos.model.Repo
import com.challenge.repos.utils.Constants
import com.challenge.repos.utils.Tools
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by Mahmoud Hamwi on 18-Jul-22.
 */
class DataSource(
    private val api: ApiService
) : PagingSource<Int, Repo>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        val pageIndex = params.key ?: 1
        val context = App.getInstance()
        if (!Tools.isNetworkAvailable(context)) {
            return LoadResult.Error(
                Exception(context.getString(R.string.please_check_your_internet_connection))
            )
        }
        return try {
            val response = api.getReposResponse(Constants.DEFAULT_ORGANIZATION_REPOS, pageIndex)
            if (response.isSuccessful) {
                val repos = response.body() ?: emptyList()
                val nextKey = pageIndex + 1
                if (repos.isNotEmpty()) {
                    LoadResult.Page(
                        repos, null,
                        if (nextKey == Constants.MAX_REPOS_PAGES) null else nextKey
                    )
                } else {
                    /*
                     * I did this because the total pages not sent in the response.
                     */
                    val message = if (pageIndex > 1) {
                        context.getString(R.string.no_more_data)
                    } else {
                        context.getString(R.string.no_data_available)
                    }
                    LoadResult.Error(Exception(message))
                }
            } else {
                LoadResult.Error(Exception(response.message()))
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}