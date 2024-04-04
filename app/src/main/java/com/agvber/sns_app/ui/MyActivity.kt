package com.agvber.sns_app.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.agvber.sns_app.MemoryStorage
import com.agvber.sns_app.R
import com.agvber.sns_app.databinding.ActivityMyBinding
import com.agvber.sns_app.model.Image
import com.agvber.sns_app.model.User

class MyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyBinding
    private var profileUri: Uri? = null

    val user: User by lazy {
        MemoryStorage.getUser()
    }

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let {
                binding.imgProfile.setImageURI(it)
                profileUri = it
            }
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
                initUserProfileImage()
            }
        }
    }

    private fun initUserProfileImage() {
        val image = user.image

        binding.run {
            when (image) {
                // is의 역할 -> 스마트 캐스팅 : 타입 검사 + 형변환
                is Image.ImageDrawable -> imgProfile.setImageResource(image.drawable)
                is Image.ImageUri -> imgProfile.setImageURI(image.uri)
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
            Toast.makeText(this, getString(R.string.toast_my_done), Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun clickProfileChange() {
        binding.btnProfilechange.setOnClickListener {
            changeProfilePhoto(binding.imgProfile)
        }
    }

    fun changeProfilePhoto(view: View) {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun clickCancel() {
        binding.btnCancel.setOnClickListener {
            finish()
            Toast.makeText(this, getString(R.string.toast_my_cancel), Toast.LENGTH_SHORT).show()
        }
    }

    private fun editUserInfo(
        newName: String,
        newBio: String,
        newEmail: String,
        newPhoneNumber: String,
    ) {
        this.grantUriPermission(
            this.packageName, profileUri, Intent.FLAG_GRANT_READ_URI_PERMISSION
        )

        with(user) {
            name = newName
            bio = newBio
            email = newEmail
            phoneNumber = newPhoneNumber
            if(profileUri != null){
                image = Image.ImageUri(profileUri!!)
            }

            MemoryStorage.setUser(user)
        }
    }

    private fun clickSwitchtoLogin() {
        binding.btnSwitchtologin.setOnClickListener {
            val switchIntent = Intent(this, LogInActivity::class.java)
            switchIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(switchIntent)
        }
    }
}
