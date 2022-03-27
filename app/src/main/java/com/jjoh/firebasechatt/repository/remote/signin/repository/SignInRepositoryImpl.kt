package com.jjoh.firebasechatt.repository.remote.signin.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.jjoh.firebasechatt.repository.remote.signin.datasource.SignInDataSource
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val signInDataSource: SignInDataSource
) : SignInRepository {
    override suspend fun tryLogin(id: String, pss: String): Task<AuthResult> {
        return signInDataSource.tryLogin(id, pss)
    }
}