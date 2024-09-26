package com.example.tweetandroidapp.views.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tweetandroidapp.R
import com.example.tweetandroidapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        userViewModel= ViewModelProvider(requireActivity())[UserViewModel::class.java]

        binding.viewModel = userViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

}
