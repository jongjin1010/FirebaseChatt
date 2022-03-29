package com.jjoh.domain.repository

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface SignInRepository {

    suspend fun tryLogin(
        id: String, pss: String
    ): Task<AuthResult>
}