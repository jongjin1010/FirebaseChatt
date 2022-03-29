package com.jjoh.domain.usecase

import com.jjoh.domain.repository.GetListRepository
import javax.inject.Inject

class TryGetListUseCase @Inject constructor(
    private val getListRepository : GetListRepository
){
    suspend fun execute() = getListRepository.tryGetList()
}