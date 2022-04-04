package com.jjoh.presentation.view.chat

import android.annotation.SuppressLint
import android.os.Handler
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
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
import com.jjoh.presentation.R
import com.jjoh.presentation.base.BaseActivity
import com.jjoh.presentation.databinding.ActivityMessageBinding
import com.jjoh.presentation.view.model.ChatModel
import com.jjoh.presentation.view.model.ChatModel.Comment
import com.jjoh.presentation.view.model.Friend
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MessageActivity : BaseActivity<ActivityMessageBinding>(R.layout.activity_message) {
    private val fireDatabase = FirebaseDatabase.getInstance().reference
    private var chatRoomUid: String? = null
    private var destinationUid: String? = null
    private var uid: String? = null
    private var recyclerView: RecyclerView? = null

    @SuppressLint("SimpleDateFormat")
    override fun init() {
        val imageView = binding.messageActivityImageView
        val editText = binding.messageActivityEditText

        val time = System.currentTimeMillis()
        val dateFormat = SimpleDateFormat("MM월dd일 hh:mm")
        val curTime = dateFormat.format(Date(time)).toString()

        destinationUid = intent.getStringExtra("destinationUid")
        uid = Firebase.auth.currentUser?.uid.toString()
        recyclerView = binding.messageActivityRecyclerview

        imageView.setOnClickListener {
            Log.d("클릭 시 dest", "$destinationUid")

            val chatModel = ChatModel()
            chatModel.users.put(uid.toString(), true)
            chatModel.users.put(destinationUid!!, true)

            val comment = Comment(uid, editText.text.toString(), curTime)

            if (chatRoomUid == null) {
                imageView.isEnabled = false
                fireDatabase.child("chatrooms").push().setValue(chatModel).addOnSuccessListener {
                    checkChatRoom()
                    Handler().postDelayed({
                        println(chatRoomUid)
                        fireDatabase.child("chatrooms").child(chatRoomUid.toString())
                            .child("comments").push()
                            .setValue(comment)
                        binding.messageActivityEditText.text = null
                    }, 1000L)
                    Log.d("chatUidNull dest", "$destinationUid")
                }
            } else {
                fireDatabase.child("chatrooms").child(chatRoomUid.toString()).child("comments")
                    .push().setValue(comment)
                binding.messageActivityEditText.text = null

                Log.d("chatUidNotNull dest", "$destinationUid")
            }
        }
        checkChatRoom()
    }


    private fun checkChatRoom() {
        fireDatabase.child("chatrooms").orderByChild("users/$uid").equalTo(true)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {}
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (item in snapshot.children) {
                        println(item)
                        val chatModel = item.getValue<ChatModel>()
                        if (chatModel?.users!!.containsKey(destinationUid)) {
                            chatRoomUid = item.key
                            binding.messageActivityImageView.isEnabled = true
                            recyclerView?.layoutManager = LinearLayoutManager(this@MessageActivity)
                            recyclerView?.adapter = RecyclerViewAdapter()
                        }
                    }
                }
            })
    }

    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MessageViewHolder>() {

        private val comments = ArrayList<Comment>()
        private var friend: Friend? = null

        init {
            fireDatabase.child("users").child(destinationUid.toString())
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {}
                    override fun onDataChange(snapshot: DataSnapshot) {
                        friend = snapshot.getValue<Friend>()
                        binding.messageActivityTextViewTopName.text = friend?.userName
                        getMessageList()
                    }
                })
        }

        fun getMessageList() {
            fireDatabase.child("chatrooms").child(chatRoomUid.toString()).child("comments")
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {}
                    override fun onDataChange(snapshot: DataSnapshot) {
                        comments.clear()

                        for (data in snapshot.children) {
                            val item = data.getValue<Comment>()
                            comments.add(item!!)
                            println(comments)
                        }
                        notifyDataSetChanged()
                        recyclerView?.scrollToPosition(comments.size - 1)
                    }
                })
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MessageViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)

            return MessageViewHolder(view)
        }

        @SuppressLint("RtlHardcoded")
        override fun onBindViewHolder(holder: RecyclerViewAdapter.MessageViewHolder, position: Int) {
            holder.textViewMessage.textSize = 20F
            holder.textViewMessage.text = comments[position].message
            holder.textViewTime.text = comments[position].time

            if (comments[position].uid.equals(uid)) {
                holder.textViewMessage.setBackgroundResource(R.drawable.rightbubble)
                holder.textViewName.visibility = View.INVISIBLE
                holder.layoutDestination.visibility = View.INVISIBLE
                holder.layoutMain.gravity = Gravity.RIGHT

            } else {
                Glide.with(holder.itemView.context)
                    .load(friend?.profileImageUrl)
                    .apply(RequestOptions().circleCrop())
                    .into(holder.imageViewProfile)

                holder.textViewName.text = friend?.userName
                holder.layoutDestination.visibility = View.VISIBLE
                holder.textViewName.visibility = View.VISIBLE
                holder.textViewMessage.setBackgroundResource(R.drawable.leftbubble)
                holder.layoutMain.gravity = Gravity.LEFT
            }
        }

        inner class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textViewMessage: TextView = view.findViewById(R.id.messageItem_textView_message)
            val textViewName: TextView = view.findViewById(R.id.messageItem_textview_name)
            val imageViewProfile: ImageView = view.findViewById(R.id.messageItem_imageview_profile)
            val layoutDestination: LinearLayout = view.findViewById(R.id.messageItem_layout_destination)
            val layoutMain: LinearLayout = view.findViewById(R.id.messageItem_linearlayout_main)
            val textViewTime: TextView = view.findViewById(R.id.messageItem_textView_time)
        }

        override fun getItemCount(): Int {
            return comments.size
        }
    }
}

