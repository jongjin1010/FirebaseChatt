package com.jjoh.domain.usecase

import com.jjoh.domain.repository.SignInRepository
import javax.inject.Inject

class TryLoginUseCase @Inject constructor(
    private val signInRepository: SignInRepository
) {
    suspend fun execute(id: String, pss: String) = signInRepository.tryLogin(id, pss)
}