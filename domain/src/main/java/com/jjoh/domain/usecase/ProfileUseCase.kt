package com.jjoh.domain.usecase

import android.net.Uri
import com.jjoh.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
){
    suspend fun executeUploadNewImage(uid: String?, imageUri: Uri?) = profileRepository.uploadNewImage(uid, imageUri)
    suspend fun executeUploadNewName(name: String, uid: String?) = profileRepository.uploadNewName(name, uid)
}