package com.midnightgeek.testproject.repo.local.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.midnightgeek.testproject.repo.local.models.ModelUserDb
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(data: ModelUserDb)

    @Query("DELETE FROM tbl_user")
    fun deleteUser(): Completable

    @Query("SELECT * FROM tbl_user")
    fun fetchUser(): Single<ModelUserDb>

    @Query("SELECT * FROM tbl_user")
    fun fetchCurrentUser(): ModelUserDb

}