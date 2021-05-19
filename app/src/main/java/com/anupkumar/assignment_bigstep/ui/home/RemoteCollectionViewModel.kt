package com.anupkumar.assignment_bigstep.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anupkumar.assignment_bigstep.data.local.VideoCollectionData
import com.anupkumar.assignment_bigstep.data.repository.Repository
import com.anupkumar.assignment_bigstep.utils.Event
import com.anupkumar.assignment_bigstep.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RemoteCollectionViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val CollectionList = MutableLiveData<Resource<Event<ArrayList<VideoCollectionData>>>>()


    fun getCollectionList(): MutableLiveData<Resource<Event<ArrayList<VideoCollectionData>>>> {
        return CollectionList
    }

    fun getRemoteCollection() {
        compositeDisposable.addAll(
            repository.getCollection()
                ?.subscribeOn(Schedulers.io())
                ?.map { Response ->
                    val list: ArrayList<VideoCollectionData> = Response!!.data
                    list
                }
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(
                    { result ->
                        CollectionList.setValue(Resource.success(Event(result)))
                    })
                    { error ->
                        Log.e("ViewModel", "message: " + error.message.toString())
                    }

        )
    }
    fun insertCollection(videoCollectionData: VideoCollectionData?) {
        repository.insertCollection(videoCollectionData)
    }

    fun DetailVideo(trackId: Long?,collectionId: Long?) : Boolean {
        return repository.getDetailData(trackId,collectionId)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}