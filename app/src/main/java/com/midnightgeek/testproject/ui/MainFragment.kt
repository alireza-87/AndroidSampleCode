package com.midnightgeek.testproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.midnightgeek.testproject.R
import com.midnightgeek.testproject.databinding.FragmentLoginBinding
import com.midnightgeek.testproject.databinding.FragmentMainBinding
import com.midnightgeek.testproject.utils.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MainFragment : DaggerFragment() {
    lateinit var binder: FragmentMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainFragmentViewModel

    companion object {
        val TAG = "LoginFragment"
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        val view = binder.root
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MainFragmentViewModel::class.java)
        binder.viewmodel = viewModel
        return view
    }

}