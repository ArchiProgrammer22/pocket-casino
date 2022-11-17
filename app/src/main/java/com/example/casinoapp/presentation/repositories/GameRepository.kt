package com.example.casinoapp.presentation.repositories

interface GameRepository {
    fun getRandomResults(): Triple<Int, Int, Int>
    fun updateScore(score: Float)
    fun getScore(): Float
    fun getWinReward(): Float
    fun setWinReward(float: Float)
}