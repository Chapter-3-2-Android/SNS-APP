package com.agvber.sns_app.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.agvber.sns_app.MemoryStorage
import com.agvber.sns_app.data.PreviewProvider
import com.agvber.sns_app.databinding.ActivityMainBinding
import com.agvber.sns_app.model.Image
import com.agvber.sns_app.model.Post
import com.agvber.sns_app.model.User
import com.agvber.sns_app.ui.MyActivity
import com.agvber.sns_app.ui.detail.DetailActivity



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

        setMemotyStorage()
        initView()
    }

    private fun initView() {
        initUserArea()
        initUserProfileImage()
        initButtons()
        initGridView()
    }

    private fun setMemotyStorage() {
        // 로그인 페이지에서 userID 값을 넘겨받은 경우
        if (intent.hasExtra("userID")) {
            val userid = intent.getStringExtra("userID")

            val matchedUser = PreviewProvider.users.filter {
                it.id == userid
            }.firstOrNull()
                ?: throw ClassCastException("PreviewProvider.users에 일치하는 ${userid}가 없습니다.")

            MemoryStorage.setUser(matchedUser)
            user.updatePostData()
        } else { // 로그인 페이지에서 userID 값을 넘겨받지 못한 경우 default 설정
            val dafauleUser = PreviewProvider.users[0]

            MemoryStorage.setUser(dafauleUser)
            user.updatePostData()
        }
    }

    private fun User.updatePostData() {
        this.apply {
            postDatas = posts
        }
    }

    private fun initUserArea() {
        binding.run {
            tvUsernameTitle.text = user.name
            tvPostsValue.text = posts.size.toString()
            tvUsernameSub.text = user.name
            tvBio.text = user.bio
        }
    }

    private fun initUserProfileImage() {
        val image = user.image

        binding.run {
            when(image) {
                is Image.ImageDrawable -> civProfileImage.setImageResource(image.drawable)
                is Image.ImageUri -> { } // civProfileImage.setImageURI(image.uri)
            }
        }
    }

    private fun initButtons() {
        binding.run {
            arrayOf(civProfileImage, btnEditProfile).forEach {
                it.setOnClickListener { runMyPage() }
            }
        }
    }

    private fun initGridView() {
        val postAdapter = PostAdapter(this, posts)
        binding.gvPosts.adapter = postAdapter

        binding.gvPosts.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            runDetailPage(position)
        }
    }

    private fun runMyPage() {
        val intent = Intent(this, MyActivity::class.java)
        startActivity(intent)
    }

    private fun runDetailPage(index: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("postIndex", index)

        startActivity(intent)
    }
}