package com.jjoh.firebasechatt.repository.remote.signin.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface SignInDataSource {
    suspend fun tryLogin(id: String, pss: String): Task<AuthResult>
}