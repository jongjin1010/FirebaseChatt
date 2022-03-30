package com.jjoh.firebasechatt.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.jjoh.data.repository.remote.datasource.*
import com.jjoh.data.repository.remote.datasourceimpl.*
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
    fun provideHomeDataSource(
        fireBaseDataBase: FirebaseDatabase
    ) : HomeDataSource {
        return HomeDataSourceImpl(
            fireBaseDataBase
        )
    }

    @Provides
    @Singleton
    fun provideChatListDataSource(
        fireBaseDataBase: FirebaseDatabase
    ) : ChatListDataSource {
        return ChatListDataSourceImpl(
            fireBaseDataBase
        )
    }

    @Provides
    @Singleton
    fun provideProfileDataSource(
        fireBaseDataBase: FirebaseDatabase,
        firebaseStorage: FirebaseStorage
    ) : ProfileDataSource {
        return ProfileDataSourceImpl(
            fireBaseDataBase, firebaseStorage
        )
    }
}