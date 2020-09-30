package com.midnightgeek.testproject.repo.local

import com.midnightgeek.testproject.repo.local.interfaces.LocalRepo
import com.midnightgeek.testproject.repo.local.interfaces.UserDao
import com.midnightgeek.testproject.repo.local.models.ModelUserDb
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class LocalDataSource @Inject constructor(

    private val userDao: UserDao
) : LocalRepo {
    override fun getUser(): Single<ModelUserDb> {
        return userDao.fetchUser()
    }

    override fun saveUser(data: ModelUserDb) {
        userDao.saveUser(data)
    }

    override fun removeUser(): Completable {
        return userDao.deleteUser()
    }

    override fun getCurrentUser(): ModelUserDb {
        return userDao.fetchCurrentUser()
    }


}