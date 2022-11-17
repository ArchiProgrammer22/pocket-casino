package com.example.casinoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.casinoapp.data.local.dao.HintDao
import com.example.casinoapp.domain.Hint

@Database(entities = [Hint::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun getHintDao(): HintDao
}