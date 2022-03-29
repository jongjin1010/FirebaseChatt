package com.jjoh.firebasechatt.di

import com.jjoh.domain.repository.GetListRepository
import com.jjoh.domain.repository.SetChatListRepository
import com.jjoh.domain.repository.SignInRepository
import com.jjoh.domain.repository.SignUpRepository
import com.jjoh.domain.usecase.TryLoginUseCase
import com.jjoh.domain.usecase.TryRegUseCase
import com.jjoh.domain.usecase.TryGetListUseCase
import com.jjoh.domain.usecase.TrySetChatListUseCase
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

    @Provides
    @Singleton
    fun provideTryGetList(getListRepository: GetListRepository) = TryGetListUseCase(getListRepository)

    @Provides
    @Singleton
    fun provideTrySetChatList(setChatListRepository: SetChatListRepository) = TrySetChatListUseCase(setChatListRepository)
}