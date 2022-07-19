package com.challenge.repos.views.fragments

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.challenge.repos.R
import com.challenge.repos.databinding.FragmentSplashBinding
import com.challenge.repos.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Mahmoud Hamwi on 13-Jul-22.
 */
@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    private val handler = Handler(Looper.getMainLooper())
    private val splashRunnable = {
        findNavController().navigate(R.id.action_splashFragment_to_reposFragment)
    }

    override fun setListeners() {

    }

    override fun bindFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding {
        return DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_splash,
            container,
            false
        )
    }

    override fun onResume() {
        super.onResume()
        hideToolbar()
        handler.postDelayed(splashRunnable, Constants.SPLASH_DURATION)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(splashRunnable)
    }
}