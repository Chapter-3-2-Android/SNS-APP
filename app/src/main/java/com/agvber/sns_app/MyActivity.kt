package com.agvber.sns_app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.agvber.sns_app.data.PreviewProvider
import com.agvber.sns_app.databinding.ActivityMyBinding
import com.agvber.sns_app.model.User

class MyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyBinding

    val user: User by lazy {
        MemoryStorage.getUser()
    }

    companion object {
        // 이미지 선택 요청을 구분하기 위한 요청 코드, 임의의 상수
        private const val REQUEST_IMAGE_PICK = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayUserInfo()
        clickDone()
        clickProfileChange()
        clickCancel()
        clickSwitchtoLogin()

    }

    private fun displayUserInfo() {
        with(binding) {
            user.run {
                etName.setText(name)
                etId.setText(id)
                etBio.setText(bio ?: "")
                etEmail.setText(email ?: "")
                etPhone.setText(phoneNumber ?: "")
            }
        }
    }

    private fun clickDone() {
        binding.btnDone.setOnClickListener {
            val newName = binding.etName.text.toString()
            val newBio = binding.etBio.text.toString()
            val newEmail = binding.etEmail.text.toString()
            val newPhoneNumber = binding.etPhone.text.toString()

            editUserInfo(newName, newBio, newEmail, newPhoneNumber)
            Toast.makeText(this, "정보가 수정 되었습니다.", Toast.LENGTH_SHORT).show()
            val doneIntent = Intent(this, MainActivity::class.java)
            startActivity(doneIntent)
        }
    }

    private fun clickProfileChange() {
        binding.btnProfilechange.setOnClickListener {
            changeProfilePhoto(binding.imgProfile)
        }
    }

    private fun clickCancel() {
        binding.btnCancel.setOnClickListener {
            finish()
            Toast.makeText(this, "정보 수정이 취소되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun editUserInfo(
        newName: String,
        newBio: String,
        newEmail: String,
        newPhoneNumber: String
    ) {
        user?.let {
            user.name = newName
            user.bio = newBio
            user.email = newEmail
            user.phoneNumber = newPhoneNumber
            MemoryStorage.setUser(user)
        }
//            MemoryStorage.getUser()
//            Log.d("debug", MemoryStorage.getUser().toString())
    }

    private fun clickSwitchtoLogin() {
        binding.btnSwitchtologin.setOnClickListener {
            val switchIntent = Intent(this, LogInActivity::class.java)
            startActivity(switchIntent)
        }
    }

    fun changeProfilePhoto(view: View) {
        val changePictureIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)

        // 묵시적 인텐트 호출 최신 방법?
        startActivityForResult(changePictureIntent, REQUEST_IMAGE_PICK)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK && data != null){
            val selectedImageURI = data.data
            binding.imgProfile.setImageURI(selectedImageURI)
        }
    }
}