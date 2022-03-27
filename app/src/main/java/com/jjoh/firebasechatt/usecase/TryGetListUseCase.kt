package com.jjoh.firebasechatt.usecase

import com.jjoh.firebasechatt.repository.remote.getlist.repository.GetListRepository
import javax.inject.Inject

class TryGetListUseCase @Inject constructor(
    private val getListRepository : GetListRepository
){
    suspend fun execute() = getListRepository.tryGetList()
}