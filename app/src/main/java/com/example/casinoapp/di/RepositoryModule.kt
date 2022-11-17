package com.example.casinoapp.di

import com.example.casinoapp.data.repositories.GameRepositoryImpl
import com.example.casinoapp.data.repositories.RepositoryImpl
import com.example.casinoapp.presentation.repositories.GameRepository
import com.example.casinoapp.presentation.repositories.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    fun bindGameRepository(gameRepositoryImpl: GameRepositoryImpl): GameRepository
}