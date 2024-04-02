package com.agvber.sns_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agvber.sns_app.adapter.PostAdapter
import com.agvber.sns_app.data.PreviewProvider
import com.agvber.sns_app.databinding.ActivityMainBinding
import com.agvber.sns_app.model.Post

class MainActivity : AppCompatActivity() {
    val posts: List<Post> by lazy {
        // TODO(setUser) : [for test] 추후 로그인 탭에서 intent로 userID 받은 뒤 수정할 예정,
        MemoryStorage.setUser(
            PreviewProvider.users[0]
        )

        val user = MemoryStorage.getUser()

        PreviewProvider.posts.filter {
            it.userId == user.id
        }
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPost()
    }

    private fun initPost() {
        val postAdapter = PostAdapter(this, posts)
        binding.gvPosts.adapter = postAdapter
    }
}