package com.example.casinoapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.casinoapp.presentation.repositories.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repository: GameRepository,
) : ViewModel() {

    private val drawables = listOf(
        android.R.drawable.star_on,
        android.R.drawable.star_off,
    )

    private var boolean: Boolean? = null

    fun updateScore(score: String): String {
        if (score.isEmpty() ||
            score.contains("^[A-Za-z]*$") || score == "0") {
            return "Wrong enter!"
        }
        repository.updateScore(score.toFloat())
        return "Success refilling!"
    }

    fun getScore() = liveData {
        emit(repository.getScore())
    }

    fun slotResult(): Triple<Int, Int, Int>? {
        if (repository.getScore() >= 10) {
            repository.updateScore(-10f)
            val randomNumbers = repository.getRandomResults()

            boolean = if (randomNumbers.first == randomNumbers.second &&
                randomNumbers.second == randomNumbers.third
            ) {
                repository.updateScore(repository.getWinReward())
                true
            } else {
                false
            }

            return Triple(
                drawables[randomNumbers.first],
                drawables[randomNumbers.second],
                drawables[randomNumbers.third],
            )
        }
        return null
    }

    fun getSlotStringResult(): String {
        if (boolean!!) {
            return "You win!"
        }
        return "Retry one time!"
    }

    fun setWinRewards(float: Float): String {
        if (repository.getScore() > 100) {
            repository.updateScore(-100f)
            repository.setWinReward(float)
            return "Success bought!"
        }
        return "Not enough money!"
    }
}
