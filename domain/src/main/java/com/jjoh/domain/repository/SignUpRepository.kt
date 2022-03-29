package com.jjoh.domain.repository

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.storage.UploadTask

interface SignUpRepository {

    suspend fun tryReg(
        id: String, pss: String, name: String, imageUri: Uri?
    ): Task<AuthResult>

    suspend fun putUser(
        user: String,
        image: Uri?
    ) : UploadTask

    suspend fun setUser(
        userIdSt: String
    ): Task<Uri>

}