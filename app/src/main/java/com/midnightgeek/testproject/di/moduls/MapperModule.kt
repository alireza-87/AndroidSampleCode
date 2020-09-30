package com.midnightgeek.testproject.di.moduls

import com.midnightgeek.testproject.utils.Mapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapperModule {
    @Provides
    @Singleton
    fun providMapper(): Mapper {
        return Mapper()
    }
}