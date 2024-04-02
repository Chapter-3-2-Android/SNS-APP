package com.agvber.sns_app

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.agvber.sns_app.databinding.ActivityDetailBinding
import com.agvber.sns_app.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var likeBool = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        commentReaction()
        clickLike()
        postMore()
    }

    private fun commentReaction() { //댓글에서 하트, 박수 클릭 시 추가
        val et = binding.etComment
        val tvHeart = binding.tvTextHeart
        val tvClip = binding.tvTextClip
        tvHeart.setOnClickListener {
            et.append(tvHeart.text)
        }

        tvClip.setOnClickListener {
            et.append(tvClip.text)
        }
    }

    private fun clickLike() { //좋아요 누를 시 이벤트, tv_NumLikes 뒤에 string.xml에 정의해서 붙이기
        val ivPostHeart = binding.ivPostHeart
        val tvNumLikes = binding.tvNumLikes
        val firstNumLikes = tvNumLikes.text.split(" ").first().toInt()

        ivPostHeart.setOnClickListener {
            if (likeBool) {
                ivPostHeart.setImageResource(R.drawable.ic_heart_selected)
                tvNumLikes.text = (firstNumLikes + 1).toString()
                likeBool = false
            } else {
                ivPostHeart.setImageResource(R.drawable.ic_heart_none)
                tvNumLikes.text = firstNumLikes.toString()
                likeBool = true
            }
        }
    }

    private fun postMore() { // 게시된 글 더보기
        val tvPost = binding.tvPostedPost
        val tvMorePost = binding.tvPostedPostMore

        tvPost.post {
            val lineCount = tvPost.layout.lineCount
            if (lineCount > 0) {
                if (tvPost.layout.getEllipsisCount(lineCount - 1) > 0) {
                    tvMorePost.visibility = View.VISIBLE

                    tvMorePost.setOnClickListener {
                        if (tvMorePost.text == "more") {
                            tvPost.maxLines = Int.MAX_VALUE
                            tvMorePost.text = "fold"
                        } else {
                            tvPost.maxLines = 2
                            tvMorePost.text = "more"
                        }
                    }
                }
            }

        }


    }
}