package com.project.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.project.presentation.R
import com.project.presentation.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()
    }

    private fun initViewModel() = with(viewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            uiState.collectLatest { uiState ->
                withContext(Dispatchers.Main) {
                    if (uiState.isLoginSuccess) {
                        val navController = findNavController()

                        val navOptions = NavOptions.Builder()
                            .setPopUpTo(R.id.nav_graph, inclusive = true) // 스택 초기화
                            .build()

                        navController.navigate(R.id.nav_home, null, navOptions)
                    } else {

                    }
                }
            }

        }
    }

    private fun initView() = with(binding) {
        tvLoginJoin.setOnClickListener {
            findNavController().navigate(R.id.nav_join)
        }
        btnLogin.setOnClickListener {
            viewModel.login(
                email = etLoginEmail.text.toString(),
                password = etLoginPassword.text.toString()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}