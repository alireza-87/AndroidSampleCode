package com.midnightgeek.testproject.di.moduls

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun providesApplication(application: Application?): Context?

}