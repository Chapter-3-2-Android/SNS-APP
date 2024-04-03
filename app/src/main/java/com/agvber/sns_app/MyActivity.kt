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
        MemoryStorage.setUser(PreviewProvider.users[0])
        MemoryStorage.getUser()
    }

    companion object {
        private const val REQUEST_IMAGE_PICK = 100 // 이미지선택 요청을 구분하기 위한 요청 코드, 임의의 상수
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 기존의 User 값을 받아와서 출력
        displayUserInfo()

        // Done 버튼 -> 유저 정보 수정
        binding.btnDone.setOnClickListener {
            val newName = binding.etName.text.toString()
            val newBio = binding.etBio.text.toString()
            val newEmail = binding.etEmail.text.toString()
            val newPhoneNumber = binding.etPhone.text.toString()

            editUserInfo(newName, newBio, newEmail, newPhoneNumber)
            Toast.makeText(this, "정보가 수정 되었습니다.", Toast.LENGTH_SHORT).show()
        }

        // profile change 버튼 -> 사진 수정
        binding.btnProfilechange.setOnClickListener {
            changeProfilePhoto(binding.imgProfile)
        }

        // Cancel 버튼 -> 다시 메인 페이지로 돌아감
        binding.btnCancel.setOnClickListener {
            finish()
            Toast.makeText(this, "정보 수정이 취소되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    // 사용자 정보가 null이 아닌 경우 Edittext에 값 설정
    private fun displayUserInfo() {

        with(binding){

        }

        user.run {
            binding.etName.setText(name)
            binding.etId.setText(id)
            binding.etBio.setText(bio ?: " ")
            binding.etEmail.setText(email ?: " ")
            binding.etPhone.setText(phoneNumber ?: " ")
        }
// with(binding) 수정 고려해보기 : 가독성 측면
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
//        MemoryStorage.getUser()
//        Log.d("debug", MemoryStorage.getUser().toString())
    }

    // 사진 수정 로직

    fun changeProfilePhoto(view: View) {
    val changePictureIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(changePictureIntent, REQUEST_IMAGE_PICK)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK && data != null){
            val selectedImageUri = data.data
            binding.imgProfile.setImageURI(selectedImageUri)
        }
    }
}



//    private fun changeProfilePhoto(view: View) {
//        val profileImages = arrayOf(
//            R.drawable.img_profile1,
//            R.drawable.img_profile2,
//            R.drawable.img_profile3,
//            R.drawable.img_profile4,
//            R.drawable.img_profile5
//        ) // 서클 이미지에, 가로세로 길이 설정할 것
//        val randomIndex = (0 until profileImages.size).random()
//        binding.imgProfile.setImageResource(profileImages[randomIndex])
//    }


