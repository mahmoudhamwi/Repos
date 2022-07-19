package com.challenge.repos.adapter

import androidx.recyclerview.widget.DiffUtil
import com.challenge.repos.model.Repo

/**
 * Created by Mahmoud Hamwi on 18-Jul-22.
 */
object DataComparator : DiffUtil.ItemCallback<Repo>() {

    override fun areItemsTheSame(
        oldItem: Repo,
        newItem: Repo
    ): Boolean = oldItem == newItem


    override fun areContentsTheSame(
        oldItem: Repo,
        newItem: Repo
    ): Boolean = oldItem.id == newItem.id
}