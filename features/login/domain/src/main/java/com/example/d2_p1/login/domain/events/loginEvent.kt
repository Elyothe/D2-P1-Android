package com.example.d2_p1.login.domain.events


sealed class LoginEvent {
    data class OnEmailChanged(val email: String) : LoginEvent()
    data class OnPasswordChanged(val password: String) : LoginEvent()
    object OnClickedLogin : LoginEvent()
}
