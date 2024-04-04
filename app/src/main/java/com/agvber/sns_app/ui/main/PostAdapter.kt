package com.agvber.sns_app.ui.main

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

    override fun getView(
        position: Int, convertView: View?, parent: ViewGroup?
    ): View {
        val inflater = parent?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_post_gridview, parent, false)

        val imageViewPost = view.findViewById<ImageView>(R.id.iv_post)
        imageViewPost.setImageResource(posts[position].image)

        return view
    }
}