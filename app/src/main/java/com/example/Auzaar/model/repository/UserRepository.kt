package com.example.Auzaar.model.repository

import com.example.Auzaar.model.Response
import com.example.Auzaar.model.User
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class UserRepository(
    private val userRef: CollectionReference
) {
    suspend fun addUserToFirestore(
        email: String,
        password: String
    ) = flow {
        try {
            emit(Response.Loading)
            val userId = userRef.document().id
            val user = User(
                id = userId,
                email = email,
                password = password
            )
            val addition = userRef.document(userId).set(user).await()
            emit(Response.Success(addition))
        } catch (e: Exception){
                emit(Response.Error(e.message ?: e.toString()))
        }
    }
}