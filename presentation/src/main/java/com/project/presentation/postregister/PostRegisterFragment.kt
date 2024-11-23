package com.project.presentation.postregister

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.project.presentation.databinding.FragmentPostRegisterBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class PostRegisterFragment : Fragment() {
    private var _binding: FragmentPostRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PostRegisterViewModel by viewModels()

    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            insertImage(uri)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() = with(binding) {
        fun initEtDataToViewModel() {
            val list = listOf(
                etPostRegisterTitle to UpdateEtType.TITLE,
                etPostRegisterContent to UpdateEtType.CONTENT,
                etPostRegisterTag to UpdateEtType.TAG
            )

            list.forEach {
                it.first.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                    }

                    override fun afterTextChanged(s: Editable?) {
                        viewModel.updateEtUiState(
                            type = it.second,
                            data = s.toString()
                        )
                    }

                })
            }
        }

        fun initSelectImage() {
            ivPostRegister.setOnClickListener {
                pickImageLauncher.launch("image/*")
            }
        }

        fun initTopBar() {
            ivPostRegisterX.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        fun initRegisterBtn() {
            btnPostRegisterRegister.setOnClickListener {
                viewModel.postContent()
            }
        }

        fun initIsChallenge() {
            arguments?.getBoolean("isChallenge")?.let {
                binding.scPostDetailIsChallenge.isChecked = it
            }

        }
        initEtDataToViewModel()
        initSelectImage()
        initTopBar()
        initRegisterBtn()
        initIsChallenge()
    }

    private fun initViewModel() = with(viewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            uiState.collectLatest { uiState ->
                isBtnEnable(uiState)
            }
        }
    }

    private fun isBtnEnable(uiState: PostRegisterUiState) {
        uiState.run {
            binding.btnPostRegisterRegister.isEnabled = (img != null && title.isNotEmpty() && content.isNotEmpty())
        }
    }

    private fun insertImage(uri: Uri) {
        Glide.with(this)
            .load(uri)
            .into(binding.ivPostRegister)

        viewModel.updateImgUri(uri)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}