package com.example.d2_p1.admin.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.d2_p1.login.domain.events.LoginEvent
import com.example.d2_p1.login.domain.states.LoginState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var state = androidx.compose.runtime.mutableStateOf(LoginState())
        private set

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChanged -> {
                state.value = state.value.copy(email = event.email)
            }
            is LoginEvent.OnPasswordChanged -> {
                state.value = state.value.copy(password = event.password)
            }
            is LoginEvent.OnClickedLogin -> {
                simulateLogin()
            }
        }
    }

    private fun simulateLogin() {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true, errorMessage = null)

            delay(2000)

            if (state.value.email == "" && state.value.password == "") {
                state.value = state.value.copy(isLoading = false, isSuccess = true)
            } else {
                state.value = state.value.copy(
                    isLoading = false,
                    errorMessage = "Email ou mot de passe incorrect."
                )
            }
        }
    }
}
