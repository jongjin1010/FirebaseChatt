package com.jjoh.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.jjoh.domain.usecase.TryRegUseCase
import com.jjoh.presentation.view.model.Friend
import com.jjoh.presentation.widget.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val tryRegUseCase: TryRegUseCase
) : ViewModel() {
    lateinit var database: DatabaseReference

    private val _checkReg = SingleLiveEvent<Any>()
    val checkReg: LiveData<Any>
        get() = _checkReg

    fun tryReg(
        id: String, pss: String, name: String, imageUri: Uri?
    ) = viewModelScope.launch {
        tryRegUseCase.execute1(id, pss, name, imageUri).let {
            it.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = Firebase.auth.currentUser
                    val userId = user?.uid
                    val userIdSt = userId.toString()
                    putUser(id, name, userIdSt, imageUri)
                }
            }
        }
    }

    private fun putUser(
        id: String, name: String, userIdSt: String, imageUri: Uri?
    ) = viewModelScope.launch {
            tryRegUseCase.execute2(userIdSt, imageUri).let {
                it.addOnSuccessListener {
                    setUser(id, name, userIdSt, imageUri)
                }
            }
        }

    private fun setUser(
        id: String, name: String, userIdSt: String, imageUri: Uri?
    ) = viewModelScope.launch {
        tryRegUseCase.execute3(userIdSt).let { task ->
            task.addOnSuccessListener {
                val userProfile: Uri? = it
                val friend = Friend(id, name, userProfile.toString(), userIdSt)

                Firebase.database.reference.child("users").child(userIdSt).setValue(friend)

                _checkReg.call()
            }
        }
    }

}
