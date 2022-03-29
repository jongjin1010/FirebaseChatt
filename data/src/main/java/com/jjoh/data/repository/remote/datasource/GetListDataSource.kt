package com.jjoh.data.repository.remote.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot

interface GetListDataSource {
    suspend fun tryGetList(): Task<DataSnapshot>
}