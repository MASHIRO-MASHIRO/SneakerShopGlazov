package com.example.sneakershopglazov.ui.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.sneakershopglazov.data.RetrofitInstance
import com.example.sneakershopglazov.data.model.VerifyOtpRequest
import kotlinx.coroutines.launch

class VerifyOTPViewModel : ViewModel() {

    fun verifyOTP(
        email: String,
        token: String,
        type: String,
        context: Context,
        navController: NavController
    ) {
        viewModelScope.launch {
            try {
                val requestType = if (type == "recovery") "recovery" else "signup"

                val request = VerifyOtpRequest(
                    type = requestType,
                    email = email,
                    token = token
                )

                val response = RetrofitInstance.userManagementService.verifyOTP(request)

                if (response.isSuccessful) {
                    if (type == "recovery") {
                        navController.navigate("new_password/$email")
                    } else {
                        navController.navigate("login") {
                            popUpTo("register") { inclusive = true }
                        }
                    }
                } else {
                    Toast.makeText(context, "Неверный код", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Ошибка сети: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
