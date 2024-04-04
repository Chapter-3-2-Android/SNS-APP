package com.agvber.sns_app.data

import com.agvber.sns_app.R
import com.agvber.sns_app.model.Image
import com.agvber.sns_app.model.Post
import com.agvber.sns_app.model.User
import java.time.LocalDateTime

object PreviewProvider {
    val drawableImages = listOf(
        R.drawable.img_post1,
        R.drawable.img_post2,
        R.drawable.img_post3,
        R.drawable.img_post4,
        R.drawable.img_post5,
        R.drawable.img_post6,
        R.drawable.img_post7,
        R.drawable.img_post8,
        R.drawable.img_post9,
        R.drawable.img_post10,
        R.drawable.img_post11,
        R.drawable.img_post12,
        R.drawable.img_post13,
        R.drawable.img_post14,
        R.drawable.img_post15,
        R.drawable.img_post16,
        R.drawable.img_post17,
        R.drawable.img_post18,
        R.drawable.img_post19,
        R.drawable.img_post20,
    )

    var posts: List<Post> = listOf()

    init {
        posts = (1..drawableImages.size).map {
            val year = (2020..2024).random()
            val month = (1..11).random()
            val day = (1..28).random()

            Post(
                userId = "test1",
                id = it,
                image = drawableImages[it - 1],
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt #hashtag",
                time = LocalDateTime.of(year, month, day, 10, 30, 15)
            )
        }
    }

    val users = mutableListOf(
        User(
            id = "test1",
            password = "test1pw",
            image = Image.ImageDrawable(R.drawable.img_profile1),
            name = "임희진",
            phoneNumber = "010-1234-9876",
            email = null,
            bio = "Hi",
        ),
        User(
            id = "test2",
            password = "test2pw",
            image = Image.ImageDrawable(R.drawable.img_profile2),
            name = "양동원",
            phoneNumber = "010-2234-7876",
            email = "example@gmail.com",
            bio = null,
        ),
        User(
            id = "test3",
            password = "test3pw",
            image = Image.ImageDrawable(R.drawable.img_profile3),
            name = "김태영",
            phoneNumber = "010-1234-9876",
            email = null,
            bio = "안녕",
        )
    )
}