package com.agvber.sns_app

import com.agvber.sns_app.model.User

object MemoryStorage {

    private lateinit var user: User

    internal fun getUser(): User = user
    internal fun setUser(user: User) {
        this.user = user
    }

}