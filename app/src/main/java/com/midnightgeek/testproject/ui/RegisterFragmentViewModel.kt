package com.midnightgeek.testproject.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.midnightgeek.testproject.models.ModelResRegisterRoot
import com.midnightgeek.testproject.repo.RepoHandler
import com.midnightgeek.testproject.repo.remote.models.ModelUserApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegisterFragmentViewModel @Inject constructor(val repoHandler: RepoHandler) : ViewModel() {
    private var disposable = CompositeDisposable()
    val onClick = MutableLiveData<Int>()
    val registerData = MutableLiveData<ModelResRegisterRoot>()


    val TAG = "RegisterViewModel"

    fun onClick(action: Int) {
        onClick.value = action
    }

    fun regiser(name: String, family: String, email: String, pass: String,photo:String) {
        var test = ModelUserApi()
        test.email = email
        test.first_name = name
        test.last_name = family
        test.password = pass
        test.photo = photo

        disposable.add(
            repoHandler.register(test)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.e(TAG, " onSuccess -> ${it}")
                    registerData.value = it
                }

        )

    }
}