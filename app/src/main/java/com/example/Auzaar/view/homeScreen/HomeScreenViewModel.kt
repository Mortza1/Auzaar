package com.example.Auzaar.view.homeScreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Auzaar.model.Response
import com.example.Auzaar.model.repository.AuthRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class HomeScreenViewModel(): ViewModel() {
    private val repository: AuthRepository = AuthRepository(auth = Firebase.auth)

    private val _signOutState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signOutState: State<Response<Boolean>> = _signOutState

    fun signOut() {
        viewModelScope.launch {
            repository.signOut().collect { response ->
                _signOutState.value = response
                Log.d("tag", response.toString())
            }
        }
    }
}