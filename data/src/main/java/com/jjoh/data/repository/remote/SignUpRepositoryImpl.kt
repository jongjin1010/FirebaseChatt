package com.jjoh.data.repository.remote

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.storage.UploadTask
import com.jjoh.data.repository.remote.datasource.SignUpDataSource
import com.jjoh.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
  private val signUpDataSource: SignUpDataSource
) : SignUpRepository {

    override suspend fun tryReg(
        id: String,
        pss: String,
        name: String,
        imageUri: Uri?
    ): Task<AuthResult> {
        return signUpDataSource.tryReg(id, pss, name, imageUri)
    }

    override suspend fun putUser(user: String, image: Uri?): UploadTask {
        return signUpDataSource.putUer(user, image)
    }

    override suspend fun setUser(userIdSt: String): Task<Uri> {
        return signUpDataSource.setUser(userIdSt)
    }

}