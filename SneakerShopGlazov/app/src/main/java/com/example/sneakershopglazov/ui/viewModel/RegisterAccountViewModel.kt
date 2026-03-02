package com.example.myapplication.ui.viewModel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.sneakershopglazov.data.model.RegisterAccountRequest


class RegisterAccountViewModelViewModel : ViewModel() {

    fun signUp(signUpRequest: RegisterAccountRequest, context: Context, navController: NavController) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(
            "my_app_preferences",
            Context.MODE_PRIVATE
        )
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.userManagementService.signUp(signUpRequest)
                if (response.isSuccessful) {
                    response.body()?.let {
                        sharedPreferences.edit().apply {
                            putString("userEmail", signUpRequest.email)
                            apply()
                        }
                        navController.navigate("verifyOTP")
                    }
                } else {
                    val messageText : String = response.message().toString()
                    Toast.makeText(context, "Пользователь ввел некорректные данные \n $messageText", Toast.LENGTH_SHORT).show()
                }
            }
            catch (e: Exception) {
                val errorText : String = e.message.toString()
                Toast.makeText(context, "Cбой при регистрации \n $errorText", Toast.LENGTH_SHORT).show()
            }
        }
    }


}