package com.example.casinoapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.casinoapp.R
import com.example.casinoapp.presentation.viewmodels.GameViewModel
import com.example.casinoapp.presentation.viewmodels.LoadingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val loadingViewModel: LoadingViewModel by viewModels()
    val gameViewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        loadingViewModel.loadHints()
    }
}