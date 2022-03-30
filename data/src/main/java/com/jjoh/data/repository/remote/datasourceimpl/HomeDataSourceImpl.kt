package com.jjoh.data.repository.remote.datasourceimpl

import com.google.android.gms.tasks.Task
import com.google.firebase.database.*
import com.jjoh.data.repository.remote.datasource.HomeDataSource
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
) : HomeDataSource {

    override suspend fun tryGetList(): Task<DataSnapshot> {
        return firebaseDatabase.reference.child("users").get()

    }
}

