package com.jjoh.firebasechatt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjoh.firebasechatt.usecase.TryLoginUseCase
import com.jjoh.firebasechatt.widget.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val tryLoginUseCase: TryLoginUseCase
) : ViewModel() {

    private val _checkLogin = SingleLiveEvent<Any>()
    val checkLogin: LiveData<Any>
        get() = _checkLogin

    private val _failLogin = SingleLiveEvent<Any>()
    val failLogin: LiveData<Any>
        get() = _failLogin

    fun tryLogin(id: String, pw: String) = viewModelScope.launch {
        tryLoginUseCase.execute(id, pw).let {
            it.addOnCompleteListener { task ->
                if(task.isSuccessful){
                    _checkLogin.call()
                }
                else {
                    _failLogin.call()
                }
            }
        }
     }
}
