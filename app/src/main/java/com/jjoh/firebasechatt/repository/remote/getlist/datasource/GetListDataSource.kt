package com.jjoh.firebasechatt.repository.remote.getlist.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.database.ValueEventListener

interface GetListDataSource {
    suspend fun tryGetList()
}