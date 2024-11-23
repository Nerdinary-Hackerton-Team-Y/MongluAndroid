package com.project.presentation.join

data class JoinUiState(
    val email: String,
    val pw: String,
    val pw2: String
) {
    companion object {
        fun init() = JoinUiState(
            email = "",
            pw = "",
            pw2 = ""
        )
    }
}