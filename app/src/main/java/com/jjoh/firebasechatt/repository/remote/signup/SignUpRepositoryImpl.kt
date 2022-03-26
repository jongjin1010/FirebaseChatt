package com.jjoh.firebasechatt.repository.remote.signup

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.jjoh.firebasechatt.repository.remote.datasource.SignUpDataSource
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
  private val signUpDataSource: SignUpDataSource
) : SignUpRepository {

    override suspend fun tryReg(
        id: String,
        pss: String,
        name: String,
        profile: String
    ): Task<AuthResult> {
        return signUpDataSource.tryReg(id,pss,name,profile)
    }
}