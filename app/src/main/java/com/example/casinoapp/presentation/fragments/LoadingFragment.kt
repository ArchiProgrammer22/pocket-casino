package com.example.casinoapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.casinoapp.databinding.FragmentLoadingBinding
import com.example.casinoapp.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class LoadingFragment : Fragment() {

    private var binding: FragmentLoadingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoadingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).loadingViewModel.getRandomHint().observe(viewLifecycleOwner) {
            binding?.hintText?.text = it.text
        }
        startWithDelay((activity as MainActivity).loadingViewModel.checkConfig())
    }

    private fun startWithDelay(res: Int) {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                activity?.runOnUiThread {
                    findNavController().navigate(res)
                }
            }
        }, 1000)
    }
}