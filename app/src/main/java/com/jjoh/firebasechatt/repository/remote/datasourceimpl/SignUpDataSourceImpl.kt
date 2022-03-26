package com.jjoh.firebasechatt.repository.remote.datasourceimpl

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.jjoh.firebasechatt.remote.model.Friend
import com.jjoh.firebasechatt.repository.remote.datasource.SignUpDataSource
import javax.inject.Inject

class SignUpDataSourceImpl @Inject constructor(
    private var firebaseAuth: FirebaseAuth,
    private val firebaseStorage: FirebaseStorage,
) : SignUpDataSource {

    override suspend fun tryReg(id: String, pss: String, name: String, imageUri: Uri?): Task<AuthResult> {
        firebaseAuth = Firebase.auth
        return firebaseAuth.createUserWithEmailAndPassword(id, pss)
    }

    override suspend fun putUer(user: String, image: Uri?): UploadTask {
        return firebaseStorage.reference.child("userImages").child("$user/photo").putFile(image!!)
    }

    override suspend fun setUser(userIdSt: String): Task<Uri> {
        return firebaseStorage.reference.child("userImages").child("$userIdSt/photo").downloadUrl

    }
}