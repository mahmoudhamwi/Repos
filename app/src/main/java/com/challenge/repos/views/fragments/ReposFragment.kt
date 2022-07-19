package com.challenge.repos.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.challenge.repos.R
import com.challenge.repos.adapter.LoadingStateAdapter
import com.challenge.repos.adapter.RepoAdapter
import com.challenge.repos.databinding.FragmentReposBinding
import com.challenge.repos.utils.hide
import com.challenge.repos.utils.show
import com.challenge.repos.viewModels.RepoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Mahmoud Hamwi on 14-Jul-22.
 */
@AndroidEntryPoint
class ReposFragment : BaseFragment<FragmentReposBinding>() {

    @Inject
    lateinit var mAdapter: RepoAdapter

    private val mViewModel: RepoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.recyclerView.layoutManager = LinearLayoutManager(context)
        mBinding.recyclerView.setHasFixedSize(true)
        mBinding.recyclerView.adapter = mAdapter
        mBinding.recyclerView.adapter = mAdapter.withLoadStateHeaderAndFooter(
            header = LoadingStateAdapter { mAdapter.retry() },
            footer = LoadingStateAdapter { mAdapter.retry() }
        )
        mAdapter.addLoadStateListener { loadState ->
            configureViews(loadState)
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
                ?: loadState.refresh as? LoadState.Error
            errorState?.let {
                val message = errorState.error.message
                /*
                 * I did this because the total pages not sent in the response.
                 */
                if (!message.equals(getString(R.string.no_more_data))) {
                    showAlert(getString(R.string.error), errorState.error.message)
                }
            }
        }
        loadData()
    }

    override fun bindFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentReposBinding {
        return DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_repos,
            container,
            false
        )
    }

    override fun setListeners() {
        mAdapter.onItemClick = {
            val bundle = bundleOf("repo" to it)
            findNavController().navigate(R.id.repoDetailsFragment, bundle)
        }

        mBinding.retryBtn.setOnClickListener {
            mAdapter.retry()
        }
    }

    private fun loadData() {
        lifecycleScope.launch {
            mViewModel.getRepos().collectLatest {
                mAdapter.submitData(it)
            }
        }
    }

    private fun configureViews(loadState: CombinedLoadStates) {
        when (loadState.source.refresh) {
            is LoadState.NotLoading -> {
                mBinding.recyclerView.show()
                mBinding.placeholder.hide()
                mBinding.placeholder.stopShimmer()
                if (mAdapter.itemCount < 1) {
                    mBinding.retryBtn.show()
                }
            }
            is LoadState.Loading -> {
                mBinding.placeholder.show()
                mBinding.placeholder.startShimmer()
                mBinding.recyclerView.hide()
                mBinding.retryBtn.hide()
            }
            is LoadState.Error -> {
                mBinding.recyclerView.hide()
                mBinding.placeholder.hide()
                mBinding.placeholder.stopShimmer()
                mBinding.retryBtn.show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        showToolbar()
        setToolbarTitle(R.string.app_name, false)
    }
}