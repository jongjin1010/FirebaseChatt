package com.jjoh.domain.repository

import android.net.Uri
import com.google.android.gms.tasks.Task

interface ProfileRepository {
    suspend fun uploadNewImage(uid: String?, imageUri: Uri?): Task<Void>
    suspend fun uploadNewName(name: String, uid: String?): Task<Void>
}