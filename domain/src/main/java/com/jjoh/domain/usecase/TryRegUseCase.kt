package com.jjoh.domain.usecase

import android.net.Uri
import com.jjoh.domain.repository.SignUpRepository
import javax.inject.Inject

class TryRegUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend fun execute1(id: String, pss:String, name:String, imageUri: Uri?) = signUpRepository.tryReg(id, pss, name, imageUri)
    suspend fun execute2(user: String, image: Uri?) = signUpRepository.putUser(user, image)
    suspend fun execute3(userIdSt: String) = signUpRepository.setUser(userIdSt)
}