package com.jjoh.firebasechatt.repository.remote.signup

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject


interface SignUpRepository {

    suspend fun tryReg(
        id: String, pss: String, name: String, profile: String
    ): Task<AuthResult>
}