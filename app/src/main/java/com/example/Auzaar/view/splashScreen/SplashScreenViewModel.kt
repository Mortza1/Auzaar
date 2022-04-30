package com.example.Auzaar.view.splashScreen

import  androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.Auzaar.model.repository.AuthRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers

class SplashScreenViewModel() : ViewModel() {
private val repository: AuthRepository = AuthRepository(auth = Firebase.auth)
    val isUserAuthenticated get() = repository.isUserAuthenticatedInFirebase()

    fun getAuthState() = liveData(Dispatchers.IO) {
        repository.getFirebaseAuthState().collect { response ->
            emit(response)

        }
    }

}