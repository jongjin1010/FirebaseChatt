package com.jjoh.presentation.view.chat

import com.jjoh.presentation.base.BaseFragment
import com.jjoh.presentation.R
import com.jjoh.presentation.databinding.FragmentChatBinding
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