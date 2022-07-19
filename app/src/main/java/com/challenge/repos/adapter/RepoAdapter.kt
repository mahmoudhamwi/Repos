package com.challenge.repos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.challenge.repos.databinding.ItemRepoBinding
import com.challenge.repos.model.Repo
import com.challenge.repos.utils.DateUtil
import com.challenge.repos.utils.ImageLoaderUtil
import javax.inject.Inject

/**
 * Created by Mahmoud Hamwi on 16-Jul-22.
 */
class RepoAdapter @Inject constructor() :
    PagingDataAdapter<Repo, RepoAdapter.DataViewHolder>(DataComparator) {

    var onItemClick: ((Repo) -> Unit)? = null

    inner class DataViewHolder(private val binding: ItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: Repo) {
            ImageLoaderUtil.loadImage(repo.owner.avatarUrl, binding.image)
            binding.orgName.text = repo.owner.name
            binding.repoName.text = repo.name
            binding.date.text = DateUtil.formatDate(repo.createdAt, true)
            binding.parent.setOnClickListener {
                onItemClick?.invoke(repo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(
        ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}