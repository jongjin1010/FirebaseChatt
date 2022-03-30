package com.jjoh.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjoh.domain.usecase.ProfileUseCase
import com.jjoh.presentation.widget.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
) : ViewModel(){

    private val _successImage = SingleLiveEvent<Any>()
    val successImage: LiveData<Any>
        get() = _successImage

    private val _failureImage = SingleLiveEvent<Any>()
    val failureImage: LiveData<Any>
        get() = _failureImage

    private val _successName = SingleLiveEvent<Any>()
    val successName: LiveData<Any>
        get() = _successName

    private val _failureName = SingleLiveEvent<Any>()
    val failureName: LiveData<Any>
        get() = _failureName

    fun uploadNewImage(uid: String?, imageUri: Uri?) = viewModelScope.launch {
        profileUseCase.executeUploadNewImage(uid, imageUri).addOnSuccessListener {
            _successImage.call()
        } .addOnCanceledListener {
            _failureImage.call()
        }
    }

    fun uploadNewName(name: String, uid: String?) = viewModelScope.launch {
        profileUseCase.executeUploadNewName(name, uid).addOnSuccessListener {
            _successName.call()
        } .addOnCanceledListener {
            _failureName.call()
        }
    }
}