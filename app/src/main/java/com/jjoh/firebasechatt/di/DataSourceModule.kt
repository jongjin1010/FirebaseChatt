package com.jjoh.firebasechatt.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.jjoh.data.repository.remote.datasource.GetListDataSource
import com.jjoh.data.repository.remote.datasourceimpl.GetListDataSourceImpl
import com.jjoh.data.repository.remote.datasource.SignUpDataSource
import com.jjoh.data.repository.remote.datasource.SignInDataSource
import com.jjoh.data.repository.remote.datasourceimpl.SignInDataSourceImpl
import com.jjoh.data.repository.remote.datasourceimpl.SignUpDataSourceImpl
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