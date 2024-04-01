package com.agvber.sns_app.data

import com.agvber.sns_app.R
import com.agvber.sns_app.model.Post
import com.agvber.sns_app.model.User
import java.time.LocalDateTime

object PreviewProvider {

    val posts = listOf<Post>(
        Post(
            id = 1,
            image = R.drawable.ic_launcher_foreground,
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt #hashtag",
            time = LocalDateTime.now()
        ),
        Post(
            id = 2,
            image = R.drawable.ic_launcher_foreground,
            content = "Digital goodies designer @pixsellz \nEverything is designed.",
            time = LocalDateTime.now()
        )
    )

    val users = listOf<User>(
        User(
            id = "34njkdsf",
            password = "fujiwefh3k4",
            name = "임희진",
            phoneNumber = "010-1234-9876",
            email = null,
            bio = null,
            postDatas = posts
        ),
        User(
            id = "14n4dfjsf",
            password = "fearujfh4",
            name = "양동원",
            phoneNumber = "010-2234-7876",
            email = "example@gmail.com",
            bio = null,
            postDatas = posts
        ),
        User(
            id = "134njjddsf",
            password = "fuji43fh3k4",
            name = "김태영",
            phoneNumber = "010-1234-9876",
            email = null,
            bio = "안녕",
            postDatas = posts
        )
    )
}