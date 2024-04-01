package com.agvber.sns_app.data

import com.agvber.sns_app.R
import com.agvber.sns_app.model.Post
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
}