package com.anupkumar.assignment_bigstep.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anupkumar.assignment_bigstep.adapter.HistoryCollectionAdapter
import com.anupkumar.assignment_bigstep.data.local.VideoCollectionData
import com.anupkumar.assignment_bigstep.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class History : Fragment() {

    private var binding: FragmentHistoryBinding? = null
    private var viewModel: HistoryCollectionViewModel? = null
    private var adapter: HistoryCollectionAdapter? = null
    private val videoCollectionList: List<VideoCollectionData>? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HistoryCollectionViewModel::class.java)
        initRecyclerView()
        observeData()
    }

    private fun observeData() {
        viewModel?.getHistoryCollectionList()?.observe(viewLifecycleOwner, {
            adapter?.updateList(it)
        })
    }

    private fun initRecyclerView() {
        binding!!.HistoryRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = HistoryCollectionAdapter(requireContext(), videoCollectionList)
        binding!!.HistoryRecyclerView.adapter = adapter
    }
}