package com.example.sneakershopglazov.ui.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RegisterAccountViewModelViewModel : ViewModel() {
    var showDialog = mutableStateOf(false)
    var dialogText = mutableStateOf("")
}