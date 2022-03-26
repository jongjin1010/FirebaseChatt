package com.jjoh.firebasechatt.repository.remote.signup

import android.icu.lang.UProperty
import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import com.jjoh.firebasechatt.remote.model.Friend
import javax.inject.Inject


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