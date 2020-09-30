package com.midnightgeek.testproject.di.moduls

import com.midnightgeek.testproject.repo.RepoHandler
import com.midnightgeek.testproject.utils.Authonticator
import com.midnightgeek.testproject.utils.Mapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AuthModule {
    @Provides
    @Singleton
    fun providAuthonticator(repoHandler: RepoHandler): Authonticator {
        return Authonticator(repoHandler)
    }
}