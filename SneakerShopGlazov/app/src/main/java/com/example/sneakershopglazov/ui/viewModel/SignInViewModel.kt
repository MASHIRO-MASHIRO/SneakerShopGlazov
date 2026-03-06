package com.example.sneakershopglazov.ui.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.sneakershopglazov.data.RetrofitInstance
import com.example.sneakershopglazov.data.UserSession
import com.example.sneakershopglazov.data.model.SignInRequest
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {

    var showDialog = mutableStateOf(false)
    var dialogText = mutableStateOf("")

    fun signIn(signInRequest: SignInRequest, navController: NavController) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.userManagementService.signIn(signInRequest)
                dialogText.value = "Должны быть заполнены все поля"
                showDialog.value = true
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val accessToken = body.access_token
                        val userId = body.user.id

                        UserSession.accessToken = accessToken
                        UserSession.userId = userId
                    }

                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                } else {
                    if(signInRequest.email.isEmpty() && signInRequest.password.isEmpty())
                        dialogText.value = "Введены некорректные данные пользователя! Попробуйте ещё разочек"
                    else
                        dialogText.value = "Должны быть заполнены все поля"
                    showDialog.value = true
                }
            } catch (e: Exception) {
                dialogText.value = "Ошибка: ${e.message}"
                showDialog.value = true
            }
        }
    }
}