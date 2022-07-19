package com.challenge.repos.views.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.challenge.repos.R
import com.challenge.repos.databinding.FragmentRepoDetailsBinding
import com.challenge.repos.model.Repo
import com.challenge.repos.utils.DateUtil
import com.challenge.repos.utils.ImageLoaderUtil
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Mahmoud Hamwi on 14-Jul-22.
 */
@AndroidEntryPoint
class RepoDetailsFragment : BaseFragment<FragmentRepoDetailsBinding>() {
    private var repo: Repo? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        repo = arguments?.getSerializable("repo") as Repo
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ImageLoaderUtil.loadImage(repo?.owner?.avatarUrl, mBinding.repo.image)
        mBinding.repo.orgName.text = repo?.owner?.name
        mBinding.repo.repoName.text = repo?.name
        mBinding.repo.date.text = DateUtil.formatDate(repo?.createdAt, true)
        mBinding.startsCount.text = this.getString(R.string.stars_count, repo?.stargazersCount)
    }

    override fun bindFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRepoDetailsBinding {
        return DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_repo_details,
            container,
            false
        )
    }

    override fun setListeners() {

    }

    override fun onResume() {
        super.onResume()
        showToolbar()
        setToolbarTitle(R.string.repo_details, true)
    }
}