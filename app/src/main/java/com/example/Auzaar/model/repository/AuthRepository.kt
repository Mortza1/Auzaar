package com.example.Auzaar.model.repository

import android.util.Log
import com.example.Auzaar.model.Response
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class AuthRepository(
    private val auth: FirebaseAuth
) {
    fun isUserAuthenticatedInFirebase(): Boolean = auth.currentUser != null

    suspend fun createUserWithEmailPassword(email: String, password: String) = flow {
        try {
            emit(Response.Loading)
            auth.createUserWithEmailAndPassword(email, password).await()
            emit(Response.Success(true))
            Log.d("tag", "Account created successfully")
        } catch (e: Exception){
            emit(Response.Error(e.message ?: e.toString()))
            Log.d("tag", "sign up unsuccessful")
        }
    }

    suspend fun signInWithEmailPassword(email: String, password: String) = flow {
        try {
                emit(Response.Loading)
                auth.signInWithEmailAndPassword(email, password).await()
                emit(Response.Success(true))
                Log.d("tag", "logged in SUCCESSfully")
        } catch (e: Exception){
                emit(Response.Error(e.message ?: e.toString()))
                Log.d("tag", "sorry didnt log in")
        }
    }
    suspend fun signOut() = flow {
        try {
            emit(Response.Loading)
            withContext(Dispatchers.IO){
                auth.signOut()
            }
            emit(Response.Success(true))
    } catch (e: Exception){
        emit(Response.Error(e.message ?: e.toString()))
            Log.d("tag","error in signing out")
    }}
    fun getFirebaseAuthState() = callbackFlow  {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }
}