package com.jjoh.domain.usecase

import android.net.Uri
import com.jjoh.domain.repository.SignUpRepository
import javax.inject.Inject

class RegUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend fun executeReg(id: String, pss:String, name:String, imageUri: Uri?) = signUpRepository.tryReg(id, pss, name, imageUri)
    suspend fun executePutUser(user: String, image: Uri?) = signUpRepository.putUser(user, image)
    suspend fun executeSetUser(userIdSt: String) = signUpRepository.setUser(userIdSt)
}