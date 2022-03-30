package com.jjoh.firebasechatt.di

import com.jjoh.data.repository.remote.*
import com.jjoh.data.repository.remote.datasource.*
import com.jjoh.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSignUpRepository(
        signUpDataSource: SignUpDataSource
    ): SignUpRepository {
        return SignUpRepositoryImpl(
            signUpDataSource
        )
    }

    @Provides
    @Singleton
    fun provideSignInRepository(
        signInDataSource: SignInDataSource
    ): SignInRepository {
        return SignInRepositoryImpl(
            signInDataSource
        )
    }

    @Provides
    @Singleton
    fun provideHomeRepository(
        homeDataSource: HomeDataSource
    ) : HomeRepository {
        return HomeRepositoryImpl(
            homeDataSource
        )
    }

    @Provides
    @Singleton
    fun provideChatListRepository(
        ChatListDataSource: ChatListDataSource
    ) : ChatListRepository {
        return ChatListRepositoryImpl(
            ChatListDataSource
        )
    }

    @Provides
    @Singleton
    fun provideProfileRepository(
        profileDataSource: ProfileDataSource
    ) : ProfileRepository {
        return ProfileRepositoryImpl(
            profileDataSource
        )
    }
}