package com.agvber.sns_app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.agvber.sns_app.data.PreviewProvider
import com.agvber.sns_app.databinding.ActivityDetailBinding
import java.time.Duration
import java.time.LocalDateTime

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var isLike = true
    private val postDataIndex by lazy { intent.getIntExtra("postIndex", 0) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        commentReaction()
        clickLike()
        postContentMore()

        setPostDataToUI(postDataIndex)

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
        val firstNumLikes = PreviewProvider.posts[postDataIndex].like
        val likes = " " + getString(R.string.numLikes)
        ivPostHeart.setOnClickListener {
            if (isLike) {
                ivPostHeart.setImageResource(R.drawable.ic_heart_selected)
                tvNumLikes.text = (firstNumLikes + 1).toString() + likes
                isLike = false
            } else {
                ivPostHeart.setImageResource(R.drawable.ic_heart_none)
                tvNumLikes.text = firstNumLikes.toString() +likes
                isLike = true
            }
        }
    }

    private fun postContentMore() { // 게시된 글 더보기
        val tvPost = binding.tvPostContent
        val tvMorePost = binding.tvPostContentMore

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

    private fun setPostDataToUI(index: Int) { // LocalDateTime 사용 필, string.xml에 NumLikes 뒤 문장 필
        val postData = PreviewProvider.posts[index]
        val userData = PreviewProvider.users[index]
        binding.ivMainImage.setImageResource(postData.image)
        binding.tvPostContent.text = userData.name + " " + postData.content

        val likes = " " + getString(R.string.numLikes)

        binding.tvNumLikes.text = postData.like.toString() + likes

        val currentTime = LocalDateTime.now()
        val duration = Duration.between(postData.time, currentTime)
        val daysDiff = duration.toDays() % 365
        val hourDiff = duration.toHours() % 24
        val minDiff = duration.toMinutes() % 60

        var timeText = ""
        if (daysDiff > 0) {
            timeText += "${daysDiff} days "
        }
        if (hourDiff > 0) {
            timeText += "${hourDiff} hours "
        }
        if (minDiff > 0) {
            timeText += "${minDiff} minutes "
        }
        binding.tvPostedTime.text = "$timeText ago"
    }
}