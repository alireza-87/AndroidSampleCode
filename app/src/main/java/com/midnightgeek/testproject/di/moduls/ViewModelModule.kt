package com.midnightgeek.testproject.di.moduls

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.midnightgeek.testproject.MainActivityViewModel
import com.midnightgeek.testproject.di.utils.ViewModelKey
import com.midnightgeek.testproject.ui.LoginFragmentViewModel
import com.midnightgeek.testproject.ui.MainFragmentViewModel
import com.midnightgeek.testproject.ui.RegisterFragmentViewModel
import com.midnightgeek.testproject.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivitytViewModel(mainActivityViewModel: MainActivityViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    abstract fun bindMainFragmentViewModel(mainFragmentViewModel: MainFragmentViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(LoginFragmentViewModel::class)
    abstract fun bindLoginFragmentViewModel(loginViewModel: LoginFragmentViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(RegisterFragmentViewModel::class)
    abstract fun binRegisterFragmentViewModel(registerFragmentViewModel: RegisterFragmentViewModel?): ViewModel?


    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory?): ViewModelProvider.Factory?

}