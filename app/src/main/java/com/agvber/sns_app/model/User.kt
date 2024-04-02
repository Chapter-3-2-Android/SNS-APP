package com.agvber.sns_app.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String,
    val password: String,
    @DrawableRes val image: Int,
    var name: String,
    var phoneNumber: String?,
    var email: String?,
    var bio: String?,
    var postDatas: List<Post>
): Parcelable