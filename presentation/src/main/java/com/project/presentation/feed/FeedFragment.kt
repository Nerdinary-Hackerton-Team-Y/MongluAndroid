package com.project.presentation.feed

import android.annotation.SuppressLint
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.project.presentation.R
import com.project.presentation.databinding.FragmentFeedBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FeedFragment : Fragment() {
    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FeedViewModel by viewModels()
    private lateinit var feedAdapter: FeedAdapter
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private val sortList = listOf("최신순", "인기순")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(layoutInflater, container, false)
        return _binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListener()
        initViewModel()
    }


    private fun initViewModel() = with(viewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            uiState.collectLatest { uiState ->
                feedAdapter.setItems(uiState.feedList)

            }
        }
    }

    private fun init() {
        arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, sortList)
        feedAdapter = FeedAdapter(callback = object : FeedAdapterCallback {
            override fun onItemClick() {

            }

            override fun onLikeClick() {

            }
        })
    }

    private fun initView(){
        binding.rvFeed.adapter = feedAdapter
        binding.spinnerSort.adapter = arrayAdapter
    }

    @SuppressLint("SetTextI18n")
    private fun initListener() {
        binding.apply {
            ivSearch.setOnClickListener {
                viewModel.searchFeed(etHashtag.text.toString())
                hideKeyboard(etHashtag)
                etHashtag.clearFocus()
            }

            etHashtag.apply {
                onFocusChangeListener = OnFocusChangeListener { _, hasFocused ->
                    if (hasFocused) {
                        setTextColor(
                            requireContext().getColor(R.color.gray900)
                        )
                    } else {
                        if (etHashtag.text.isNotEmpty()) {
                            setTextColor(
                                requireContext().getColor(R.color.mong500)
                            )
                            if (!etHashtag.text.contains("#")) {
                                etHashtag.setText("#${etHashtag.text}")
                            }
                        }

                    }
                }

                setOnEditorActionListener { _, actionId, event ->
                    if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)
                    ) {
                        viewModel.searchFeed(etHashtag.text.toString())
                        hideKeyboard(etHashtag)
                        etHashtag.clearFocus()
                        true // 이벤트 소비
                    } else {
                        false // 이벤트 전달
                    }
                }
            }

            spinnerSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    // 선택된 항목 처리
                    viewModel.setSortCode(position)
                    val str = etHashtag.text.toString().apply{
                        replace("#","")
                    }
                    viewModel.searchFeed(str)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // 선택이 없을 때 처리 (필요 시 구현)
                }
            }
        }
    }

    // 키보드 숨기기 함수
    private fun hideKeyboard(editText: EditText) {
        val imm = requireContext().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }
}