package com.challenge.repos.views.activity

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.challenge.repos.R
import com.challenge.repos.databinding.ActivityMainBinding
import com.challenge.repos.utils.hide
import com.challenge.repos.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private var mActionBar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(mBinding.toolbar)
        mBinding.toolbar.setNavigationOnClickListener { onBackPressed() }
        mActionBar = supportActionBar
    }

    fun setToolbarTitle(title: String) {
        mActionBar?.title = title
    }

    fun setToolbarTitle(resId: Int) {
        setToolbarTitle(resId, true)
    }

    fun setToolbarTitle(resId: Int, showBackButton: Boolean) {
        mActionBar?.title = baseContext.getString(resId)
        mActionBar?.setDisplayHomeAsUpEnabled(showBackButton)
        mActionBar?.setDisplayShowHomeEnabled(showBackButton)
    }

    fun showToolbar() {
        if (!mBinding.toolbar.isShown) {
            mBinding.toolbar.show()
        }
    }

    fun hideToolbar() {
        mBinding.toolbar.hide()
    }
}