package com.jjoh.firebasechatt.repository.remote.datasourceimpl

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jjoh.firebasechatt.repository.remote.datasource.SignUpDataSource
import com.jjoh.firebasechatt.repository.remote.signup.SignUpRepository
import javax.inject.Inject

class SignUpDataSourceImpl @Inject constructor(
    private var firebaseAuth: FirebaseAuth
) : SignUpDataSource {

    override suspend fun tryReg(id: String, pss: String, name: String, profile: String): Task<AuthResult> {
        firebaseAuth = Firebase.auth

        return firebaseAuth.createUserWithEmailAndPassword(id,pss)
    }
}