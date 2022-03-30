package com.jjoh.presentation.view.chat

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jjoh.presentation.base.BaseFragment
import com.jjoh.presentation.viewmodel.HomeViewModel
import com.jjoh.presentation.widget.utils.Utils.friend
import com.jjoh.presentation.R
import com.jjoh.presentation.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val getListViewModel by activityViewModels<HomeViewModel>()

    companion object {
        fun newInstance(): HomeFragment { return HomeFragment() }
    }

    override fun init() {
        binding.fragment = this

        getListViewModel.tryGetList()
        observe()
    }

    @SuppressLint("NotifyDataSetChanged")
    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder>() {
        init {
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            return CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home, parent, false))
        }

        inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.home_item_iv)
            val textView: TextView = itemView.findViewById(R.id.home_item_tv)
            val textViewEmail: TextView = itemView.findViewById(R.id.home_item_email)
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

            Glide.with(holder.itemView.context).load(friend[position].profileImageUrl)
                .apply(RequestOptions().circleCrop())
                .into(holder.imageView)
            holder.textView.text = friend[position].userName
            holder.textViewEmail.text = friend[position].email

            holder.itemView.setOnClickListener {
                val intent = Intent(context, MessageActivity::class.java)
                intent.putExtra("destinationUid", friend[position].uid)
                context?.startActivity(intent)
            }
        }

        override fun getItemCount(): Int = friend.size

    }

    private fun observe() {
        getListViewModel.success.observe(this) {
            val recyclerView = binding.homeRecycler
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = RecyclerViewAdapter()
        }

        getListViewModel.failure.observe(this) {
            shortShowToast("목록을 불러오지 못했습니다")
        }
    }



}
