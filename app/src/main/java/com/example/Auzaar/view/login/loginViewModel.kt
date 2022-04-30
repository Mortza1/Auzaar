package com.example.Auzaar.view.login

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

class loginViewModel() : ViewModel() {
    private val repository: AuthRepository = AuthRepository(auth = Firebase.auth)


    private val _signInState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signInState: State<Response<Boolean>> = _signInState

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            repository.signInWithEmailPassword(email, password).collect { response ->
                _signInState.value = response
                Log.d("tag", response.toString())
            }
        }
    }
}