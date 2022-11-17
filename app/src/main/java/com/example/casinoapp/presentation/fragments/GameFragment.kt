package com.example.casinoapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.casinoapp.databinding.FragmentGameBinding
import com.example.casinoapp.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameFragment : Fragment() {

    private var binding: FragmentGameBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateScore()
        binding?.oneHandPlay?.setOnClickListener {
            updateImages()
            updateScore()
        }
    }

    private fun updateImages() {
        val drawables = (activity as MainActivity).gameViewModel.slotResult()
        if (drawables != null) {
            binding?.imageView?.setImageResource(drawables.first)
            binding?.imageView2?.setImageResource(drawables.second)
            binding?.imageView3?.setImageResource(drawables.third)

            val text = (activity as MainActivity).gameViewModel.getSlotStringResult()
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Not enough money!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateScore() {
        (activity as MainActivity).gameViewModel.getScore().observe(viewLifecycleOwner) {
            binding?.gameScoreText?.text = "$it$"
        }
    }
}