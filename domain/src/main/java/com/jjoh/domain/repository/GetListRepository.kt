package com.jjoh.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot

interface GetListRepository {
    suspend fun tryGetList(): Task<DataSnapshot>
}