package com.jjoh.domain.usecase

import com.jjoh.domain.repository.HomeRepository
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val homeRepository : HomeRepository
){
    suspend fun execute() = homeRepository.tryGetList()
}