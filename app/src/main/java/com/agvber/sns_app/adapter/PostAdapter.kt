package com.agvber.sns_app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.agvber.sns_app.R
import com.agvber.sns_app.model.Post

class PostAdapter(val context: Context, val posts: List<Post>) : BaseAdapter() {

    override fun getCount(): Int {
        return posts.count()
    }

    override fun getItem(position: Int): Any {
        return posts[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int, convertView: View?, parent: ViewGroup?
    ): View {
        val view = LayoutInflater.from(context).inflate(R.layout.gv_post_item, null)

        val iv = view.findViewById<ImageView>(R.id.iv_post)
        iv.setImageResource(posts[position].image)

        return view
    }
}













