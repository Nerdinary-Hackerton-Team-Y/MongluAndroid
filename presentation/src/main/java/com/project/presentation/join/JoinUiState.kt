package com.project.presentation.join

data class JoinUiState(
    val email: String,
    val pw: String,
    val pw2: String,
    val isRegisterSuccess: Boolean,
) {
    companion object {
        fun init() = JoinUiState(
            email = "",
            pw = "",
            pw2 = "",
            isRegisterSuccess = false
        )
    }
}