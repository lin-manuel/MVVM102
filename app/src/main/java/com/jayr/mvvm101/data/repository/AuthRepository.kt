package com.jayr.mvvm101.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance() // initializes the firebase auth
) {
//    get current user
val currentUser: FirebaseUser? = auth.currentUser

//    login
suspend fun login(email:String, password:String){
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener {
            task ->
            if(task.isSuccessful){
                print("Welcome home!")
            }else{
                print("something went wrong")
            }
        }.await()
}
//    register
suspend fun register(email:String, password:String){
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener {
                task ->
            if(task.isSuccessful){
                print("Welcome home!")
            }else{
                print("something went wrong")
            }
        }.await()
}
//    forgot password
suspend fun forgotPassword(email:String){
    auth.sendPasswordResetEmail(email)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                print("Email Sent!")
            }
        }.await()
}
}