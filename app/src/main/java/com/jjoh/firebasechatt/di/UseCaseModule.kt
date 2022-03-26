package com.jjoh.firebasechatt.di

import com.jjoh.firebasechatt.repository.remote.signup.SignUpRepository
import com.jjoh.firebasechatt.usecase.TryRegUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideTryRegUseCase(signUpRepository: SignUpRepository) = TryRegUseCase(signUpRepository)
}