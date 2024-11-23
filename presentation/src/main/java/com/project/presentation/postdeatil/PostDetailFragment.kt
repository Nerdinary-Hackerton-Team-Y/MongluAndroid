package com.project.presentation.postdeatil

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.project.presentation.R
import com.project.presentation.databinding.FragmentPostDetailBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PostDetailFragment : Fragment() {
    private var _binding: FragmentPostDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PostDetailViewModel by viewModels()

    private val commentListAdapter: CommentListAdapter by lazy {
        CommentListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()
    }

    private fun initView() = with(binding) {
        fun initRvComment() {
            rvPostDetailComment.run {
                adapter = commentListAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

//        fun initEtCommentEnable() {
//            ivPostDetailBtnAddComment.setOnClickListener {
//                if (ivPostDetailBtnAddComment.drawable.constantState ==
//                    ContextCompat.getDrawable(
//                        requireContext(),
//                        R.drawable.ic_add_comment_on
//                    )?.constantState
//                ) {
//                    viewModel.addComment(etPostDetailComment.text.toString())
//                } else if (ivPostDetailBtnAddComment.drawable.constantState ==
//                    ContextCompat.getDrawable(
//                        requireContext(),
//                        R.drawable.ic_add_comment_off
//                    )?.constantState
//                ) {
//                }
//            }
//
//            etPostDetailComment.addTextChangedListener(object : TextWatcher {
//                override fun beforeTextChanged(
//                    s: CharSequence?,
//                    start: Int,
//                    count: Int,
//                    after: Int
//                ) {
//                }
//
//                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                }
//
//                override fun afterTextChanged(s: Editable?) {
//                    if (s.toString().isNotEmpty()) {
//                        ivPostDetailBtnAddComment.setImageDrawable(
//                            ContextCompat.getDrawable(
//                                requireContext(),
//                                R.drawable.ic_add_comment_on
//                            )
//                        )
//                    } else ivPostDetailBtnAddComment.setImageDrawable(
//                        ContextCompat.getDrawable(
//                            requireContext(),
//                            R.drawable.ic_add_comment_off
//                        )
//                    )
//                }
//            })
//        }

        ivPostDetailX.setOnClickListener {
            findNavController().popBackStack()
        }

        initRvComment()
    }

    private fun initViewModel() = with(viewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            uiState.collectLatest { uiState ->
                onBind(uiState)
            }
        }
    }

    private fun onBind(uiState: PostDetailUiState) = with(binding) {
        uiState.commentItem?.let {
            commentListAdapter.submitList(it)

            tvPostDetailChatNum.text = it.size.toString()
        }

        uiState.contentItem?.let {
            tvPostDetailTitle.text = it.iconImg
            tvPostDetailWriter.text = it.writer
            tvPostDetailContent.text = it.content

            Glide.with(requireContext())
                .load(it.contentImg)
                .into(ivPostDetailImg)
            Glide.with(requireContext())
                .load(it.iconImg)

        }

        val isMineIv = listOf(
            ivPostDetailDelete,
            ivPostDetailWriterIcon
        )
        isMineIv.forEach {
            if (uiState.isMine) it.visibility = View.VISIBLE
            else it.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}