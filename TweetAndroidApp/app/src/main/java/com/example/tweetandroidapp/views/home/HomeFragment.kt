package com.example.tweetandroidapp.views.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tweetandroidapp.R
import com.example.tweetandroidapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var tweetAdapter: TweetAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Set up DataBinding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        
        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Set up RecyclerView
        tweetAdapter = TweetAdapter()
        binding.tweetRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tweetAdapter
        }

        // Observe LiveData from ViewModel and update the RecyclerView when data changes
        viewModel.tweets.observe(viewLifecycleOwner, Observer { tweets ->
            tweetAdapter.submitList(tweets)
        })

        // Handle the floating action button click to navigate to the write tweet screen
        binding.writeTweetButton.setOnClickListener {
//            findNavController().navigate(R.id.action_homeFragment_to_writeTweetFragment)
        }

        return binding.root
    }
}
