package com.anupkumar.assignment_bigstep.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.anupkumar.assignment_bigstep.data.local.VideoCollectionData
import com.anupkumar.assignment_bigstep.databinding.FragmentVideoDetailsBinding
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat

class VideoDetailFragment : Fragment() {

    private  var binding: FragmentVideoDetailsBinding? = null
    private var viewModel: RemoteCollectionViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentVideoDetailsBinding.inflate(inflater)
        return binding?.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(RemoteCollectionViewModel::class.java)
        arguments?.getParcelable<VideoCollectionData>("Collection_details")?.let{
              Glide.with(this).load(it.artworkUrl100).into(binding?.imageBackdrop!!)
              binding?.textCollectionName?.text = it.collectionName ?: "Collection name not found"
              binding?.textArtistName?.text = "Artist : " + it.artistName
            var releaseDate = it.releaseDate
            var date: String? = releaseDate?.substring(0, 10)
            var sdf = SimpleDateFormat("yyyy-MM-dd")
            var sdf2 = SimpleDateFormat("dd-MM-yyyy")
            var actualDate = sdf2.format(sdf.parse(date))
            binding?.textDate?.text = "Release Date : "+ actualDate
            binding?.textPrice?.text = "Price $: "+it.collectionPrice.toString()
            if(!(viewModel?.DetailVideo(it.trackId,it.collectionId))!!){
                   viewModel?.insertCollection(it)
               }
        }
    }
}
