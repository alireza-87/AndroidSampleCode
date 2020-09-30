package com.midnightgeek.testproject.repo.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_user")
class ModelUserDb constructor(
    uid: Int,
    firstName: String,
    lastName: String,
    email: String,
    avatar: String
) {
    constructor() : this(0, "", "", "", "")

    @PrimaryKey
    var uid: Int? = uid
    var firstName: String? = firstName
    var lastName: String? = lastName
    var email: String? = email
    var avatar: String? = avatar
}