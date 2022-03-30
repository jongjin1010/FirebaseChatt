package com.jjoh.data.repository.remote

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.jjoh.data.repository.remote.datasource.ProfileDataSource
import com.jjoh.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDataSource: ProfileDataSource
): ProfileRepository{

    override suspend fun uploadNewImage(uid: String?, imageUri: Uri?): Task<Void> {
        return profileDataSource.uploadNewImage(uid, imageUri)
    }

    override suspend fun uploadNewName(name: String, uid: String?): Task<Void> {
        return profileDataSource.uploadNewName(name, uid)
    }
}