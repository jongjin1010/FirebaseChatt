package com.jjoh.presentation.view.chat

import com.jjoh.presentation.base.BaseFragment
import com.jjoh.presentation.R
import com.jjoh.presentation.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    companion object{
        fun newInstance() : ProfileFragment {
            return ProfileFragment()
        }
    }
    override fun init() {

    }
}