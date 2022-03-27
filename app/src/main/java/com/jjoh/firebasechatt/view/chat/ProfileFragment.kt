package com.jjoh.firebasechatt.view.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jjoh.firebasechatt.R
import com.jjoh.firebasechatt.base.BaseFragment
import com.jjoh.firebasechatt.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    companion object{
        fun newInstance() : ProfileFragment {
            return ProfileFragment()
        }
    }
    override fun init() {

    }
}