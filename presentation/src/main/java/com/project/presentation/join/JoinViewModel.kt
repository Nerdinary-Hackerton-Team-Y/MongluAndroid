package com.project.presentation.join

import androidx.lifecycle.ViewModel
import com.project.presentation.postregister.UpdateEtType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class JoinViewModel : ViewModel() {
    private val _uiSate = MutableStateFlow(JoinUiState.init())
    val uiState = _uiSate.asStateFlow()

    fun updateEtUiState(type: UpdateJoinEtType, data: String) {
        when (type) {
            UpdateJoinEtType.EMAIL -> _uiSate.update { prev -> prev.copy(email = data) }
            UpdateJoinEtType.PW -> _uiSate.update { prev -> prev.copy(pw = data) }
            UpdateJoinEtType.PW_RE -> _uiSate.update { prev -> prev.copy(pw2 = data) }
        }
    }

    fun join(clear: () -> Unit) {
        clear
    }
}

enum class UpdateJoinEtType {
    EMAIL, PW, PW_RE
}