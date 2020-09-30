package com.midnightgeek.testproject.repo.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.midnightgeek.testproject.repo.local.interfaces.UserDao
import com.midnightgeek.testproject.repo.local.models.ModelUserDb

@Database(
    entities = [ModelUserDb::class],
    version = 1,
    exportSchema = false
)
abstract class DataBaseLocal : RoomDatabase() {
    abstract val userDao: UserDao?

}
