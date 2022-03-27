package com.jjoh.firebasechatt.di

import com.jjoh.firebasechatt.repository.remote.getlist.datasource.GetListDataSource
import com.jjoh.firebasechatt.repository.remote.getlist.repository.GetListRepository
import com.jjoh.firebasechatt.repository.remote.getlist.repository.GetListRepositoryImpl
import com.jjoh.firebasechatt.repository.remote.signin.datasource.SignInDataSource
import com.jjoh.firebasechatt.repository.remote.signin.repository.SignInRepository
import com.jjoh.firebasechatt.repository.remote.signin.repository.SignInRepositoryImpl
import com.jjoh.firebasechatt.repository.remote.signup.datasource.SignUpDataSource
import com.jjoh.firebasechatt.repository.remote.signup.repository.SignUpRepository
import com.jjoh.firebasechatt.repository.remote.signup.repository.SignUpRepositoryImpl
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
    ) : GetListRepository{
        return GetListRepositoryImpl(
            getListDataSource
        )
    }
}