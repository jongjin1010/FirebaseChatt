package com.jjoh.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.ktx.getValue
import com.jjoh.domain.usecase.TrySetChatListUseCase
import com.jjoh.presentation.view.model.ChatModel
import com.jjoh.presentation.view.model.Friend
import com.jjoh.presentation.widget.utils.SingleLiveEvent
import com.jjoh.presentation.widget.utils.Utils.chatModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val trySetChatListUseCase: TrySetChatListUseCase
) : ViewModel() {

    private val _success = SingleLiveEvent<Any>()
    val success: LiveData<Any>
        get() = _success

    private val _failure = SingleLiveEvent<Any>()
    val failure: LiveData<Any>
        get() = _failure

    fun getAllUid(uid: String?) = viewModelScope.launch {
        trySetChatListUseCase.execute(uid).addOnSuccessListener { snapshot ->
            chatModel.clear()
            for(data in snapshot.children){
                chatModel.add(data.getValue<ChatModel>()!!)
                println(data)
            }

            _success.call()
        }
    }
}