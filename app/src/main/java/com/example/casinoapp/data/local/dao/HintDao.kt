package com.example.casinoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.casinoapp.domain.Hint

@Dao
interface HintDao {
    @Query("SELECT * FROM Hint")
    fun getHints(): List<Hint>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertHint(vararg hint: Hint)
}