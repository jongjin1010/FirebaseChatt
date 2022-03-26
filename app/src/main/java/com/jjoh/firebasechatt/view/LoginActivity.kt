package com.jjoh.firebasechatt.view

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jjoh.firebasechatt.R
import com.jjoh.firebasechatt.base.BaseActivity
import com.jjoh.firebasechatt.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private lateinit var check: String

    override fun init() {
        binding.activity = this
    }

    fun goRegBtn(view: View){
        startActivity(Intent(this, RegActivity::class.java))
    }

    fun login(view: View) {
        val id = binding.putId.toString()
        val pw = binding.putPw.toString()
        
        checkLoginData(id, pw)

        Log.d(TAG, "login: $check")
    }

    private fun checkLoginData(id: String, pw: String) {
        check = if(id.isEmpty()&&pw.isEmpty()){
           "YES"
        }else "NO"
    }
}
