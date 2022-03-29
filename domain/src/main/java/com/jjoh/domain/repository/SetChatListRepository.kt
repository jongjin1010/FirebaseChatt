package com.jjoh.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot

interface SetChatListRepository {

    suspend fun getAllUid(uid: String?): Task<DataSnapshot>
}