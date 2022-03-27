package com.jjoh.firebasechatt.repository.remote.getlist.repository

import com.google.firebase.database.ValueEventListener
import com.jjoh.firebasechatt.repository.remote.getlist.datasource.GetListDataSource
import javax.inject.Inject

class GetListRepositoryImpl @Inject constructor(
    private val getListDataSource: GetListDataSource
) : GetListRepository{

    override suspend fun tryGetList() {
        return getListDataSource.tryGetList()
    }
}