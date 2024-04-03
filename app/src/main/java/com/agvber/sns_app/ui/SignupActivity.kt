package com.agvber.sns_app.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.agvber.sns_app.MemoryStorage
import com.agvber.sns_app.data.PreviewProvider
import com.agvber.sns_app.databinding.ActivitySignupBinding
import com.agvber.sns_app.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SignupActivity : AppCompatActivity() {
    companion object {
        private val emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$".toRegex()
        private val phoneNumberRegex = "^\\+?\\d{1,3}[- ]?\\d{3,}(?:[- ]?\\d{3,})?\$".toRegex()
    }

    private lateinit var binding: ActivitySignupBinding
    private val signUpData = MutableStateFlow(SignupData())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            signUpData.collectLatest {
                runOnUiThread {
                    binding.confirmBtn.isEnabled = it.checkStatus()
                }
            }
        }
        binding.confirmBtn.setOnClickListener {
            if (!signUpData.value.checkStatus()) {
                Toast.makeText(this@SignupActivity, "에러가 발생하였습니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            MemoryStorage.setUser(signUpData.value.asExternalModel())
            finish()
        }

        watchEditText()
    }

    private fun watchEditText() {
        binding.apply {
            phoneNumerOrEmailEt.addTextChangedListener { editable ->
                val emailOrPhoneNumber = editable.toString()
                if (emailRegex.containsMatchIn(emailOrPhoneNumber)) {
                    signUpData.asyncUpdate { it.copy(email = it.toString()) }
                    return@addTextChangedListener
                }
                if (phoneNumberRegex.containsMatchIn(emailOrPhoneNumber)) {
                    signUpData.asyncUpdate { it.copy(phoneNumber = emailOrPhoneNumber) }
                    return@addTextChangedListener
                }
                signUpData.asyncUpdate { it.copy(email = null, phoneNumber = null) }
            }
            nameEt.addTextChangedListener {
                signUpData.asyncUpdate { it.copy(name = it.toString()) }
            }
            idEt.addTextChangedListener {
                signUpData.asyncUpdate { it.copy(id = it.toString()) }
            }
            passwordEt.addTextChangedListener {
                signUpData.asyncUpdate { it.copy(password = it.toString()) }
            }
        }
    }

    private fun <T> MutableStateFlow<T>.asyncUpdate(function: (T) -> T) {
        lifecycleScope.launch {
            this@asyncUpdate.emit(function(value))
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
        postDatas = PreviewProvider.posts
    )
}