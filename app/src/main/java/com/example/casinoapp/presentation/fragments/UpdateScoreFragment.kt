package com.example.casinoapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.casinoapp.databinding.FragmentUpdateScoreBinding
import com.example.casinoapp.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateScoreFragment : Fragment() {

    private var binding: FragmentUpdateScoreBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateScoreBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.refillButton?.setOnClickListener {
            val score = binding?.editTextNumber?.text
            val result = (activity as MainActivity).gameViewModel.updateScore(score?.toString()!!)
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }
}