package com.challenge.repos.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.challenge.repos.R
import com.challenge.repos.utils.Tools
import com.challenge.repos.views.activity.MainActivity

/**
 * Created by Mahmoud Hamwi on 13-Jul-22.
 */
abstract class BaseFragment<B : ViewBinding> : Fragment() {
    protected lateinit var mBinding: B
    private lateinit var mActivity: MainActivity
    private lateinit var mAlertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val builder = AlertDialog.Builder(context!!)
        builder.setPositiveButton(context!!.getString(R.string.ok), null)
        mAlertDialog = builder.create()

        mBinding = bindFragment(inflater, container)
        setListeners()
        return mBinding.root
    }

    abstract fun bindFragment(inflater: LayoutInflater, container: ViewGroup?): B

    abstract fun setListeners()

    fun showToolbar() {
        mActivity.showToolbar()
    }

    fun hideToolbar() {
        mActivity.hideToolbar()
    }

    fun setToolbarTitle(resId: Int) {
        mActivity.setToolbarTitle(resId)
    }

    fun setToolbarTitle(resId: Int, showBackButton: Boolean) {
        mActivity.setToolbarTitle(resId, showBackButton)
    }

    fun setToolbarTitle(title: String) {
        mActivity.setToolbarTitle(title)
    }

    fun showAlert(title: String, message: String?) {
        mAlertDialog.setTitle(title)
        mAlertDialog.setMessage(message)
        mAlertDialog.show()
    }

    fun showAlert(titleResId: Int, messageResId: Int) {
        mAlertDialog.setTitle(titleResId)
        mAlertDialog.setMessage(getString(messageResId))
        mAlertDialog.show()
    }

    fun showAlert(message: String?) {
        mAlertDialog.setMessage(message)
        mAlertDialog.show()
    }

    fun hideAlert() {
        mAlertDialog.hide()
    }

    fun isNetworkAvailable(): Boolean {
        return isNetworkAvailable(true)
    }

    fun isNetworkAvailable(showDialog: Boolean): Boolean {
        if (!Tools.isNetworkAvailable(context)) {
            if (showDialog) {
                showAlert(R.string.connection_error, R.string.please_check_your_internet_connection)
            }
            return false
        }
        return true
    }

}