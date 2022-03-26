package com.jjoh.firebasechatt.repository.remote.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface SignUpDataSource {
    suspend fun tryReg(
        id: String, pss:String, name: String, profile: String
    ): Task<AuthResult>
}