package com.midnightgeek.testproject.repo.local.interfaces

import com.midnightgeek.testproject.repo.local.models.ModelUserDb
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

interface LocalRepo {
    //User
    fun getUser(): Single<ModelUserDb>
    fun saveUser(data: ModelUserDb)
    fun removeUser(): Completable
    fun getCurrentUser(): ModelUserDb

}