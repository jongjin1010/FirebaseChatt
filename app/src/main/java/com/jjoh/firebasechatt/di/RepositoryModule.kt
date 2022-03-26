package com.jjoh.firebasechatt.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.jjoh.firebasechatt.repository.remote.datasource.SignUpDataSource
import com.jjoh.firebasechatt.repository.remote.datasourceimpl.SignUpDataSourceImpl
import com.jjoh.firebasechatt.repository.remote.signup.SignUpRepository
import com.jjoh.firebasechatt.repository.remote.signup.SignUpRepositoryImpl
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
    ):SignUpRepository{
        return SignUpRepositoryImpl(
            signUpDataSource
        )
    }
}