package com.jjoh.data.repository.remote.datasourceimpl

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.jjoh.data.repository.remote.datasource.ProfileDataSource
import javax.inject.Inject

class ProfileDataSourceImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseStorage: FirebaseStorage
) : ProfileDataSource {

    override suspend fun uploadNewImage(uid: String?, imageUri: Uri?): Task<Void> {
        return firebaseStorage.reference.child("userImages/$uid/photo").delete().addOnSuccessListener {
            firebaseStorage.reference.child("userImages/$uid/photo").putFile(imageUri!!).addOnSuccessListener {
                    firebaseStorage.reference.child("userImages/$uid/photo").downloadUrl.addOnSuccessListener {
                        val photoUri : Uri = it
                        firebaseDatabase.reference.child("users/$uid/profileImageUrl").setValue(photoUri.toString())
                    }
                }
        }
    }

    override suspend fun uploadNewName(name: String, uid: String?): Task<Void> {
        return firebaseDatabase.reference.child("users/$uid/name").setValue(name)
            .addOnSuccessListener {
                firebaseDatabase.reference.child("users/$uid/userName").setValue(name)
            }
    }
}