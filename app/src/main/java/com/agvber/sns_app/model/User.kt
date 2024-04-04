package com.agvber.sns_app.model

import android.net.Uri
import androidx.annotation.DrawableRes

data class User(
    val id: String,
    val password: String,
    var image: Image,
    var name: String,
    var phoneNumber: String?,
    var email: String?,
    var bio: String?,
    var postDatas: List<Post> = listOf()
)

sealed interface Image {
    data class ImageDrawable(@DrawableRes val drawable: Int) : Image
    data class ImageUri(val uri: Uri) : Image
}