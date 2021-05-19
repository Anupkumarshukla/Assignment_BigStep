package com.anupkumar.assignment_bigstep.adapter

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.anupkumar.assignment_bigstep.R
import com.anupkumar.assignment_bigstep.data.local.VideoCollectionData
import com.anupkumar.assignment_bigstep.databinding.ListItemBinding
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

class RemoteVideoCollectionAdapter(private val mContext: Context, private var mList: ArrayList<VideoCollectionData>?) : RecyclerView.Adapter<RemoteVideoCollectionAdapter.ViewHolder>() {

    private var binding: ListItemBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(mContext)
        binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding!!)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList?.get(position))
        holder.itemBinding.collectionName.text = mList?.get(position)?.collectionName ?: "Collection name not found"
        Glide.with(mContext).load(mList!!.get(position)?.artworkUrl60).into(holder.itemBinding.ImageView)
        holder.itemBinding.price.text = "Price $: "+mList?.get(position)?.collectionPrice.toString()


        var releaseDate = mList!!.get(position)?.releaseDate
        var date: String? = releaseDate?.substring(0, 10)
        var sdf = SimpleDateFormat("yyyy-MM-dd")
        var sdf2 = SimpleDateFormat("dd-MM-yyyy")
        var actualDate = sdf2.format(sdf.parse(date))
        holder.itemBinding.releaseDate.text = "Release Date : "+ actualDate

    }

    override fun getItemCount(): Int {
        return if (mList == null) 0 else mList?.size!!
    }

    inner class ViewHolder(itemBinding: ListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val itemBinding: ListItemBinding = itemBinding
        fun bind(videoCollectionData: VideoCollectionData?) {
            val bundle = Bundle()
            bundle.putParcelable("Collection_details", videoCollectionData)
             binding?.root?.setOnClickListener { view ->
                 Navigation.findNavController(view).navigate(R.id.action_home_to_details, bundle)
             }
        }

    }

    fun updateList(updatedList: ArrayList<VideoCollectionData>?) {
        mList = updatedList
        notifyDataSetChanged()
    }
}
