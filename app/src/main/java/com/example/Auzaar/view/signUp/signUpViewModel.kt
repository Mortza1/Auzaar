package com.example.Auzaar.view.signUp

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Auzaar.model.Response
import com.example.Auzaar.model.User
import com.example.Auzaar.model.repository.AuthRepository
import com.example.Auzaar.model.repository.UserRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class signUpViewModel() : ViewModel() {
    private val authRepository: AuthRepository = AuthRepository(auth = Firebase.auth)
    private val userRepository: UserRepository = UserRepository(Firebase.firestore.collection("users"))


    private val _signUpState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signUpState: State<Response<Boolean>> = _signUpState

    private val _isUserAddedState = mutableStateOf<Response<Void?>>(Response.Success(null))
    val isUserAddedState: State<Response<Void?>> = _isUserAddedState

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            authRepository.createUserWithEmailPassword(email, password).collect { response ->
                _signUpState.value = response
                Log.d("tag", response.toString())
            }
        }
    }

    fun addUser(email: String, password : String) {
        viewModelScope.launch {
            userRepository.addUserToFirestore(email = email, password = password).collect { response ->
                _isUserAddedState.value = response
            }
        }
    }
}