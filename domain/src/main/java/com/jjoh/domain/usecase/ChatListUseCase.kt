package com.jjoh.domain.usecase

import com.jjoh.domain.repository.ChatListRepository
import javax.inject.Inject

class ChatListUseCase @Inject constructor(
    private val tryChatListRepository: ChatListRepository
){

    suspend fun execute(uid: String?) = tryChatListRepository.getAllUid(uid)
}