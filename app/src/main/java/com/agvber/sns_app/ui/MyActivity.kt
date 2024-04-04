package com.agvber.sns_app.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.agvber.sns_app.MemoryStorage
import com.agvber.sns_app.data.PreviewProvider
import com.agvber.sns_app.databinding.ActivityMyBinding
import com.agvber.sns_app.model.User
import com.agvber.sns_app.ui.main.MainActivity

class MyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyBinding

    val user: User by lazy {
        MemoryStorage.getUser()
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
        newPhoneNumber: String,
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
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let {
                binding.imgProfile.setImageURI(it)
            }
        }
}
