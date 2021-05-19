package com.anupkumar.assignment_bigstep.di

import android.app.Application
import androidx.room.Room
import com.anupkumar.assignment_bigstep.data.local.VideoCollectionDB
import com.anupkumar.assignment_bigstep.data.local.VideoCollectionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideVideoCollectionDB(application: Application?): VideoCollectionDB {
        return Room.databaseBuilder(application!!, VideoCollectionDB::class.java, "VideoCollectionDB")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideVideoCollectionDao(videoCollectionDB: VideoCollectionDB): VideoCollectionDao {
        return videoCollectionDB.videoCollectionDao()
    }
}