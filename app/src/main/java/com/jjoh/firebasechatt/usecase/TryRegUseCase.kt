package com.jjoh.firebasechatt.usecase

import com.jjoh.firebasechatt.repository.remote.signup.SignUpRepository
import javax.inject.Inject

class TryRegUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend fun execute(id: String, pss:String, name:String, img: String) = signUpRepository.tryReg(id,pss,name,img)

}