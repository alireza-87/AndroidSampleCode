package com.midnightgeek.testproject.utils

import android.util.Log
import com.midnightgeek.testproject.models.ModelResLoginRoot
import com.midnightgeek.testproject.models.ModelResRegisterRoot
import com.midnightgeek.testproject.models.ModelUserRoot
import com.midnightgeek.testproject.repo.local.models.ModelUserDb
import com.midnightgeek.testproject.repo.remote.models.ModelResLogin
import com.midnightgeek.testproject.repo.remote.models.ModelResRegister
import com.midnightgeek.testproject.repo.remote.models.ModelUserApi

/**
 * <p>Class Mapper</p>
 * This class used to map application classes to each others
 */
class Mapper {
    private val TAG = "Mapper"
    fun map(data: Any, T: Class<*>): Any? {
        try {
            val result = T.newInstance()
            if (data is ModelUserDb) {//DataBase Models Convert
                if (result is ModelUserRoot) {
                    result.uid = data.uid
                    result.firstName = data.firstName!!
                    result.lastName = data.lastName!!
                    result.email = data.email!!
                    result.avatar = data.avatar!!
                }
            } else if (data is ModelUserRoot) {//Core Models Convert
                if (result is ModelUserDb) {
                    result.uid = data.uid
                    result.firstName = data.firstName
                    result.lastName = data.lastName
                    result.email = data.email
                    result.avatar = data.avatar
                } else if (result is ModelUserApi) {
                    result.first_name = data.firstName
                    result.last_name = data.lastName
                    result.email = data.email
                    result.password = data.pass
                    result.photo = data.avatar
                }
            } else if (data is ModelResRegister) {
                if (result is ModelResRegisterRoot) {
                    result.error = data.error
                    result.success = data.success
                    result.errors.addAll(data.errors)
                }
            } else if (data is ModelResLogin) {
                if (result is ModelResLoginRoot) {
                    result.error = data.error
                    result.success = data.success
                }
            }

            return result
        } catch (ex: Exception) {
            Log.e(TAG, " Mapper ERRO : $ex")
            return null
        }
    }
}