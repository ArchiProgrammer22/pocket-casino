package com.example.casinoapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.casinoapp.Utils
import com.example.casinoapp.data.local.Database
import com.example.casinoapp.data.local.dao.HintDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Provides
    fun provideRoom(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context, Database::class.java,
            Utils.DBNAME
        ).build()
    }

    @Provides
    fun provideDao(database: Database): HintDao {
        return database.getHintDao()
    }

    @Provides
    fun provideSharedPrefs(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(Utils.FILE_NAME, Context.MODE_PRIVATE)
    }
}