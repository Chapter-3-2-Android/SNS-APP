package com.agvber.sns_app.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agvber.sns_app.MemoryStorage
import com.agvber.sns_app.data.PreviewProvider
import com.agvber.sns_app.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var isLike = true
    private val postDataIndex by lazy { intent.getIntExtra("postIndex", 0) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listView = binding.lvDetail
        listView.adapter = DetailListViewAdapter(this, MemoryStorage.getUser().postDatas)
//        listView.adapter = DetailListViewAdapter(this, PreviewProvider.posts)


        listView.requestFocusFromTouch()
        listView.deferNotifyDataSetChanged()
        listView.setSelection(intent.getIntExtra("postIndex", 0)) // intent position 가져와서 넣기
    }
}