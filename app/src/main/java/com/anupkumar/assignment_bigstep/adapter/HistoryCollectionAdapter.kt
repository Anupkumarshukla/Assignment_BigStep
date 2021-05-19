package com.anupkumar.assignment_bigstep.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anupkumar.assignment_bigstep.data.local.VideoCollectionData
import com.anupkumar.assignment_bigstep.databinding.ListItemBinding
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat

class HistoryCollectionAdapter(private val mContext: Context, private var mList: List<VideoCollectionData>?) : RecyclerView.Adapter<HistoryCollectionAdapter.ViewHolder>() {
    private var binding: ListItemBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(mContext)
        binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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
        return if (mList == null) 0 else mList!!.size!!
    }

    inner class ViewHolder(itemBinding: ListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val itemBinding: ListItemBinding = itemBinding
    }

    fun updateList(updatedList: List<VideoCollectionData>?) {
        mList = updatedList
        notifyDataSetChanged()
    }
}
