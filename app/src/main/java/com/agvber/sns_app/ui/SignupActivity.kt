package com.agvber.sns_app.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.agvber.sns_app.R
import com.agvber.sns_app.data.PreviewProvider
import com.agvber.sns_app.databinding.ActivitySignupBinding
import com.agvber.sns_app.model.User
import kotlin.random.Random

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private var signUpData = SignupData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.horizon_enter, R.anim.horizon_none)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.confirmBtn.setOnClickListener {
            eventSuccess()
            eventFail()
        }

        watchEditText()
    }

    private fun eventSuccess() {
        if (signUpData.checkStatus()) {
            PreviewProvider.users.add(signUpData.asExternalModel(binding.nameEt.toString()))
            finish()
        }
    }

    private fun eventFail() {
        if (!signUpData.checkStatus()) {
            Toast.makeText(this@SignupActivity, getString(R.string.ts_signup_error), Toast.LENGTH_SHORT).show()
        }
    }

    private fun watchEditText() {
        binding.apply {
            listOf(
                phoneNumerOrEmailEt,
                nameEt,
                idEt,
                passwordEt
            )
                .forEach { editables ->
                    editables.addTextChangedListener { editable ->
                        updateSignupData(editables.id, editable.toString())
                        binding.confirmBtn.isEnabled = signUpData.checkStatus()
                    }
                }
        }
    }

    private fun updateSignupData(viewId: Int, editText: String) {
        binding.apply {
            when (viewId) {
                phoneNumerOrEmailEt.id -> setEmailOrPhoneNumber(editText)
                nameEt.id -> signUpData = signUpData.copy(name = editText)
                idEt.id -> signUpData = signUpData.copy(id = editText)
                passwordEt.id -> signUpData = signUpData.copy(password = editText)
            }
        }
    }

    private fun setEmailOrPhoneNumber(emailOrPhoneNumber: String) {
        signUpData = if (emailRegex.containsMatchIn(emailOrPhoneNumber)) {
            signUpData.copy(email = emailOrPhoneNumber)
        } else if (phoneNumberRegex.containsMatchIn(emailOrPhoneNumber)) {
            signUpData.copy(phoneNumber = emailOrPhoneNumber)
        } else {
            signUpData.copy(email = null, phoneNumber = null)
        }
    }

    companion object {
        private val emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$".toRegex()
        private val phoneNumberRegex = "^\\+?\\d{1,3}[- ]?\\d{3,}(?:[- ]?\\d{3,})?\$".toRegex()
    }
}


private data class SignupData(
    val phoneNumber: String? = null,
    val email: String? = null,
    val name: String? = null,
    val id: String? = null,
    val password: String? = null,
) {
    fun checkStatus(): Boolean = (!phoneNumber.isNullOrBlank() || !email.isNullOrBlank()) &&
            !name.isNullOrBlank() && !id.isNullOrBlank() && !password.isNullOrBlank()
}

private fun SignupData.asExternalModel(userId: String): User {
    val postDatas = PreviewProvider.posts
        .shuffled()
        .slice(0..< Random.nextInt(4, PreviewProvider.posts.size))
        .map { it.copy(userId = userId) }

    return User(
        id = id!!,
        password = password!!,
        name = name!!,
        phoneNumber = phoneNumber,
        email = email,
        bio = "",
        postDatas = postDatas,
        image = PreviewProvider.users.let { it[Random.nextInt(0, it.size)].image }
    )
}