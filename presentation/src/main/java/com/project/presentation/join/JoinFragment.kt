package com.project.presentation.join

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.project.presentation.databinding.FragmentJoinBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class JoinFragment : Fragment() {
    private var _binding: FragmentJoinBinding? = null
    private val binding get() = _binding!!

    private val viewModel: JoinViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJoinBinding.inflate(inflater, container, false)
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
                etJoinEmail to UpdateJoinEtType.EMAIL,
                etJoinPassword to UpdateJoinEtType.PW,
                etJoinPasswordRe to UpdateJoinEtType.PW_RE
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

        fun initAllCheckBox() {
            val checkBoxList = listOf(
                checkboxJoinService,
                checkboxJoinLocation,
                checkboxJoinInformation
            )

            checkboxJoinAllCheck.setOnCheckedChangeListener { _, isChecked ->
                checkBoxList.forEach {
                    it.isChecked = isChecked
                }
            }

            checkBoxList.forEach { checkBox ->
                checkBox.setOnCheckedChangeListener { _, _ ->
                    val allChecked = checkBoxList.all { it.isChecked }
                    checkboxJoinAllCheck.isChecked = allChecked
                }
            }
        }


        fun initClick() {
            btnJoin.setOnClickListener {
                viewModel.join(clear = {
                    findNavController().popBackStack()
                })
            }
            ivJoinX.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        initEtDataToViewModel()
        initAllCheckBox()
        initClick()
    }

    private fun initViewModel() = with(viewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            uiState.collectLatest { uiSate ->
                onBind(uiSate)
            }
        }
    }

    private fun onBind(uiState: JoinUiState) = with(binding) {
        uiState.let {
            if (it.pw != it.pw2) {
                tvJoinPasswordReDescription.visibility = View.VISIBLE
            } else {
                tvJoinPasswordReDescription.visibility = View.GONE

                val pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!~@$]).{8,}$".toRegex()
                val emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()



                btnJoin.isEnabled =
                    it.pw.matches(pwPattern) && it.email.matches(emailPattern) && checkboxJoinAllCheck.isChecked
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
