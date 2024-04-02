package com.agvber.sns_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agvber.sns_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val signupIntent = Intent(this, SIgnupActivity::class.java)
        startActivity(signupIntent)

        val myIntent = Intent(this, MyActivity::class.java)

        myIntent.putExtra("string", "string")

        startActivity(myIntent)

    }
}