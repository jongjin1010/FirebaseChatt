package com.jjoh.firebasechatt.di

import com.jjoh.data.repository.remote.datasource.GetListDataSource
import com.jjoh.data.repository.remote.GetListRepositoryImpl
import com.jjoh.data.repository.remote.datasource.SignInDataSource
import com.jjoh.data.repository.remote.SignInRepositoryImpl
import com.jjoh.data.repository.remote.datasource.SignUpDataSource
import com.jjoh.data.repository.remote.SignUpRepositoryImpl
import com.jjoh.domain.repository.GetListRepository
import com.jjoh.domain.repository.SignInRepository
import com.jjoh.domain.repository.SignUpRepository
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
    fun provideGetListRepository(
        getListDataSource: GetListDataSource
    ) : GetListRepository {
        return GetListRepositoryImpl(
            getListDataSource
        )
    }
}