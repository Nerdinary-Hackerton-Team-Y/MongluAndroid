package com.project.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.data.repository.RepositoryFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.init())
    val uiState = _uiState.asStateFlow()

    private val pcpRepository = RepositoryFactory.createPCPRepository()

    fun getPCP(nx: Int, ny: Int) = viewModelScope.launch {
        Log.d(
            "pcp", pcpRepository.getPCP(nx = nx, ny = ny).toString()
        )
    }
}