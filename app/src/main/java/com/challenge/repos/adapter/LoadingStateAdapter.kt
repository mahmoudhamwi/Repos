package com.challenge.repos.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.challenge.repos.App
import com.challenge.repos.R
import com.challenge.repos.databinding.LoadingViewBinding
import com.challenge.repos.utils.hide
import com.challenge.repos.utils.show

/**
 * Created by Mahmoud Hamwi on 18-Jul-22.
 */
class LoadingStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoadingStateAdapter.LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) =
        holder.bindViewHolder(loadState)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateViewHolder = LoadStateViewHolder.createViewHolder(parent, retry)

    class LoadStateViewHolder(
        private val binding: LoadingViewBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.retry.setOnClickListener { retry.invoke() }
        }

        fun bindViewHolder(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                Log.d("error_test", "error = " + loadState.error.message)
                val message = loadState.error.message
                binding.errorMsg.text = message
                if (message != App.getInstance().getString(R.string.no_more_data)) {
                    binding.retry.show()
                } else {
                    binding.retry.hide()
                }
            }
            binding.progress.isVisible = loadState is LoadState.Loading
        }

        companion object {
            fun createViewHolder(parent: ViewGroup, retry: () -> Unit): LoadStateViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LoadingViewBinding.inflate(layoutInflater, parent, false)
                return LoadStateViewHolder(binding, retry)
            }
        }
    }
}