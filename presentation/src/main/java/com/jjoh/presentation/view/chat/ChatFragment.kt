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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.jjoh.presentation.base.BaseFragment
import com.jjoh.presentation.R
import com.jjoh.presentation.databinding.FragmentChatBinding
import com.jjoh.presentation.view.model.ChatModel
import com.jjoh.presentation.view.model.Friend
import com.jjoh.presentation.viewmodel.ChatListViewModel
import com.jjoh.presentation.widget.utils.Utils.chatModel
import com.jjoh.presentation.widget.utils.Utils.friend
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class ChatFragment : BaseFragment<FragmentChatBinding>(R.layout.fragment_chat) {
    private val viewModel by activityViewModels<ChatListViewModel>()
    private var uid: String? = null

    companion object {
        fun newInstance(): ChatFragment {
            return ChatFragment()
        }
    }

    override fun init() {
        binding.fragment = this

        viewModel.getAllUid(Firebase.auth.currentUser?.uid.toString())
    }


    @SuppressLint("NotifyDataSetChanged")
    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder>() {
        private val destinationUsers: ArrayList<String> = arrayListOf()

        init {
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecyclerViewAdapter.CustomViewHolder {
            return CustomViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false)
            )
        }

        inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val image: ImageView = itemView.findViewById(R.id.chat_item_imageview)
            val title: TextView = itemView.findViewById(R.id.chat_textview_title)
            val lastMessage: TextView = itemView.findViewById(R.id.chat_item_textview_lastmessage)
        }

        override fun onBindViewHolder(holder: RecyclerViewAdapter.CustomViewHolder, position: Int) {

            var destinationUid: String? = null

            for (user in chatModel[position].users.keys) {
                if (user != uid) {
                    destinationUid = user
                    destinationUsers.add(destinationUid)
                }
            }

            val fireDatabase = FirebaseDatabase.getInstance().reference

            fireDatabase.child("users").child("$destinationUid").addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {}
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val friend = snapshot.getValue<Friend>()
                        Glide.with(holder.itemView.context).load(friend?.profileImageUrl)
                            .apply(RequestOptions().circleCrop()).into(holder.image)
                        holder.title.text = friend?.userName
                    }
                })

            val commentMap = TreeMap<String, ChatModel.Comment>(reverseOrder())
            commentMap.putAll(chatModel[position].comments)

            val lastMessageKey = commentMap.keys.toTypedArray()[0]
            holder.lastMessage.text = chatModel[position].comments[lastMessageKey]?.message

            holder.itemView.setOnClickListener {
                val intent = Intent(context, MessageActivity::class.java)
                intent.putExtra("destinationUid", destinationUsers[position])
                context?.startActivity(intent)
            }
        }

        override fun getItemCount(): Int = chatModel.size
    }

    private fun observe() {
        viewModel.success.observe(this) {
            val recyclerView = binding.chatfragmentRecyclerview
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = RecyclerViewAdapter()
        }

        viewModel.failure.observe(this) {
            shortShowToast("목록을 불러오지 못했습니다")
        }
    }
}