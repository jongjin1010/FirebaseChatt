package com.jjoh.firebasechatt.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.jjoh.firebasechatt.repository.remote.getlist.datasource.GetListDataSource
import com.jjoh.firebasechatt.repository.remote.getlist.datasource.GetListDataSourceImpl
import com.jjoh.firebasechatt.repository.remote.signup.datasource.SignUpDataSource
import com.jjoh.firebasechatt.repository.remote.signin.datasource.SignInDataSource
import com.jjoh.firebasechatt.repository.remote.signin.datasource.SignInDataSourceImpl
import com.jjoh.firebasechatt.repository.remote.signup.datasource.SignUpDataSourceImpl
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
        firebaseAuth: FirebaseAuth,
        firebaseStorage: FirebaseStorage,
    ): SignUpDataSource {
        return SignUpDataSourceImpl(
            firebaseAuth, firebaseStorage
        )
    }

    @Provides
    @Singleton
    fun provideSignInDataSource(
        firebaseAuth: FirebaseAuth,
    ): SignInDataSource {
        return SignInDataSourceImpl(
            firebaseAuth
        )
    }

    @Provides
    @Singleton
    fun provideGetListDataSource(
        fireBaseDataBase: FirebaseDatabase
    ) : GetListDataSource {
        return GetListDataSourceImpl(
            fireBaseDataBase
        )
    }
}