package com.example.casinoapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.casinoapp.R
import com.example.casinoapp.domain.Hint
import com.example.casinoapp.presentation.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class LoadingViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun getRandomHint() = liveData {
        val hints = repository.getHints()
        val randomNum = Random.nextInt(0, hints.size)
        emit(hints[randomNum])
    }

    fun checkConfig(): Int {
        return if (repository.getMode()) {
            R.id.action_loadingFragment_to_gameMenuFragment
        } else {
            R.id.action_loadingFragment_to_webViewFragment
        }
    }

    fun setLink(link: String) = repository.setLink(link)

    fun getLink(): String {
        return repository.getLink()
    }

    fun loadHints() {
        viewModelScope.launch {
            repository.insertLink(
                Hint("Play and win!"),
                Hint("Life is what happens to you while you are making plans"),
                Hint("Once we won 1 million at a time")
            )
        }
    }
}