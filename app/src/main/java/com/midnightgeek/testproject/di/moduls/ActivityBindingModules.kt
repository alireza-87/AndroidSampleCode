package com.midnightgeek.testproject.di.moduls

import com.midnightgeek.testproject.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModules {
    @ContributesAndroidInjector(modules = [MainfragmentBindingModule::class])
    abstract fun bindMainactivity(): MainActivity?
}