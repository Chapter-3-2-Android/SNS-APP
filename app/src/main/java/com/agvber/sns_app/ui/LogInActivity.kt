package com.agvber.sns_app.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.agvber.sns_app.R
import com.agvber.sns_app.data.PreviewProvider
import com.agvber.sns_app.databinding.ActivityLogInBinding
import com.agvber.sns_app.ui.main.MainActivity

class LogInActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
// "Log in" 버튼이 눌렸을 때 호출되는 로직


        binding.btnLogin.setOnClickListener {
            // 호출 됐을 때, editText의 userId와 password값을 가져온다.
            val userId = binding.etId.text.toString() // test1
            val password = binding.etPassword.text.toString() // test1p

            // PreviewProvider.users list의 id값과 입력받은 userId 값을 비교한다.
            // 비교해서 일치하는 데이터가 있으면, User 객체를 가져온다.
            // 데이터가 없다면, null로 collectUser 초기화
            val collectUser = PreviewProvider.users.filter {
                it.id == userId
            }.firstOrNull()

            // collectUser : 제대로 User 객체를 가져왔는지 체크한다.
            if (collectUser != null) {
                // User 객체의 비밀번호와 입력받은 UserPassword가 일치하는지 판단한다.

                // 일치한다면, 로그인 성공 로직을 구현한다.
                if (collectUser.password == password) {
                    Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("userID", userId)

                    startActivity(intent)
                }
                else {
                    Toast.makeText(this,(R.string.ts_login_id), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, (R.string.ts_login_id), Toast.LENGTH_SHORT).show()
            }
        }
        binding.tvSignup.setOnClickListener {
            val signupIntent = Intent(this, SignupActivity::class.java)
            startActivity(signupIntent)
        }
    }
}