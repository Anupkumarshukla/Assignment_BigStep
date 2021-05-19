package com.anupkumar.assignment_bigstep.data.remote

import com.anupkumar.assignment_bigstep.data.local.VideoCollectionData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class VideoCollectionResponse (
    @Expose
    @SerializedName("resultCount")
    var resultCount: Int,

    @Expose
    @SerializedName("results")
    val data: ArrayList<VideoCollectionData>
)