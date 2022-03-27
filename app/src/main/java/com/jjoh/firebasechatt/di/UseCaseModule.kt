package com.jjoh.firebasechatt.di

import com.jjoh.firebasechatt.repository.remote.signin.repository.SignInRepository
import com.jjoh.firebasechatt.repository.remote.signup.repository.SignUpRepository
import com.jjoh.firebasechatt.usecase.TryLoginUseCase
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

    @Provides
    @Singleton
    fun provideTryLoginUseCase(signInRepository: SignInRepository) = TryLoginUseCase(signInRepository)
}