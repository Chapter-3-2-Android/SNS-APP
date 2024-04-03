package com.agvber.sns_app.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.agvber.sns_app.MemoryStorage
import com.agvber.sns_app.data.PreviewProvider
import com.agvber.sns_app.databinding.ActivitySignupBinding
import com.agvber.sns_app.model.User
import kotlin.random.Random

class SignupActivity : AppCompatActivity() {
    companion object {
        private val emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$".toRegex()
        private val phoneNumberRegex = "^\\+?\\d{1,3}[- ]?\\d{3,}(?:[- ]?\\d{3,})?\$".toRegex()
    }

    private lateinit var binding: ActivitySignupBinding
    private var signUpData = SignupData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.confirmBtn.setOnClickListener {
            if (!signUpData.checkStatus()) {
                Toast.makeText(this@SignupActivity, "에러가 발생하였습니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            MemoryStorage.setUser(signUpData.asExternalModel())
            finish()
        }

        watchEditText()
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
                        when (editables.id) {
                            phoneNumerOrEmailEt.id -> {
                                val emailOrPhoneNumber = editable.toString()
                                signUpData = if (emailRegex.containsMatchIn(emailOrPhoneNumber)) {
                                    signUpData.copy(email = emailOrPhoneNumber)
                                } else if (phoneNumberRegex.containsMatchIn(emailOrPhoneNumber)) {
                                    signUpData.copy(phoneNumber = emailOrPhoneNumber)
                                } else {
                                    signUpData.copy(email = null, phoneNumber = null)
                                }
                            }

                            nameEt.id -> signUpData = signUpData.copy(name = editable.toString())
                            idEt.id -> signUpData = signUpData.copy(id = editable.toString())
                            passwordEt.id -> signUpData =
                                signUpData.copy(password = editable.toString())
                        }
                        binding.confirmBtn.isEnabled = signUpData.checkStatus()
                    }
                }
        }
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

private fun SignupData.asExternalModel(): User {
    return User(
        id = id!!,
        password = password!!,
        name = name!!,
        phoneNumber = phoneNumber,
        email = email,
        bio = "",
        postDatas = PreviewProvider.posts,
        image = PreviewProvider.users.let { it[Random.nextInt(0, it.size)].image }
    )
}