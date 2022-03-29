package com.jjoh.domain.usecase

import com.jjoh.domain.repository.SetChatListRepository
import javax.inject.Inject

class TrySetChatListUseCase @Inject constructor(
    private val trySetChatListRepository: SetChatListRepository
){

    suspend fun execute(uid: String?) = trySetChatListRepository.getAllUid(uid)
}