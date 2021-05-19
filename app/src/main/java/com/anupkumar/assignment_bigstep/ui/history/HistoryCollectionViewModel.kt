package com.anupkumar.assignment_bigstep.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.anupkumar.assignment_bigstep.data.local.VideoCollectionData
import com.anupkumar.assignment_bigstep.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryCollectionViewModel @Inject constructor(repository: Repository) : ViewModel() {

    private var HistoryCollectionList: LiveData<List<VideoCollectionData>>? = null
    fun getHistoryCollectionList(): LiveData<List<VideoCollectionData>> {
        return HistoryCollectionList!!
    }

    init {
        HistoryCollectionList = repository.getHistoryCollection()
    }

}