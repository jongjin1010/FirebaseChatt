package com.jjoh.firebasechatt.repository.remote.datasource

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import com.jjoh.firebasechatt.remote.model.Friend

interface SignUpDataSource {

    suspend fun tryReg(
        id: String, pss: String, name: String, imageUri: Uri?
    ): Task<AuthResult>

    suspend fun putUer(
        user: String, image: Uri?
    ): UploadTask

    suspend fun setUser(
        userIdSt: String
    ): Task<Uri>
}