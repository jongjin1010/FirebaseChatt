package com.jjoh.firebasechatt.usecase

import android.net.Uri
import com.jjoh.firebasechatt.repository.remote.signin.repository.SignInRepository
import javax.inject.Inject

class TryLoginUseCase @Inject constructor(
    private val signInRepository: SignInRepository
) {
    suspend fun tryLogin(id: String, pss: String) = signInRepository.tryLogin(id, pss)
}