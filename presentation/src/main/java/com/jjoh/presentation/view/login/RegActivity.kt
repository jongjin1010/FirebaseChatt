package com.jjoh.presentation.view.login

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.activity.viewModels
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.jjoh.presentation.base.BaseActivity
import com.jjoh.presentation.viewmodel.SignUpViewModel
import com.jjoh.presentation.R
import com.jjoh.presentation.databinding.ActivityRegBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class RegActivity : BaseActivity<ActivityRegBinding>(R.layout.activity_reg) {
    private var profileCheck = false
    private var imageUri : Uri? = null
    private val signUpViewModel by viewModels<SignUpViewModel>()


    override fun init(){
        binding.activity = this
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        observe()
    }

    fun goLoginBtn(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    fun tryReg(view: View) {
        val id = binding.regId.text.toString()
        val pss = binding.regPss.text.toString()
        val name = binding.regName.text.toString()

        val pwPattern = "^.*(?=^.{6,12}\$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*\$"
        val patterns = Pattern.compile(pwPattern)
        val matcher = patterns.matcher(pss)

        if (id.isEmpty() && pss.isEmpty() && name.isEmpty() && !profileCheck) {
            shortShowToast("아이디와 비밀번호, 프로필 사진을 제대로 입력해주세요.")
            Log.d("PROFILE", "tryReg: $profileCheck")

        } else if (!profileCheck) {
            shortShowToast("프로필사진을 등록해주세요")

        } else if (!matcher.find()){
            shortShowToast("비밀번호 형식을 지켜주세요")

        } else {
            binding.loading.visibility = View.VISIBLE
            Log.d("CHECK", "tryReg: $id, $pss")
            signUpViewModel.tryReg(id, pss, name, imageUri)
        }
    }

    private fun observe() {
        signUpViewModel.checkReg.observe(this) {
            binding.loading.visibility = View.INVISIBLE
            shortShowToast("회원가입이 완료되었습니다")
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        signUpViewModel.failure.observe(this) {
            binding.loading.visibility = View.INVISIBLE
            shortShowToast("형식을 다시 확인해주세요ㅡ")
        }
    }

    fun profileImg(view: View) {
        val intentImage = Intent(Intent.ACTION_PICK)
        intentImage.type = MediaStore.Images.Media.CONTENT_TYPE
        getContent.launch(intentImage)
        profileCheck = true
    }

    private val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                imageUri = result.data?.data
                binding.regProfileImg.setImageURI(imageUri)
                Log.d("이미지", "성공")
            } else {
                Log.d("이미지", "실패")
            }
        }
}
