package com.midnightgeek.testproject.repo

import com.midnightgeek.testproject.models.ModelResLoginRoot
import com.midnightgeek.testproject.models.ModelResRegisterRoot
import com.midnightgeek.testproject.repo.local.LocalDataSource
import com.midnightgeek.testproject.repo.local.models.ModelUserDb
import com.midnightgeek.testproject.repo.remote.ApiHandler
import com.midnightgeek.testproject.repo.remote.models.ModelLoginApi
import com.midnightgeek.testproject.repo.remote.models.ModelUserApi
import com.midnightgeek.testproject.utils.Mapper
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepoHandler @Inject constructor(
    private var localDataSource: LocalDataSource,
    private var apiHandler: ApiHandler,
    private var mapper: Mapper
) {
    private val TAG = "RepoHandler"
    fun register(modelReqRegister: ModelUserApi): Observable<ModelResRegisterRoot> {
        return Observable.defer {
            apiHandler.register(modelReqRegister).subscribeOn(Schedulers.io()).map {
                return@map mapper.map(it, ModelResRegisterRoot::class.java) as ModelResRegisterRoot
            }
        }
    }

    fun login(email: String, pass: String): Observable<ModelResLoginRoot> {
        var model = ModelLoginApi()
        model.email = email
        model.password = pass
        return Observable.defer {
            apiHandler.login(model).subscribeOn(Schedulers.io()).map {
                if (it.success){
                    //save user here ...
                }
                return@map mapper.map(it, ModelResLoginRoot::class.java) as ModelResLoginRoot
            }
        }

    }

    fun getAuth(): Single<ModelUserDb> {
        return localDataSource.getUser()
    }


}