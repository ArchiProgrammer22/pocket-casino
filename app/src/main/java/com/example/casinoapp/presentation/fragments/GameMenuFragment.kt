package com.example.casinoapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.casinoapp.R
import com.example.casinoapp.databinding.FragmentGameMenuBinding
import com.example.casinoapp.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameMenuFragment : Fragment() {

    private var binding: FragmentGameMenuBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        changeBehaviourBackButton()
        binding = FragmentGameMenuBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).gameViewModel.getScore().observe(viewLifecycleOwner) {
            binding?.scoreText?.text = "$it$"
        }

        binding?.playButton?.setOnClickListener {
            findNavController().navigate(R.id.action_gameMenuFragment_to_gameFragment)
        }
        binding?.addFab?.setOnClickListener {
            findNavController().navigate(R.id.action_gameMenuFragment_to_updateScoreFragment)
        }
        binding?.shopButton?.setOnClickListener {
            findNavController().navigate(R.id.action_gameMenuFragment_to_shopFragment)
        }
    }

    private fun changeBehaviourBackButton() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
    }
}