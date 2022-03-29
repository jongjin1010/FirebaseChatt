package com.jjoh.data.repository.remote

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.jjoh.data.repository.remote.datasource.SetChatListDataSource
import com.jjoh.domain.repository.SetChatListRepository
import javax.inject.Inject

class SetChatListRepositoryImpl @Inject constructor(
    private val setChatListDataSource: SetChatListDataSource
) : SetChatListRepository{
    override suspend fun getAllUid(uid: String?): Task<DataSnapshot> {
        return setChatListDataSource.getAllUid(uid)
    }

}