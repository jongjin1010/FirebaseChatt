package com.jjoh.firebasechatt.repository.remote.getlist.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.jjoh.firebasechatt.repository.remote.getlist.datasource.GetListDataSource
import javax.inject.Inject

class GetListRepositoryImpl @Inject constructor(
    private val getListDataSource: GetListDataSource
) : GetListRepository{

    override suspend fun tryGetList(): Task<DataSnapshot> {
        return getListDataSource.tryGetList()
    }
}