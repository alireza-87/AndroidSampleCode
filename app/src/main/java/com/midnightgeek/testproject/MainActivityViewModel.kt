package com.midnightgeek.testproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.midnightgeek.testproject.repo.local.models.ModelUserDb
import com.midnightgeek.testproject.utils.Authonticator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(val authonticator: Authonticator) : ViewModel(){
    val isLogin = MutableLiveData<Boolean>()

    fun isLogin(){
        authonticator.isLogin()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : DisposableSingleObserver<ModelUserDb>() {
                override fun onSuccess(users: ModelUserDb) {
                    isLogin.value=true
                }

                override fun onError(e: Throwable) {
                    isLogin.value=false
                }
            })


    }
}