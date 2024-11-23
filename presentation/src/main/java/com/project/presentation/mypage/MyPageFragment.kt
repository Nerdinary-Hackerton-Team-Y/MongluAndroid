package com.project.presentation.mypage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.project.presentation.NavColorSet
import com.project.presentation.R
import com.project.presentation.databinding.FragmentMypageBinding
import com.project.presentation.feed.FeedAdapter
import com.project.presentation.feed.FeedAdapterCallback
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MyPageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MyPageViewModel by viewModels()
    private lateinit var feedAdapter: FeedAdapter
    private var selectedTab: Int = 0
    private var navSetContext: NavColorSet? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavColorSet) {
            navSetContext = context
            context.setNavMypage()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMypageBinding.inflate(layoutInflater, container, false)
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
                if (selectedTab == 0) {
                    feedAdapter.setItems(uiState.myPostList)
                } else {
                    feedAdapter.setItems(uiState.reviewedList)
                }
            }
        }
    }

    private fun init() {
        feedAdapter = FeedAdapter(
            isShowLike = false,
            callback = object : FeedAdapterCallback {
                override fun onItemClick() {

                }

                override fun onLikeClick() {

                }
            })
    }

    private fun initView() {
        binding.apply {
            rvFeed.adapter = feedAdapter
            Glide.with(ivProfile)
                .load("https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg")
                .circleCrop()
                .into(ivProfile)
        }
        setTabState()
    }

    private fun initListener() {
        binding.apply {
            clMyPost.setOnClickListener {
                selectedTab = 0
                setTabState()
                feedAdapter.setItems(viewModel.uiState.value.myPostList)
                viewModel.getMyPost()
            }
            clReviewPost.setOnClickListener {
                selectedTab = 1
                setTabState()
                feedAdapter.setItems(viewModel.uiState.value.reviewedList)
                viewModel.getReviewedPost()
            }
        }
    }

    private fun setTabState() {
        binding.apply {
            if (selectedTab == 0) {
                viewLine1.visibility = View.VISIBLE
                viewLine2.visibility = View.GONE
                tvMyPost.setTextColor(requireContext().getColor(R.color.gray900))
                tvReviewPost.setTextColor(requireContext().getColor(R.color.gray400))
                tvMyPost.typeface = resources.getFont(R.font.pretendard_semibold)
                tvReviewPost.typeface = resources.getFont(R.font.pretendard_regular)
            } else {
                viewLine1.visibility = View.GONE
                viewLine2.visibility = View.VISIBLE
                tvMyPost.setTextColor(requireContext().getColor(R.color.gray400))
                tvReviewPost.setTextColor(requireContext().getColor(R.color.gray900))
                tvMyPost.typeface = resources.getFont(R.font.pretendard_regular)
                tvReviewPost.typeface = resources.getFont(R.font.pretendard_semibold)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        navSetContext?.setNavMypage()
    }
}