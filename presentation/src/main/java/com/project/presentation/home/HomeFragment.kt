package com.project.presentation.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import com.project.presentation.NavColorSet
import com.project.presentation.databinding.FragmentHomeBinding
import com.project.presentation.util.CoordinateConverter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var challengeHonorRankAdapter: HonorRankAdapter
    private lateinit var normalHonorRankAdapter: HonorRankAdapter

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(requireContext())
    }
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                getLocationForPCP()
            } else {
                Toast.makeText(requireContext(), "위치 권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }

    private var navSetContext: NavColorSet? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavColorSet) {
            navSetContext = context
            context.setNavHome()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getPYT()
        initView()
        initViewModel()
    }

    private fun initViewModel() = with(viewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            uiState.collectLatest { uiState ->
                challengeHonorRankAdapter.setItems(uiState.challengeHonorList)
                normalHonorRankAdapter.setItems(uiState.normalHonorList)

                uiState.weather?.let { weather ->
                    uiState.region?.let { region ->
                        binding.tvWeatherState.text = weather.str.replace("#VALUE#", region)
                    }
                }
            }

        }
    }


    private fun getPYT() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            getLocationForPCP()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun getLocationForPCP() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.lastLocation.addOnCompleteListener { task: Task<Location> ->
            val location: Location? = task.result
            if (location != null) {
                val latitude = location.latitude
                val longitude = location.longitude
                CoordinateConverter().convertToXy(latitude, longitude).run {
                    viewModel.getWeatherState(nx, ny)
                }
            } else {
                Toast.makeText(requireContext(), "위치를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun init() {
        normalHonorRankAdapter = HonorRankAdapter(object : HonorRankAdapterCallback {
            override fun onItemClick() {
                TODO("Not yet implemented")
            }

            override fun onLikeClick() {
                TODO("Not yet implemented")
            }

        })

        challengeHonorRankAdapter = HonorRankAdapter(object : HonorRankAdapterCallback {
            override fun onItemClick() {
                TODO("Not yet implemented")
            }

            override fun onLikeClick() {
                TODO("Not yet implemented")
            }

        })
    }

    private fun initView() {
        binding.apply {
            rvRankChallenge.adapter = challengeHonorRankAdapter
            rvRankChallenge.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvRankNormal.adapter = normalHonorRankAdapter
            rvRankNormal.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun onResume() {
        super.onResume()
        navSetContext?.setNavHome()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}