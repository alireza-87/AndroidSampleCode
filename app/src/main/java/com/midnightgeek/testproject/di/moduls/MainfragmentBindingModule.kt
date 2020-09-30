package com.midnightgeek.testproject.di.moduls

import com.midnightgeek.testproject.ui.LoginFragment
import com.midnightgeek.testproject.ui.MainFragment
import com.midnightgeek.testproject.ui.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainfragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun provideLoginFragment(): LoginFragment?

    @ContributesAndroidInjector
    abstract fun provideMainFragment(): MainFragment?

    @ContributesAndroidInjector
    abstract fun provideRegisterFragment(): RegisterFragment?


}