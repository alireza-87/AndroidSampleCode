package com.midnightgeek.testproject

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.midnightgeek.testproject.databinding.ActivityMainBinding
import com.midnightgeek.testproject.ui.LoginFragment
import com.midnightgeek.testproject.ui.MainFragment
import com.midnightgeek.testproject.ui.RegisterFragment
import com.midnightgeek.testproject.utils.Authonticator
import com.midnightgeek.testproject.utils.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var binder: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
        binder.viewmodel = viewModel
        initObserv()
        Thread {
            viewModel.isLogin()
        }.start()
    }

    fun initObserv(){
        viewModel.isLogin.observe(this, Observer {
            if (it){
                initMainPage()
            }else{
                initLoginFragment()
            }
        })
    }

    fun initRegisterFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frlMain, RegisterFragment.newInstance())
            .addToBackStack(RegisterFragment.TAG)
            .commit()

    }

    fun initLoginFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frlMain, LoginFragment.newInstance())
            .commit()

    }

    fun initMainPage() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frlMain, MainFragment.newInstance())
            .addToBackStack(MainFragment.TAG)
            .commit()

    }


}