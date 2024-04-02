package com.agvber.sns_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agvber.sns_app.databinding.ActivityMyBinding

class MyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }
}