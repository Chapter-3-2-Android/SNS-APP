package com.agvber.sns_app

import android.content.Intent
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
        setAdapter()
    }

    private fun initView() {
        initUserArea()
        initButtons()
    }

    private fun setUserStorage() {
        // 로그인 페이지에서 userID 값을 넘겨받은 경우
        if (intent.hasExtra("userID")) {
            val userid = intent.getStringExtra("userID")
                ?: throw NullPointerException("userID의 값이 null입니다.")

            val user = PreviewProvider.users.filter {
                it.id == userid
            }.firstOrNull()
                ?: throw ClassCastException("PreviewProvider.users에 일치하는 ${userid}가 없습니다.")

            MemoryStorage.setUser(user)
        } else { // 로그인 페이지에서 userID 값을 넘겨받지 못한 경우 default 설정
            val dafauleUser = PreviewProvider.users[0]
            MemoryStorage.setUser(dafauleUser)
        }
    }

    private fun initUserArea() {
        with(binding) {
            tvUsernameTitle.text = user.name

            civProfileImage.setImageResource(user.image)
            tvPosts.text = posts.size.toString()

            tvUsernameSub.text = user.name
            tvBio.text = user.bio
        }

        binding.run {
            tvUsernameTitle.text = user.name

            civProfileImage.setImageResource(user.image)
            tvPosts.text = posts.size.toString()

            tvUsernameSub.text = user.name
            tvBio.text = user.bio
        }
    }

    private fun initButtons() {
        binding.run {
            arrayOf(civProfileImage, btnEditProfile).forEach {
                it.setOnClickListener { callMyPage() }
            }
        }
    }

    private fun callMyPage() {
        // TODO: 추후 병합 시 활성화
        val intent = Intent(this, MyActivity::class.java)
        startActivity(intent)
    }

    private fun setAdapter() {
        val postAdapter = PostAdapter(this, posts)
        binding.gvPosts.adapter = postAdapter
    }
}