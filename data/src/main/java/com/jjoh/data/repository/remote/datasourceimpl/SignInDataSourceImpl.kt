package com.jjoh.data.repository.remote.datasourceimpl

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jjoh.data.repository.remote.datasource.SignInDataSource
import javax.inject.Inject

class SignInDataSourceImpl @Inject constructor(
    private var firebaseAuth: FirebaseAuth,
) : SignInDataSource {

    override suspend fun tryLogin(id: String, pss: String): Task<AuthResult> {
        firebaseAuth = Firebase.auth
        return firebaseAuth.signInWithEmailAndPassword(id, pss)
    }

}
