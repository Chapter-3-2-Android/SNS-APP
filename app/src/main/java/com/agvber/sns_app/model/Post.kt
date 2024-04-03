package com.agvber.sns_app.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import kotlin.random.Random

@Parcelize
data class Post(
    val userId: String,
    val id: Int,
    @DrawableRes val image: Int,
    val content: String, // Lorem ipsum dolor
    val time: LocalDateTime,
    var like: Int = Random.nextInt(1, 100)
) : Parcelable
