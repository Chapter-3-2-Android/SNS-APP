package com.agvber.sns_app.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
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
    lateinit var user: User

    val posts: List<Post> by lazy {
        if (user.postDatas.size != 0) {
            user.postDatas
        } else {
            PreviewProvider.posts.filter {
                it.userId == user.id
            }
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

    override fun onResume() {
        super.onResume()

        user = MemoryStorage.getUser()
        initUserArea()
        initUserProfileImage()
    }

    private fun initView() {
        initButtons()
        initGridView()
    }

    private fun setMemotyStorage() {
        // 로그인 페이지에서 userID 값을 넘겨받은 경우
        if (intent.hasExtra("userID")) {
            val userid = intent.getStringExtra("userID")
            val matchedUser = findMatchUserOrDefault(userid!!)

            MemoryStorage.setUser(matchedUser!!)
            user = MemoryStorage.getUser()
            user.updatePostData()
        } else { // 로그인 페이지에서 userID 값을 넘겨받지 못한 경우 default 설정
            val dafauleUser = getDefaultUser()

            MemoryStorage.setUser(dafauleUser)
            user = MemoryStorage.getUser()
            user.updatePostData()
        }

    }

    private fun findMatchUserOrDefault(userid: String): User {
        val matchedUser = PreviewProvider.users.filter {
            it.id == userid
        }.firstOrNull()

        if (matchedUser == null) {
            val message = "PreviewProvider.users에 일치하는 ${userid}가 없습니다."
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

            return getDefaultUser()
        }

        return matchedUser
    }

    private fun getDefaultUser(): User {
        return if (PreviewProvider.users.size > 3) {
            PreviewProvider.users.last()
        } else {
            PreviewProvider.users[0]
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
            when (image) {
                // is의 역할 -> 스마트 캐스팅 : 타입 검사 + 형변환
                is Image.ImageDrawable -> ivProfileImage.setImageResource(image.drawable)
                is Image.ImageUri -> ivProfileImage.setImageURI(image.uri)
            }
        }
    }

    private fun initButtons() {
        binding.run {
            arrayOf(ivProfileImage, btnEditProfile).forEach {
                it.setOnClickListener { runMyPage() }
            }
        }
    }

    private fun initGridView() {
        val postAdapter = PostAdapter(posts)
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