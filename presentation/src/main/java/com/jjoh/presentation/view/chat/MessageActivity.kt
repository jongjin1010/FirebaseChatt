package com.jjoh.presentation.view.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jjoh.presentation.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)
    }
}