package com.agvber.sns_app.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.agvber.sns_app.databinding.ActivityLogInBinding
import com.agvber.sns_app.ui.main.MainActivity

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val myIntent = Intent(this, MainActivity::class.java)
            myIntent.putExtra("userID", "test1")
            startActivity(myIntent)

            val userId = binding.tvId.text.toString()
            val password = binding.etPassword.text.toString()

            if (userId.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "아이디와 비밀번호를 모두 입력하세요", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "로그인 완료", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


//
//        val signupIntent = Intent(this, SignupActivity::class.java)
//        startActivity(signupIntent)
//
////
//        val myIntent = Intent(this, MyActivity::class.java)
//        myIntent.putExtra("string", "string")
//        startActivity(myIntent)
//    }
//}