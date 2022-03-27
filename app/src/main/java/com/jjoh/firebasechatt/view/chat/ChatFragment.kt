package com.jjoh.firebasechatt.view.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jjoh.firebasechatt.R
import com.jjoh.firebasechatt.base.BaseFragment
import com.jjoh.firebasechatt.databinding.FragmentChatBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ChatFragment : BaseFragment<FragmentChatBinding>(R.layout.fragment_chat) {

    companion object{
        fun newInstance() : ChatFragment {
            return ChatFragment()
        }
    }
    override fun init() {

    }

}