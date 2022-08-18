package com.sawacorp.mytime.di

import android.content.Context
import com.sawacorp.mytime.repository.SliceRepository
import com.sawacorp.mytime.storage.DataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class MainModule {

    @Provides
    fun provideRepository(
        database: DataBase,
    ): SliceRepository {
        return SliceRepository(database)
    }

    @Provides
    fun provideDatabase(
        @ApplicationContext
        application: Context
    ): DataBase {
        return DataBase.getDatabase(application)
    }

}