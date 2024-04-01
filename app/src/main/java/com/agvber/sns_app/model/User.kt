package com.agvber.sns_app.model

data class User(
    val id: String,
    val password: String,
    var name: String,
    var phoneNumber: String?,
    var email: String?,
    var bio: String?,
    var postDatas: List<Post>
)