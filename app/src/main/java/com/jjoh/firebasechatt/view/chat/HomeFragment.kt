package com.jjoh.firebasechatt.view.chat

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
import com.google.firebase.database.*
import com.jjoh.firebasechatt.R
import com.jjoh.firebasechatt.base.BaseFragment
import com.jjoh.firebasechatt.databinding.FragmentHomeBinding
import com.jjoh.firebasechatt.viewmodel.GetListViewModel
import com.jjoh.firebasechatt.widget.utils.Util.friend
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val getListViewModel by activityViewModels<GetListViewModel>()
    var check = false

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun init() {
        binding.fragment = this

        val recyclerView = binding.homeRecycler
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = RecyclerViewAdapter()

    }

    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder>() {

        init {
            updateList()
            if (check){
                notifyDataSetChanged()
            }
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

        override fun getItemCount(): Int {
            return friend.size
        }
    }

    private fun updateList() {
        getListViewModel.trtGetList()
    }

    private fun observe() {
        getListViewModel.check.observe(this) {
            check = true
        }
    }

}