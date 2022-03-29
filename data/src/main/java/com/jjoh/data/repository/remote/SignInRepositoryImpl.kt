package com.jjoh.data.repository.remote

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.jjoh.data.repository.remote.datasource.SignInDataSource
import com.jjoh.domain.repository.SignInRepository
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val signInDataSource: SignInDataSource
) : SignInRepository {
    override suspend fun tryLogin(id: String, pss: String): Task<AuthResult> {
        return signInDataSource.tryLogin(id, pss)
    }
}