package com.agvber.sns_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agvber.sns_app.adapter.PostAdapter
import com.agvber.sns_app.data.PreviewProvider
import com.agvber.sns_app.databinding.ActivityMainBinding
import com.agvber.sns_app.model.Post
import com.agvber.sns_app.model.User

class MainActivity : AppCompatActivity() {
    val user: User by lazy {
        MemoryStorage.getUser()
    }

    val posts: List<Post> by lazy {
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

        setUserStorage()
        initView()
    }

    private fun initView() {
        initUserProfileImage()
        initUserName()
        initUserBio()
        initPostAdapter()
    }

    private fun setUserStorage() {
        // 로그인 페이지에서 userID 값을 넘겨받은 경우
        if (intent.hasExtra("userID")) {
            val userid = intent.getStringExtra("userID")

            val user = PreviewProvider.users.filter {
                it.id == userid
            } as User

            MemoryStorage.setUser(user)
        } else { // 로그인 페이지에서 userID 값을 넘겨받지 못한 경우 default 설정
            MemoryStorage.setUser(PreviewProvider.users[0])
        }
    }

    private fun initUserProfileImage() {
        binding.civProfileImage.setImageResource(user.image)
    }

    private fun initUserName() {
        binding.tvUsernameTitle.text = user.name
        binding.tvUsernameSub.text = user.name
    }

    private fun initUserBio() {
        binding.tvBio.text = user.bio
    }

    private fun initPostAdapter() {
        val postAdapter = PostAdapter(this, posts)
        binding.gvPosts.adapter = postAdapter
    }
}