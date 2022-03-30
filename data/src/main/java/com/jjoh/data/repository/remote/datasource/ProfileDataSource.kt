package com.jjoh.data.repository.remote.datasource

import android.net.Uri
import com.google.android.gms.tasks.Task

interface ProfileDataSource {
    suspend fun uploadNewImage(uid: String?, imageUri: Uri?): Task<Void>
    suspend fun uploadNewName(name: String, uid: String?): Task<Void>
}