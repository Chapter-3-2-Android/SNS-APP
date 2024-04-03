package com.agvber.sns_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agvber.sns_app.databinding.ActivityLogInBinding

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        val signupIntent = Intent(this, SIgnupActivity::class.java)
//        startActivity(signupIntent)
//
//        val myIntent = Intent(this, MyActivity::class.java)
//
//        myIntent.putExtra("string", "string")
//
//        startActivity(myIntent)
    }
}
