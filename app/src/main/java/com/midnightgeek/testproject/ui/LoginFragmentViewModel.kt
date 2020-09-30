package com.midnightgeek.testproject.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.midnightgeek.testproject.models.ModelResLoginRoot
import com.midnightgeek.testproject.repo.RepoHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginFragmentViewModel @Inject constructor(private val repoHandler: RepoHandler) :
    ViewModel() {
    val onClick = MutableLiveData<Int>()
    val loginData = MutableLiveData<ModelResLoginRoot>()

    private var disposable = CompositeDisposable()
    val TAG = "LoginFragmentViewModel"

    fun onClick(action: Int) {
        onClick.value = action
    }

    fun login(email: String, pass: String) {
        disposable.add(
            repoHandler.login(email, pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.e(TAG, " onSuccess -> ${it.success}")
                    loginData.value = it
                }

        )
    }
}