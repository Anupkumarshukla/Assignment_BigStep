package com.anupkumar.assignment_bigstep.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "VideoCollection_table")
data class VideoCollectionData (@PrimaryKey(autoGenerate = true) val id: Int,
                                val artistId: Long?,
                                val collectionId: Long?,
                                val trackId: Long?,
                                val artistName: String?,
                                val collectionName: String?,
                                val trackName: String?,
                                val artistViewUrl: String?,
                                val collectionViewUrl: String?,
                                val trackViewUrl: String?,
                                val previewUrl: String?,
                                val artworkUrl30: String?,
                                val artworkUrl60: String?,
                                val artworkUrl100: String?,
                                val collectionPrice: Double?,
                                val trackPrice: Double?,
                                val releaseDate: String?,
                                val currency: String?) : Parcelable