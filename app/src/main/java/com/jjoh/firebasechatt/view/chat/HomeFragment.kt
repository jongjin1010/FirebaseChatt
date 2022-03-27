package com.jjoh.firebasechatt.view.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jjoh.firebasechatt.R
import com.jjoh.firebasechatt.base.BaseFragment
import com.jjoh.firebasechatt.databinding.FragmentHomeBinding


class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    companion object{
        fun newInstance() : HomeFragment {
            return HomeFragment()
        }
    }
    override fun init() {

    }

}