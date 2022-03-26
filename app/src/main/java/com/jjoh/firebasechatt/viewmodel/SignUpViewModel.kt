package com.jjoh.firebasechatt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjoh.firebasechatt.usecase.TryRegUseCase
import com.jjoh.firebasechatt.widget.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val tryRegUseCase: TryRegUseCase
): ViewModel() {

    val checkReg: LiveData<Any> get() = _checkReg
    private val _checkReg = SingleLiveEvent<Any>()

    fun tryReg(id: String, pss: String, name: String, profile: String) = viewModelScope.launch {
     tryRegUseCase.execute(id, pss, name, profile).let {
            it.addOnCanceledListener {
                if(it.isSuccessful){
                    _checkReg.call()
                }
            }
        }
    }


}