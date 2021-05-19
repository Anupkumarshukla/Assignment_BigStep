package com.anupkumar.assignment_bigstep.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [VideoCollectionData::class], version = 19, exportSchema = false)
abstract class VideoCollectionDB : RoomDatabase() {
    abstract fun videoCollectionDao(): VideoCollectionDao
}