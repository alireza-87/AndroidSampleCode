package com.midnightgeek.testproject.di.components

import android.app.Application
import com.midnightgeek.testproject.base.BaseApplication
import com.midnightgeek.testproject.di.moduls.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [MapperModule::class, DataModule::class, AppModule::class, AndroidSupportInjectionModule::class, MainfragmentBindingModule::class, ActivityBindingModules::class])
interface AppComponents : AndroidInjector<DaggerApplication> {
    fun inject(application: BaseApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application?): Builder?
        fun build(): AppComponents?
    }

}