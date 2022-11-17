package com.example.casinoapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.casinoapp.databinding.FragmentShopBinding
import com.example.casinoapp.presentation.MainActivity

class ShopFragment : Fragment() {

    private var binding: FragmentShopBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.button?.setOnClickListener {
            val resultString = (activity as MainActivity).gameViewModel.setWinRewards(1.5f)
            Toast.makeText(context, resultString, Toast.LENGTH_SHORT).show()
        }
    }
}