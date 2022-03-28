package com.jjoh.firebasechatt.repository.remote.getlist.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class GetListDataSourceImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
) : GetListDataSource {

    override suspend fun tryGetList(): Task<DataSnapshot> {
        return firebaseDatabase.reference.child("users").get()

    }
}

