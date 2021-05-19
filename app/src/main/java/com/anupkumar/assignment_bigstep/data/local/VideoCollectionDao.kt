package com.anupkumar.assignment_bigstep.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VideoCollectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCollection(VideoCollectionData: VideoCollectionData?)

    @Query("DELETE FROM VideoCollection_table WHERE collectionName = :collectionName")
    fun deleteRecoard(collectionName: String?)

    @Query("DELETE FROM VideoCollection_table")
    fun deleteAll()

    @Query("SELECT * FROM VideoCollection_table")
    fun getHistoryCollection(): LiveData<List<VideoCollectionData>>







    @Query("SELECT * FROM VideoCollection_table WHERE trackId = :trackId OR collectionId = :collectionId")
    fun getDetail(trackId: Long?,collectionId: Long?): Boolean
}