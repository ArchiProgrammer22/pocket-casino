package com.example.casinoapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Hint(
    val text: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
