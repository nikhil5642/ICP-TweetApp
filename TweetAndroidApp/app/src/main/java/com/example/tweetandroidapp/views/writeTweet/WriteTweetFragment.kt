package com.example.tweetandroidapp.views.writeTweet

import DummyTweetsRepository
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tweetandroidapp.R
import com.example.tweetandroidapp.databinding.FragmentWriteTweetBinding
import com.example.tweetandroidapp.icp.ICClient
import com.example.tweetandroidapp.repositories.TweetsRepository
import com.example.tweetandroidapp.repositories.UserRepository

class WriteTweetFragment : Fragment(), WriteTweetListener {

    private lateinit var binding: FragmentWriteTweetBinding
    private lateinit var viewModel: WriteTweetViewModel
    private lateinit var tweetsRepository: TweetsRepository
    private lateinit var userRepository: UserRepository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Set up DataBinding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_write_tweet, container, false)
        tweetsRepository= DummyTweetsRepository(requireContext())
        userRepository=UserRepository(requireContext())
        viewModel = ViewModelProvider(this,WriteViewModelFactory(userRepository,tweetsRepository,
            this))[WriteTweetViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onTweetPosted() {
        Toast.makeText(requireContext(), "Tweet Posted!", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()
    }

    override fun onTweetPostError() {
        Toast.makeText(requireContext(), "Please write something!", Toast.LENGTH_SHORT).show()
    }
}

