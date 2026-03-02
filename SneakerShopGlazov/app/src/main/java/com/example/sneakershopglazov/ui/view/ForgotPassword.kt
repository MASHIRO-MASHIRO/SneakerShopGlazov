package com.example.sneakershopglazov.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ForgotPassword(modifier: Modifier = Modifier) {

    var email by remember { mutableStateOf(value = "") }

    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.Center) {
        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier.fillMaxWidth()){
            Spacer(modifier = Modifier.width(65.dp))
            Text(modifier = Modifier.fillMaxWidth(), text = "Забыл Пароль", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth()){
            Spacer(modifier = Modifier.width(20.dp))
            Text(modifier = Modifier.fillMaxWidth(), text = "Введите свою учетную запись для сброса", color = Color.Gray, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(60.dp))
        Text(text = "Email", color = Color.Gray, fontSize = 16.sp)
        OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = email, onValueChange = {email = it})
        Spacer(modifier.height(20.dp))
        Button(onClick = {
            viewModel.signUp(SignUpRequest(email, password), context, navController)
        }, shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(4.dp, 0.dp).fillMaxWidth()) {
            Text("Отправить", fontSize = 16.sp)
        }
        Spacer(modifier.height(400.dp))
    }
}

@Preview
@Composable
private fun ForgotPasswordPreview() {
    ForgotPassword()
}