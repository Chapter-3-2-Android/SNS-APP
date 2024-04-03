package com.agvber.sns_app.data

import com.agvber.sns_app.R
import com.agvber.sns_app.model.Image
import com.agvber.sns_app.model.Post
import com.agvber.sns_app.model.User
import java.time.LocalDateTime

object PreviewProvider {

    val posts = listOf<Post>(
        Post(
            userId = "test1",
            id = 1,
            image = R.drawable.img_post1,
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt #hashtag",
            time = LocalDateTime.of(2022, 4, 3, 10, 30, 15)
        ),
        Post(
            userId = "test1",
            id = 2,
            image = R.drawable.img_post2,
            content = "Digital goodies designer @pixsellz \nEverything is designed.",
            time = LocalDateTime.of(2022, 5, 3, 14, 30, 15)
        ),
        Post(
            userId = "test1",
            id = 3,
            image = R.drawable.img_post3,
            content = "Digital goodies designer @pixsellz \nEverything is designed.",
            time = LocalDateTime.now()
        ),
        Post(
            userId = "test1",
            id = 4,
            image = R.drawable.img_post4,
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt #hashtag",
            time = LocalDateTime.now()
        ),
        Post(
            userId = "test1",
            id = 5,
            image = R.drawable.img_post5,
            content = "Digital goodies designer @pixsellz \nEverything is designed.",
            time = LocalDateTime.now()
        ),
        Post(
            userId = "test1",
            id = 6,
            image = R.drawable.img_post6,
            content = "Digital goodies designer @pixsellz \nEverything is designed.",
            time = LocalDateTime.now()
        ),
        Post(
            userId = "test1",
            id = 7,
            image = R.drawable.img_post7,
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt #hashtag",
            time = LocalDateTime.now()
        ),
        Post(
            userId = "test1",
            id = 8,
            image = R.drawable.img_post8,
            content = "Digital goodies designer @pixsellz \nEverything is designed.",
            time = LocalDateTime.now()
        ),
        Post(
            userId = "test1",
            id = 9,
            image = R.drawable.img_post9,
            content = "Digital goodies designer @pixsellz \nEverything is designed.",
            time = LocalDateTime.now()
        ),
        Post(
            userId = "test1",
            id = 10,
            image = R.drawable.img_post10,
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt #hashtag",
            time = LocalDateTime.now()
        ),
        Post(
            userId = "test1",
            id = 11,
            image = R.drawable.img_post11,
            content = "Digital goodies designer @pixsellz \nEverything is designed.",
            time = LocalDateTime.now()
        ),
        Post(
            userId = "test2",
            id = 1,
            image = R.drawable.img_post12,
            content = "Digital goodies designer @pixsellz \nEverything is designed.",
            time = LocalDateTime.now()
        )
    )

    val users = listOf(
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