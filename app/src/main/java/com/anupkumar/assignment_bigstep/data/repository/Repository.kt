package com.anupkumar.assignment_bigstep.data.repository

import androidx.lifecycle.LiveData
import com.anupkumar.assignment_bigstep.data.local.VideoCollectionDao
import com.anupkumar.assignment_bigstep.data.local.VideoCollectionData
import com.anupkumar.assignment_bigstep.data.remote.VideoCollectionApiService
import com.anupkumar.assignment_bigstep.data.remote.VideoCollectionResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class Repository @Inject constructor(private val videoCollectionDao: VideoCollectionDao, private val apiService: VideoCollectionApiService) {


    fun getCollection(): Observable<VideoCollectionResponse?>? {
        return apiService.getVideoCollection()
    }

    fun insertCollection(videoCollection: VideoCollectionData?) {

        videoCollectionDao.insertCollection(videoCollection)
    }



    fun getDetailData(trackId: Long?,collectionId: Long?) : Boolean{
        return videoCollectionDao.getDetail(trackId,collectionId)
    }



    fun getHistoryCollection(): LiveData<List<VideoCollectionData>> {
        return videoCollectionDao.getHistoryCollection()
    }


}