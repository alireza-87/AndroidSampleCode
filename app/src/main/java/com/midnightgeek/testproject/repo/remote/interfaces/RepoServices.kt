package com.midnightgeek.testproject.repo.remote.interfaces

import com.midnightgeek.testproject.repo.remote.models.ModelLoginApi
import com.midnightgeek.testproject.repo.remote.models.ModelResLogin
import com.midnightgeek.testproject.repo.remote.models.ModelResRegister
import com.midnightgeek.testproject.repo.remote.models.ModelUserApi
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface RepoServices {

    @POST("/zarcle/zarcle/login")
    fun login(@Body modelLoginApi: ModelLoginApi): Observable<ModelResLogin>

    @POST("/zarcle/zarcle/register")
    fun register(@Body modelReqRegister: ModelUserApi): Observable<ModelResRegister>

}