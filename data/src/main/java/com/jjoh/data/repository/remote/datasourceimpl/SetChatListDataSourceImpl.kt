package com.jjoh.data.repository.remote.datasourceimpl

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.jjoh.data.repository.remote.datasource.SetChatListDataSource
import javax.inject.Inject

class SetChatListDataSourceImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
) : SetChatListDataSource{

    override suspend fun getAllUid(uid: String?): Task<DataSnapshot> {
        return firebaseDatabase.reference.child("chatrooms").orderByChild("users/$uid").equalTo(true).get()

    }


}