package com.midnightgeek.testproject.base

import com.facebook.drawee.backends.pipeline.Fresco
import com.midnightgeek.testproject.di.components.AppComponents
import com.midnightgeek.testproject.di.components.DaggerAppComponents
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * <p>Application Class</p>
 *  Application initializer
 */
class BaseApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component: AppComponents =
            DaggerAppComponents.builder().application(this)?.build()!!
        component.inject(this)
        return component
    }
}