package com.example.tweetandroidapp.views.home

import DummyTweetsRepository
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tweetandroidapp.R
import com.example.tweetandroidapp.databinding.FragmentHomeBinding
import com.example.tweetandroidapp.views.login.UserViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var tweetAdapter: TweetAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Set up DataBinding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        setHasOptionsMenu(true)
        // Initialize ViewModel
        val tweetsRepository= DummyTweetsRepository(requireContext())
        homeViewModel = ViewModelProvider(this,HomeViewModelFactory(tweetsRepository))[HomeViewModel::class.java]
        userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        binding.viewModel = homeViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Set up RecyclerView
        tweetAdapter = TweetAdapter()

        binding.tweetRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tweetAdapter
        }
        val dividerItemDecoration = DividerItemDecoration(
            binding.tweetRecyclerView.context,
            (binding.tweetRecyclerView.layoutManager as LinearLayoutManager).orientation
        )
        binding.tweetRecyclerView.addItemDecoration(dividerItemDecoration)
        homeViewModel.tweets.observe(viewLifecycleOwner, Observer { tweets ->
            tweetAdapter.submitList(tweets)
        })

        binding.writeTweetButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_writeTweetFragment)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                userViewModel.logOut()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.loadTweets()
    }
}
