package com.midnightgeek.testproject.di.moduls

import android.app.Application
import androidx.room.Room
import com.midnightgeek.testproject.repo.local.DataBaseLocal
import com.midnightgeek.testproject.repo.local.LocalDataSource
import com.midnightgeek.testproject.repo.local.interfaces.LocalRepo
import com.midnightgeek.testproject.repo.local.interfaces.UserDao
import com.midnightgeek.testproject.repo.remote.interfaces.RepoServices
import com.midnightgeek.testproject.utils.UnsafeOkHttpClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class, AppModule::class])
class DataModule {
    private val BASE_URL = "https://api.zarcle.com/"

    @Singleton
    @Provides
    fun provideRetrofit(application: Application?): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient =
            UnsafeOkHttpClient.getUnsafeOkHttpClient().newBuilder().addInterceptor(interceptor)
                .build()

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): RepoServices {
        return retrofit.create(RepoServices::class.java)
    }

    @Singleton
    @Provides
    fun providesRoomDatabase(application: Application?): DataBaseLocal {
        return Room.databaseBuilder(
            application!!,
            DataBaseLocal::class.java, "DataBase.db"
        ).build()
    }


    @Singleton
    @Provides
    fun providesUserDao(database: DataBaseLocal): UserDao {
        return database.userDao!!
    }

    @Singleton
    @Provides
    fun provideRepository(
        userDao: UserDao
    ): LocalRepo {
        return LocalDataSource(
            userDao
        )
    }

}