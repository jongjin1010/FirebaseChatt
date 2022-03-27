package com.jjoh.firebasechatt.view.login

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import com.jjoh.firebasechatt.R
import com.jjoh.firebasechatt.base.BaseActivity
import com.jjoh.firebasechatt.databinding.ActivityLoginBinding
import com.jjoh.firebasechatt.view.chat.MainActivity
import com.jjoh.firebasechatt.viewmodel.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val signInViewModel by viewModels<SignInViewModel>()

    override fun init() {
        binding.activity = this
        isTrue()
    }

    fun goRegBtn(view: View){
        startActivity(Intent(this, RegActivity::class.java))
        finish()
    }

    fun login(view: View) {
        binding.loading.visibility = View.VISIBLE
        val id = binding.putId.text.toString()
        val pss = binding.putPw.text.toString()

        if (id.isEmpty() && pss.isEmpty()){
            longShowToast("아이디와 비밀번호를 제대로 입력해주세요.")
        } else {
            signInViewModel.tryLogin(id, pss)
        }

    }

    private fun isTrue() {
        signInViewModel.checkLogin.observe(this) {
            binding.loading.visibility = View.INVISIBLE
            shortShowToast("로그인에 성공했어요")
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        signInViewModel.failLogin.observe(this) {
            binding.loading.visibility = View.INVISIBLE
            longShowToast("잘못된 아이디 혹은 비밀번호 입니다")
        }
    }


}
