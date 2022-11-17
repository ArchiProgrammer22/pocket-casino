package com.example.casinoapp.presentation.repositories

import com.example.casinoapp.domain.Hint

interface Repository {
    suspend fun getHints(): List<Hint>
    fun getMode(): Boolean
    suspend fun insertLink(vararg hint: Hint)
    fun setLink(link: String)
    fun getLink(): String
}