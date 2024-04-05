package com.agvber.sns_app.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.agvber.sns_app.R
import com.agvber.sns_app.model.Post

class PostAdapter(val posts: List<Post>) : BaseAdapter() {

    override fun getCount(): Int {
        return posts.count()
    }

    override fun getItem(position: Int): Any {
        return posts[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(
        position: Int, convertView: View?, parent: ViewGroup?
    ): View {
        // inflate 메서드에 null을 root 매개변수로 제공하여 인플레이션된 뷰는 부모의 어떤 레이아웃 매개변수도 적용받지 않는다.
        val layoutInflater = LayoutInflater.from(parent?.context)
        val view = layoutInflater.inflate(R.layout.item_post_gridview, parent,false)

        val imageViewPost = view.findViewById<ImageView>(R.id.iv_post)
        imageViewPost.setImageResource(posts[position].image)

        return view
    }
}