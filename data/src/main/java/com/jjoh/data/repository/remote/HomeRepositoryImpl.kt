package com.jjoh.data.repository.remote

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.jjoh.data.repository.remote.datasource.HomeDataSource
import com.jjoh.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val getListDataSource: HomeDataSource
) : HomeRepository {

    override suspend fun tryGetList(): Task<DataSnapshot> {
        return getListDataSource.tryGetList()
    }
}