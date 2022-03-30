package com.jjoh.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.jjoh.domain.usecase.HomeUseCase
import com.jjoh.presentation.view.model.Friend
import com.jjoh.presentation.widget.utils.SingleLiveEvent
import com.jjoh.presentation.widget.utils.Utils.friend
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    private val _success = SingleLiveEvent<Any>()
    val success: LiveData<Any>
        get() = _success

    private val _failure = SingleLiveEvent<Any>()
    val failure: LiveData<Any>
        get() = _failure

    fun tryGetList() = viewModelScope.launch {
        homeUseCase.execute().addOnSuccessListener { snapshot ->
            val myUid = Firebase.auth.currentUser?.uid.toString()
            friend.clear()
            for (data in snapshot.children) {
                val item = data.getValue<Friend>()
                if (item?.uid.equals(myUid)) {
                    continue
                }
                friend.add(item!!)
            }
            _success.call()
        }

        homeUseCase.execute().addOnCanceledListener {
            _failure.call()
        }
    }
}

