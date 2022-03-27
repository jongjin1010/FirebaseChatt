package com.jjoh.firebasechatt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjoh.firebasechatt.usecase.TryGetListUseCase
import com.jjoh.firebasechatt.widget.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetListViewModel @Inject constructor(
    private val tryGetListUseCase: TryGetListUseCase
) : ViewModel(){

    private val _check = SingleLiveEvent<Any>()
    val check: LiveData<Any>
        get() = _check

    fun trtGetList() = viewModelScope.launch {
        tryGetListUseCase.execute().let {
            _check.call()
        }
    }
}