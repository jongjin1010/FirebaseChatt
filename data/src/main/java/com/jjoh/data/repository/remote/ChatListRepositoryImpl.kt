package com.jjoh.data.repository.remote

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.jjoh.data.repository.remote.datasource.ChatListDataSource
import com.jjoh.domain.repository.ChatListRepository
import javax.inject.Inject

class ChatListRepositoryImpl @Inject constructor(
    private val setChatListDataSource: ChatListDataSource
) : ChatListRepository{
    override suspend fun getAllUid(uid: String?): Task<DataSnapshot> {
        return setChatListDataSource.getAllUid(uid)
    }

}