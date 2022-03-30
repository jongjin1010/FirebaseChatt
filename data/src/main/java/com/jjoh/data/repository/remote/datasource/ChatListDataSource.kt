package com.jjoh.data.repository.remote.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot

interface ChatListDataSource {
    suspend fun getAllUid(uid: String?): Task<DataSnapshot>
}