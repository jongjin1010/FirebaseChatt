package com.jjoh.firebasechatt.repository.remote.getlist.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

interface GetListRepository {
    suspend fun tryGetList(): Task<DataSnapshot>
}