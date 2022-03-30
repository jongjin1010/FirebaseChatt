package com.jjoh.firebasechatt.di

import com.jjoh.domain.repository.*
import com.jjoh.domain.usecase.*
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
    fun provideTryRegUseCase(signUpRepository: SignUpRepository) = RegUseCase(signUpRepository)

    @Provides
    @Singleton
    fun provideTryLoginUseCase(signInRepository: SignInRepository) = LoginUseCase(signInRepository)

    @Provides
    @Singleton
    fun provideTryGetList(homeRepository: HomeRepository) = HomeUseCase(homeRepository)

    @Provides
    @Singleton
    fun provideTrySetChatList(chatListRepository: ChatListRepository) = ChatListUseCase(chatListRepository)

    @Provides
    @Singleton
    fun provideProfileRepository(profileRepository: ProfileRepository) = ProfileUseCase(profileRepository)
}