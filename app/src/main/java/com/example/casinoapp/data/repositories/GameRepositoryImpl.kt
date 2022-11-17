package com.example.casinoapp.data.repositories

import android.content.SharedPreferences
import com.example.casinoapp.Utils
import com.example.casinoapp.presentation.repositories.GameRepository
import javax.inject.Inject
import kotlin.random.Random

class GameRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : GameRepository {
    override fun getRandomResults(): Triple<Int, Int, Int> {
        return Triple(
            Random.nextInt(0, 2),
            Random.nextInt(0, 2),
            Random.nextInt(0, 2)
        )
    }

    override fun updateScore(score: Float) {
        val secondScore = getScore() + score
        sharedPreferences.edit()
            .putFloat(Utils.UPDATE_SCORE, secondScore)
            .apply()
    }

    override fun getScore() = sharedPreferences.getFloat(Utils.UPDATE_SCORE, 0f)

    override fun getWinReward() = sharedPreferences.getFloat(Utils.WIN_REWARD, 40f)

    override fun setWinReward(float: Float) {
        val upperReward = getWinReward() * float
        sharedPreferences.edit()
            .putFloat(Utils.WIN_REWARD, upperReward)
            .apply()
    }
}