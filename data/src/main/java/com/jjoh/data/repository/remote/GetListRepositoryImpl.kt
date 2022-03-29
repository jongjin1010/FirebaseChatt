package com.jjoh.data.repository.remote

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.jjoh.data.repository.remote.datasource.GetListDataSource
import com.jjoh.domain.repository.GetListRepository
import javax.inject.Inject

class GetListRepositoryImpl @Inject constructor(
    private val getListDataSource: GetListDataSource
) : GetListRepository {

    override suspend fun tryGetList(): Task<DataSnapshot> {
        return getListDataSource.tryGetList()
    }
}