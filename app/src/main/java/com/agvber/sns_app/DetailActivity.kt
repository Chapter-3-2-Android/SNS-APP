package com.agvber.sns_app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.agvber.sns_app.adapter.DetailListViewAdapter
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
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

       val listView = binding.lvDetail
        listView.adapter = DetailListViewAdapter(this, PreviewProvider.posts)

    }
}