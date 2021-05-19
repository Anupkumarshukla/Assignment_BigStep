package com.anupkumar.assignment_bigstep.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anupkumar.assignment_bigstep.adapter.RemoteVideoCollectionAdapter
import com.anupkumar.assignment_bigstep.data.local.VideoCollectionData
import com.anupkumar.assignment_bigstep.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class Home : Fragment(){

    private var binding: FragmentHomeBinding? = null
    private var viewModel: RemoteCollectionViewModel? = null
    private var adapter: RemoteVideoCollectionAdapter? = null
    private val videoCollectionList: ArrayList<VideoCollectionData>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(RemoteCollectionViewModel::class.java)
        initRecyclerView()
        observeData()
        viewModel!!.getRemoteCollection()
    }



    private fun observeData() {
        viewModel!!.getCollectionList().observe(viewLifecycleOwner, {
            it.data?.getIfNotHandled()?.run {
                adapter!!.updateList(this)
            }
        })
    }

    private fun initRecyclerView() {
        binding!!.HomeRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = RemoteVideoCollectionAdapter(requireContext(), videoCollectionList)
        binding!!.HomeRecyclerView.adapter = adapter
    }

}