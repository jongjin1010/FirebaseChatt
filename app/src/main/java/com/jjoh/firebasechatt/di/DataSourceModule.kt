package com.jjoh.firebasechatt.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.jjoh.firebasechatt.repository.remote.datasource.SignUpDataSource
import com.jjoh.firebasechatt.repository.remote.datasourceimpl.SignUpDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideSignUpDataSource(
        firebaseAuth: FirebaseAuth
    ): SignUpDataSource{
        return SignUpDataSourceImpl(
            firebaseAuth
        )
    }
}