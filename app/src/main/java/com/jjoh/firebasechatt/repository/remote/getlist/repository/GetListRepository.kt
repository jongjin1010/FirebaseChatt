package com.jjoh.firebasechatt.repository.remote.getlist.repository

import com.google.firebase.database.ValueEventListener

interface GetListRepository {

    suspend fun tryGetList()
}