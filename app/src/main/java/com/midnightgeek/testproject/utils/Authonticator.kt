package com.midnightgeek.testproject.utils

import com.midnightgeek.testproject.repo.RepoHandler
import com.midnightgeek.testproject.repo.local.models.ModelUserDb
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class Authonticator @Inject constructor(val repoHandler: RepoHandler) {

    fun isLogin(): Single<ModelUserDb> {
        return repoHandler.getAuth()
    }
}