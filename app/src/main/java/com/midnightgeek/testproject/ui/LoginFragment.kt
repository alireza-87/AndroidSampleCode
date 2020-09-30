package com.midnightgeek.testproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.midnightgeek.testproject.MainActivity
import com.midnightgeek.testproject.R
import com.midnightgeek.testproject.databinding.FragmentLoginBinding
import com.midnightgeek.testproject.utils.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoginFragment : DaggerFragment() {
    lateinit var binder: FragmentLoginBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: LoginFragmentViewModel

    companion object {
        val TAG = "LoginFragment"
        fun newInstance() = LoginFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        val view = binder.root
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(LoginFragmentViewModel::class.java)
        binder.viewmodel = viewModel
        initObserver()
        return view
    }

    fun initObserver() {
        viewModel.onClick.observe(this, Observer {
            if (it == 1 && isValidData()) {
                binder.prgBar.visibility = View.VISIBLE
                viewModel.login(binder.edtEmail.text.toString(), binder.edtPass.text.toString())
            } else if (it == 2) {
                fragmentManager?.popBackStack()
                (activity as MainActivity).initMainPage()
            }
        })

        viewModel.loginData.observe(this, Observer {
            binder.prgBar.visibility = View.GONE
            if (it.success) {
                Toast.makeText(context, getString(R.string.toast_login_success), Toast.LENGTH_LONG)
                    .show()
                (activity as MainActivity).initRegisterFragment()
            } else {
                Toast.makeText(context, getString(R.string.toast_login_fail), Toast.LENGTH_LONG)
                    .show()
                binder.edtEmail.setText("")
                binder.edtPass.setText("")

            }
        })
    }

    fun isValidData(): Boolean {
        if (binder.edtPass.text.isNullOrEmpty())
            return false
        if (binder.edtEmail.text.isNullOrEmpty())
            return false
        if (!binder.edtEmail.text.toString().contains("@"))
            return false
        if (!Regex("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+").containsMatchIn(binder.edtEmail.text.toString()))
            return false
        return true
    }
}