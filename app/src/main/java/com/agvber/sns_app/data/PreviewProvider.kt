package com.agvber.sns_app.data

import com.agvber.sns_app.R
import com.agvber.sns_app.model.Post
import com.agvber.sns_app.model.User
import java.time.LocalDateTime

object PreviewProvider {

    val posts = listOf<Post>(
        Post(
            userId = "test1",
            id = 1,
            image = R.drawable.ic_launcher_foreground,
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt #hashtag",
            time = LocalDateTime.now()
        ),
        Post(
            userId = "test2",
            id = 2,
            image = R.drawable.ic_launcher_foreground,
            content = "Digital goodies designer @pixsellz \nEverything is designed.",
            time = LocalDateTime.now()
        ),
        Post(
            userId = "test3",
            id = 3,
            image = R.drawable.ic_launcher_foreground,
            content = "Digital goodies designer @pixsellz \nEverything is designed.",
            time = LocalDateTime.now()
        )
    )

    val users = listOf<User>(
        User(
            id = "test1",
            password = "test1pw",
            name = "임희진",
            phoneNumber = "010-1234-9876",
            email = null,
            bio = null,
            postDatas = posts
        ),
        User(
            id = "test2",
            password = "test2pw",
            name = "양동원",
            phoneNumber = "010-2234-7876",
            email = "example@gmail.com",
            bio = null,
            postDatas = posts
        ),
        User(
            id = "test3",
            password = "test3pw",
            name = "김태영",
            phoneNumber = "010-1234-9876",
            email = null,
            bio = "안녕",
            postDatas = posts
        )
    )
}