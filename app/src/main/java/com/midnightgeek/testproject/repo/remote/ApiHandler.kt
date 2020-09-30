package com.midnightgeek.testproject.repo.remote

import com.midnightgeek.testproject.repo.remote.interfaces.RepoServices
import com.midnightgeek.testproject.repo.remote.models.ModelLoginApi
import com.midnightgeek.testproject.repo.remote.models.ModelResLogin
import com.midnightgeek.testproject.repo.remote.models.ModelResRegister
import com.midnightgeek.testproject.repo.remote.models.ModelUserApi
import io.reactivex.Observable
import javax.inject.Inject

class ApiHandler @Inject constructor(var repoService: RepoServices) {


    fun register(modelReqRegister: ModelUserApi): Observable<ModelResRegister> {
        return this.repoService.register(modelReqRegister)
    }

    fun login(modelLoginApi: ModelLoginApi): Observable<ModelResLogin> {
        return this.repoService.login(modelLoginApi)
    }

}