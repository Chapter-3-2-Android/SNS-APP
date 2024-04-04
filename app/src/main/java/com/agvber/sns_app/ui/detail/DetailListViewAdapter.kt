package com.agvber.sns_app.ui.detail

import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.agvber.sns_app.MemoryStorage
import com.agvber.sns_app.R
import com.agvber.sns_app.data.PreviewProvider
import com.agvber.sns_app.model.Image
import com.agvber.sns_app.model.Post
import java.time.Duration
import java.time.LocalDateTime

class DetailListViewAdapter(context: Context, private val data: List<Post>) :
    ArrayAdapter<Post>(context, R.layout.item_detail_listview, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            itemView = inflater.inflate(R.layout.item_detail_listview, parent, false)
        }

        commentReaction(itemView!!)
        clickLike(itemView, position)
        postContentMore(itemView)
        setPostDataToUI(itemView, position)

        return itemView
    }

    private fun commentReaction(itemView: View) { //댓글에서 하트, 박수 클릭 시 추가
        val et = itemView.findViewById<EditText>(R.id.et_comment)
        val tvHeart = itemView.findViewById<TextView>(R.id.tv_textHeart)
        val tvClip = itemView.findViewById<TextView>(R.id.tv_textClip)
        tvHeart.setOnClickListener {
            et.append(tvHeart.text)
        }

        tvClip.setOnClickListener {
            et.append(tvClip.text)
        }
    }

    private fun clickLike(
        itemView: View,
        position: Int
    ) { //좋아요 누를 시 이벤트, tv_NumLikes 뒤에 string.xml에 정의해서 붙이기
        val ivPostHeart = itemView.findViewById<ImageView>(R.id.iv_postHeart)
        val tvNumLikes = itemView.findViewById<TextView>(R.id.tv_numLikes)
        val firstNumLikes = PreviewProvider.posts[position].like
        val likes = " " + itemView.context.getString(R.string.tv_detail_numLikes)
        var isLike = true
        ivPostHeart.setOnClickListener {
            if (isLike) {
                ivPostHeart.setImageResource(R.drawable.ic_heart_selected)
                tvNumLikes.text = (firstNumLikes + 1).toString() + likes
                isLike = false
            } else {
                ivPostHeart.setImageResource(R.drawable.ic_heart_none)
                tvNumLikes.text = firstNumLikes.toString() + likes
                isLike = true
            }
        }
    }

    private fun postContentMore(itemView: View) { // 게시된 글 더보기
        val tvPost = itemView.findViewById<TextView>(R.id.tv_postContent)
        val tvMorePost = itemView.findViewById<TextView>(R.id.tv_postContentMore)

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

    private fun setPostDataToUI(itemView: View, position: Int) {
        val userData = MemoryStorage.getUser()
        val postData = data[position]
//        val userData = PreviewProvider.users[0]
        itemView.findViewById<ImageView>(R.id.iv_mainImage).setImageResource(postData.image)

        val contentText = userData.name + " " + postData.content
        val contentSpannableString = SpannableString(contentText)
        contentSpannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            userData.name.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        itemView.findViewById<TextView>(R.id.tv_postContent).text = contentSpannableString

        val likes = " " + itemView.context.getString(R.string.tv_detail_numLikes)
        itemView.findViewById<TextView>(R.id.tv_numLikes).text = postData.like.toString() + likes

        val currentTime = LocalDateTime.now()
        val duration = Duration.between(postData.time, currentTime)
        val daysDiff = duration.toDays() % 365
        val hourDiff = duration.toHours() % 24
        val minDiff = duration.toMinutes() % 60

        val timeText = buildString {
            when {
                (daysDiff > 0) -> {
                    if (daysDiff >= 30) append("${daysDiff / 30} ${itemView.context.getString(R.string.tv_detail_months)} ")
                    else append("$daysDiff ${itemView.context.getString(R.string.tv_detail_days)} ")
                }

                (hourDiff > 0) -> append("$hourDiff ${itemView.context.getString(R.string.tv_detail_hours)} ")
                (minDiff > 0) -> append("$minDiff ${itemView.context.getString(R.string.tv_detail_minutes)} ")
            }
        }
        if (timeText.isEmpty()) itemView.findViewById<TextView>(R.id.tv_postedTime).text = itemView.context.getString(R.string.tv_detail_now)
        else itemView.findViewById<TextView>(R.id.tv_postedTime).text =
            "$timeText " + itemView.context.getString(R.string.tv_detail_ago)

        val sponsorText = itemView.findViewById<TextView>(R.id.tv_sponsor).text
        val sponsorSpannableString = SpannableString(sponsorText)
        sponsorSpannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            sponsorText.split("\n").first().length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        itemView.findViewById<TextView>(R.id.tv_sponsor).text = sponsorSpannableString

        val profileImg1 = itemView.findViewById<ImageView>(R.id.iv_profileImage)
        val profileImg2 = itemView.findViewById<ImageView>(R.id.iv_commentProfile_img)

        val image = userData.image
        when (image) {
            is Image.ImageDrawable -> {
                profileImg1.setImageResource(image.drawable)
                profileImg2.setImageResource(image.drawable)
            }
            is Image.ImageUri -> {
                profileImg1.setImageURI(image.uri)
                profileImg2.setImageURI(image.uri)
            }
        }
    }
}